package main.java.wang.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import main.java.wang.bean.Item;
import main.java.wang.bean.Party;
import main.java.wang.dao.ItemDao;

@Controller
public class PartyController {
	@Autowired
    private ItemDao itemDao;
	// 错误提示信息
	private List<String> messageList;
	@RequestMapping("/InitParty") // 请求url地址映射
	private String initParty(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String partyDate = sdf.format(new Date());
		request.setAttribute("partyDate", partyDate);
		try {
			Item item = new Item();
			item.setItemType("TIME");
			// 时间区间list
			List<Item> timeList = itemDao.findItem(item);
			request.setAttribute("timeList", timeList);
			item.setItemType("PARTY_ROOM");
			// 会议室名称list
			List<Item> partyRoomList = itemDao.findItem(item);
			request.setAttribute("partyRoomList", partyRoomList);
			// 原因list
			item.setItemType("RESION");
			List<Item> resionList = itemDao.findItem(item);
			request.setAttribute("resionList", resionList);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "/createParty";
    }
	
	//Request Response
    @RequestMapping(value="/CreateParty",produces="text/html;charset=UTF-8")
    private String reqAndrep(HttpServletRequest request, HttpServletResponse response){
		try {
			// check入力信息
			checkParty(request);
			if (messageList.isEmpty()) {
				// 会议室对象
				Party party = new Party();
				party.setPartyId(request.getParameter("party_id"));
				party.setPartyDate(request.getParameter("party_date"));
				party.setStartTime(request.getParameter("start_time"));
				party.setEndTime(request.getParameter("end_time"));
				party.setUserName(request.getParameter("user_name"));
				party.setTelNo(request.getParameter("tel_no"));
				party.setEmailNo(request.getParameter("email_no"));
				party.setResionId(request.getParameter("resion_id"));
				party.setComment(request.getParameter("comment"));
				
				itemDao.insertParty(party);
				messageList.add("会议室预约成功！");
			}
			request.setAttribute("infoList", messageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "forward:/InitParty";
    }

    /**
     * 预约会议室的一些项目check校验
     * @param request
     * @return
     */
    private void checkParty(HttpServletRequest request) throws Exception {
    	messageList = new ArrayList<String>();
		// 预约会议室必须入力check
		if (StringUtils.isEmpty(request.getParameter("party_id"))) {
			messageList.add("预约会议室不能为空！");
		}
		if (StringUtils.isEmpty(request.getParameter("party_date"))) {
			messageList.add("预约会日期不能为空！");
		}
		if (StringUtils.isEmpty(request.getParameter("start_time"))) {
			messageList.add("预约会议开始时间不能为空！");
		}
		if (StringUtils.isEmpty(request.getParameter("end_time"))) {
			messageList.add("预约会议结束时间不能为空！");
		}
		if (StringUtils.isEmpty(request.getParameter("user_name"))) {
			messageList.add("预约会议者不能为空！");
		}
		if (StringUtils.isEmpty(request.getParameter("tel_no"))) {
			messageList.add("预约电话不能为空！");
		}
		if (StringUtils.isEmpty(request.getParameter("resion_id"))) {
			messageList.add("预约原因不能为空！");
		}
		
		if (request.getParameter("party_date").length() > 10) {
			messageList.add("预约会日长度不能超过10！");
		}
		if (request.getParameter("start_time").length() > 5) {
			messageList.add("预约会议开始时间长度不能超过5！");
		}
		if (request.getParameter("end_time").length() > 5) {
			messageList.add("预约会议开始时间长度不能超过5！");
		}
		if (request.getParameter("user_name").length() > 15) {
			messageList.add("预约会议者长度不能超过15！");
		}
		if (request.getParameter("email_no").length() > 15) {
			messageList.add("邮箱长度不能超过15！");
		}
		if (request.getParameter("comment").length() > 223) {
			messageList.add("预约会议内容不能超过223！");
		}
		String regex="^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$";
		if (Pattern.matches(regex, request.getParameter("tel_no"))) {
			messageList.add("入力的电话号码格式不正！");
		}
		Item item = new Item();
		item.setItemType("PARTY_ROOM");
		item.setItemCode(request.getParameter("party_id"));
		if (itemDao.findItemCount(item).isEmpty()) {
			messageList.add("预约会议室不存在！");
		}
		item.setItemType("TIME");
		item.setItemCode(request.getParameter("start_time"));
		if (itemDao.findItemCount(item).isEmpty()) {
			messageList.add("预约会议开始时间不存在！");
		}
		item.setItemCode(request.getParameter("end_time"));
		if (itemDao.findItemCount(item).isEmpty()) {
			messageList.add("预约会议结束时间不存在！");
		}
		item.setItemType("RESION");
		item.setItemCode(request.getParameter("resion_id"));
		if (itemDao.findItemCount(item).isEmpty()) {
			messageList.add("预约会议原因不存在！");
		}
		if (request.getParameter("start_time").compareTo(request.getParameter("end_time")) >= 0) {
			messageList.add("预约会议开始时间不能等于结束时间！");
		}
		Party party = new Party();
		party.setPartyId(request.getParameter("party_id"));
		party.setPartyDate(request.getParameter("party_date"));
		List<Party> partyList = itemDao.findParty(party);
		if (!partyList.isEmpty()) {
			for (Party map : partyList) {
				if ((map.getStartTime().compareTo(request.getParameter("start_time")) >= 0 && map.getStartTime().compareTo(request.getParameter("end_time")) < 0) 
						|| (map.getEndTime().compareTo(request.getParameter("start_time")) > 0 && map.getEndTime().compareTo(request.getParameter("end_time")) <= 0)) {
					messageList.add("预约会议室已经存在！");
					break;
				}
			}
		}
    }
    
    /**
     * 计算字符串的字节长度
     * @param s 字符串
     * @return length
     */
    public int getWordCount(String s){
        int length = 0;
        for(int i = 0; i < s.length(); i++){
            int ascii = Character.codePointAt(s, i);
            if(ascii >= 0 && ascii <=255) {
            	length++;
            } else {
            	length += 2;
            }
        }
        return length;
    }
}

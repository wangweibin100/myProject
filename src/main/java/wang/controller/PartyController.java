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
	// ������ʾ��Ϣ
	private List<String> messageList;
	@RequestMapping("/InitParty") // ����url��ַӳ��
	private String initParty(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String partyDate = sdf.format(new Date());
		request.setAttribute("partyDate", partyDate);
		try {
			Item item = new Item();
			item.setItemType("TIME");
			// ʱ������list
			List<Item> timeList = itemDao.findItem(item);
			request.setAttribute("timeList", timeList);
			item.setItemType("PARTY_ROOM");
			// ����������list
			List<Item> partyRoomList = itemDao.findItem(item);
			request.setAttribute("partyRoomList", partyRoomList);
			// ԭ��list
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
			// check������Ϣ
			checkParty(request);
			if (messageList.isEmpty()) {
				// �����Ҷ���
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
				messageList.add("������ԤԼ�ɹ���");
			}
			request.setAttribute("infoList", messageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "forward:/InitParty";
    }

    /**
     * ԤԼ�����ҵ�һЩ��ĿcheckУ��
     * @param request
     * @return
     */
    private void checkParty(HttpServletRequest request) throws Exception {
    	messageList = new ArrayList<String>();
		// ԤԼ�����ұ�������check
		if (StringUtils.isEmpty(request.getParameter("party_id"))) {
			messageList.add("ԤԼ�����Ҳ���Ϊ�գ�");
		}
		if (StringUtils.isEmpty(request.getParameter("party_date"))) {
			messageList.add("ԤԼ�����ڲ���Ϊ�գ�");
		}
		if (StringUtils.isEmpty(request.getParameter("start_time"))) {
			messageList.add("ԤԼ���鿪ʼʱ�䲻��Ϊ�գ�");
		}
		if (StringUtils.isEmpty(request.getParameter("end_time"))) {
			messageList.add("ԤԼ�������ʱ�䲻��Ϊ�գ�");
		}
		if (StringUtils.isEmpty(request.getParameter("user_name"))) {
			messageList.add("ԤԼ�����߲���Ϊ�գ�");
		}
		if (StringUtils.isEmpty(request.getParameter("tel_no"))) {
			messageList.add("ԤԼ�绰����Ϊ�գ�");
		}
		if (StringUtils.isEmpty(request.getParameter("resion_id"))) {
			messageList.add("ԤԼԭ����Ϊ�գ�");
		}
		
		if (request.getParameter("party_date").length() > 10) {
			messageList.add("ԤԼ���ճ��Ȳ��ܳ���10��");
		}
		if (request.getParameter("start_time").length() > 5) {
			messageList.add("ԤԼ���鿪ʼʱ�䳤�Ȳ��ܳ���5��");
		}
		if (request.getParameter("end_time").length() > 5) {
			messageList.add("ԤԼ���鿪ʼʱ�䳤�Ȳ��ܳ���5��");
		}
		if (request.getParameter("user_name").length() > 15) {
			messageList.add("ԤԼ�����߳��Ȳ��ܳ���15��");
		}
		if (request.getParameter("email_no").length() > 15) {
			messageList.add("���䳤�Ȳ��ܳ���15��");
		}
		if (request.getParameter("comment").length() > 223) {
			messageList.add("ԤԼ�������ݲ��ܳ���223��");
		}
		String regex="^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$";
		if (Pattern.matches(regex, request.getParameter("tel_no"))) {
			messageList.add("�����ĵ绰�����ʽ������");
		}
		Item item = new Item();
		item.setItemType("PARTY_ROOM");
		item.setItemCode(request.getParameter("party_id"));
		if (itemDao.findItemCount(item).isEmpty()) {
			messageList.add("ԤԼ�����Ҳ����ڣ�");
		}
		item.setItemType("TIME");
		item.setItemCode(request.getParameter("start_time"));
		if (itemDao.findItemCount(item).isEmpty()) {
			messageList.add("ԤԼ���鿪ʼʱ�䲻���ڣ�");
		}
		item.setItemCode(request.getParameter("end_time"));
		if (itemDao.findItemCount(item).isEmpty()) {
			messageList.add("ԤԼ�������ʱ�䲻���ڣ�");
		}
		item.setItemType("RESION");
		item.setItemCode(request.getParameter("resion_id"));
		if (itemDao.findItemCount(item).isEmpty()) {
			messageList.add("ԤԼ����ԭ�򲻴��ڣ�");
		}
		if (request.getParameter("start_time").compareTo(request.getParameter("end_time")) >= 0) {
			messageList.add("ԤԼ���鿪ʼʱ�䲻�ܵ��ڽ���ʱ�䣡");
		}
		Party party = new Party();
		party.setPartyId(request.getParameter("party_id"));
		party.setPartyDate(request.getParameter("party_date"));
		List<Party> partyList = itemDao.findParty(party);
		if (!partyList.isEmpty()) {
			for (Party map : partyList) {
				if ((map.getStartTime().compareTo(request.getParameter("start_time")) >= 0 && map.getStartTime().compareTo(request.getParameter("end_time")) < 0) 
						|| (map.getEndTime().compareTo(request.getParameter("start_time")) > 0 && map.getEndTime().compareTo(request.getParameter("end_time")) <= 0)) {
					messageList.add("ԤԼ�������Ѿ����ڣ�");
					break;
				}
			}
		}
    }
    
    /**
     * �����ַ������ֽڳ���
     * @param s �ַ���
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

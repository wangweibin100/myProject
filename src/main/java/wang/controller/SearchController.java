package main.java.wang.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.java.wang.bean.Item;
import main.java.wang.bean.Party;
import main.java.wang.bean.PartyInfor;
import main.java.wang.dao.ItemDao;
@Controller
public class SearchController {
	@Autowired
    private ItemDao itemDao;
	@RequestMapping("/InitSearchParty") // 请求url地址映射
	private String initSearchParty(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String partyDate = sdf.format(new Date());
		request.setAttribute("partyDate", partyDate);
		try {
			Item item = new Item();
			item.setItemType("PARTY_ROOM");
			// 会议室名称list
			List<Item> partyRoomList = itemDao.findItem(item);
			request.setAttribute("partyRoomList", partyRoomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "/searchParty";
    }
	@RequestMapping(value="/SearchParty",produces="text/html;charset=UTF-8")
    private @ResponseBody List<PartyInfor> searchParty(String partyId, String partyDate){
		List<PartyInfor> partyList = null;
		try {
			// 会议室对象
			Party party = new Party();
			// 会议室ID
			party.setStartTime(partyId);
		
			List<Party> partyInforList =  itemDao.searchParty(party);
			partyList = forEach(partyInforList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return partyList;
    }

	private List<PartyInfor> forEach(List<Party> partyInforList) {
		List<PartyInfor> partyList = new ArrayList<PartyInfor>();
		// 会议室
		String partyId = "";
		// 预约日期
		String partyDate = "";
		PartyInfor partyInfor = new PartyInfor();
		for (Party item : partyInforList) {			
			if (!partyId.equals(item.getPartyId()) && !partyDate.equals(item.getPartyDate())) {
				partyInfor.setPartyId(item.getPartyId());
				partyInfor.setPartyDate(item.getPartyDate());
				partyList.add(partyInfor);
				partyInfor = new PartyInfor();
			}
			setPartyTimeValue(item, partyInfor);
			partyId = item.getPartyId();
			partyDate = item.getPartyDate();
		}
		return partyList;
	}
	/**
	 * 设置预约会议室相应时间的人和电话
	 * @param item
	 * @param partyInfor
	 */
	private void setPartyTimeValue(Party item, PartyInfor partyInfor) {
		String startTime = item.getStartTime();
		String endTime = item.getEndTime();
		String comment = "预约者：" + item.getUserName() + "，预约电话" + item.getTelNo() + "。";
		for (int i = Integer.parseInt(startTime); i < Integer.parseInt(endTime); i++) {
			switch(i){
			    case 1 :
			    	partyInfor.setTime01(comment);
			    	break;
			    case 2 :
			    	partyInfor.setTime02(comment);
			    	break;
			    case 3 :
			    	partyInfor.setTime03(comment);
			    	break;
			    case 4 :
			    	partyInfor.setTime04(comment);
			    	break;
			    case 5 :
			    	partyInfor.setTime05(comment);
			    	break;
			    case 6 :
			    	partyInfor.setTime06(comment);
			    	break;
			    case 7 :
			    	partyInfor.setTime07(comment);
			    	break;
			    case 8 :
			    	partyInfor.setTime08(comment);
			    	break;
			    case 9 :
			    	partyInfor.setTime09(comment);
			    	break;
			    case 10 :
			    	partyInfor.setTime10(comment);
			    	break;
			    case 11 :
			    	partyInfor.setTime11(comment);
			    	break;
			    case 12 :
			    	partyInfor.setTime12(comment);
			    	break;
			    case 13 :
			    	partyInfor.setTime13(comment);
			    	break;
			    case 14 :
			    	partyInfor.setTime14(comment);
			    	break;
			    case 15 :
			    	partyInfor.setTime15(comment);
			    	break;
			    case 16 :
			    	partyInfor.setTime16(comment);
			    	break;
			    case 17 :
			    	partyInfor.setTime17(comment);
			    	break;
			    default : 
			    	partyInfor.setTime18(comment);
			}
		}
	}
}

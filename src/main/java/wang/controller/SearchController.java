package main.java.wang.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.java.wang.bean.Item;
import main.java.wang.bean.Party;
import main.java.wang.dao.ItemDao;
@Controller
public class SearchController {
	@Autowired
    private ItemDao itemDao;
	@RequestMapping("/InitSearchParty") // ����url��ַӳ��
	private String initSearchParty(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String partyDate = sdf.format(new Date());
		request.setAttribute("partyDate", partyDate);
		try {
			Item item = new Item();
			item.setItemType("PARTY_ROOM");
			// ����������list
			List<Item> partyRoomList = itemDao.findItem(item);
			request.setAttribute("partyRoomList", partyRoomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "/searchParty";
    }
	@RequestMapping(value="/SearchParty",produces="text/html;charset=UTF-8")
    private @ResponseBody List<Party> searchParty(String partyId, String partyDate){
		List<Party> partyList = null;
		try {
			// �����Ҷ���
			Party party = new Party();
			// ������ID
			party.setStartTime(partyId);
			// ����ʱ��
			party.setPartyDate(partyDate);
			
			partyList =  itemDao.searchParty(party);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return partyList;
    }
}

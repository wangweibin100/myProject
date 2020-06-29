package main.java.wang.dao;

import java.util.List;

import main.java.wang.bean.Item;
import main.java.wang.bean.Party;

public interface ItemDao {
	public List<Item> findItem(Item item) throws Exception;
	
	public List<Item> findItemCount(Item item) throws Exception;
	
	public List<Party> findParty(Party party) throws Exception;
	
	public void insertParty(Party party) throws Exception;
	
	public List<Party> searchParty(Party party) throws Exception;
}


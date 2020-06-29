package main.java.wang.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import main.java.wang.bean.Item;
import main.java.wang.bean.Party;

public class ItemDaoImpl extends SqlSessionDaoSupport implements ItemDao {
	@Override
	public List<Item> findItem(Item item) throws Exception {
        SqlSession sqlSession = this.getSqlSession();
        return sqlSession.selectList("selectItem", item);
	}
	@Override
	public List<Item> findItemCount(Item item) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("selectItemCount", item);
	}
	@Override
	public List<Party> findParty(Party party) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("selectParty", party);
	}
	
	public void insertParty(Party party) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.insert("insertParty", party);
	}
	
	@Override
	public List<Party> searchParty(Party party) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		return sqlSession.selectList("searchParty", party);
	}
}

package main.java.wang.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import main.java.wang.bean.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{
    @Override
	public void insertUser(User user) throws Exception {
		System.out.println("userService : ¥Ê¥¢”√ªß..."); 
        SqlSession sqlSession = this.getSqlSession();
        sqlSession.insert("insertUser", user);
	}
}

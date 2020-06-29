package main.java.wang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.wang.bean.User;
import main.java.wang.dao.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;  

	@Override
    public void saveUser(User user) {  
        System.out.println("userService : ¥Ê¥¢”√ªß...");
        try {
			userDao.insertUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}  
    }  
}

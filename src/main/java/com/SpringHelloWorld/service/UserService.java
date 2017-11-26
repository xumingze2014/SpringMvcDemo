package com.SpringHelloWorld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringHelloWorld.Dao.LoginLogDao;
import com.SpringHelloWorld.Dao.UserDao;
import com.SpringHelloWorld.Domain.LoginLog;
import com.SpringHelloWorld.Domain.User;


@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchUser(String name, String password) {
        int matchCount = userDao.getMatchCount(name, password);
        return matchCount > 0;
    }

    public User findUserByUsername(String username) {
        return userDao.findUserByName(username);
    }

    public void loginSuccess(User user) {
    	try {
    		user.setCredits(user.getCredits() + 5);
            LoginLog loginLog = new LoginLog();
            loginLog.setUserId(user.getUserId());
            loginLog.setIp(user.getLastIp());
            loginLog.setLoginDate(user.getLastVisit());
            userDao.updateLoginInfo(user);
            loginLogDao.insertLoginLog(loginLog);
    	}catch(Exception e) {
    		System.out.println(e.toString());
    	}
        
    }
}
package com.SpringHelloWorld.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.SpringHelloWorld.Domain.LoginLog;


@Repository
public class LoginLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertLoginLog(LoginLog loginLog) {
        String sql = "insert into t_login_log(user_id, ip,login_datetime) values(?,?,?)";
        Object[] pra = { loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate() };
        jdbcTemplate.update(sql, pra);
    }
}
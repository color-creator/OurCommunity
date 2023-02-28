package com.yy.yycomunity.service;

import com.yy.yycomunity.dao.UserMapper;
import com.yy.yycomunity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }


}

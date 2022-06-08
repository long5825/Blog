package com.chengdu.longblog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengdu.longblog.mapper.UserMapper;
import com.chengdu.longblog.entity.User;
import com.chengdu.longblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;

   /* public User checkUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User user1 = userMapper.selectOne(new QueryWrapper<>(user));
        return user;
    }*/
}

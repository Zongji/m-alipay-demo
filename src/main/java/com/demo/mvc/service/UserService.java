package com.demo.mvc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mvc.dto.User;
import com.demo.mvc.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    public User login(String userName, String password) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", userName);
        User user = userMapper.selectOne(queryWrapper);
        log.info("user:{}", user);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }else {
            return null;
        }
    }

}

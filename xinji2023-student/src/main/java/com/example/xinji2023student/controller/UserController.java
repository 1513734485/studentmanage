package com.example.xinji2023student.controller;
import com.example.xinji2023student.entity.User;
import com.example.xinji2023student.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/findAll")
    public List<User> findAll(){
        List<User> list=userMapper.selectList(null);
        return list;
    }
}
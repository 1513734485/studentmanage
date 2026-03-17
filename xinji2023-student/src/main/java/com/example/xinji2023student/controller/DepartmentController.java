package com.example.xinji2023student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.xinji2023student.entity.Department;
import com.example.xinji2023student.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;

    // 查询所有部门（MyBatis-Plus方式）
    @GetMapping("/queryAll")
    public List queryAll() {
        List<Department> list =departmentMapper.selectList(null);
        // selectList(null) 表示无查询条件，查询所有数据
        return list;
    }

    @GetMapping("/findById/{departmentId}")
    public Department selectById(@PathVariable("departmentId") Integer departmentId) {
        return departmentMapper.findById(departmentId);
    }
}
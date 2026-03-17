package com.example.xinji2023student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xinji2023student.entity.Student;
import com.example.xinji2023student.mapper.StudentMapper;
import com.example.xinji2023student.mapper.StudentNoMybatisPlusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController{
    @Autowired
    private StudentMapper studentMapper;
    //  @GetMapping("/student1")
    //public String query1(){
    //  List<Student> list = studentMapper.selectList(null);
    //System.out.println(list);
    //return  "查询学生";

    //}
    @PostMapping("/insert")
    public int insert(@RequestBody Student student){
        int result=studentMapper.insert(student);
        return result;
    }
    @DeleteMapping("/deleteById/{studentId}")
    public int delete(@PathVariable String studentId){
        int row=studentMapper.deleteById(studentId);
        return row;
    }
    @PutMapping("/update")
    public int update(@RequestBody Student student) {
        int result = studentMapper.updateById(student);
        return result;
    }

    @PutMapping("/update2/{studentId}")
    public int update2(@PathVariable String studentId, @RequestBody Student student) {
        student.setStudentId(studentId); // 使用路径中的studentId
        return studentMapper.updateById(student);
    }
    @GetMapping("queryAll")//查询所有的学生
    public List<Student>  query(){
        List<Student> list = studentMapper.selectList(null);
        System.out.println(list);
        return list;
    }
    @GetMapping("/selectById/{studentId}")
    public Student selectById(@PathVariable String studentId){
        return studentMapper.selectById(studentId);
    }
    @GetMapping("/findByPage/{page}/{size}")//page是其实页
    public IPage findByPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {

        Page<Student> pages=new Page<>(page,size);
        //设置起始页和每页数
        IPage iPage=studentMapper.selectPage(pages,null);
        System.out.println(iPage);
        return iPage;
    }
    @GetMapping("/findByPageAndQuery/{currentPage}/{pageSize}")
    public IPage<Student> findByPageAndQuery(
            @PathVariable("currentPage") Integer currentPage,
            @PathVariable("pageSize") Integer pageSize,
            // 接收查询参数
            @RequestParam(required = false) String studentName // 姓名模糊查询
    ) {
        // 创建分页对象
        Page<Student> pageInfo = new Page<>(currentPage, pageSize);
        // 创建查询条件
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        // 姓名模糊查询
        if (studentName != null && !studentName.trim().isEmpty()) {
            queryWrapper.like("student_name", studentName.trim());
        }
        // 执行查询
        IPage<Student> iPage = studentMapper.selectPage(pageInfo, queryWrapper);
        return iPage;
    }
    @Autowired
    private StudentNoMybatisPlusMapper studentNoMybatisPlusMapper;}
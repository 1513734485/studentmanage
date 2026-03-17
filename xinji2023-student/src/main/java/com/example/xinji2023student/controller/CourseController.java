package com.example.xinji2023student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xinji2023student.entity.Course;
import com.example.xinji2023student.entity.Student;
import com.example.xinji2023student.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")  // 增加这一行，为整个控制器添加前缀路径
public class CourseController {
    @Autowired
    private CourseMapper courseMapper;
    //--------------------------查询------------------------------------
    //全查询，并返回Json数据
    @GetMapping("/queryAll")
    public List<Course> queryAll(){
        List<Course> list=courseMapper.selectList(null);
        System.out.println(list);
        return list;
    }
    @GetMapping("/selectById/{courseId}")
    public Course selectById(@PathVariable("courseId") String courseId){
        return courseMapper.findById(courseId);
    }
    // 新增课程：接收JSON格式参数，对接前端Vue等框架
    @PostMapping("/insert")
    public int insert(@RequestBody Course course){
        // 插入数据，返回受影响行数（1=成功，0=失败）
        int result = courseMapper.insert(course);
        System.out.println("新增课程受影响行数：" + result);
        return result;
    }

    //-------------------------- 删除（Delete）------------------------------------
    // 主键删除：根据课程编号删除单个课程
    @DeleteMapping("/deleteById/{courseId}")
    public int deleteById(@PathVariable("courseId") String courseId){
        int row = courseMapper.deleteById(courseId);
        System.out.println("删除课程受影响行数：" + row);
        return row;
    }

    // 批量删除（可选，实用功能）：根据课程编号列表批量删除
    @DeleteMapping("/deleteBatch")
    public int deleteBatch(@RequestParam List<String> courseIds){
        int row = courseMapper.deleteBatchIds(courseIds);
        System.out.println("批量删除课程受影响行数：" + row);
        return row;
    }

    //-------------------------- 更新（Update）------------------------------------
    // 全字段更新：根据课程编号（主键）更新所有字段，需携带主键
    @PutMapping("/update")
    public int update(@RequestBody Course course){
        int result = courseMapper.updateById(course);
        System.out.println("更新课程受影响行数：" + result);
        return result;
    }

    // 指定主键更新：路径传递课程编号，请求体传递更新字段（简化前端操作）
    @PutMapping("/updateByCourseId/{courseId}")
    public int updateByCourseId(@PathVariable("courseId") String courseId,
                                @RequestBody Course course){
        // 给课程对象赋值主键，确保MyBatis-Plus能定位到要更新的记录
        course.setCourseId(courseId);
        return courseMapper.updateById(course);
    }
    @GetMapping("/findByPage/{page}/{size}")
    public IPage<Course> findByPage(@PathVariable("page") Integer page,
                                    @PathVariable("size") Integer size) {
        // 设置分页参数：页码、每页数量
        Page<Course> pages = new Page<>(page, size);
        // 执行分页查询，第二个参数为查询条件（null表示查询所有Course数据）
        IPage<Course> iPage = courseMapper.selectPage(pages, null);
        System.out.println("Course分页查询结果：" + iPage);
        return iPage;
    }
    @GetMapping("/findByPageAndQuery/{currentPage}/{pageSize}")
    public IPage<Course> findByPageAndQuery(
            @PathVariable("currentPage") Integer currentPage,
            @PathVariable("pageSize") Integer pageSize,
            // 接收课程名称模糊查询参数，非必填（required = false）
            @RequestParam(required = false) String courseName
    ) {
        // 1. 创建分页对象，传入页码和每页数量
        Page<Course> pageInfo = new Page<>(currentPage, pageSize);

        // 2. 创建查询条件构造器
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

        // 3. 构造课程名称模糊查询条件（非空判断，避免无效查询）
        if (courseName != null && !courseName.trim().isEmpty()) {
            // 注意：第二个参数是数据库表字段名（若开启驼峰命名映射，也可填实体类属性名courseName）
            queryWrapper.like("course_name", courseName.trim());
        }

        // 4. 执行分页+条件查询
        IPage<Course> iPage = courseMapper.selectPage(pageInfo, queryWrapper);

        // 可选：打印查询结果（用于调试）
        System.out.println("Course分页+条件查询结果：" + iPage);

        return iPage;
    }
}

package com.example.xinji2023student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xinji2023student.entity.Student;
import com.example.xinji2023student.entity.StudentCourses;
import com.example.xinji2023student.mapper.StudentCoursesMapper;
import com.example.xinji2023student.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentCourses")  // 与前端路径前缀保持一致
public class StudentCoursesController {
    @Autowired
    private StudentCoursesMapper studentCoursesMapper;
    @Autowired
    private StudentMapper studentMapper;

    //--------------------------查询------------------------------------
    // 全查询：返回所有学生课程记录（正确）
    @GetMapping("/queryAll")
    public List<StudentCourses> queryAll(){
        List<StudentCourses> list=studentCoursesMapper.selectList(null);
        System.out.println("学生课程全量数据：" + list);
        return list;
    }

    // 通过Id查询：查询单个学生课程记录（正确）
    @GetMapping("/selectById/{id}")
    public StudentCourses selectById(@PathVariable("id") String id){
        return studentCoursesMapper.selectById(id);
    }

    //--------------------------插入------------------------------------
    // 新增学生课程记录（正确）
    @PostMapping("/insert")
    public int insert(@RequestBody StudentCourses studentCourses){
        int result=studentCoursesMapper.insert(studentCourses);
        return result;
    }

    //--------------------------删除------------------------------------
    // 修正：删除学生课程记录（原错误操作Student表，现改为StudentCourses表）
    @DeleteMapping("/deleteByid/{courseId}")
    public int delete(@PathVariable("courseId") String courseId){
        int row=studentCoursesMapper.deleteById(courseId);
        System.out.println("删除课程记录条数：" + row);
        return row;
    }

    // 修正：普通删除接口（操作StudentCourses表）
    @GetMapping("delete2")
    public void delete2(String courseId){
        int row = studentCoursesMapper.deleteById(courseId);
        System.out.println("删除课程记录条数：" + row);
    }

    //--------------------------更新------------------------------------
    // 修正：更新学生课程记录（原错误操作Student表，现改为StudentCourses表）
    @PutMapping("/update")
    public int update(@RequestBody StudentCourses studentCourses){
        int result= studentCoursesMapper.updateById(studentCourses);
        return result;
    }

    // 修正：带路径参数更新学生课程记录（操作StudentCourses表）
    @PutMapping("/update2/{courseId}")
    public int update2(@PathVariable String courseId,@RequestBody StudentCourses studentCourses){
        // 核心修正：将字符串转换为Integer（而非Long）
        studentCourses.setId(Integer.parseInt(courseId));
        return studentCoursesMapper.updateById(studentCourses);
    }

    //--------------------------分页查询------------------------------------
    // 修正：普通分页查询（操作StudentCourses表，返回课程数据）
    @GetMapping("/findByPage/{page}/{size}")
    public IPage findByPage(@PathVariable("page") Integer page,
                            @PathVariable("size") Integer size) {
        // 核心修正：泛型改为StudentCourses，查询课程表
        Page<StudentCourses> pages = new Page<>(page, size);
        IPage iPage = studentCoursesMapper.selectPage(pages, null);
        System.out.println("学生课程普通分页数据：" + iPage.getRecords());
        return iPage;
    }

    // 带查询条件的分页查询（已正确，无需修改）
    @GetMapping("/findByPageAndQuery/{page}/{size}")
    public IPage findByPageAndQuery(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size,
            @RequestParam(required = false) String studentId,
            @RequestParam(required = false) String courseId
    ) {
        Page<StudentCourses> pages = new Page<>(page, size);
        QueryWrapper<StudentCourses> queryWrapper = new QueryWrapper<>();
        if (studentId != null && !studentId.trim().isEmpty()) {
            queryWrapper.like("student_id", studentId.trim());
        }
        if (courseId != null && !courseId.trim().isEmpty()) {
            queryWrapper.like("course_id", courseId.trim());
        }
        IPage iPage = studentCoursesMapper.selectPage(pages, queryWrapper);
        System.out.println("学生课程条件分页数据：" + iPage.getRecords());
        return iPage;
    }
}
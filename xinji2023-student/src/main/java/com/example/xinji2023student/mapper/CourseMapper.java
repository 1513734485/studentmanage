package com.example.xinji2023student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xinji2023student.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    @Select("select * from courses where course_id=#{courseId}")
    Course findById(String courseId);
}

package com.example.xinji2023student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xinji2023student.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}

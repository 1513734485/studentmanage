package com.example.xinji2023student.mapper;

import com.example.xinji2023student.entity.Department;
import com.example.xinji2023student.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentJoinMapper {
    //一对一的映射 一个学生表里面的department_id，对应了一个部门的id，一个数据
    // 一对多的映射 在一个部门表里的department_id，对应学生表里面的id，多个数据
    @Select("select * from students")
    @Results({
            //column是表里面的字段，property是实体类里面的属性
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "student_name", property = "studentName"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "birth_date", property = "birthDate"),
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "enrollment_date", property = "enrollmentDate"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "email", property = "email"),
            @Result(column = "address", property = "address"),
            @Result(column = "status", property = "status"),
            @Result(column = "department_id", property = "department",javaType = Department.class,
            one=@One(select = "com.example.xinji2023student.mapper.DepartmentMapper.findById"))
//one=@One这里是通过学生表里面的department_id去查找departments表里面对应的数据
    })
    public List<Student> getStudentAndDepartment();
}

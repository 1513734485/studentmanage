package com.example.xinji2023student.mapper;

import com.example.xinji2023student.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Mapper
//虽然没有用MybastisPlus，但是我们用了mybatis
public interface StudentNoMybatisPlusMapper {
    @Select("select * from students")
    List<Student> getAll();

    @Insert("insert into students values(#{studentId},#{studentName},#{gender},#{birthDate}," +
            "#{departmentId},#{enrollmentDate},#{phone},#{email},#{address},#{status})")
    int add(@RequestBody Student student);
    @Select("select * from students where student_id=#{studentId}")
    Student findById(String studentId);
    @Update("update students set " +
            "student_name=#{studentName},gender=#{gender},birth_date=#{birthDate},department_id=#{departmentId}," +
            "enrollment_date=#{enrollmentDate},phone=#{phone},email=#{email},address=#{address},status=#{status} " +
            "where student_id=#{studentId}")
    int update(Student student);

    // 根据学号删除学生
    @Delete("delete from students where student_id=#{studentId}")
    int delete(String studentId);
}
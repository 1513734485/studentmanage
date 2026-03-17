package com.example.xinji2023student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
@Data
@TableName("students")
public class Student {
    @TableId(type = IdType.INPUT)
    private String studentId; //学号
    private String studentName; //学生姓名
    private Gender gender; //性别
    private LocalDate birthDate; //出生日期
    private Integer departmentId; //所属系id
    private LocalDate enrollmentDate; //入学日期
    private String phone; //联系电话
    private String email; //电子邮箱
    private String address; //家庭住址
    private StudentStatus status;//学籍状态
    @TableField(exist = false)
    private Department department;
    public enum Gender{男, 女}
    public enum StudentStatus{在读, 休学, 退学, 毕业}

}

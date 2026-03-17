package com.example.xinji2023student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * 学生选课表实体类
 */
@Data
@TableName("student_courses")
public class StudentCourses {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("student_id")
    private String studentId;

    @TableField("course_id")
    private String courseId;

    @TableField("enrollment_date")
    private LocalDate enrollmentDate;

    @TableField("score")
    private BigDecimal score;

    @TableField("status")
    private String status;

    public enum Status {
        IN_PROGRESS("选修中"),
        PASSED("已通过"),
        FAILED("未通过"),
        WITHDRAWN("退课");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Status fromValue(String value) {
            for (Status status : Status.values()) {
                if (status.value.equals(value)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("无效的选课状态: " + value);
        }
    }
    @TableField(exist = false)
    private Student student; //学生信息
    @TableField(exist = false)
    private Course course; //学生信息
}
package com.example.xinji2023student.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("courses") // 指定表名（如果表名与类名不一致）
public class Course {
    @TableId(value = "course_id") // 主键字段
    private String courseId;      // 课程编号（char(8)）

    @TableField("course_name")    // 字段名映射（可选，若字段名与数据库一致可省略）
    private String courseName;    // 课程名称（varchar(100)）

    private Double credit;        // 学分（decimal(3,1) -> Java用Double或BigDecimal）
    private Integer hours;        // 课时（int）

    @TableField("department_id")  // 外键字段（关联departments表）
    private Integer departmentId; // 开课系（int）

    @TableField("teacher_id")     // 外键字段（关联teachers表）
    private Integer teacherId;    // 授课教师（int）

    private String classroom;     // 教室（varchar(50), 允许NULL）
    private String schedule;      // 上课时间（varchar(100), 允许NULL）
    private String academicYear;  // 学年（varchar(20), 允许NULL）

    private String semester;      // 学期（enum('春季','秋季') -> Java用String或自定义枚举）
}
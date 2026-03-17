package com.example.xinji2023student.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("departments") // 映射数据库departments表
public class Department {
    @TableId // 标注主键字段
    private Integer departmentId; // 部门ID（主键）
    private String departmentName; // 部门名称
    private String officeLocation; // 办公地点（图片中为officelocation，修正为驼峰命名）
    private String phone; // 部门电话
    private String headName; // 部门负责人
}
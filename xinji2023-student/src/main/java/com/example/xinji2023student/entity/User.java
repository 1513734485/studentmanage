package com.example.xinji2023student.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("user")
public class User {
    @TableId
    private Integer id; // 关键修正：改为Integer类型，适配数据库INT类型id
    // 若习惯用基本类型，也可改为 private int id;（推荐Integer，支持null值）
    private String username;
    private String password;
    private Date createdAt;
}

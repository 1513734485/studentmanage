package com.example.xinji2023student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper; // 导入MP的BaseMapper
import com.example.xinji2023student.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
// 关键：继承 BaseMapper<User>，获得MP通用CRUD方法（包含selectList）
public interface UserMapper extends BaseMapper<User> {
    // 你的自定义方法（登录、注册相关）保持不变
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectUserByUsername(@Param("username") String username);

    @Insert("INSERT INTO user (username, password) VALUES (#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);
}
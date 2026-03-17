package com.example.xinji2023student.controller;
import com.example.xinji2023student.entity.User;
import com.example.xinji2023student.mapper.UserMapper;
import com.example.xinji2023student.utils.JwtUtils;
import com.example.xinji2023student.utils.Result; // 修正这里，导入您自定义的 Result 类
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
@RestController // 添加这个注解
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> loginForm) {
        String username = loginForm.get("username");
        String password = loginForm.get("password");
// 1. 验证用户名密码
        User user = userMapper.selectByUsernameAndPassword(username, password);
        if (user == null) {
            return Result.error().message("用户名或密码错误");
        }
// 2. 生成 JWT 令牌
        String token = JwtUtils.generateToken(username);
// 3. 返回结果
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("userInfo", user);
        return Result.ok().data(data);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> registerForm) {
        // 1. 从请求参数中获取用户名和密码（与登录接口接收参数风格一致）
        String username = registerForm.get("username");
        String password = registerForm.get("password");

        // 2. 非空校验：避免传入null或空字符串（解决你之前遇到的参数为空问题）
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return Result.error().message("用户名和密码不能为空");
        }

        // 3. 唯一性校验：查询用户名是否已被注册（需确保UserMapper中有该方法）
        User existUser = userMapper.selectUserByUsername(username.trim());
        if (existUser != null) {
            return Result.error().message("用户名已存在，请更换用户名");
        }

        // 4. 封装用户对象，准备插入数据库
        User newUser = new User();
        newUser.setUsername(username.trim()); // 去除前后空格，优化数据质量
        newUser.setPassword(password.trim()); // 注意：实际项目中请加密存储（后续优化）

        // 5. 执行插入操作（需确保UserMapper中有该方法）
        int insertCount = userMapper.insertUser(newUser);
        if (insertCount != 1) {
            return Result.error().message("系统异常，注册失败");
        }

        // 6. 注册成功，返回提示信息（可按需返回用户信息或token）
        return Result.ok().message("注册成功，请登录");
    }
}

// 6. 注册成功，返回提示信息（可按需返回用户信息或token）
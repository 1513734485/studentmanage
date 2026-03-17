package com.example.xinji2023student.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@MapperScan("com.example.xinji2023student.mapper")
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor paginationInterceptor(){
        //创建mybatisPlus的拦截器，当做MybatisPlus的操作时，会被拦截器处理
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //定义了一个分页的拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}

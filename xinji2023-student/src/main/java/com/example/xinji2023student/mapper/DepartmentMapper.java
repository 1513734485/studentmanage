package com.example.xinji2023student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xinji2023student.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper // 交给Spring管理，或在启动类加@MapperScan
public interface DepartmentMapper extends BaseMapper<Department> {
    // 继承BaseMapper后，无需手动编写CRUD方法，MyBatis-Plus自动提供
    @Select("select * from departments where department_id=#{departmentId}")
    Department findById(Integer departmentId);
}
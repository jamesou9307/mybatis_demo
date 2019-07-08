package com.james.mybatis.mapper;

import com.james.mybatis.entity.SysRole;

import java.util.List;

public interface RoleMapper {

    List<SysRole> selectAll();

    SysRole selectById(Long id);
}

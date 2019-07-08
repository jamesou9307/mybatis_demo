package com.james.mybatis.mapper;

import com.james.mybatis.entity.SysRole;
import com.james.mybatis.entity.SysUser;

import java.util.List;

public interface UserMapper {


    SysUser selectById(Long id);

    List<SysUser> selectAll();

    List<SysRole> selectRoleByUserId(Long id);
}

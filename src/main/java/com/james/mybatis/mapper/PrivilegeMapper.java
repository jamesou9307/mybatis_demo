package com.james.mybatis.mapper;

import com.james.mybatis.entity.SysPrivilege;

import java.util.List;

public interface PrivilegeMapper {

    List<SysPrivilege> selectAll();

}

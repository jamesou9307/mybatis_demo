package com.james.mybatis.mapper;

import com.james.mybatis.entity.SysRole;
import com.james.mybatis.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    SysUser selectById(Long id);

    List<SysUser> selectAll();

    List<SysRole> selectRoleByUserId(Long id);

    int insert(SysUser sysUser);

    int insert1(SysUser sysUser);

    int updateById(SysUser sysUser);

    /*多个接口参数查询时，可以使用param注解*/
    List<SysRole> selectRoleByUserIdAndEnabled(@Param("userId") long userId,@Param("enabled")Integer enabled);

    /*动态拼接select语句*/
    List<SysUser> selectByUser(SysUser sysUser);

    /*动态拼接update语句测试*/
    int updateByIdSelective(SysUser sysUser);

    /*动态拼接insert语句*/
    int insertSelective(SysUser sysUser);

    /*根据用户id或者用户名查询，假设用户名是唯一的*/
    SysUser selectByIdOrUserName(SysUser sysUser);
}

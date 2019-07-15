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

    /*动态拼接sql语句测试*/
    List<SysUser> selectByUser(SysUser sysUser);

    /*动态sql,foreach标签的实现使用*/
    List<SysUser> selectByList(List<Long> idList);
}

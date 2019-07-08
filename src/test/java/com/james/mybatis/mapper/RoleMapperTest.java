package com.james.mybatis.mapper;

import com.james.mybatis.entity.SysRole;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;



import java.util.List;

public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void testSelectAll(){

        SqlSession sqlSession=getSqlSession();

        try{
            List<SysRole> sysRoleList=sqlSession.selectList("com.james.mybatis.mapper.RoleMapper.selectAll");
            printRoleList(sysRoleList);
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void testSelectById(){
        SqlSession sqlSession=getSqlSession();
        try{
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
        SysRole sysRole=roleMapper.selectById(1l);

        Assert.assertNotNull(sysRole);

        Assert.assertEquals("管理员",sysRole.getRoleName());
        }finally {
            sqlSession.close();
        }

    }

    private void printRoleList(List<SysRole> sysRoleList){

        for (SysRole sysRole:sysRoleList){

            System.out.println(                    String.format("{id=%d  role_name=%s  enabled=%s  create_by=%s  }",sysRole.getId(), sysRole.getRoleName(),sysRole.getEnabled(),sysRole.getCreateBy()));
        }

    }
}

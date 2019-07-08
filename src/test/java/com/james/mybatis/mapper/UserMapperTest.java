package com.james.mybatis.mapper;

import com.james.mybatis.entity.City;
import com.james.mybatis.entity.SysRole;
import com.james.mybatis.entity.SysUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.lang.management.OperatingSystemMXBean;
import java.util.List;

public class UserMapperTest extends BaseMapperTest{
    private static SqlSessionFactory sqlSessionFactory;



    @Test
    public void testSelectAll(){

        SqlSession sqlSession=getSqlSession();

        try {
            List<SysUser> sysUserList=sqlSession.selectList("com.james.mybatis.mapper.UserMapper.selectAll");
            printUserList(sysUserList);

        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById(){

        SqlSession sqlSession= getSqlSession();
        try{
            //获取UserMapper接口
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser sysUser=userMapper.selectById(1001l);
            //user不为空
            Assert.assertNotNull(sysUser);
            //userName=test
            Assert.assertEquals("test",sysUser.getUserName());

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserId(){

        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole> sysRoleList=userMapper.selectRoleByUserId(0l);

            Assert.assertNotNull(sysRoleList);




        }finally {
            sqlSession.close();
        }
    }

    private void printUserList(List<SysUser> sysUserList){
        for(SysUser sysUser:sysUserList){
            System.out.println(

                    String.format("{id=%d  user_name=%s  user_password=%s  user_email=%s  user_info=%s }",sysUser.getId(),sysUser.getUserName(),sysUser.getUserPassword(),sysUser.getUserEmail(),sysUser.getUserInfo())

            );

        }
    }
}

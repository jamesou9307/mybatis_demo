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

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.Reader;
import java.lang.management.OperatingSystemMXBean;
import java.util.Date;
import java.util.List;


public class UserMapperTest extends BaseMapperTest {




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

    @Test
    public void testInsert(){

        SqlSession sqlSession=getSqlSession();

        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

            SysUser sysUser=new SysUser();
            sysUser.setUserName("james");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("291387044@qq.com");
            sysUser.setUserInfo("good man");
            sysUser.setHeadImg(new byte[]{1,2,3,4});
            sysUser.setCreateTime(new Date());

            //result为执行sql影响的行数
            int result=userMapper.insert(sysUser);


            //只插入了1条数据
            Assert.assertEquals(1,result);
            //id为空，没有给id赋值，并且没有配置给id回写
            Assert.assertNull(sysUser.getId());

        }finally {
            //这里选择不提交
            //sqlSessionFactory.openSession默认不自动提交
            sqlSession.rollback();
            sqlSession.close();
        }


    }

    @Test
    public void testSelectRoleByUserIdAndEnabled(){
        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole> sysRoles=userMapper.selectRoleByUserIdAndEnabled(1001l,1);

            //会找到不止一个可用角色
            Assert.assertTrue(sysRoles.size()>0);


        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUser(){
        SqlSession sqlSession=getSqlSession();

        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

            SysUser queryUser=new SysUser();

            queryUser.setUserName("ad");


            List<SysUser> sysUserList1=userMapper.selectByUser(queryUser);

            //通过用户名模糊查询
            Assert.assertTrue(sysUserList1.size()>0);

            queryUser.setUserName(null);
            queryUser.setUserEmail("admin@mybatis.tk");

            sysUserList1=userMapper.selectByUser(queryUser);

            //通过邮箱精确查询
            Assert.assertTrue(sysUserList1.size()>0);

            queryUser.setUserName("ad");
            queryUser.setUserEmail("test@mybatis.tk");

            sysUserList1=userMapper.selectByUser(queryUser);

            //通过用户名模糊查询,邮箱名精确查询,
            Assert.assertTrue(sysUserList1.size()==0);


        }finally {
            sqlSession.close();
        }

    }

    @Test
    /*
    *测试使用JDBC返回自增式主键，适用于mysql,sql server
    * */
    public void testInsert1(){
        SqlSession sqlSession=getSqlSession();
        try{

            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

            SysUser sysUser=new SysUser();
            sysUser.setUserName("lbj");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("291387044@tk");
            sysUser.setUserInfo("bad boy");
            sysUser.setHeadImg(new byte[]{1,2,3,4});
            sysUser.setCreateTime(new Date());

            int result=userMapper.insert1(sysUser);

            Assert.assertEquals(1,result);

            Assert.assertNotNull(sysUser.getId());

        }finally {

            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById(){

        SqlSession sqlSession=getSqlSession();
       try{

           UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
           SysUser sysUser=userMapper.selectById(1l);

           Assert.assertEquals("admin",sysUser.getUserName());

           Assert.assertEquals("admin@mybatis.tk",sysUser.getUserEmail());

           sysUser.setUserEmail("admin_test@mybatis.tk");

           int result=userMapper.updateById(sysUser);

           Assert.assertEquals(1,result);

           Assert.assertEquals("admin_test@mybatis.tk",sysUser.getUserEmail());


       }finally {

            sqlSession.rollback();
            sqlSession.close();
       }

    }

    @Test
    public void testUpdateByIdSelective(){
        SqlSession sqlSession=getSqlSession();

        try{
            UserMapper userMapper= sqlSession.getMapper(UserMapper.class);

            SysUser sysUser=new SysUser();
            sysUser.setId(1l);
            sysUser.setUserEmail("mybatis_01@tk");
            sysUser.setUserName("admin_01");

            int result=userMapper.updateByIdSelective(sysUser);

            //只更新了一条数据
            Assert.assertTrue(result>=1);

            //根据id查找记录
            SysUser target=userMapper.selectById(1l);

            Assert.assertEquals("mybatis_01@tk",target.getUserEmail());
            Assert.assertEquals("admin_01",target.getUserName());

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }

    }

    @Test
    public void testInsertSelective(){

        SqlSession sqlSession=getSqlSession();

        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser sysUser=new SysUser();
            int result=userMapper.insertSelective(sysUser);
            //测试插入结果
            Assert.assertEquals(1,result);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }

    }

    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser sysUser=new SysUser();
            sysUser.setId(1l);
            SysUser target=userMapper.selectByIdOrUserName(sysUser);

            Assert.assertNotNull(target);

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

package com.james.mybatis.mapper;

import com.james.mybatis.entity.SysPrivilege;
import com.james.mybatis.mapper.BaseMapperTest;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class PrivilegeMapperTest extends BaseMapperTest {

    @Test
    public void testSelectAll(){

        SqlSession sqlSession=getSqlSession();
        try {

            List<SysPrivilege> sysPrivilegeList=sqlSession.selectList("com.james.mybatis.mapper.PrivilegeMapper.selectAll");
            printPrivilegeList(sysPrivilegeList);
        }finally {
            sqlSession.close();
        }


    }

    private void printPrivilegeList(List<SysPrivilege> sysPrivilegeList) {

        for (SysPrivilege sysPrivilege:sysPrivilegeList){

            System.out.println(String.format("{  id=%d,  name=%s  url=%s  }",sysPrivilege.getId(),sysPrivilege.getPrivilegeName(),sysPrivilege.getPrivilegeUrl()));

        }

    }

}

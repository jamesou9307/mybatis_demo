package com.james.mybatis.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

public class BaseMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init(){
        try{
            Reader reader= Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        }catch (IOException ie){

            ie.printStackTrace();

        }
    }

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}

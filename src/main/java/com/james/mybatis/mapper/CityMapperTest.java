package com.james.mybatis.mapper;

import com.james.mybatis.entity.City;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.apache.ibatis.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CityMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init(){
        try{

            Reader reader=Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            reader.close();

        }catch (IOException ie){
            ie.printStackTrace();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            List<City> cityList=sqlSession.selectList("selectAll");
            printCityList(cityList);

        }finally {
            sqlSession.close();
        }
    }

    private void printCityList(List<City> cityList){
        for(City city:cityList){
            System.out.println(

                    String.format("{id=%s  name=%s  countryCode=%s  distrint=%s  population=%d  }",city.getId(),city.getName(),city.getCountryCode(),city.getDistrict(),city.getPopulation())

            );

        }
    }

}

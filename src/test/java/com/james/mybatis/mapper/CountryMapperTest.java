package com.james.mybatis.mapper;

import com.james.mybatis.entity.Country;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

public class CountryMapperTest extends BaseMapperTest{

    @Test
    public void testSelectByPrimaryKey(){

        SqlSession sqlSession=getSqlSession();

        try{

            CountryMapper countryMapper=sqlSession.getMapper(CountryMapper.class);

            Country country=countryMapper.selectByPrimaryKey("ARG");

            Assert.assertNotNull(country);

        }finally {
            sqlSession.close();
        }
    }

}

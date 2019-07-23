package com.james.mybatis.mapper;

import com.james.mybatis.entity.Country;
import com.james.mybatis.entity.CountryExample;

public interface CountryMapper {
    int deleteByExample(CountryExample example);

    int deleteByPrimaryKey(String code);

    int insert(Country record);

    int insertSelective(Country record);

    Country selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);
}
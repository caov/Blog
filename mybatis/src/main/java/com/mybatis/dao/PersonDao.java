package com.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.mybatis.domain.Person;


@Mapper
public interface PersonDao{
    
    @Select("select * from person where address = #{address}")
    List<Person> findByAddress(String address);
    
    @SelectProvider(type = PersonSqlProvider.class, method = "findByAddress2")
    List<Person> findByAddress2(String address);
    
    Person findByNameAndAddress(@Param("name")String name,@Param("address")String address);

    void save(Person person);
    
}

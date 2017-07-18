package com.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity  //@Entity注解指明这是一个和数据库表映射的实体类
public class Person {
    
    @Id   //@Id注解指明这是一个和数据库表映射的实体类
    @GeneratedValue //@GeneratedValue注解默认使用主键生成方式为自增
    private Long id;
    
    private String name;
    
    private Integer age;
    
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}

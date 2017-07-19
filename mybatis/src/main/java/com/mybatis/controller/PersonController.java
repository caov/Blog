package com.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybatis.dao.PersonDao;
import com.mybatis.domain.Person;

@RestController
public class PersonController {
    
    @Autowired
    PersonDao personDao;
    
    @RequestMapping("/save")
    public Person save(@RequestParam("name") String name,
                    @RequestParam("address") String address,
                    @RequestParam("age") Integer age) {
        Person person = new Person();
        person.setAddress(address);
        person.setName(name);
        person.setAge(age);
        personDao.save(person);
        return person;
    }
    
    @RequestMapping("/findByAddress")
    public List<Person> findByAddress(@RequestParam("address") String address){
        System.out.println(address);
        List<Person> list = personDao.findByAddress(address);
        return list;
    }
    
    /**
     * @MethodName:  findByAddress2
     * @Description: 测试findByAddress2()方法
     * @author Van
      */
    @RequestMapping("/findByAddress2")
    public List<Person> query1( @RequestParam("address") String address){
        List<Person> list = personDao.findByAddress2(address);
        return list;
    }
    
    /**
    * @MethodName:  findByNameAndAddress
    * @Description: 测试findByNameAndAddress()方法
    * @author Van
     */
    @RequestMapping("/findByNameAndAddress")
    public Person findByNameAndAddress(@RequestParam("name") String name, @RequestParam("address")String address){
        Person person = personDao.findByNameAndAddress(name, address);
        return person;
    }
    
}

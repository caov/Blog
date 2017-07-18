package com.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.PersonRepository;
import com.jpa.domain.Person;

@RestController
public class PersonController {
    
    //spring Data JPA已自动注册了Bean，所以可以自动导入
    @Autowired
    PersonRepository personRepository;
    
    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        Person person = new Person();
        person.setAddress(address);
        person.setName(name);
        person.setAge(age);
        personRepository.save(person);
        return person;
    }
    
    @RequestMapping("/queryByAddress")
    public List<Person> queryByAddress(String address){
        System.out.println(address);
        List<Person> list = personRepository.findByAddress(address);
        return list;
    }
    
    /**
     * @MethodName:  query1
     * @Description: 测试findByNameAndAddress()方法
     * @author Van
      */
    @RequestMapping("/query1")
    public Person query1(String name, String address){
        Person person = personRepository.findByNameAndAddress(name, address);
        return person;
    }
    
    /**
    * @MethodName:  query2
    * @Description: 测试withNameAndAddressQuery()方法
    * @author Van
     */
    @RequestMapping("/query2")
    public Person query2(String name, String address){
        Person person = personRepository.withNameAndAddressQuery(name, address);
        return person;
    }
    
    /**
     * @MethodName:  sort
     * @Description: 测试排序
     * @author Van
      */
    @RequestMapping("/sort")
    public List<Person> sort(){
        List<Person> list= personRepository.findAll(new Sort(Direction.ASC, "age"));
        return list;
    }
    
    /**
    * @MethodName:  page
    * @Description: 测试分页
    * @author Van
     */
    @RequestMapping("/page")
    public Page<Person> page(){
        Page<Person> pagePerson = personRepository.findAll(new PageRequest(1, 2));
        return pagePerson;
    }
}

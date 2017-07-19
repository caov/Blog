package com.mybatis.dao;

public class PersonSqlProvider {
    public String findByAddress2(String address) {
        String sql = "select * from person ";
        if(null != address) {
            sql += " where address=" + "'"+address+"'"; //字符串要加单引号
        }
        return sql;
    }
}

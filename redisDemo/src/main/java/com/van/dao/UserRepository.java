package com.van.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.van.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}

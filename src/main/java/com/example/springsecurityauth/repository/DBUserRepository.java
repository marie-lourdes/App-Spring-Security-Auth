package com.example.springsecurityauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurityauth.model.DBUser;

public interface DBUserRepository extends JpaRepository<DBUser,Integer> {
	public DBUser findByUsername(String username);
}

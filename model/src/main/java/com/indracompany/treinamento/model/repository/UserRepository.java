package com.indracompany.treinamento.model.repository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.indracompany.treinamento.model.entity.User;

 	
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	 @Query("select u from User u where u.email = ?1")
	 public User findByEmail(String email);
	}
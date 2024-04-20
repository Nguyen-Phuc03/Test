package com.Springboot.Test.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Springboot.Test.model.student;

public interface StudentRepository extends JpaRepository<student, Integer>{
	 boolean existsByName(String name);

}

package com.Springboot.Test.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class StudentCreateModel {
	@NotEmpty(message = "Tên không được trống")
	private String name;
	@NotNull(message = "Tuổi không được trống")
    @Min(value = 1, message = "Tuổi phải lớn hơn hoặc bằng 1")
	private int age;
	public StudentCreateModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}

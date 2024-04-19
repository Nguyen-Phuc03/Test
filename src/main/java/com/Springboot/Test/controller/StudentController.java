package com.Springboot.Test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Springboot.Test.model.StudentCreateModel;
import com.Springboot.Test.model.student;
import com.Springboot.Test.service.StudentRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentRepository repo;

	@GetMapping("/studentlist")
	public String ShowStudentList(Model model) {
		List<student> students = repo.findAll();
		model.addAttribute("students", students);
		return "student/StudentList";
	}

	@GetMapping("/create")
	public String Showcreate(Model model) {
		StudentCreateModel studentmodel = new StudentCreateModel();
		model.addAttribute("studentmodel", studentmodel);
		return "student/createstudent";
	}

	@PostMapping("/create")
	public String createStudent(@Valid @ModelAttribute StudentCreateModel studentmodel, BindingResult result,Model model) {
		if (studentmodel.getName().isEmpty()) {
			result.addError(new FieldError("studentmodel", "name", "Tên không được bỏ trống"));
		}
		if (result.hasErrors()) {
			model.addAttribute("studentmodel", studentmodel);
			return "student/createstudent";
		}

		student student = new student();
		student.setName(studentmodel.getName());
		student.setAge(studentmodel.getAge());

		repo.save(student);
		return "redirect:/student/studentlist";
	}

	@GetMapping("/edit")
	public String ShowEdit(Model model, @RequestParam int id) {
		try {
			student student = repo.findById(id).get();
			model.addAttribute("student", student);
			StudentCreateModel studentmodel = new StudentCreateModel();
			studentmodel.setName(student.getName());
			studentmodel.setAge(student.getAge());

			model.addAttribute("studentmodel", studentmodel);
		} catch (Exception ex) {
			System.out.println("Exception:" + ex.getMessage());
			return "redirect:/student/studentlist";
		}

		return "student/editstudent";
	}
	@PostMapping("/edit")
	public String EditStudent(Model model, @RequestParam int id,@Valid @ModelAttribute StudentCreateModel studentmodel,BindingResult result) {
	try {
		student student=repo.findById(id).get();
		model.addAttribute("student", student);
		if(result.hasErrors()) {
			model.addAttribute("studentmodel", studentmodel);
			return"student/editstudent";
		}
		student.setName(studentmodel.getName());
		student.setAge(studentmodel.getAge());

		repo.save(student);
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
	return "redirect:/student/studentlist";
	}
	@GetMapping("/delete")
	public String DeleteStudent(@RequestParam int  id) {
		try {
			student student=repo.findById(id).get();
			repo.delete(student);
		}
		catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}
		
		return "redirect:/student/studentlist";
	}
	
}

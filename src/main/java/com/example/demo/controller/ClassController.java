package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.GymClass;
import com.example.demo.request.ClassBookingRequest;
import com.example.demo.request.GymClassRequest;
import com.example.demo.service.ClassService;

@RestController
public class ClassController {
	
	@Autowired
	private ClassService classService;
	
	@GetMapping("/classes")
	public List<GymClass> getClasses(){
		return classService.getAll();
	}
	
	@GetMapping("/classes/{id}")
	public GymClass getClass(@PathVariable int id){
		//return classService.getClassById(id);
		return null;
	}
	
	@PostMapping("/classes")
	public String addClass(@RequestBody GymClassRequest gymClassRequest){
		return classService.addGymCLass(gymClassRequest);
	}
	
	@PostMapping("/bookings")
	public String bookClass(@RequestBody ClassBookingRequest request){
		return classService.bookClass(request);
	}
}

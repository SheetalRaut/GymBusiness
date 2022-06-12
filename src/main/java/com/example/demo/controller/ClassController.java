package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping(value = "/classes")
	public ResponseEntity<List<GymClass>> getClasses(){
		return new ResponseEntity<List<GymClass>>(classService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/classes/{id}")
	public ResponseEntity<GymClass> getClass(@PathVariable int id){
		return new ResponseEntity<GymClass>(classService.getClassById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/classes")
	public ResponseEntity<String> addClass(@RequestBody GymClassRequest gymClassRequest){
		return new ResponseEntity<String>(classService.addGymCLass(gymClassRequest), HttpStatus.OK);
	}
	
	@PostMapping(value = "/bookings")
	public ResponseEntity<String> bookClass(@RequestBody ClassBookingRequest request){
		return new ResponseEntity<String>(classService.bookClass(request), HttpStatus.OK);
	}
}

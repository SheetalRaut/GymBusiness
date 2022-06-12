package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.GymClass;
import com.example.demo.request.ClassBookingRequest;
import com.example.demo.request.GymClassRequest;

public interface ClassService {
	
	List<GymClass> getAll();
	
	GymClass getClassById(int id);
	
	String addGymCLass(GymClassRequest gymClass);
	
	String bookClass(ClassBookingRequest request);

}

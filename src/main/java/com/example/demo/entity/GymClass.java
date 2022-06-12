package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class GymClass {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int capacity;
	
	private Date startDate;
	
	private Date endDate;
	
	private String category;
	
	private String healthLevelRequired;
	
	public GymClass(){
		
	}
	
	public GymClass(GymClassBuilder builder){
		this.name = builder.name;
		this.capacity = builder.capacity;
		this.startDate = builder.startDate;
		this.endDate = builder.endDate;
		this.category = builder.category;
		this.healthLevelRequired = builder.healthLevelRequired;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getHealthLevelRequired() {
		return healthLevelRequired;
	}

	public void setHealthLevelRequired(String healthLevelRequired) {
		this.healthLevelRequired = healthLevelRequired;
	}

	public static class GymClassBuilder{
		private String name;
		
		private int capacity;
		
		private Date startDate;
		
		private Date endDate;
		
		private String category;
		
		private String healthLevelRequired;
		
		public GymClassBuilder(){
			
		}
		
		public GymClassBuilder withName(String name){
			this.name = name;
			return this;
		}
		
		public GymClassBuilder withCapacity(int capacity){
			this.capacity = capacity;
			return this;
		}
		
		public GymClassBuilder withStartDate(Date startDate){
			this.startDate = startDate;
			return this;
		}
		
		public GymClassBuilder withEndDate(Date endDate){
			this.endDate = endDate;
			return this;
		}
		
		public GymClassBuilder withCategory(String category){
			this.category = category;
			return this;
		}
		
		public GymClassBuilder withHealthLevelRequired(String healthLevelRequired){
			this.healthLevelRequired = healthLevelRequired;
			return this;
		}
		
		public GymClass build(){
			GymClass gymClass = new GymClass(this);
			return gymClass;
		}
	}
	
}

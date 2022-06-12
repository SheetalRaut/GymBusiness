package com.example.demo.request;

import lombok.Data;

@Data
public class MemberRequest {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	
	
}

package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Member {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;

	public Member(){
		
	}

	public Member(MemberBuilder builder){
		this.name = builder.name;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static class MemberBuilder{
		
		private String name;
		
		public MemberBuilder(){
			
		}
		
		public MemberBuilder withName(String name) {
            this.name = name;
            return this;
        }
        
        public Member build(){
        	Member member = new Member(this);
        	return member;
        }
	}
}

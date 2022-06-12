package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Memberships {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int memberId;

	private int classId;
	
	private Date bookingDate;
	
	public Memberships(){
		
	}
	
	public Memberships(MembershipsBuilder builder){
		this.memberId = builder.memberId;
		this.classId = builder.classId;
		this.bookingDate = builder.bookingDate;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMeeberId(int memberId) {
		this.memberId = memberId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public static class MembershipsBuilder{
		
		private int memberId;

		private int classId;
		
		private Date bookingDate;
		
		public MembershipsBuilder(){
			
		}
		
		public MembershipsBuilder withMemberId(int memberId){
			this.memberId = memberId;
			return this;
		}
		
		public MembershipsBuilder withClassId(int classId){
			this.classId = classId;
			return this;
		}
		
		public MembershipsBuilder withBookingDate(Date date){
			this.bookingDate = date;
			return this;
		}
		
		public Memberships build(){
			Memberships memberships = new Memberships(this);
			return memberships;
		}
	}
}

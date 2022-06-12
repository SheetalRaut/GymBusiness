package com.example.demo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.GymClass;
import com.example.demo.entity.Member;
import com.example.demo.entity.Memberships;
import com.example.demo.repository.GymClassRepository;
import com.example.demo.repository.MemberRespository;
import com.example.demo.repository.MembershipsRepository;
import com.example.demo.request.ClassBookingRequest;
import com.example.demo.request.GymClassRequest;
import com.example.demo.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService{

	@Autowired
	private GymClassRepository classRepository;
	
	@Autowired
	private MemberRespository memberRespository;
	
	@Autowired
	private MembershipsRepository membershipsRepository;
	
	
	public List<GymClass> getAll() {
		return classRepository.findAll();
	}

	public GymClass getClassById(int id) {
		return classRepository.findById(id).get();
	}

	public String addGymCLass(GymClassRequest gymClassRequest) {
		if(isRequestInvalid(gymClassRequest)){
			return "Please check request again ";
		}
		GymClass gymClass = new GymClass.GymClassBuilder()
							.withName(gymClassRequest.getName())
							.withCapacity(gymClassRequest.getCapacity())
							.withStartDate(getDate(gymClassRequest.getStartDate()))
							.withEndDate(getDate(gymClassRequest.getEndDate()))
							.withCategory(gymClassRequest.getCategory())
							.withHealthLevelRequired(gymClassRequest.getHealthLevelRequired())
							.build();
		classRepository.save(gymClass);
		return "Gym Class Added Successfully!";
	}

	public String bookClass(ClassBookingRequest bookingRequest) {
		
		if(isMembershipRequestValid(bookingRequest)){
			Memberships memberships = new Memberships.MembershipsBuilder()
											.withMemberId(bookingRequest.getMemberNumber())
											.withClassId(bookingRequest.getClassNumber())
											.withBookingDate(getDate(bookingRequest.getBookingDate()))
											.build();
			membershipsRepository.save(memberships);
			
		}else{
			return "Request is not Valid";
		}
		return "Added successfully!!";
	}
	
	private boolean isRequestInvalid(GymClassRequest gymClassRequest){
		
		Date startDate = getDate(gymClassRequest.getStartDate());
		Date endDate = getDate(gymClassRequest.getEndDate());
		boolean isValidStartDate = Objects.nonNull(startDate);
		boolean isValidEndDate = Objects.nonNull(endDate);
		
		if(!(isValidStartDate && isValidEndDate) ){
			return true;
		}
		
		if(!endDate.after(startDate)){
			return true;
		}
		
		return false;
	}
	
	private boolean isMembershipRequestValid(ClassBookingRequest bookingRequest){
		//check if class number is valid 
		Optional<GymClass> gymClass = classRepository.findById(bookingRequest.getClassNumber());
		if(gymClass.isPresent()){
			//check if member number is valid 
			Optional<Member> member = memberRespository.findById(bookingRequest.getMemberNumber());
			if(member.isPresent()){
				Date bookingDate = getDate(bookingRequest.getBookingDate());
				Date startDate = gymClass.get().getStartDate();
				Date endDate = gymClass.get().getEndDate();
				if(bookingDate.equals(startDate) || bookingDate.equals(endDate) || ( bookingDate.after(startDate) && endDate.before(bookingDate))){
					return true;
				}
				else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
		//return true;
	}
	
	private Date getDate(String date){
		Date parsedDate = null;
		try {
			parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}

}

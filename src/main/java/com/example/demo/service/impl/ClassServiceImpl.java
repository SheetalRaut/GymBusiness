package com.example.demo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ErrorDTO;
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
		ErrorDTO error = new ErrorDTO();
		if(isRequestInvalid(gymClassRequest, error)){
			return error.getErrorMessage();
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
		ErrorDTO errorDTO = new ErrorDTO();
		
		if(isMembershipRequestValid(bookingRequest, errorDTO)){
			Memberships memberships = new Memberships.MembershipsBuilder()
											.withMemberId(bookingRequest.getMemberNumber())
											.withClassId(bookingRequest.getClassNumber())
											.withBookingDate(getDate(bookingRequest.getBookingDate()))
											.build();
			membershipsRepository.save(memberships);
			
		}else{
			return errorDTO.getErrorMessage();
		}
		return "Added successfully!!";
	}
	
	private boolean isRequestInvalid(GymClassRequest gymClassRequest, ErrorDTO errorDTO){
		
		Date startDate = getDate(gymClassRequest.getStartDate());
		Date endDate = getDate(gymClassRequest.getEndDate());
		boolean isValidStartDate = Objects.nonNull(startDate);
		boolean isValidEndDate = Objects.nonNull(endDate);
		
		if(!(isValidStartDate && isValidEndDate) ){
			if(!endDate.after(startDate)){
				return true;
			}
			else{
				errorDTO.setErrorMessage("End date should be after start date");
			}
		}else {
			if(errorDTO.getErrorMessage().isEmpty()){
				errorDTO.setErrorMessage("Start date and end date should be format dd/MM/YYYY");
			}
		}
		
		return false;
	}
	
	private boolean isMembershipRequestValid(ClassBookingRequest bookingRequest, ErrorDTO errorDTO){
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
					errorDTO.setErrorMessage("Please check booking date in request it needs to be in between "+ startDate + endDate);
					return false;
				}
			}else{
				errorDTO.setErrorMessage("Please check member number in request");
				return false;
			}
		}else{
			errorDTO.setErrorMessage("Please check class number in request");
			return false;
		}
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
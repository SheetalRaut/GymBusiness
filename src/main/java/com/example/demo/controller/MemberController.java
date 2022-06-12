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

import com.example.demo.entity.Member;
import com.example.demo.request.MemberRequest;
import com.example.demo.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService; 
	
	@GetMapping(value = "/getAllMembers")
	public ResponseEntity<List<Member>> getAll(){
		return new ResponseEntity<List<Member>>(memberService.getAllMembers(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getMember/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable Integer id){
		return new ResponseEntity<Member>(memberService.getMemberById(id),HttpStatus.OK);
	}
	
	@PostMapping(name = "/addMember")
	public ResponseEntity<String> addMember(@RequestBody MemberRequest member){
		return new ResponseEntity<String>(memberService.saveMember(member), HttpStatus.OK);
	}
}

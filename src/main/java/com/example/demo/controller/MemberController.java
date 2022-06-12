package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@GetMapping(value = "/getAllMembers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Member> getAll(){
		return memberService.getAllMembers();
	}
	
	@GetMapping(value = "/getMember/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Member getMemberById(@PathVariable Integer id){
		return memberService.getMemberById(id);
	}
	
	@PostMapping(name = "/addMember")
	public String addMember(@RequestBody MemberRequest member){
		return memberService.saveMember(member);
	}
}

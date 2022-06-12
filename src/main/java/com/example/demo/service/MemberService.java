package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Member;
import com.example.demo.request.MemberRequest;

public interface MemberService {
	
	List<Member> getAllMembers();
	
	Member getMemberById(Integer id);
	
	String saveMember(MemberRequest member);
}

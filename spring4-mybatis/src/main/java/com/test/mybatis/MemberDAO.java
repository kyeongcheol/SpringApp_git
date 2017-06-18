package com.test.mybatis;

import java.util.ArrayList;

//DAO class
public interface MemberDAO 
{
	public ArrayList<Member> getMembers(); 
	public void insertMember(Member member);
	public void updateMember(String name);
	public void deleteMember(String name);

}

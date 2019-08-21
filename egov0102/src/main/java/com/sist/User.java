package com.sist;

/**
 * USER VO
 * @author sist1
 * IP,PORT:211.238.142.124: 1521 
 * SID: orcl
 * ID/Pass: HRSPRING/HRSPRING1026
 * 
 * U_ID     NOT NULL VARCHAR2(20)      
   NAME     NOT NULL VARCHAR2(20 CHAR) 
   PASSWORD NOT NULL VARCHAR2(20 CHAR) 

 */
public class User {
	private String u_id      ;//사용자 ID
	private String name      ;//이름
	private String password  ;//비번
	
	
	
	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", name=" + name + ", password=" + password + "]";
	}
	
	
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

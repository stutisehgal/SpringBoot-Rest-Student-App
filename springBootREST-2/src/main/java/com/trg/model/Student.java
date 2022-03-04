package com.trg.model;

import java.sql.Date;

public class Student {

	private int id;
	private String name;
	private float marks;
	private Date dob;
	public Student(int id, String name, float marks, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.marks = marks;
		this.dob = dob;
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
	public float getMarks() {
		return marks;
	}
	public void setMarks(float marks) {
		this.marks = marks;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
}

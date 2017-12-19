package org.dolphin.commons.io.objectStream;

import java.io.Serializable;

public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7319213542671564722L;
	private Integer id;
	private String name;
	
	public Student(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
	
}

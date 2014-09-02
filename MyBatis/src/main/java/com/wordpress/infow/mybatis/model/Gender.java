package com.wordpress.infow.mybatis.model;

public class Gender extends Human {
	private int id;
	private String gender;

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
		super.setId(id);
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Gender [id=" + this.id + ", gender=" + this.gender + ", toString()=" + super.toString() + "]";
	}

}

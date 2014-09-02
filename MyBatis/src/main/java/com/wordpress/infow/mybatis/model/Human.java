package com.wordpress.infow.mybatis.model;

public class Human extends Mammal {
	private int id;
	private String name;

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
		super.setId(id);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Human [id=" + this.id + ", name=" + this.name + ", toString()=" + super.toString() + "]";
	}


}

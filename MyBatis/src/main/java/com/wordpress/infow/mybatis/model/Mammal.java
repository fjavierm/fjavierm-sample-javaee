package com.wordpress.infow.mybatis.model;

public class Mammal {
	private int id;
	private int age;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Mammal [id=" + this.id + ", age=" + this.age + "]";
	}

}

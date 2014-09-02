package com.wordpress.infow.mybatis.main;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.wordpress.infow.mybatis.mappers.GenderMapper;
import com.wordpress.infow.mybatis.model.Gender;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();

		Gender gender1 = new Gender();
		gender1.setId(1);
		gender1.setAge(10);
		gender1.setName("John");
		gender1.setGender("male");

		Gender gender2 = new Gender();
		gender2.setId(2);
		gender2.setAge(20);
		gender2.setName("Jane");
		gender2.setGender("female");

		Gender gender3 = new Gender();
		gender3.setId(3);
		gender3.setAge(30);
		gender3.setName("Will");
		gender3.setGender("male");

		Gender gender4 = new Gender();
		gender4.setId(4);
		gender4.setAge(40);
		gender4.setName("Susan");
		gender4.setGender("female");

		Gender gender5 = new Gender();
		gender5.setId(5);
		gender5.setAge(50);
		gender5.setName("James");
		gender5.setGender("male");

		System.out.println("================================================================================");
		System.out.println("                              INSERT OPERATION");
		System.out.println("================================================================================");

		System.out.println("Insert: " + main.insertGenderHumanMammal(gender1));
		System.out.println("Insert: " + main.insertGenderHumanMammal(gender2));
		System.out.println("Insert: " + main.insertGenderHumanMammal(gender3));
		System.out.println("Insert: " + main.insertGenderHumanMammal(gender4));
		System.out.println("Insert: " + main.insertGenderHumanMammal(gender5));

		List<Gender> genders = main.getAllGenderHumanMammal();

		for (Gender gender : genders) {
			System.out.println(gender.toString());
		}

		System.out.println("================================================================================");
		System.out.println("                           SELECT BY ID OPERATION");
		System.out.println("================================================================================");

		Gender genderRecovered = main.getGenderHumanMammalById(1);
		System.out.println(genderRecovered.toString());

		System.out.println("================================================================================");
		System.out.println("                              UPDATE OPERATION");
		System.out.println("================================================================================");

		gender1.setAge(gender1.getAge() + 5);
		gender2.setAge(gender2.getAge() + 6);
		gender3.setAge(gender3.getAge() + 7);
		gender4.setAge(gender4.getAge() + 8);
		gender5.setAge(gender5.getAge() + 9);

		gender1.setName(gender1.getName() + "Up");
		gender2.setName(gender2.getName() + "Up");
		gender3.setName(gender3.getName() + "Up");
		gender4.setName(gender4.getName() + "Up");
		gender5.setName(gender5.getName() + "Up");

		gender1.setGender(gender1.getGender() + "Up");
		gender2.setGender(gender2.getGender() + "Up");
		gender3.setGender(gender3.getGender() + "Up");
		gender4.setGender(gender4.getGender() + "Up");
		gender5.setGender(gender5.getGender() + "Up");

		main.updateGenderHumanMammal(gender1);
		main.updateGenderHumanMammal(gender2);
		main.updateGenderHumanMammal(gender3);
		main.updateGenderHumanMammal(gender4);
		main.updateGenderHumanMammal(gender5);

		genders = main.getAllGenderHumanMammal();

		for (Gender gender : genders) {
			System.out.println(gender.toString());
		}

		System.out.println("================================================================================");
		System.out.println("                              DELETE OPERATION");
		System.out.println("================================================================================");

		System.out.println("Delete(" + gender1.getId() + "): " + main.deleteGenderHumanMammal(gender1.getId()));
		System.out.println("Delete(" + gender2.getId() + "): " + main.deleteGenderHumanMammal(gender2.getId()));
		System.out.println("Delete(" + gender3.getId() + "): " + main.deleteGenderHumanMammal(gender3.getId()));
		System.out.println("Delete(" + gender4.getId() + "): " + main.deleteGenderHumanMammal(gender4.getId()));
		System.out.println("Delete(" + gender5.getId() + "): " + main.deleteGenderHumanMammal(gender5.getId()));

		genders = main.getAllGenderHumanMammal();

		if ((genders == null) || genders.isEmpty()) {
			System.out.println("Elements removed correctly.");
		} else {
			System.out.println("ERROR: Element should be removed.");
		}
	}

	public int insertGenderHumanMammal(Gender gender) {
		int result = 0;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			GenderMapper genderMapper = sqlSession.getMapper(GenderMapper.class);
			result = genderMapper.insertGenderHumanMammal(gender);
			sqlSession.commit();

		} finally {
			sqlSession.close();
		}

		return result;
	}

	public int updateGenderHumanMammal(Gender gender) {
		int result = 0;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			GenderMapper genderMapper = sqlSession.getMapper(GenderMapper.class);
			result = genderMapper.updateGenderHumanMammal(gender);
			sqlSession.commit();

		} finally {
			sqlSession.close();
		}

		return result;
	}

	public Gender getGenderHumanMammalById(int id) {
		Gender gender = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			GenderMapper genderMapper = sqlSession.getMapper(GenderMapper.class);
			gender = genderMapper.getGenderHumanMammalById(id);

		} finally {
			sqlSession.close();
		}

		return gender;
	}

	public List<Gender> getAllGenderHumanMammal() {
		List<Gender> genders = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			GenderMapper genderMapper = sqlSession.getMapper(GenderMapper.class);
			genders = genderMapper.getAllGenderHumanMammals();

		} finally {
			sqlSession.close();
		}

		return genders;
	}

	public int deleteGenderHumanMammal(int id) {
		int result = 0;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			GenderMapper genderMapper = sqlSession.getMapper(GenderMapper.class);
			result = genderMapper.deleteGenderHumanMammal(id);
			sqlSession.commit();

		} finally {
			sqlSession.close();
		}

		return result;
	}
}


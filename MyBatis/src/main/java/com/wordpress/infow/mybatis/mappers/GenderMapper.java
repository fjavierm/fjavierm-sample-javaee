package com.wordpress.infow.mybatis.mappers;

import java.util.List;

import com.wordpress.infow.mybatis.model.Gender;

public interface GenderMapper {
	Gender getGenderById(int id);

	List<Gender> getAllGenders();

	int insertGender(Gender gender);

	int updateGender(Gender gender);

	int deleteGender(int id);

	Gender getGenderHumanById(int id);

	List<Gender> getAllGenderHumans();

	int insertGenderHuman(Gender gender);

	int updateGenderHuman(Gender gender);

	int deleteGenderHuman(int id);

	Gender getGenderHumanMammalById(int id);

	List<Gender> getAllGenderHumanMammals();

	int insertGenderHumanMammal(Gender gender);

	int updateGenderHumanMammal(Gender gender);

	int deleteGenderHumanMammal(int id);
}

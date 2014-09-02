package com.wordpress.infow.mybatis.mappers;

import java.util.List;

import com.wordpress.infow.mybatis.model.Human;

public interface HumanMapper {
	Human getHumanById(int id);

	List<Human> getAllHumans();

	int insertHuman(Human human);

	int updateHuman(Human human);

	int deleteHuman(int id);

	Human getHumanMammalById(int id);

	List<Human> getAllHumanMammals();

	int insertHumanMammal(Human human);

	int updateHumanMammal(Human human);

	int deleteHumanMammal(int id);
}

package com.wordpress.infow.mybatis.mappers;

import java.util.List;

import com.wordpress.infow.mybatis.model.Mammal;

public interface MammalMapper {
	Mammal getMammalById(int id);

	List<Mammal> getAllMammals();

	int insertMammal(Mammal mammal);

	int updateMammal(Mammal mammal);

	int deleteMammal(int id);
}

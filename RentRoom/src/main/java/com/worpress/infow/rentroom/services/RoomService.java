package com.worpress.infow.rentroom.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.worpress.infow.rentroom.model.Room;

@Stateless
public class RoomService {

	@PersistenceContext
	EntityManager em;

	public Room createRoom(@NotNull Room room) {
		this.em.persist(room);

		return room;

	}

	public List<Room> findAllRooms() {
		TypedQuery<Room> typedQuery = this.em.createNamedQuery(Room.FIND_ALL, Room.class);

		return typedQuery.getResultList();
	}

	public List<Room> findByCity(@NotNull String city) {
		TypedQuery<Room> typedQuery = this.em.createNamedQuery(Room.FIND_BY_CITY, Room.class);
		typedQuery.setParameter("city", city);

		return typedQuery.getResultList();
	}

	public List<Room> findByTittle(@NotNull String tittle) {
		TypedQuery<Room> typedQuery = this.em.createNamedQuery(Room.FIND_BY_TITTLE, Room.class);
		typedQuery.setParameter("tittle", tittle);

		return typedQuery.getResultList();
	}

	public Room findById(@NotNull Integer id) {
		TypedQuery<Room> typedQuery = this.em.createNamedQuery(Room.FIND_BY_ID, Room.class);
		typedQuery.setParameter("id", id);

		return typedQuery.getSingleResult();
	}

	public Room updateRoom(@NotNull Room room) {
		this.em.merge(room);

		return room;
	}

	public void removeRoom(@NotNull Room room) {
		this.em.remove(room);
	}
}

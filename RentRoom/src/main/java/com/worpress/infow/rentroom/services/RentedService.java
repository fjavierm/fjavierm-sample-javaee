package com.worpress.infow.rentroom.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.worpress.infow.rentroom.model.Rented;
import com.worpress.infow.rentroom.model.Room;
import com.worpress.infow.rentroom.model.User;

@Stateless
public class RentedService {

	@PersistenceContext
	EntityManager em;

	public Rented createRented(@NotNull Rented rented) {
		this.em.persist(rented);

		return rented;
	}

	public List<Rented> findAllRented() {
		TypedQuery<Rented> typedQuery = this.em.createNamedQuery(Rented.FIND_ALL, Rented.class);

		return typedQuery.getResultList();
	}

	public List<Rented> findByUser(@NotNull User user) {
		TypedQuery<Rented> typedQuery = this.em.createNamedQuery(Rented.FIND_BY_USER, Rented.class);
		typedQuery.setParameter("user", user);

		return typedQuery.getResultList();
	}

	public List<Rented> findByRoom(@NotNull Room room) {
		TypedQuery<Rented> typedQuery = this.em.createNamedQuery(Rented.FIND_BY_ROOM, Rented.class);
		typedQuery.setParameter("room", room);

		return typedQuery.getResultList();
	}

	public Rented findById(@NotNull Integer id) {
		TypedQuery<Rented> typedQuery = this.em.createNamedQuery(Rented.FIND_BY_ID, Rented.class);
		typedQuery.setParameter("id", id);

		return typedQuery.getSingleResult();
	}

	public Rented updateRented(@NotNull Rented rented) {
		this.em.merge(rented);

		return rented;
	}

	public void removeRented(@NotNull Rented rented) {
		this.em.remove(rented);
	}
}

package com.worpress.infow.rentroom.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.worpress.infow.rentroom.model.User;

@Stateless
public class UserService {

	@PersistenceContext
	private EntityManager em;

	public User createUser(@NotNull User user) {
		this.em.persist(user);

		return user;
	}

	public List<User> findAllUsers() {
		TypedQuery<User> typedQuery = this.em.createNamedQuery(User.FIND_ALL, User.class);

		return typedQuery.getResultList();
	}

	public User findByLogin(@NotNull String login) {
		TypedQuery<User> typedQuery = this.em.createNamedQuery(User.FIND_BY_LOGIN, User.class);
		typedQuery.setParameter("login", login);

		return typedQuery.getSingleResult();
	}

	public User findByEmail(@NotNull String email) {
		TypedQuery<User> typedQuery = this.em.createNamedQuery(User.FIND_BY_EMAIL, User.class);
		typedQuery.setParameter("email", email);

		return typedQuery.getSingleResult();
	}

	public User findById(@NotNull Integer id) {
		TypedQuery<User> typedQuery = this.em.createNamedQuery(User.FIND_BY_ID, User.class);
		typedQuery.setParameter("id", id);

		return typedQuery.getSingleResult();
	}

	public User updateUser(@NotNull User user) {
		this.em.merge(user);

		return user;
	}

	public void removeUser(@NotNull User user) {
		this.em.remove(user);
	}
}

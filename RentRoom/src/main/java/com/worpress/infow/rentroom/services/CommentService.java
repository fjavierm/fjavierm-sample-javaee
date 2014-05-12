package com.worpress.infow.rentroom.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.worpress.infow.rentroom.model.Comment;
import com.worpress.infow.rentroom.model.Room;
import com.worpress.infow.rentroom.model.User;

@Stateless
public class CommentService {

	@PersistenceContext
	EntityManager em;

	public Comment createComment(@NotNull Comment comment) {
		this.em.persist(comment);

		return comment;
	}

	public List<Comment> findAllComments() {
		TypedQuery<Comment> typedQuery = this.em.createNamedQuery(Comment.FIND_ALL, Comment.class);

		return typedQuery.getResultList();
	}

	public List<Comment> findByUser(@NotNull User user) {
		TypedQuery<Comment> typedQuery = this.em.createNamedQuery(Comment.FIND_BY_USER, Comment.class);
		typedQuery.setParameter("user", user);

		return typedQuery.getResultList();
	}

	public List<Comment> findByRoom(@NotNull Room room) {
		TypedQuery<Comment> typedQuery = this.em.createNamedQuery(Comment.FIND_BY_ROOM, Comment.class);
		typedQuery.setParameter("room", room);

		return typedQuery.getResultList();
	}

	public Comment findById(@NotNull Integer id) {
		TypedQuery<Comment> typedQuery = this.em.createNamedQuery(Comment.FIND_BY_ID, Comment.class);
		typedQuery.setParameter("id", id);

		return typedQuery.getSingleResult();
	}

	public Comment updateComment(@NotNull Comment comment) {
		this.em.merge(comment);

		return comment;
	}

	public void removeComment(@NotNull Comment comment) {
		this.em.remove(comment);
	}
}

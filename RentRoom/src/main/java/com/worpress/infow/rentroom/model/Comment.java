package com.worpress.infow.rentroom.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name = "comment")
@NamedQueries ({
		@NamedQuery (name = Comment.FIND_ALL, query = "SELECT c FROM Comment c"),
		@NamedQuery (name = Comment.FIND_BY_USER, query = "SELECT c FROM Comment c WHERE c.user = :user"),
		@NamedQuery (name = Comment.FIND_BY_ROOM, query = "SELECT c FROM Comment c WHERE c.roomBean = :room"),
		@NamedQuery (name = Comment.FIND_BY_ID, query = "SELECT c FROM Comment c WHERE c.id = :id")
})
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Comment.findAll";
	public static final String FIND_BY_USER = "Comment.findByUSer";
	public static final String FIND_BY_ROOM = "Comment.findByRoom";
	public static final String FIND_BY_ID = "Comment.findById";

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (unique = true, nullable = false)
	private int id;

	@Column (nullable = false, length = 500)
	private String comment;

	// bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn (name = "room", nullable = false)
	private Room roomBean;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn (name = "author", nullable = false)
	private User user;

	public Comment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Room getRoomBean() {
		return this.roomBean;
	}

	public void setRoomBean(Room roomBean) {
		this.roomBean = roomBean;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = (prime * result) + ((this.comment == null) ? 0 : this.comment.hashCode());
		result = (prime * result) + ((this.roomBean == null) ? 0 : this.roomBean.hashCode());
		result = (prime * result) + ((this.user == null) ? 0 : this.user.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Comment)) {
			return false;
		}

		Comment comment = (Comment) obj;

		if (this.comment != null ? !this.comment.equals(comment.comment) : comment.comment != null) {
			return false;
		}
		if (this.roomBean != null ? !this.roomBean.equals(comment.roomBean) : comment.roomBean != null) {
			return false;
		}
		if (this.user != null ? !this.user.equals(comment.user) : comment.user != null) {
			return false;
		}

		return true;
	}

}
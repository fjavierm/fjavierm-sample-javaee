package com.worpress.infow.rentroom.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "user")
@NamedQueries ({
		@NamedQuery (name = User.FIND_ALL, query = "SELECT u FROM User u"),
		@NamedQuery (name = User.FIND_BY_LOGIN, query = "SELECT u FROM User u WHERE u.login = :login"),
		@NamedQuery (name = User.FIND_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email"),
		@NamedQuery (name = User.FIND_BY_ID, query = "SELECT u FROM User u WHERE u.id = :id")
})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "User.findAll";
	public static final String FIND_BY_LOGIN = "User.findByName";
	public static final String FIND_BY_EMAIL = "User.findByEmail";
	public static final String FIND_BY_ID = "User.findById";

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (unique = true, nullable = false)
	private int id;

	@Column (nullable = false, length = 30)
	private String city;

	@Column (nullable = false, length = 45)
	private String email;

	@Column (nullable = false, length = 45)
	private String login;

	@Column (nullable = false, length = 50)
	private String password;

	@Column (nullable = false, length = 45)
	private String phone;

	// bi-directional many-to-one association to Comment
	@OneToMany (mappedBy = "user")
	private List<Comment> comments;

	// bi-directional many-to-one association to Rented
	@OneToMany (mappedBy = "user")
	private List<Rented> renteds;

	// bi-directional many-to-one association to Room
	@OneToMany (mappedBy = "user")
	private List<Room> rooms;

	public User() {
	}

	public User(String city, String email, String login, String password, String phone) {
		super();
		this.city = city;
		this.email = email;
		this.login = login;
		this.password = password;
		this.phone = phone;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		this.getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		this.getComments().remove(comment);
		comment.setUser(null);

		return comment;
	}

	public List<Rented> getRenteds() {
		return this.renteds;
	}

	public void setRenteds(List<Rented> renteds) {
		this.renteds = renteds;
	}

	public Rented addRented(Rented rented) {
		this.getRenteds().add(rented);
		rented.setUser(this);

		return rented;
	}

	public Rented removeRented(Rented rented) {
		this.getRenteds().remove(rented);
		rented.setUser(null);

		return rented;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room addRoom(Room room) {
		this.getRooms().add(room);
		room.setUser(this);

		return room;
	}

	public Room removeRoom(Room room) {
		this.getRooms().remove(room);
		room.setUser(null);

		return room;
	}

	@Override
	public int hashCode() {
		return this.login.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}

		User user = (User) obj;

		if (!this.login.equals(user.login)) {
			return false;
		}

		return true;
	}

}
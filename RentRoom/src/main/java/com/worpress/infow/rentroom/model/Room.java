package com.worpress.infow.rentroom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "room")
@NamedQueries ({
		@NamedQuery (name = Room.FIND_ALL, query = "SELECT r FROM Room r"),
		@NamedQuery (name = Room.FIND_BY_CITY, query = "SELECT r FROM Room r WHERE r.city = :city"),
		@NamedQuery (name = Room.FIND_BY_TITTLE, query = "SELECT r FROM Room r WHERE r.tittle = :tittle"),
		@NamedQuery (name = Room.FIND_BY_ID, query = "SELECT r FROM Room r WHERE r.id = :id")
})
public class Room implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Room.findAll";
	public static final String FIND_BY_CITY = "Room.findByCity";
	public static final String FIND_BY_TITTLE = "Room.findByTittle";
	public static final String FIND_BY_ID = "Room.findById";

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (unique = true, nullable = false)
	private int id;

	@Column (nullable = false, length = 100)
	private String address;

	@Column (nullable = false, length = 30)
	private String city;

	@Column (nullable = false, length = 500)
	private String description;

	@Column (nullable = false, precision = 6, scale = 2)
	private BigDecimal prize;

	@Column (nullable = false, length = 45)
	private String tittle;

	// bi-directional many-to-one association to Comment
	@OneToMany (mappedBy = "roomBean", fetch = FetchType.EAGER)
	private List<Comment> comments;

	// bi-directional many-to-one association to Rented
	@OneToMany (mappedBy = "roomBean", fetch = FetchType.EAGER)
	private List<Rented> renteds;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn (name = "owner", nullable = false)
	private User user;

	public Room() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrize() {
		return this.prize;
	}

	public void setPrize(BigDecimal prize) {
		this.prize = prize;
	}

	public String getTittle() {
		return this.tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		this.getComments().add(comment);
		comment.setRoomBean(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		this.getComments().remove(comment);
		comment.setRoomBean(null);

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
		rented.setRoomBean(this);

		return rented;
	}

	public Rented removeRented(Rented rented) {
		this.getRenteds().remove(rented);
		rented.setRoomBean(null);

		return rented;
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

		result = (prime * result) + ((this.city == null) ? 0 : this.city.hashCode());
		result = (prime * result) + ((this.tittle == null) ? 0 : this.tittle.hashCode());
		result = (prime * result) + ((this.user == null) ? 0 : this.user.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Room)) {
			return false;
		}

		Room room = (Room) obj;

		if (this.city != null ? !this.city.equals(room.city) : room.city != null) {
			return false;
		}
		if (this.tittle != null ? !this.tittle.equals(room.tittle) : room.tittle != null) {
			return false;
		}
		if (this.user != null ? !this.user.equals(room.user) : room.user != null) {
			return false;
		}

		return true;
	}

}
package com.worpress.infow.rentroom.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
@Table (name = "rented")
@NamedQueries ({
		@NamedQuery (name = Rented.FIND_ALL, query = "SELECT r FROM Rented r"),
		@NamedQuery (name = Rented.FIND_BY_USER, query = "SELECT r FROM Rented r WHERE r.user = :user"),
		@NamedQuery (name = Rented.FIND_BY_ROOM, query = "SELECT r FROM Rented r WHERE r.roomBean = :room"),
		@NamedQuery (name = Rented.FIND_BY_ID, query = "SELECT r FROM Rented r WHERE r.id = :id")
})
public class Rented implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Rented.findAll";
	public static final String FIND_BY_USER = "Rented.findByUSer";
	public static final String FIND_BY_ROOM = "Rented.findByRoom";
	public static final String FIND_BY_ID = "Rented.findById";

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (unique = true, nullable = false)
	private int id;

	@Column (nullable = false)
	private int nigths;

	@Column (name = "stat_date", nullable = false)
	private Timestamp statDate;

	@Column (name = "total_prize", nullable = false, precision = 6, scale = 2)
	private BigDecimal totalPrize;

	// bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn (name = "room", nullable = false)
	private Room roomBean;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn (name = "renter", nullable = false)
	private User user;

	public Rented() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNigths() {
		return this.nigths;
	}

	public void setNigths(int nigths) {
		this.nigths = nigths;
	}

	public Timestamp getStatDate() {
		return this.statDate;
	}

	public void setStatDate(Timestamp statDate) {
		this.statDate = statDate;
	}

	public BigDecimal getTotalPrize() {
		return this.totalPrize;
	}

	public void setTotalPrize(BigDecimal totalPrize) {
		this.totalPrize = totalPrize;
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

		result = (prime * result) + ((this.roomBean == null) ? 0 : this.roomBean.hashCode());
		result = (prime * result) + ((this.statDate == null) ? 0 : this.statDate.hashCode());
		result = (prime * result) + ((this.user == null) ? 0 : this.user.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Rented)) {
			return false;
		}

		Rented rented = (Rented) obj;

		if (this.roomBean != null ? !this.roomBean.equals(rented.roomBean) : rented.roomBean != null) {
			return false;
		}
		if (this.statDate != null ? !this.statDate.equals(rented.statDate) : rented.statDate != null) {
			return false;
		}
		if (this.user != null ? !this.user.equals(rented.user) : rented.user != null) {
			return false;
		}

		return true;
	}

}
package com.management.gym.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="trainer")
public class Trainer {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="email")
	private String email;

	@Column(name="phone")
	private String phone;

	@Column(name="street")
	private String street;

	@Column(name="city")
	private String city;

	@Column(name="zip")
	private String zip;
	
	@OneToMany(mappedBy = "trainer")
	@JsonIgnore
	private List<GymSession> gymSessions;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}	
	public List<GymSession> getGymSessions() {
		return gymSessions;
	}
	public void setGymSessions(List<GymSession> gymSessions) {
		this.gymSessions = gymSessions;
	}
	
	@Override
	public String toString() {
		return "Trainer [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", street=" + street
				+ ", city=" + city + ", zip=" + zip + "]";
	}
	

}

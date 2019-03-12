package com.uniovi.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private Long ID;

	private String title;
	private String description;
	private String email;

	@ManyToOne
	@JoinColumn(name = "offer_id")
	private Offer offer;

	public Message() {
	}

	/**
	 * @param id
	 * @param title
	 * @param descripcion
	 * @param date
	 * @param amount
	 */
	public Message(Long id, String title, String description, String email) {
		super();
		this.ID = id;
		this.title = title;
		this.description = description;
		this.email = email;
	}

	/**
	 * @param title
	 * @param descripcion
	 * @param date
	 * @param amount
	 * @param user
	 */
	public Message(String title, String description, String email, Offer offer) {
		super();
		this.title = title;
		this.description = description;
		this.email = email;
		this.offer = offer;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return ID;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.ID = id;
	}

	/**
	 * @return the user
	 */
	public Offer getOffer() {
		return offer;
	}

	/**
	 * @param user the user to set
	 */
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the date
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param date the date to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [ID=" + ID + ", title=" + title + ", description=" + description + ", email=" + email
				+ ", offer=" + offer + "]";
	}

}

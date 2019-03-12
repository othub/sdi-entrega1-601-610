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
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private User receiver;

	@ManyToOne
	@JoinColumn(name = "offer_id")
	private Offer offer;

	private String message;

	public Message() {
	}
	
	/**
	 * @param id
	 * @param message
	 */
	public Message(Long id, String message) {
		super();
		this.ID = id;
		this.message = message;
	}
	
	/**
	 * @param sender
	 * @param receiver
	 * @param offer
	 */
	public Message(User sender, User receiver, Offer offer) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.offer = offer;
	}
	
	/**
	 * @param sender
	 * @param receiver
	 * @param offer
	 * @param message
	 */
	public Message(User sender, User receiver, Offer offer, String message) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.offer = offer;
		this.message = message;
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

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Message [ID=" + ID + ", sender=" + sender + ", receiver=" + receiver + ", offer=" + offer + ", message="
				+ message + "]";
	}

}

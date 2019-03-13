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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    private String messageText;
    private String date = new SimpleDateFormat("yyyy-mm-dd hh:mm").format(Calendar.getInstance().getTime());

    public Message() {
    }

    /**
     * @param id
     * @param message
     */
    public Message(Long id, String message) {
	super();
	this.id = id;
	this.messageText = message;
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
	this.messageText = message;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
	this.id = id;
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

    /**
     * @return the messageText
     */
    public String getMessageText() {
	return messageText;
    }

    /**
     * @param messageText the messageText to set
     */
    public void setMessageText(String messageText) {
	this.messageText = messageText;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Message [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", offer=" + offer
		+ ", messageText=" + messageText + ", date=" + date + "]";
    }

    /**
     * @return the date
     */
    public String getDate() {
	return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
	this.date = date;
    }

}

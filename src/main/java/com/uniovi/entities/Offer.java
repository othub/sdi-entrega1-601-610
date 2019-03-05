package com.uniovi.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @version $Id$
 */
@Entity
public class Offer {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private double amount;
    private String date = new SimpleDateFormat("yyyy-mm-dd hh:mm").format(Calendar.getInstance().getTime());

    public boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Offer() {
    }

    /**
     * @param id
     * @param title
     * @param descripcion
     * @param date
     * @param amount
     */
    public Offer(Long id, String title, String description, double amount) {
	super();
	this.id = id;
	this.title = title;
	this.description = description;
	this.amount = amount;
    }

    /**
     * @param title
     * @param descripcion
     * @param date
     * @param amount
     * @param user
     */
    public Offer(String title, String description, double amount, User user) {
	super();
	this.title = title;
	this.description = description;
	this.amount = amount;
	this.user = user;
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

    /**
     * @return the user
     */
    public User getUser() {
	return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
	this.user = user;
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
    public String getDate() {
	return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
	this.date = date;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
	return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
	this.amount = amount;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Offer [id=" + id + ", title=" + title + ", description=" + description + ", amount=" + amount
		+ ", date=" + date + ", isAvailable=" + isAvailable + ", user=" + user + "]";
    }

    /**
     * @return the isAvailable
     */
    public boolean isAvailable() {
	return isAvailable;
    }

    /**
     * @param isAvailable the isAvailable to set
     */
    public void setAvailable(boolean isAvailable) {
	this.isAvailable = isAvailable;
    }

}

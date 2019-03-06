package com.uniovi.entities;

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
public class ProductBought {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private double amount;

    public String vendorEmail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ProductBought() {
    }

    /**
     * @param id
     * @param title
     * @param descripcion
     * @param date
     * @param amount
     */
    public ProductBought(Long id, String title, String description, double amount, String vendorEmail) {
	super();
	this.id = id;
	this.title = title;
	this.description = description;
	this.amount = amount;
	this.vendorEmail = vendorEmail;
    }

    /**
     * @param title
     * @param descripcion
     * @param date
     * @param amount
     * @param user
     */
    public ProductBought(String title, String description, double amount, User user, String vendorEmail) {
	super();
	this.title = title;
	this.description = description;
	this.amount = amount;
	this.user = user;
	this.vendorEmail = vendorEmail;
    }

    /**
     * @return the vendorEmail
     */
    public String getVendorEmail() {
	return vendorEmail;
    }

    /**
     * @param vendorEmail the vendorEmail to set
     */
    public void setVendorEmail(String vendorEmail) {
	this.vendorEmail = vendorEmail;
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
	return "ProductBought [id=" + id + ", title=" + title + ", description=" + description + ", amount=" + amount
		+ ", vendorEmail=" + vendorEmail + ", user=" + user + "]";
    }

}

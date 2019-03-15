package com.uniovi.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @version $Id$
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String email;

    private String name;
    private String lastName;
    private double moneySum = 100;

    // ROLES
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Offer> offers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ProductBought> offersBought;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> messagesSent;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> messagesReceived;

    private String password;

    @Transient // propiedad que no se almacena e la tabla.
    private String passwordConfirm;

    public User(String email, String name, String lastName) {
	super();
	this.email = email;
	this.name = name;
	this.lastName = lastName;
    }

    public User() {
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getPasswordConfirm() {
	return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
	this.passwordConfirm = passwordConfirm;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getFullName() {
	return this.name + " " + this.lastName;
    }

    /**
     * @return the role
     */
    public String getRole() {
	return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
	this.role = role;
    }

    /**
     * @return the moneySum
     */
    public double getMoneySum() {
	return moneySum;
    }

    /**
     * @param moneySum the moneySum to set
     */
    public void setMoneySum(double moneySum) {
	this.moneySum = moneySum;
    }

    /**
     * @return the offers
     */
    public Set<Offer> getOffers() {
	return offers;
    }

    /**
     * @param offers the offers to set
     */
    public void setOffers(Set<Offer> offers) {
	this.offers = offers;
    }

    /**
     * @return the offersBought
     */
    public Set<ProductBought> getOffersBought() {
	return offersBought;
    }

    /**
     * @return the messagesSent
     */
    public Set<Message> getMessagesSent() {
	return messagesSent;
    }

    /**
     * @param messagesSent the messagesSent to set
     */
    public void setMessagesSent(Set<Message> messagesSent) {
	this.messagesSent = messagesSent;
    }

    /**
     * @return the messagesReceived
     */
    public Set<Message> getMessagesReceived() {
	return messagesReceived;
    }

    /**
     * @param messagesReceived the messagesReceived to set
     */
    public void setMessagesReceived(Set<Message> messagesReceived) {
	this.messagesReceived = messagesReceived;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "User [id=" + id + ", email=" + email + ", name=" + name + ", lastName=" + lastName + ", moneySum="
		+ moneySum + "]";
    }

    /**
     * @param offersBought the offersBought to set
     */
    public void setOffersBought(Set<ProductBought> offersBought) {
	this.offersBought = offersBought;
    }

}

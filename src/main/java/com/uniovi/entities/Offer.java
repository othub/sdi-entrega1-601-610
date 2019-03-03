/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.uniovi.entities;

import java.util.Calendar;
import java.util.Date;

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
    private String descripcion;
    private double amount;
    private Date date = Calendar.getInstance().getTime();

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
    public Offer(Long id, String title, String descripcion, double amount) {
	super();
	this.id = id;
	this.title = title;
	this.descripcion = descripcion;
	this.amount = amount;
    }

    /**
     * @param title
     * @param descripcion
     * @param date
     * @param amount
     * @param user
     */
    public Offer(String title, String descripcion, double amount, User user) {
	super();
	this.title = title;
	this.descripcion = descripcion;
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
    public String getDescripcion() {
	return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    /**
     * @return the date
     */
    public Date getDate() {
	return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
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
	return "Offer [id=" + id + ", title=" + title + ", descripcion=" + descripcion + ", date=" + date + ", amount="
		+ amount + ", user=" + user + "]";
    }

}

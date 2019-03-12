package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;

/**
 * 
 * @version $Id$
 */
public interface MessagesRepository extends CrudRepository<Message, Long> {

}

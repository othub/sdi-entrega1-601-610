package com.uniovi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

/**
 * 
 * @version $Id$
 */
public interface MessagesRepository extends CrudRepository<Message, Long> {
	
	/**
	 * @param pageable
	 * @param user
	 * @return
	 */
	@Query("SELECT m FROM Message m WHERE m.sender = ?1 and m.receiver =?2 ORDER BY o.id ASC ")
	Message findMessageByUser(User sender, User receiver);

}

package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Message;
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
    @Query("SELECT m FROM Message m WHERE m.sender = ?1 ORDER BY m.id ASC ")
    List<Message> findMessageByUser(User sender);

}

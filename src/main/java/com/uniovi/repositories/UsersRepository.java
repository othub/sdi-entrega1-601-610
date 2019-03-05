package com.uniovi.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.User;

/**
 * 
 * @version $Id$
 */
public interface UsersRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    /**
     * @param rest
     * @param id
     */
    /**
     * @param rest
     * @param id
     */
    @Modifying
    @Transactional
    @Query("UPDATE User SET moneySum = ?1 WHERE id = ?2")
    void updateUserAmount(double rest, Long id);
}

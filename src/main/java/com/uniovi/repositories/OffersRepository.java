package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

/**
 * 
 * @version $Id$
 */
public interface OffersRepository extends CrudRepository<Offer, Long> {

    /**
     * @param pageable
     * @param user
     * @return
     */
    @Query("SELECT o FROM Offer o WHERE o.user = ?1 ORDER BY o.id ASC ")
    Page<Offer> findAllByUser(Pageable pageable, User user);

    /**
     * @param pageable
     * @return
     */
    Page<Offer> findAll(Pageable pageable);

    /**
     * @param pageable
     * @param searchText
     * @return
     */
    @Query("SELECT o FROM Offer o WHERE (LOWER(o.title) LIKE LOWER(?1))")
    Page<Offer> searchOfferByTitle(Pageable pageable, String searchText);

    /**
     * @param isAvailable
     * @param id
     */
    @Modifying
    @Transactional
    @Query("UPDATE Offer SET isAvailable = ?1 WHERE id = ?2")
    void updateAvailable(boolean isAvailable, Long id);

    /**
     * 
     * @param isHighlighted
     * @param id
     */
    @Modifying
    @Transactional
    @Query("UPDATE Offer SET isHighlighted = ?1 WHERE id = ?2")
	void updateHighlight(boolean isHighlighted, Long id);

}

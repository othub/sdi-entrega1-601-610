package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.ProductBought;
import com.uniovi.entities.User;

/**
 * 
 * @version $Id$
 */
public interface ProductBoughtRepository extends CrudRepository<ProductBought, Long> {

    /**
     * @param pageable
     * @param user
     * @return
     */
    @Query("SELECT o FROM ProductBought o WHERE o.user = ?1 ORDER BY o.id ASC ")
    Page<ProductBought> findAllByUser(Pageable pageable, User user);

    /**
     * @param pageable
     * @return
     */
    Page<ProductBought> findAll(Pageable pageable);

}

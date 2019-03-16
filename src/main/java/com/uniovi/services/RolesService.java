package com.uniovi.services;

import org.springframework.stereotype.Service;

/**
 * 
 * @version $Id$
 */
@Service
public class RolesService {

    String[] roles = { "ROLE_STANDARD", "ROLE_ADMIN" };

    /**
     * @return the roles admin or standard
     */
    public String[] getRoles() {
	return roles;
    }
}

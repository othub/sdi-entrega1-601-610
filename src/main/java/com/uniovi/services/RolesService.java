package com.uniovi.services;

import org.springframework.stereotype.Service;

/**
 * implementamos la función getRoles() que retorna la lista con los roles. Vamos
 * a incluir los roles en una lista, pero lo ideal sería incluir una entidad
 * Role en bases de datos y relacionarla con la entidad user.
 * 
 * @version $Id$
 */
@Service
public class RolesService {
	String[] roles = { "ROLE_STANDARD", "ROLE_ADMIN" };

	public String[] getRoles() {
		return roles;
	}
}

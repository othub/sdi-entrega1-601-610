package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;

/**
 * Como necesitaremos varios usuarios y notas para ver en funcionamiento la
 * aplicación, vamos a crear un servicio de prueba InsertSampleDataService,
 * utilizaremos el método init() de este servicio para crear dinámicamente
 * varios usuarios con sus notas.
 * 
 * @version $Id$
 */
@Service
public class InsertSampleDataService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @SuppressWarnings("serial")
    @PostConstruct
    public void init() {
	User user1 = new User("algo@gmail.com", "Pedro", "Díaz");
	user1.setPassword("123456");
	user1.setRole(rolesService.getRoles()[0]);
	User user2 = new User("algo1@gmail.com", "Lucas", "Núñez");
	user2.setPassword("123456");
	user2.setRole(rolesService.getRoles()[0]);
	User user3 = new User("algo3@gmail.com", "María", "Rodríguez");
	user3.setPassword("123456");
	user3.setRole(rolesService.getRoles()[1]);

	Set<Mark> user1Marks = new HashSet<Mark>() {
	    {
		add(new Mark("Nota A1", 10.0, user1));
		add(new Mark("Nota A2", 9.0, user1));
		add(new Mark("Nota A3", 7.0, user1));
		add(new Mark("Nota A4", 6.5, user1));
	    }
	};
	user1.setMarks(user1Marks);

	Set<Mark> user2Marks = new HashSet<Mark>() {
	    {
		add(new Mark("Nota B1", 5.0, user2));
		add(new Mark("Nota B2", 4.3, user2));
		add(new Mark("Nota B3", 8.0, user2));
		add(new Mark("Nota B4", 3.5, user2));
	    }
	};
	user2.setMarks(user2Marks);
	Set<Mark> user3Marks = new HashSet<Mark>() {
	    {
		;
		add(new Mark("Nota C1", 5.5, user3));
		add(new Mark("Nota C2", 6.6, user3));
		add(new Mark("Nota C3", 7.0, user3));
	    }
	};
	user3.setMarks(user3Marks);

	usersService.addUser(user1);
	usersService.addUser(user2);
	usersService.addUser(user3);
    }
}
package com.uniovi;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;

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
	User user1 = new User("algo@gmail.com", "Pedro", "Pascal");
	user1.setPassword("123456");
	user1.setRole(rolesService.getRoles()[0]);
	User user2 = new User("algo1@gmail.com", "Lucas", "Hernandez");
	user2.setPassword("123456");
	user2.setRole(rolesService.getRoles()[0]);
	User user3 = new User("algo2@gmail.com", "Iker", "Casillas");
	user3.setPassword("123456");
	user3.setRole(rolesService.getRoles()[0]);
	User user4 = new User("algo3@gmail.com", "Xavi", "Hernandez");
	user4.setPassword("123456");
	user4.setRole(rolesService.getRoles()[0]);
	User user5 = new User("alg4@gmail.com", "Pedro", "Rodriguez");
	user5.setPassword("123456");
	user5.setRole(rolesService.getRoles()[0]);
	User user6 = new User("algo5@gmail.com", "Leo", "Messi");
	user6.setPassword("123456");
	user6.setRole(rolesService.getRoles()[0]);

	User user7 = new User("algo7@gmail.com", "Neymar", "Jr");
	user7.setPassword("123456");
	user7.setRole(rolesService.getRoles()[0]);

	User admin = new User("admin@email.com", "Othmane", "Bakhtaoui");
	admin.setPassword("123456");
	admin.setRole(rolesService.getRoles()[1]);

	Set<Offer> user2Offers = new HashSet<Offer>() {
	    {
		add(new Offer("Patinete 2019", "patinete electrico", 40, user2));
		add(new Offer("title22", "descr22", 22, user2));

	    }
	};
	user2.setOffers(user2Offers);

	Set<Offer> user4Offers = new HashSet<Offer>() {
	    {
		add(new Offer("title41", "descr41", 41, user4));
		add(new Offer("title42", "descr42", 42, user4));
	    }
	};
	user4.setOffers(user4Offers);

	Set<Offer> user3Offers = new HashSet<Offer>() {
	    {
		add(new Offer("title31", "descr31", 31, user3));
		add(new Offer("title32", "descr32", 32, user3));
	    }
	};
	user3.setOffers(user3Offers);

	Set<Offer> user7Offers = new HashSet<Offer>() {
	    {
		add(new Offer("title71", "descr71", 71, user7));
		add(new Offer("title72", "descr72", 72, user7));
	    }
	};
	user7.setOffers(user7Offers);

	Set<Offer> user6Offers = new HashSet<Offer>() {
	    {
		add(new Offer("Coche VolksWagen", "golf", 60, user6));
		add(new Offer("Coche Mercedes", "220c", 70, user6));
	    }
	};
	user6.setOffers(user6Offers);

	Set<Offer> user5Offers = new HashSet<Offer>() {
	    {
		add(new Offer("title51", "descr51", 51, user5));
		add(new Offer("title52", "descr52", 52, user5));
	    }
	};
	user5.setOffers(user5Offers);

	usersService.addUser(user1);
	usersService.addUser(user2);
	usersService.addUser(user3);
	usersService.addUser(user4);
	usersService.addUser(user5);
	usersService.addUser(user6);
	usersService.addUser(user7);
	usersService.addUser(admin);
    }
}
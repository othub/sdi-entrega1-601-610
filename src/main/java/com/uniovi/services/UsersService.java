package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @return all the users in the system
     */
    public List<User> getUsers() {
	List<User> users = new ArrayList<User>();
	usersRepository.findAll().forEach(users::add);
	return users;
    }

    /**
     * @param id of the user
     * @return the user
     */
    public User getUser(Long id) {
	return usersRepository.findById(id).get();
    }

    /**
     * @param user that will be added with encrypted password
     */
    public void addUser(User user) {
	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	usersRepository.save(user);
    }

    /**
     * @param id of the user that will be deleted
     */
    public void deleteUser(Long id) {
	usersRepository.deleteById(id);
    }

    /**
     * @param email of the user that will be found
     * @return
     */
    public User getUserByEmail(String email) {
	return usersRepository.findByEmail(email);
    }

}
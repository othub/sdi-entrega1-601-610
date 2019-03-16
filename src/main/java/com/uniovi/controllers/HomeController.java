package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

/**
 * La pagina inicial cuando accedemos a localhost:8090
 * 
 */
@Controller
public class HomeController {

    @Autowired
    private UsersService usersService;

    /**
     * returns the index and add the user's money in the view if he's logged in
     * 
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping("/")
    public String index(Model model, Principal principal) {
	if (principal != null) {
	    String email = principal.getName();
	    User user = usersService.getUserByEmail(email);
	    if (user != null)
		model.addAttribute("userMoney", user.getMoneySum());
	}
	return "index"; // devuelve el index.html
    }

}
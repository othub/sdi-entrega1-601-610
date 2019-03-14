package com.uniovi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.services.security.SecurityService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SignUpFormValidator signUpFormValidator;

    @Autowired
    private RolesService rolesService;

    @RequestMapping("/user/list")
    public String getListado(Model model) {
	model.addAttribute("usersList", usersService.getUsers());
	return "user/list";
    }

    @RequestMapping(value = { "/nav" }, method = RequestMethod.GET)
    public String nav(Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	model.addAttribute("userMoney", activeUser.getMoneySum());
	return "nav";
    }

    // _______________________LOGIN______________________//

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
	return "login";
    }

    // _______________________SIGNUP______________________//

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
	model.addAttribute("user", new User());
	return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result, Model model) {
	signUpFormValidator.validate(user, result);
	if (result.hasErrors()) {
	    return "signup";
	}
	// Standard role
	user.setRole(rolesService.getRoles()[0]);
	usersService.addUser(user);
	securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
	return "redirect:home";
    }

    // ______________________MULTIPLE DELETES ___________________________//

    @PostMapping("/user/list")
    public String delete(@RequestParam("idChecked") List<String> idUsers) {
	if (idUsers != null) {
	    for (String id : idUsers) {
		Long idToDelete = Long.parseLong(id);
		User user = usersService.getUser(idToDelete);
		if (!user.getEmail().equals("admin@email.com"))
		    usersService.deleteUser(idToDelete);
		else
		    return "error/deletion";
	    }
	}
	return "redirect:/user/list";
    }

    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
	User user = usersService.getUser(id);
	if (!user.getEmail().equals("admin@email.com")) {
	    usersService.deleteUser(id);
	    return "redirect:/user/list";
	} else
	    return "error/deletion";
    }

}
package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.ProductBought;
import com.uniovi.entities.User;
import com.uniovi.services.ProductBoughtService;
import com.uniovi.services.UsersService;

/**
 * 
 * @version $Id$
 */
@Controller
public class ProductBoughtController {

	@Autowired
	private UsersService usersService;

	@SuppressWarnings("unused")
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private ProductBoughtService offersBoughtService;

	// __________________________ OFFERS bought _____________________//

	/**
	 * returns the user's bought offers
	 * 
	 * @param model
	 * @param pageable
	 * @param principal
	 * @param searchText
	 * @return
	 */
	@RequestMapping("/offer/bought")
	public String getListBought(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<ProductBought> offers = new PageImpl<ProductBought>(new LinkedList<ProductBought>());
		offers = offersBoughtService.getProductBoughtsForUser(pageable, user);
		model.addAttribute("userMoney", user.getMoneySum());
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "offer/bought";
	}
}

package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

/**
 * 
 * @version $Id$
 */
@Controller
public class OffersController {

    @Autowired
    private OffersService offersService;

    @Autowired
    private UsersService usersService;

    @SuppressWarnings("unused")
    @Autowired
    private HttpSession httpSession;

    // ___________________ ADD ___________________--//

    /**
     * Adds the offer that will be highlighted or not depending of the checkbox
     * value
     * 
     * @return
     */
    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String setOffer(@ModelAttribute Offer offer,
	    @RequestParam(value = "isHighlighted", required = false) String checkboxValue) {
	if (checkboxValue != null) {
	    offer.setHighlighted(true);
	} else {
	    offer.setHighlighted(false);
	}
	offersService.addOffer(offer);
	return "redirect:/offer/list";
    }

    /**
     * 
     * returns the add.html of the html
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/offer/add")
    public String getOffer(Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	model.addAttribute("user", activeUser);
	model.addAttribute("userMoney", activeUser.getMoneySum());
	return "offer/add";
    }

    // ____________________- DELETE ___________________--//

    @RequestMapping("/offer/delete/{id}")
    public String deleteOffer(@PathVariable Long id) {
	offersService.deleteOffer(id);
	return "redirect:/offer/list";
    }

    /**
     * multiple delete of the offers
     * 
     * @param idOffers
     * @return
     */
    @PostMapping("/offer/list")
    public String delete(@RequestParam("idChecked") List<String> idOffers) {
	if (idOffers != null) {
	    for (String id : idOffers) {
		Long idToDelete = Long.parseLong(id);
		offersService.deleteOffer(idToDelete);
	    }
	}
	return "redirect:/offer/list";
    }

    // ____________________- LIST ___________________--//

    /*
     * returns the existing offers
     */
    @RequestMapping("/offer/list")
    public String getList(Model model, Pageable pageable, Principal principal,
	    @RequestParam(value = "", required = false) String searchText) {
	String email = principal.getName();
	User user = usersService.getUserByEmail(email);
	Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
	if (searchText != null && !searchText.isEmpty()) {
	    offers = offersService.searchOffersByTitle(pageable, searchText);
	} else {
	    offers = offersService.getOffersForUser(pageable, user);
	}
	model.addAttribute("userMoney", user.getMoneySum());
	model.addAttribute("offerList", offers.getContent());
	model.addAttribute("page", offers);
	return "offer/list";
    }

    // _________________________ HOME _____________________//

    /**
     * returns all the existing offers in the system
     * 
     * @param model
     * @param pageable
     * @param principal
     * @param searchText
     * @return
     */
    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model, Pageable pageable, Principal principal,
	    @RequestParam(value = "", required = false) String searchText) {
	String email = principal.getName();
	User user = usersService.getUserByEmail(email);
	Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());

	if (searchText != null && !searchText.isEmpty()) {
	    offers = offersService.searchOffersByTitle(pageable, searchText);
	} else {
	    offers = offersService.getOffers(pageable);
	}

	model.addAttribute("userMoney", user.getMoneySum());
	model.addAttribute("offerList", offers.getContent());
	model.addAttribute("page", offers);

	return "home";
    }

    // _________________________ UPDATE _______________________//
    /**
     * Actualiza la tabla de ofertas a traves del boton
     * 
     * @param model
     * @return
     */
    @RequestMapping("/home/update")
    public String updateList(Model model, Pageable pageable, Principal principal) {
	Page<Offer> offers = offersService.getOffers(pageable);
	model.addAttribute("offerList", offers.getContent());
	return "home :: tableOffers";
    }

    // _________________________ BUY _______________________//

    /**
     * sets the offer as bought and changes the button to bought
     * 
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/home/{id}/available", method = RequestMethod.GET)
    public String setAvailableFalse(Model model, @PathVariable Long id) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	if (offersService.setAvailable(activeUser, false, id)) {
	    return "redirect:/home";
	} else
	    return "error/buying";
    }

    // _________________________ HIGHLIGHT _______________________//

    /**
     * sets the offer as destacado and changes the button to destacado
     * 
     * @param id
     * @return
     */
    @RequestMapping("/offer/highlight/{id}")
    public String highlight(@PathVariable Long id) {
	Offer offer = offersService.getOffer(id);
	if (offer.getUser().getMoneySum() >= 20) {
	    offersService.updateHighlight(id);
	    return "redirect:/offer/list";
	} else
	    return "error/highlighting";
    }
}

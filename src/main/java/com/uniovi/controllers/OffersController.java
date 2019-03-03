package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

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
@Controller // al lugar de Rest para interactuar con las vistas
public class OffersController {

    @Autowired // Inyectar el servicio
    private OffersService offersService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private HttpSession httpSession;

    /**
     * Añadimos el POST Al lugar de request param, pasamos un parametro de la
     * entidad Offer
     * 
     * @return
     */
    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String setOffer(@ModelAttribute Offer offer) {
	offersService.addOffer(offer);
	return "redirect:/offer/list";
    }

    @RequestMapping(value = "/offer/add")
    public String getOffer(Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	model.addAttribute("user", activeUser);
	return "offer/add";
    }

    /*
     * incluimos una función por cada URL a la que va a responder el controlador,
     */
    @RequestMapping("/offer/list")
    public String getList(Model model, Pageable pageable, Principal principal,
	    @RequestParam(value = "", required = false) String searchText) {
	String email = principal.getName();
	User user = usersService.getUserByEmail(email);
	Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
	if (searchText != null && !searchText.isEmpty()) {
	    // offers = offersService.searchOffersByDescriptionAndNameForUser(pageable,
	    // searchText, user);
	} else {
	    offers = offersService.getOffersForUser(pageable, user);
	}
	model.addAttribute("offerList", offers.getContent());
	model.addAttribute("page", offers);
	return "offer/list";
    }

    /**
     * Actualiza la tabla de notas a traves del boton
     * 
     * @param model
     * @return
     */
    @RequestMapping("/offer/list/update")
    public String updateList(Model model, Pageable pageable, Principal principal) {
	String email = principal.getName();
	User user = usersService.getUserByEmail(email);
	Page<Offer> offers = offersService.getOffersForUser(pageable, user);
	model.addAttribute("offerList", offers.getContent());
	return "offer/list :: tableOffers";
    }

    /**
     * al lugar de ../offer/details?id=4 lo incluimos en el path como path variable
     * y no requestParam para que sea ../offer/details/4
     * 
     * @param id
     * @return
     */
    @RequestMapping("/offer/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
	model.addAttribute("offer", offersService.getOffer(id));
	return "offer/details";
    }

    @RequestMapping("/offer/delete/{id}")
    public String deleteOffer(@PathVariable Long id) {
	offersService.deleteOffer(id);
	// return "se ha borrado la nota con el id :" + id; <- intenta devolverlo como
	// fichero
	return "redirect:/offer/list";
    }

    @RequestMapping(value = "/offer/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
	model.addAttribute("offer", offersService.getOffer(id));
	model.addAttribute("usersList", usersService.getUsers());
	return "offer/edit";
    }

}

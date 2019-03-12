package com.uniovi.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.uniovi.entities.Message;
import com.uniovi.entities.User;
import com.uniovi.services.MessagesService;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

/**
 * 
 * @version $Id$
 */
@Controller // al lugar de Rest para interactuar con las vistas
public class MessagesController {

	@Autowired
	private MessagesService messagesService;

	@Autowired // Inyectar el servicio
	private OffersService offersService;

	@Autowired // Inyectar el servicio
	private UsersService usersService;

	@SuppressWarnings("unused")
	@Autowired
	private HttpSession httpSession;

	// ___________________ ADD ___________________--//

	/**
	 * Añadimos el POST Al lugar de request param, pasamos un parametro de la
	 * entidad Offer
	 * 
	 * @return
	 */
	@RequestMapping(value = "/message/send", method = RequestMethod.GET)
	public String sendMessage() {
		return "message/send";
	}
//
//	@RequestMapping(value = "/message/add")
//	public String getOffer(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String email = auth.getName();
//		User activeUser = usersService.getUserByEmail(email);
//		model.addAttribute("user", activeUser);
//		model.addAttribute("userMoney", activeUser.getMoneySum());
//		return "offer/add";
//	}
//
//	// ____________________- DELETE ___________________--//
//
//	@RequestMapping("/message/delete/{id}")
//	public String deleteOffer(@PathVariable Long id) {
//		offersService.deleteOffer(id);
//		return "redirect:/message/list";
//	}
//
//	@PostMapping("/message/list")
//	public String delete(@RequestParam("idChecked") List<String> idOffers) {
//		if (idOffers != null) {
//			for (String id : idOffers) {
//				Long idToDelete = Long.parseLong(id);
//				offersService.deleteOffer(idToDelete);
//			}
//		}
//		messagesService.addMessage(new Message());
//		return "message/list";
//	}
//
//	// ____________________- LIST ___________________--//

	/*
	 * incluimos una función por cada URL a la que va a responder el controlador,
	 */
	@RequestMapping("/message/list")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {

		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		model.addAttribute("userMoney", user.getMoneySum());
		return "message/list";
	}
//
//	// _________________________ HOME _____________________//
//
//	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
//	public String home(Model model, Pageable pageable, Principal principal,
//			@RequestParam(value = "", required = false) String searchText) {
//		String email = principal.getName();
//		User user = usersService.getUserByEmail(email);
//		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
//
//		if (searchText != null && !searchText.isEmpty()) {
//			offers = offersService.searchOffersByTitle(pageable, searchText);
//		} else {
//			offers = offersService.getOffers(pageable);
//		}
//
//		model.addAttribute("userMoney", user.getMoneySum());
//		model.addAttribute("messageList", offers.getContent());
//		model.addAttribute("page", offers);
//
//		return "home";
//	}
//
//	// _________________________ UPDATE _______________________//
//	/**
//	 * Actualiza la tabla de notas a traves del boton
//	 * 
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping("/home/update")
//	public String updateList(Model model, Pageable pageable, Principal principal) {
//		Page<Offer> offers = offersService.getOffers(pageable);
//		model.addAttribute("messageList", offers.getContent());
//		return "home :: tableOffers";
//	}

	@RequestMapping(value = "/home/{id}/message", method = RequestMethod.GET)
	public String sendMessage(Model model, @PathVariable Long id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		if (messagesService.createNewMessage(user, offersService.getOffer(id), id)) {
			return "redirect:/message/send";
		} else
			return "buying";
	}

	@RequestMapping(value = "/message/send", method = RequestMethod.POST)
	public String addNewMessage(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String messageToSend) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		
//		if (messagesService.createNewMessage(user, offersService.getOffer(id), id))
//			return "redirect:/message/send";
		
		return "redirect:/message/send";
	}
}

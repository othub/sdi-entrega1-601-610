package com.uniovi.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.MessagesService;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

/**
 * 
 * @version $Id$
 */
@Controller
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

    // ___________________ SEND MESSAGES ___________________--//

    /**
     * adds a message
     * 
     * @return
     */
    @RequestMapping(value = "/message/chat", method = RequestMethod.POST)
    public String addMessage(@ModelAttribute Message message) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	message.setSender(activeUser);
	messagesService.addMessage(message);
	return "redirect:/message/chat";
    }

    /**
     * returns the html of message/chat
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/message/chat")
    public String getMessages(Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	model.addAttribute("offersList", offersService.getOffersListForMessages());
	model.addAttribute("userMoney", activeUser.getMoneySum());
	model.addAttribute("messagesList", messagesService.getMessagesForUser(activeUser));
	return "message/chat";
    }

    // ___________________ SEND MESSAGES ___________________--//

    /**
     * @param model
     * @return the list of user's messages in the message/list.html
     */
    @RequestMapping(value = "/message/list")
    public String listMessages(Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	List<Offer> offers = offersService.getOffersListForMessagesFor(activeUser);
	model.addAttribute("userMoney", activeUser.getMoneySum());
	model.addAttribute("offersList", offers);
	return "message/list";
    }

    // ___________________ DELETE MESSAGES ___________________--//

    /**
     * returns the list of user's messages that he wishes to delete
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/message/delete")
    public String deleteMessages(Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	List<Offer> offers = offersService.getOffersListForMessagesFor(activeUser);
	model.addAttribute("userMoney", activeUser.getMoneySum());
	model.addAttribute("offersList", offers);
	return "message/delete";
    }

    /**
     * @param idMessages of the messages selected that will be deleted
     * @return
     */
    @PostMapping("/message/delete")
    public String delete(@RequestParam("idChecked") List<String> idMessages) {
	if (idMessages != null) {
	    for (String id : idMessages) {
		Long idToDelete = Long.parseLong(id);
		messagesService.deleteMessage(idToDelete);
	    }
	}
	return "redirect:/message/delete";
    }

}

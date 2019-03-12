package com.uniovi.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.ProductBought;
import com.uniovi.entities.User;
import com.uniovi.repositories.MessagesRepository;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.ProductBoughtRepository;
import com.uniovi.repositories.UsersRepository;

/**
 * gestionar todo lo relativo a la lógica de negocio de las Notas. Los servicios
 * funcionan internamente como Beans.Al lanzar el proyecto se crea
 * automáticamente un Bean por cada servicio
 * 
 * @version $Id$
 */
@Service
public class MessagesService {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private MessagesRepository messagesRepository;

	@Autowired
	private OffersRepository offersRepository;

//	@Autowired
//	private ProductBoughtRepository offersOwnedRepository;
//
//	public Offer getOffer(Long id) {
//		@SuppressWarnings("unchecked")
//		Set<Offer> consultedList = (Set<Offer>) httpSession.getAttribute("consultedList");
//		if (consultedList == null) {
//			consultedList = new HashSet<Offer>();
//		}
//		Offer offerObtained = offersRepository.findById(id).get();
//
//		consultedList.add(offerObtained);
//
//		httpSession.setAttribute("consultedList", consultedList);
//		return offerObtained;
//	}
//
	public void addMessage(Message message) {
		messagesRepository.save(message);
	}
//
//	public void deleteOffer(Long id) {
//		offersRepository.deleteById(id);
//	}
//
//	public Page<Offer> getOffersForUser(Pageable pageable, User user) {
//		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
//		if (user.getRole().equals("ROLE_STANDARD")) {
//			offers = offersRepository.findAllByUser(pageable, user);
//		}
//		// all offers
//		if (user.getRole().equals("ROLE_ADMIN")) {
//			offers = getOffers(pageable);
//		}
//		return offers;
//	}
//
//	public Page<Offer> getOffers(Pageable pageable) {
//		Page<Offer> offers = offersRepository.findAll(pageable);
//		return offers;
//	}
//
//	/**
//	 * @param pageable
//	 * @param searchText
//	 * @return
//	 */
//	public Page<Offer> searchOffersByTitle(Pageable pageable, String searchText) {
//		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
//		searchText = "%" + searchText + "%";
//		offers = offersRepository.searchOfferByTitle(pageable, searchText);
//		return offers;
//
//	}

	/**
	 * @param isAvailable
	 * @param id
	 */
	public boolean createNewMessage(User user, Offer offer, Long id) {
		if (!user.equals(offer.getUser())) {
			Message newMessage = new Message(user, offer.getUser(), offer);
			this.addMessage(newMessage);
			return true;
		}
		return false;
	}

	/**
	 * @param sender
	 * @param receiver
	 */
	public Message getOffersForUser(User sender, User receiver) {
		return messagesRepository.findMessageByUser(sender, receiver);
	}

}

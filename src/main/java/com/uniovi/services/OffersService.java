package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
public class OffersService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private OffersRepository offersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProductBoughtRepository offersOwnedRepository;

    public final static int HIGHLIGHT_PRICE = 20;

    /**
     * @param id of the offer
     * @return the offer
     */
    public Offer getOffer(Long id) {
	@SuppressWarnings("unchecked")
	Set<Offer> consultedList = (Set<Offer>) httpSession.getAttribute("consultedList");
	if (consultedList == null) {
	    consultedList = new HashSet<Offer>();
	}
	Offer offerObtained = offersRepository.findById(id).get();

	consultedList.add(offerObtained);

	httpSession.setAttribute("consultedList", consultedList);
	return offerObtained;
    }

    /**
     * If it's highlighted, 20 euros will be deducted from the user's money
     * 
     * @param offer that will be added in the repository
     */
    public void addOffer(Offer offer) {
	if (offer.isHighlighted)
	    offer.getUser().setMoneySum(offer.getUser().getMoneySum() - HIGHLIGHT_PRICE);
	offersRepository.save(offer);
    }

    /**
     * @param id of the deleted offer
     */
    public void deleteOffer(Long id) {
	offersRepository.deleteById(id);
    }

    /**
     * @return the offer's list that will be shown in the message htmls
     */
    public List<Offer> getOffersListForMessages() {
	List<Offer> offers = new ArrayList<Offer>();
	offersRepository.findAll().forEach(offers::add);
	return offers;
    }

    /**
     * @param user the user's offers
     * @return
     */
    public List<Offer> getOffersListForMessagesFor(User user) {
	List<Offer> offers = new ArrayList<Offer>();
	for (Offer o : offersRepository.findAll()) {
	    for (Message m : o.getMessagesExchanged()) { // returns the correct messages in which the user is part of
		if (m.getReceiver().getEmail().equals(user.getEmail())
			|| m.getSender().getEmail().equals(user.getEmail())) {
		    offers.add(o);
		}
	    }
	}
	List<Offer> finalList = new ArrayList<>(new HashSet<>(offers));
	// finalList.forEach(o -> o.getMessagesExchanged()
	// .forEach(m -> System.err.println("Msg in delete is: " + m.getId() +
	// m.getMessageText())));
	return finalList;
    }

    /**
     * Returns the user's personal offers
     * 
     * @param pageable
     * @param user
     * @return
     */
    public Page<Offer> getOffersForUser(Pageable pageable, User user) {
	Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
	if (user.getRole().equals("ROLE_STANDARD")) {
	    offers = offersRepository.findAllByUser(pageable, user);
	}
	return offers;
    }

    /**
     * Returns all the registred offers
     * 
     * @param pageable
     * @return
     */
    public Page<Offer> getOffers(Pageable pageable) {
	Page<Offer> offers = offersRepository.findAllSorted(pageable);
	return offers;
    }

    /**
     * @param pageable
     * @param searchText
     * @return
     */
    public Page<Offer> searchOffersByTitle(Pageable pageable, String searchText) {
	Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
	searchText = "%" + searchText + "%";
	offers = offersRepository.searchOfferByTitle(pageable, searchText);
	return offers;
    }

    /**
     * Sets the offer as bought and adds it in the list of offers bought of the user
     * 
     * @param isAvailable
     * @param id
     */
    public boolean setAvailable(User user, boolean isAvailable, Long id) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	Offer offer = offersRepository.findById(id).get();
	if (!offer.getUser().getEmail().equals(email)) { // no comprar producto propio
	    if (user.getMoneySum() >= offer.getAmount()) { // si tiene suficiente dinero
		offersRepository.updateAvailable(isAvailable, id);
		double rest = user.getMoneySum() - offer.getAmount();
		usersRepository.updateUserAmount(rest, user.getId());
		offersOwnedRepository.save(new ProductBought(offer.getTitle(), offer.getDescription(),
			offer.getAmount(), user, offer.getUser().getEmail()));
		return true;
	    }
	}
	return false;
    }

    /**
     * sets the offers as highlighted and deduct 20 euros of the user's money
     * 
     * @param id
     */
    public void updateHighlight(Long id) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	User user = usersRepository.findByEmail(email);
	Offer offer = offersRepository.findById(id).get();
	if (!offer.isHighlighted()) {
	    offersRepository.updateHighlight(true, id);
	    double rest = user.getMoneySum() - HIGHLIGHT_PRICE;
	    usersRepository.updateUserAmount(rest, user.getId());
	}
    }

}

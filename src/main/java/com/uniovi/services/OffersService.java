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

    public void addOffer(Offer offer) {
	if (offer.isHighlighted)
	    offer.getUser().setMoneySum(offer.getUser().getMoneySum() - HIGHLIGHT_PRICE);
	offersRepository.save(offer);
    }

    public void deleteOffer(Long id) {
	offersRepository.deleteById(id);
    }

    public List<Offer> getOffersListForMessages() {
	List<Offer> offers = new ArrayList<Offer>();
	offersRepository.findAll().forEach(offers::add);
	return offers;
    }

    public List<Offer> getOffersListForMessages(User user) {
	List<Offer> offers = new ArrayList<Offer>();
	for (Offer o : offersRepository.findAll()) {
	    for (Message m : o.getMessagesExchanged()) {
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

    public Page<Offer> getOffersForUser(Pageable pageable, User user) {
	Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
	if (user.getRole().equals("ROLE_STANDARD")) {
	    offers = offersRepository.findAllByUser(pageable, user);
	}
	return offers;
    }

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
     * @param isAvailable
     * @param id
     */
    public boolean setAvailable(User user, boolean isAvailable, Long id) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = auth.getName();
	Offer offer = offersRepository.findById(id).get();
	if (!offer.getUser().getEmail().equals(email)) { // no comprar producto propio
	    if (user.getMoneySum() >= offer.getAmount()) {
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

	System.err.println("NO SE PUEDE DESTACAR LA OFERTA");
    }

}

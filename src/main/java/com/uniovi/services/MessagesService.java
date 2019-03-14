package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.MessagesRepository;

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
    private MessagesRepository messagesRepository;

    public void addMessage(Message message) {

	message.setReceiver(message.getOffer().getUser());

	message.getSender().getMessagesSent().add(message);
	message.getReceiver().getMessagesReceived().add(message);

	messagesRepository.save(message);
	Offer ofr = message.getOffer();
	ofr.getMessagesExchanged().add(message);

	for (Message o : message.getOffer().getMessagesExchanged()) {
	    System.err.print("msg in offer is :" + o);
	}
    }

    /**
     * @param activeUser
     * @return
     */
    public List<Message> getMessagesForUser(User activeUser) {
	return messagesRepository.findMessageByUser(activeUser);
    }

    /**
     * @param message
     * @return
     */
    public List<Message> getConversationForUser(User user) {
	List<Message> received = new ArrayList<Message>(user.getMessagesReceived());
	List<Message> sent = new ArrayList<Message>(user.getMessagesSent());

	List<Message> full = new ArrayList<Message>(received);
	full.addAll(sent);

	for (Message msg : full) {
	    System.err.println(msg);
	}

	return full;
    }

}

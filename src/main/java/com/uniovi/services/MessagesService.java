package com.uniovi.services;

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

	/**
	 * saves the message in the repository and adds it in the list of messages of
	 * the offer and the users concerned
	 * 
	 * @param message
	 */
	public void addMessage(Message message) {
		message.setReceiver(message.getOffer().getUser());
		message.getSender().getMessagesSent().add(message);
		message.getReceiver().getMessagesReceived().add(message);

		messagesRepository.save(message);

		Offer ofr = message.getOffer();
		ofr.getMessagesExchanged().add(message);

	}

	/**
	 * Returns the user's messages
	 * 
	 * @param activeUser
	 * @return
	 */
	public List<Message> getMessagesForUser(User activeUser) {
		return messagesRepository.findMessageByUser(activeUser);
	}

	/**
	 * @param id of the message
	 * @return the message
	 */
	public Message getMessage(Long id) {
		return messagesRepository.findById(id).get();
	}

	/**
	 * @param id of the deleted message
	 */
	public void deleteMessage(Long id) {
		messagesRepository.deleteMessage(id);
	}

}

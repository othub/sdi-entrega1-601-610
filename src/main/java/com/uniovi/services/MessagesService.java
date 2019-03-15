package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.MessagesRepository;
import com.uniovi.repositories.OffersRepository;
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
    private MessagesRepository messagesRepository;

    @Autowired
    private OffersRepository offersRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void addMessage(Message message) {

	message.setReceiver(message.getOffer().getUser());

	message.getSender().getMessagesSent().add(message);
	message.getReceiver().getMessagesReceived().add(message);

	messagesRepository.save(message);
	Offer ofr = message.getOffer();
	ofr.getMessagesExchanged().add(message);

    }

    /**
     * @param activeUser
     * @return
     */
    public List<Message> getMessagesForUser(User activeUser) {
	return messagesRepository.findMessageByUser(activeUser);
    }

    /**
     * @param id
     */
    public void deleteMessage(Long id) {
	System.err.println("msg to delete in service is: " + id);
	messagesRepository.findById(id).get().getOffer().getMessagesExchanged()
		.remove(messagesRepository.findById(id).get());
	messagesRepository.findById(id).get().getSender().getMessagesReceived()
		.remove(messagesRepository.findById(id).get());
	messagesRepository.findById(id).get().getSender().getMessagesSent()
		.remove(messagesRepository.findById(id).get());
	messagesRepository.findById(id).get().getReceiver().getMessagesReceived()
		.remove(messagesRepository.findById(id).get());
	messagesRepository.findById(id).get().getReceiver().getMessagesSent()
		.remove(messagesRepository.findById(id).get());

	messagesRepository.deleteMessage(id);
    }

}

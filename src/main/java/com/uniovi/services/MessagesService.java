package com.uniovi.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Message;
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
    private HttpSession httpSession;

    @Autowired
    private MessagesRepository messagesRepository;

    public void addMessage(Message message) {
	message.setReceiver(message.getOffer().getUser());
	messagesRepository.save(message);
	System.err.println("el mensaje guardado es: " + message);
    }

    /**
     * @param activeUser
     * @return
     */
    public List<Message> getMessagesForUser(User activeUser) {
	return messagesRepository.findMessageByUser(activeUser);
    }
}

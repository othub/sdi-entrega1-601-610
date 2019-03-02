package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.services.MarksService;
import com.uniovi.services.UsersService;

/**
 * n la clase, esto indica que la clase es un controlador (Rest) responde a
 * peticiones Rest.
 * 
 * @version $Id$
 */
/**
 * 
 * @version $Id$
 */
@Controller // al lugar de Rest para interactuar con las vistas
public class MarksController {

    @Autowired // Inyectar el servicio
    private MarksService marksService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private HttpSession httpSession;

    /*
     * incluimos una función por cada URL a la que va a responder el controlador,
     */

    @RequestMapping("/mark/list")
    public String getList(Model model, Pageable pageable, Principal principal,
	    @RequestParam(value = "", required = false) String searchText) {
	// añadir sesion
	String email = principal.getName();
	User user = usersService.getUserByEmail(email);
	Page<Mark> marks = new PageImpl<Mark>(new LinkedList<Mark>());
	if (searchText != null && !searchText.isEmpty()) {
	    marks = marksService.searchMarksByDescriptionAndNameForUser(pageable, searchText, user);
	} else {
	    marks = marksService.getMarksForUser(pageable, user);
	}
	model.addAttribute("markList", marks.getContent());
	model.addAttribute("page", marks);
	return "mark/list";
    }

    /**
     * Actualiza la tabla de notas a traves del boton
     * 
     * @param model
     * @return
     */
    @RequestMapping("/mark/list/update")
    public String updateList(Model model, Pageable pageable, Principal principal) {
	String email = principal.getName();
	User user = usersService.getUserByEmail(email);
	Page<Mark> marks = marksService.getMarksForUser(pageable, user);
	model.addAttribute("markList", marks.getContent());
	return "mark/list :: tableMarks";
    }

    /**
     * Añadimos el POST Al lugar de request param, pasamos un parametro de la
     * entidad Mark
     * 
     * @return
     */
    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(@ModelAttribute Mark mark) {
	marksService.addMark(mark);
	// return "Nota añadida correctamente"; <-intenta devolver un fichero llamado
	return "redirect:/mark/list";
    }

    /**
     * al lugar de ../mark/details?id=4 lo incluimos en el path como path variable y
     * no requestParam para que sea ../mark/details/4
     * 
     * @param id
     * @return
     */
    @RequestMapping("/mark/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
	model.addAttribute("mark", marksService.getMark(id));
	return "mark/details";
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
	marksService.deleteMark(id);
	// return "se ha borrado la nota con el id :" + id; <- intenta devolverlo como
	// fichero
	return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/add")
    public String getMark(Model model) {
	model.addAttribute("usersList", usersService.getUsers());
	return "mark/add";
    }

    @RequestMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
	model.addAttribute("mark", marksService.getMark(id));
	model.addAttribute("usersList", usersService.getUsers());
	return "mark/edit";
    }

    @RequestMapping(value = "/mark/edit/{id}", method = RequestMethod.POST)
    public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Mark mark) {
	Mark original = marksService.getMark(id);
	// modificar solo score y description
	original.setScore(mark.getScore());
	original.setDescription(mark.getDescription());
	marksService.addMark(original);
	return "redirect:/mark/details/" + id;
    }

    @RequestMapping(value = "/mark/{id}/resend", method = RequestMethod.GET)
    public String setResendTrue(Model model, @PathVariable Long id) {
	marksService.setMarkResend(true, id);
	return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/{id}/noresend", method = RequestMethod.GET)
    public String setResendFalse(Model model, @PathVariable Long id) {
	marksService.setMarkResend(false, id);
	return "redirect:/mark/list";
    }

}

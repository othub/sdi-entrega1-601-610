package com.uniovi.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.ProductBought;
import com.uniovi.entities.User;
import com.uniovi.repositories.ProductBoughtRepository;

/**
 * gestionar todo lo relativo a la lógica de negocio de las Notas. Los servicios
 * funcionan internamente como Beans.Al lanzar el proyecto se crea
 * automáticamente un Bean por cada servicio
 * 
 * @version $Id$
 */
@Service
public class ProductBoughtService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ProductBoughtRepository productsRepository;

    public ProductBought getProductBought(Long id) {
	@SuppressWarnings("unchecked")
	Set<ProductBought> consultedList = (Set<ProductBought>) httpSession.getAttribute("consultedList");
	if (consultedList == null) {
	    consultedList = new HashSet<ProductBought>();
	}
	ProductBought productObtained = productsRepository.findById(id).get();

	consultedList.add(productObtained);

	httpSession.setAttribute("consultedList", consultedList);
	return productObtained;
    }

    public void addProductBought(ProductBought product) {
	productsRepository.save(product);
    }

    public void deleteProductBought(Long id) {
	productsRepository.deleteById(id);
    }

    public Page<ProductBought> getProductBoughtsForUser(Pageable pageable, User user) {
	Page<ProductBought> products = new PageImpl<ProductBought>(new LinkedList<ProductBought>());
	products = productsRepository.findAllByUser(pageable, user);

	return products;
    }

    public Page<ProductBought> getProductBoughts(Pageable pageable) {
	Page<ProductBought> products = productsRepository.findAll(pageable);
	return products;
    }

}

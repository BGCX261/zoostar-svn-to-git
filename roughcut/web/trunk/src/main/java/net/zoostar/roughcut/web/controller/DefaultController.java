package net.zoostar.roughcut.web.controller;

import java.security.Principal;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
public class DefaultController extends MultiActionController {
	
	static final Logger log = LoggerFactory.getLogger(DefaultController.class);
	
	@RequestMapping(value="/")
	public ModelAndView handleHome(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Invoked: /");
		Principal user = request.getUserPrincipal();
		log.info("User logged in: [{}]", user);
		log.info("Returning View: home.jsp");
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/admin/clearCache")
	public ModelAndView clearCache() {
		log.info("Clearing entity cache...");
		EntityManagerFactory emFactory = getApplicationContext().getBean(EntityManagerFactory.class);
		emFactory.getCache().evictAll();
		log.warn("Entity cache cleared!");
		return new ModelAndView("home");
	}
}

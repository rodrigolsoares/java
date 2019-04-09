package br.com.petruber.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomePageController {

	private static final Logger log = Logger.getLogger(HomePageController.class);


	@RequestMapping("/homePage")
	public ModelAndView homePage(HttpSession session, HttpServletRequest request) throws Throwable {

		try {

			return new ModelAndView("/homePage");

		} catch (Exception e) {
			log.error("Erro ao abrir a tela inicial", e);
			return null;
		}
	}
	
	

}

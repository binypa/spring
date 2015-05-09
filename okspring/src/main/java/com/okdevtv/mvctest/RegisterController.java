package com.okdevtv.mvctest;

import java.util.Locale;

import okjsp.User;
import okjsp.UserDAO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String register(Locale locale, Model model){
		
		return "register";
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String registerFinished(Locale locale, @ModelAttribute("user") User user, Model model){
		
		// check password and confirmPassword
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			model.addAttribute("msg", "password mismatched!");
			return "register";
		}else{
			// id dup check
			// save()
			UserDAO.save(user);
			// to finish page
			//request.getRequestDispatcher("register.jsp").forward(request, response);
			
			return "redirect:/registerFinished.do";
		}
	}
	
	@RequestMapping(value="/registerFinished.do")
	public String registerFinished(Model model) {
		return "registerFinished";
	}
}

package com.example.controller;

import javax.validation.Valid;

import com.example.model.Category;
import com.example.model.Role;
import com.example.model.SubCategory;
import com.example.repository.CategoryRepository;
import com.example.repository.RoleRepository;
import com.example.repository.SubCategoryRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.User;
import com.example.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;


	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}


	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, Model model, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}
	
	/*@RequestMapping(value={"/","/admin/home"}, method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

		List<Category> category=categoryRepository.findAll();

		modelAndView.addObject("category",category);
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}*/


	/*@RequestMapping(name = "/admin/user",method = RequestMethod.GET)
	public String  getAllUsers(Model model){
		List<User> userList=userRepository.findAll();

		List<Category> category=categoryRepository.findAll();

		model.addAttribute("category",category);

		*//*for (User user : userList){
					user.setRoles(userService.findByRoles());
			System.out.println("---------------------------"+user);
		}*//*
		model.addAttribute("userList",userList);
		return "/admin/user";
	}*/


	@RequestMapping(value="/admin/category", method = RequestMethod.GET)
	public String category(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		model.addAttribute("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");

		List<Category> category=categoryRepository.findAll();
		model.addAttribute("category",category);
		/*List<SubCategory> subCategories=new ArrayList<SubCategory>();
		for (Category category1:category) {
			SubCategory subCategory=subCategoryRepository.getOne(category1.getId());
			subCategories.add(subCategory);
		}*/
		/*List<SubCategory> subCategories=subCategoryRepository.findAll();
		for (Category category1: category ) {
			for (SubCategory subCategory:subCategories) {
				if (category1.getId() == subCategory.getChildSubCategory().getId()){

				}
			}
		}
		model.addAttribute("subcategory",subCategories);*/



		return "admin/category";
	}

}

package com.websystique.springmvc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.websystique.springmvc.model.Cart;
import com.websystique.springmvc.model.CustomerInfo;
import com.websystique.springmvc.model.Item;
import com.websystique.springmvc.model.ItemInCart;
import com.websystique.springmvc.model.OrderHeader;
import com.websystique.springmvc.model.OrderItem;
import com.websystique.springmvc.model.OrderItemId;
import com.websystique.springmvc.model.ProductOrder;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserProfile;
import com.websystique.springmvc.service.CartUtil;
import com.websystique.springmvc.service.ItemService;
import com.websystique.springmvc.service.OrderHeaderService;
import com.websystique.springmvc.service.OrderItemService;
import com.websystique.springmvc.service.UserProfileService;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.UserServiceImpl;
import com.websystique.springmvc.service.mail.OrderService;



@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	@Autowired
	ItemService itemService;
	@Autowired
	UserProfileService userProfileService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	OrderHeaderService orderHeaderService;
	@Autowired
	OrderItemService orderItemService;
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView visitHome() {
		return new ModelAndView("undex");
	}
	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public ModelAndView visitUser(Locale locale) {
		ModelAndView model = new ModelAndView("userUndex");
		model.addObject("title", "User Control Panel");
		model.addObject("message", "This page demonstrates how to use Spring security.");
		String messageOperation = messageSource.getMessage("operation.message", null, locale);
		model.addObject("operation", messageOperation);
		String messageGoTo = messageSource.getMessage("goTo.message", null, locale);
		model.addObject("Goto", messageGoTo);
		model.addObject("shopping", "shopping Platform");
		
		return model;
    }	
	@RequestMapping(value="/adminundex", method = RequestMethod.GET)
	public ModelAndView visitAdmin(Locale locale) {
		ModelAndView model = new ModelAndView("adminundex");
		model.addObject("title", "Admministrator Control Panel");
		model.addObject("message", "This page demonstrates how to use Spring security.");
		String messageOperation = messageSource.getMessage("operation.message", null, locale);
		model.addObject("operation", messageOperation);
		String messageGoTo = messageSource.getMessage("goTo.message", null, locale);
		model.addObject("Goto", messageGoTo);
		
		return model;
	}
	 @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	    public String accessDeniedPage(ModelMap model) {
	        model.addAttribute("user", getPrincipal());
	        return "accessDenied";
	    }
	 private String getPrincipal(){
	        String userName = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	        if (principal instanceof UserDetails) {
	            userName = ((UserDetails)principal).getUsername();
	        } else {
	            userName = principal.toString();
	        }
	        return userName;
	    }
	@RequestMapping(value = {"/listitems" }, method = RequestMethod.GET)
	public String listItems(ModelMap model, Locale locale, HttpServletRequest request) {
		String messageNameItem = messageSource.getMessage("itemName.message", null, locale);
		model.addAttribute("nameItem", messageNameItem);
		String messageDescriptionItem = messageSource.getMessage("itemDescription.message", null, locale);
		model.addAttribute("descriptionItem", messageDescriptionItem);
		String messagePriceItem = messageSource.getMessage("itemPrice.message", null, locale);
		model.addAttribute("priceItem", messagePriceItem);
		String messageListofItems = messageSource.getMessage("listofItems.message", null, locale);
		model.addAttribute("ListofItems", messageListofItems);
		String messageEdit = messageSource.getMessage("edit.message", null, locale);
		model.addAttribute("edit", messageEdit);
		String messageDelete = messageSource.getMessage("delete.message", null, locale);
		model.addAttribute("delete", messageDelete);
		String messageAddNewItem = messageSource.getMessage("addNewItem.message", null, locale);
		model.addAttribute("AddNewItem", messageAddNewItem);
		List<Item> items = itemService.findAllItems();
		model.addAttribute("items", items);
		String messageOperation = messageSource.getMessage("operation.message", null, locale);
		model.addAttribute("operation", messageOperation);
		String messageGoTo = messageSource.getMessage("goTo.message", null, locale);
		model.addAttribute("Goto", messageGoTo);
		String messageId = messageSource.getMessage("id.message", null, locale);
		model.addAttribute("id", messageId);
		String messageWelcome = messageSource.getMessage("welcome.message", null, locale);
		model.addAttribute("Welcome", messageWelcome);
		String messageLogout = messageSource.getMessage("logout.message", null, locale);
		model.addAttribute("Logout", messageLogout);
		String welcome = messageSource.getMessage("welcome.message", new Object[]{"John Doe"}, locale);
	 	String messageUser = messageSource.getMessage("user.message", null, locale);
	 	String messageOrder = messageSource.getMessage("order.message", null, locale);
		String messageItem = messageSource.getMessage("item.message", null, locale);
	 	model.addAttribute("message", welcome);
        model.addAttribute("mUser", messageUser);
        model.addAttribute("mOrder", messageOrder);
        model.addAttribute("mItem", messageItem);
		return "itemslist";
	}
	@RequestMapping(value = { "/sort-item" }, method=RequestMethod.GET)
	public String ascSortListItemsPanier(ModelMap model, HttpServletRequest request, Locale locale, @RequestParam(value="sortfield", 
	defaultValue="") String field) {
		String messageNameItem = messageSource.getMessage("itemName.message", null, locale);
		model.addAttribute("nameItem", messageNameItem);
		String messageDescriptionItem = messageSource.getMessage("itemDescription.message", null, locale);
		model.addAttribute("descriptionItem", messageDescriptionItem);
		String messagePriceItem = messageSource.getMessage("itemPrice.message", null, locale);
		model.addAttribute("priceItem", messagePriceItem);
		String messageListofItems = messageSource.getMessage("listofItems.message", null, locale);
		model.addAttribute("ListofItems", messageListofItems);
		String messageDelete = messageSource.getMessage("delete.message", null, locale);
		model.addAttribute("delete", messageDelete);
		String messageWelcome = messageSource.getMessage("welcome.message", null, locale);
		model.addAttribute("Welcome", messageWelcome);
		String messageLogout = messageSource.getMessage("logout.message", null, locale);
		model.addAttribute("Logout", messageLogout);
		String messageMyCart = messageSource.getMessage("mycart.message", null, locale);
		model.addAttribute("MyCart", messageMyCart);
		if(field.equals("DESC")) model.addAttribute("price", "ASC");
		
		else  model.addAttribute("price", "DESC");
		
		List <Item> itemsList = itemService.sortItemByPriceAscDesc(field);
		model.addAttribute("items", itemsList);
		Locale currentLocale = LocaleContextHolder.getLocale();
	    model.addAttribute("locale", currentLocale);
		Cart myCart = CartUtil.getCartInSession(request);
		Integer q = myCart.getTotalQuantity( myCart.getProducts());
		model.addAttribute("quan", q);
		model.addAttribute("articles", "items");
		String messageMyHistoryOrder=messageSource.getMessage("MyHistoryOrder.message", null, locale);
		model.addAttribute("historyOrder", messageMyHistoryOrder);
		model.addAttribute("logoEuro", " &euro;");
		BigDecimal totalPrice=new BigDecimal(0);
		BigDecimal subtotal = new BigDecimal(0);
	 	for(ItemInCart qa:myCart.getProducts()){
	 		subtotal = qa.getSubtotal(); 
	 		totalPrice = totalPrice.add(subtotal);
	 	}
	 	model.addAttribute("priceCart", totalPrice);
		return "itemslistpanier";
	}
	@RequestMapping(value = {"/listitemspanier" }, method = RequestMethod.GET)
	public String listItemsPanier(ModelMap model, Locale locale, HttpServletRequest request) {
		String messageNameItem = messageSource.getMessage("itemName.message", null, locale);
		model.addAttribute("nameItem", messageNameItem);
		String messageDescriptionItem = messageSource.getMessage("itemDescription.message", null, locale);
		model.addAttribute("descriptionItem", messageDescriptionItem);
		String messagePriceItem = messageSource.getMessage("itemPrice.message", null, locale);
		model.addAttribute("priceItem", messagePriceItem);
		String messageListofItems = messageSource.getMessage("listofItems.message", null, locale);
		model.addAttribute("ListofItems", messageListofItems);
		model.addAttribute("price", "DESC");
		String messageDelete = messageSource.getMessage("delete.message", null, locale);
		model.addAttribute("delete", messageDelete);
		String messageAddNewItem = messageSource.getMessage("addNewItem.message", null, locale);
		model.addAttribute("AddNewItem", messageAddNewItem);
		String messageId = messageSource.getMessage("id.message", null, locale);
		model.addAttribute("id", messageId);
		String messageWelcome = messageSource.getMessage("welcome.message", null, locale);
		model.addAttribute("Welcome", messageWelcome);
		String messageLogout = messageSource.getMessage("logout.message", null, locale);
		model.addAttribute("Logout", messageLogout);
		String messageMyCart = messageSource.getMessage("mycart.message", null, locale);
		model.addAttribute("MyCart", messageMyCart);
		List<Item> items = itemService.sortItemsByName();
		model.addAttribute("items", items);
		Locale currentLocale = LocaleContextHolder.getLocale();
	    model.addAttribute("locale", currentLocale);
		Cart myCart = CartUtil.getCartInSession(request);
		Integer q = myCart.getTotalQuantity( myCart.getProducts());
		BigDecimal priceMyCart = myCart.getCartPrice();
		model.addAttribute("priceCart", priceMyCart);
		model.addAttribute("quan", q);
		model.addAttribute("articles", "items");
		String messageMyHistoryOrder=messageSource.getMessage("MyHistoryOrder.message", null, locale);
		model.addAttribute("historyOrder", messageMyHistoryOrder);
		model.addAttribute("logoEuro", " &euro;");
		return "itemslistpanier";
	}
	@RequestMapping(value = {"/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model, Locale locale, HttpServletRequest request) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		String messageEdit = messageSource.getMessage("edit.message", null, locale);
		model.addAttribute("edit", messageEdit);
		String messageDelete = messageSource.getMessage("delete.message", null, locale);
		model.addAttribute("delete", messageDelete);
		String messageFirstName = messageSource.getMessage("FirstName.message", null, locale);
		model.addAttribute("FirstName", messageFirstName);
		String messageLastName = messageSource.getMessage("LastName.message", null, locale);
		model.addAttribute("LastName", messageLastName);
		String messageEmail = messageSource.getMessage("Email.message", null, locale);
		model.addAttribute("Email", messageEmail);
		String messageListofUsers = messageSource.getMessage("ListofUsers.message", null, locale);
		model.addAttribute("ListofUsers", messageListofUsers);
		String messageAddNewUser = messageSource.getMessage("AddNewUser.message", null, locale);
		model.addAttribute("AddNewUser", messageAddNewUser);
		String messageOperation = messageSource.getMessage("operation.message", null, locale);
		model.addAttribute("operation", messageOperation);
		String messageGoTo = messageSource.getMessage("goTo.message", null, locale);
		model.addAttribute("Goto", messageGoTo);
		String messageLogin = messageSource.getMessage("Login.message", null, locale);
		model.addAttribute("Login", messageLogin);
		String messageIdUser = messageSource.getMessage("idUser.message", null, locale);
		model.addAttribute("Id", messageIdUser);
		String messageWelcome = messageSource.getMessage("welcome.message", null, locale);
		model.addAttribute("Welcome", messageWelcome);
		String messageLogout = messageSource.getMessage("logout.message", null, locale);
		model.addAttribute("Logout", messageLogout);
	 	String messageUser = messageSource.getMessage("user.message", null, locale);
	 	model.addAttribute("Users", messageUser);
	 	String messageOrder = messageSource.getMessage("order.message", null, locale);
        model.addAttribute("Orders", messageOrder);
	 	String messageItem = messageSource.getMessage("item.message", null, locale);
        model.addAttribute("Items", messageItem);
		return "userslist";
	}
	@RequestMapping(value = {"/interfaceAdmin"}, method = RequestMethod.GET)
	public String displayAdminPage(ModelMap model, Locale locale) {
		
	        List<User> users = userService.findAllUsers();
			model.addAttribute("users", users);
			String messageEdit = messageSource.getMessage("edit.message", null, locale);
			model.addAttribute("edit", messageEdit);
			String messageDelete = messageSource.getMessage("delete.message", null, locale);
			model.addAttribute("delete", messageDelete);
			String messageFirstName = messageSource.getMessage("FirstName.message", null, locale);
			model.addAttribute("FirstName", messageFirstName);
			String messageLastName = messageSource.getMessage("LastName.message", null, locale);
			model.addAttribute("LastName", messageLastName);
			String messageEmail = messageSource.getMessage("Email.message", null, locale);
			model.addAttribute("Email", messageEmail);
			String messageListofUsers = messageSource.getMessage("ListofUsers.message", null, locale);
			model.addAttribute("ListofUsers", messageListofUsers);
			String messageAddNewUser = messageSource.getMessage("AddNewUser.message", null, locale);
			model.addAttribute("AddNewUser", messageAddNewUser);
			String messageOperation = messageSource.getMessage("operation.message", null, locale);
			model.addAttribute("operation", messageOperation);
			String messageGoTo = messageSource.getMessage("goTo.message", null, locale);
			model.addAttribute("Goto", messageGoTo);
			String messageLogin = messageSource.getMessage("Login.message", null, locale);
			model.addAttribute("Login", messageLogin);
			String messageIdUser = messageSource.getMessage("idUser.message", null, locale);
			model.addAttribute("Id", messageIdUser);
			String messageWelcome = messageSource.getMessage("welcome.message", null, locale);
			model.addAttribute("Welcome", messageWelcome);
			String messageLogout = messageSource.getMessage("logout.message", null, locale);
			model.addAttribute("Logout", messageLogout);
		 
		return "admin";
	}
	//affiche en lançant l'application la vue panier et items utilisateur 
	@RequestMapping(value = {"/spring"}, method = RequestMethod.GET)
	public String displayUserPanierPage(ModelMap model, Locale locale, @Valid Item item) {
		String messageItem = messageSource.getMessage("item.message", null, locale);
		model.addAttribute("Item", messageItem);
		String messageSuccess = messageSource.getMessage("success.message", null, locale);
		model.addAttribute("successMessage", messageSuccess);
		
		model.addAttribute("success", item.getName() + " "+ item.getDescription() + item.getPrice());
		String messageSuccessRegistry = messageSource.getMessage("successRegistry.message", null, locale);
		model.addAttribute("successMessageRegistry", messageSuccessRegistry);
		
		String messageListItem = messageSource.getMessage("itemsList.message", null, locale);
		model.addAttribute("itemsList", messageListItem);
		String messageGoTo = messageSource.getMessage("goTo.message", null, locale);
		model.addAttribute("Goto", messageGoTo);
		
		return "userpanier";
	}
	@RequestMapping(value = {"/user"}, method = RequestMethod.GET)
	public String displayUserAddPage(ModelMap model) {

		return "ajoutuser";
	}
	@RequestMapping(value = {"/newitem"}, method = RequestMethod.GET)
	public String newItem(ModelMap model, Locale locale) {
		Item item = new Item();
		String messageNameItem = messageSource.getMessage("itemName.message", null, locale);
		model.addAttribute("nameItem", messageNameItem);
		String messageDescriptionItem = messageSource.getMessage("itemDescription.message", null, locale);
		model.addAttribute("descriptionItem", messageDescriptionItem);
		String messagePriceItem = messageSource.getMessage("itemPrice.message", null, locale);
		model.addAttribute("priceItem", messagePriceItem);
		String messageItemRegistrationForm = messageSource.getMessage("itemRegistrationForm.message", null, locale);
		model.addAttribute("ItemRegistrationForm", messageItemRegistrationForm);
		String messageCancel = messageSource.getMessage("cancel.message", null, locale);
		model.addAttribute("cancel", messageCancel);
		String messageOr = messageSource.getMessage("or.message", null, locale);
		model.addAttribute("choix", messageOr);
		String messageUpdate = messageSource.getMessage("update.message", null, locale);
		model.addAttribute("Update", messageUpdate);
		String messageRegister = messageSource.getMessage("register.message", null, locale);
		model.addAttribute("Register", messageRegister);
		model.addAttribute("item", item);
		model.addAttribute("edit", false);
		return "ajoutitem";
	}
	@RequestMapping(value = { "/newitem" }, method = RequestMethod.POST)
	public String saveUser(@Valid Item item, BindingResult result,
			ModelMap model, Locale locale) {

		if (result.hasErrors()) {
			String messageNameItem = messageSource.getMessage("itemName.message", null, locale);
			model.addAttribute("nameItem", messageNameItem);
			String messageDescriptionItem = messageSource.getMessage("itemDescription.message", null, locale);
			model.addAttribute("descriptionItem", messageDescriptionItem);
			String messagePriceItem = messageSource.getMessage("itemPrice.message", null, locale);
			model.addAttribute("priceItem", messagePriceItem);
			String messageItemRegistrationForm = messageSource.getMessage("itemRegistrationForm.message", null, locale);
			model.addAttribute("ItemRegistrationForm", messageItemRegistrationForm);
			String messageCancel = messageSource.getMessage("cancel.message", null, locale);
			model.addAttribute("cancel", messageCancel);
			String messageOr = messageSource.getMessage("or.message", null, locale);
			model.addAttribute("choix", messageOr);
			String messageUpdate = messageSource.getMessage("update.message", null, locale);
			model.addAttribute("Update", messageUpdate);
			String messageRegister = messageSource.getMessage("register.message", null, locale);
			model.addAttribute("Register", messageRegister);
			return "ajoutitem";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
//		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
//			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
//		    result.addError(ssoError);
//			return "registration";
//		}
		
		itemService.saveItem(item);
		String messageItem = messageSource.getMessage("item.message", null, locale);
		model.addAttribute("Item", messageItem);
		String messageSuccess = messageSource.getMessage("success.message", null, locale);
		model.addAttribute("successMessage", messageSuccess);
		
		model.addAttribute("success", item.getName() + " "+ item.getDescription() + item.getPrice());
		String messageSuccessRegistry = messageSource.getMessage("successRegistry.message", null, locale);
		model.addAttribute("successMessageRegistry", messageSuccessRegistry);
		
		String messageListItem = messageSource.getMessage("itemsList.message", null, locale);
		model.addAttribute("itemsList", messageListItem);
		String messageGoTo = messageSource.getMessage("goTo.message", null, locale);
		model.addAttribute("Goto", messageGoTo);
		
		//return "success";
		return "itemsuccess";
	}
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model, Locale locale) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		String messageCancel = messageSource.getMessage("cancel.message", null, locale);
		model.addAttribute("cancel", messageCancel);
		String messageOr = messageSource.getMessage("or.message", null, locale);
		model.addAttribute("choix", messageOr);
		String messageUpdate = messageSource.getMessage("update.message", null, locale);
		model.addAttribute("Update", messageUpdate);
		String messageRegister = messageSource.getMessage("register.message", null, locale);
		model.addAttribute("Register", messageRegister);
		String messageUserRegistrationForm = messageSource.getMessage("userRegistrationForm.message", null, locale);
		model.addAttribute("UserRegistrationForm", messageUserRegistrationForm);
		String messageFirstName = messageSource.getMessage("FirstName.message", null, locale);
		model.addAttribute("FirstName", messageFirstName);
		String messageLastName = messageSource.getMessage("LastName.message", null, locale);
		model.addAttribute("LastName", messageLastName);
		String messageEmail = messageSource.getMessage("Email.message", null, locale);
		model.addAttribute("Email", messageEmail);
		String messagePassword = messageSource.getMessage("Password.message", null, locale);
		model.addAttribute("Password", messagePassword);
		String messageLogin = messageSource.getMessage("Login.message", null, locale);
		model.addAttribute("Login", messageLogin);
		
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model, Locale locale) {
		String messageUserRegistrationForm = messageSource.getMessage("userRegistrationForm.message", null, locale);
		model.addAttribute("UserRegistrationForm", messageUserRegistrationForm);
		String messageFirstName = messageSource.getMessage("FirstName.message", null, locale);
		model.addAttribute("FirstName", messageFirstName);
		String messageLastName = messageSource.getMessage("LastName.message", null, locale);
		model.addAttribute("LastName", messageLastName);
		String messagePassword = messageSource.getMessage("Password.message", null, locale);
		model.addAttribute("Password", messagePassword);
		String messageEmail = messageSource.getMessage("Email.message", null, locale);
		model.addAttribute("Email", messageEmail);
		if (result.hasErrors()) {
			String messageOr = messageSource.getMessage("or.message", null, locale);
			model.addAttribute("choix", messageOr);
			String messageRegister = messageSource.getMessage("register.message", null, locale);
			model.addAttribute("Register", messageRegister);
			String messageCancel = messageSource.getMessage("cancel.message", null, locale);
			model.addAttribute("cancel", messageCancel);
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			String messageOr = messageSource.getMessage("or.message", null, locale);
			model.addAttribute("choix", messageOr);
			String messageRegister = messageSource.getMessage("register.message", null, locale);
			model.addAttribute("Register", messageRegister);
			String messageCancel = messageSource.getMessage("cancel.message", null, locale);
			model.addAttribute("cancel", messageCancel);
			return "registration";
		}
		
		userService.saveUser(user);
		String messageItem = messageSource.getMessage("item.message", null, locale);
		model.addAttribute("Item", messageItem);
		String messageSuccess = messageSource.getMessage("success.message", null, locale);
		model.addAttribute("successMessage", messageSuccess);
		model.addAttribute("success", user.getFirstName() + " "+ user.getLastName());
		String messageSuccessRegistry = messageSource.getMessage("successRegistry.message", null, locale);
		model.addAttribute("successMessageRegistry", messageSuccessRegistry);
		//return "success";
		String messageGoTo = messageSource.getMessage("goTo.message", null, locale);
		model.addAttribute("Goto", messageGoTo);
		String messageUsersList = messageSource.getMessage("usersList.message", null, locale);
		model.addAttribute("UsersList", messageUsersList);
		return "registrationsuccess";
	}
	

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable int id, ModelMap model, Locale locale) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		String messageCancel = messageSource.getMessage("cancel.message", null, locale);
		model.addAttribute("cancel", messageCancel);
		String messageOr = messageSource.getMessage("or.message", null, locale);
		model.addAttribute("choix", messageOr);
		String messageUpdate = messageSource.getMessage("update.message", null, locale);
		model.addAttribute("Update", messageUpdate);
		String messageRegister = messageSource.getMessage("register.message", null, locale);
		model.addAttribute("Register", messageRegister);
		String messageUserRegistrationForm = messageSource.getMessage("userRegistrationForm.message", null, locale);
		model.addAttribute("UserRegistrationForm", messageUserRegistrationForm);
		String messageFirstName = messageSource.getMessage("FirstName.message", null, locale);
		model.addAttribute("FirstName", messageFirstName);
		String messageLastName = messageSource.getMessage("LastName.message", null, locale);
		model.addAttribute("LastName", messageLastName);
		String messageEmail = messageSource.getMessage("Email.message", null, locale);
		model.addAttribute("Email", messageEmail);
		String messagePassword = messageSource.getMessage("Password.message", null, locale);
		model.addAttribute("Password", messagePassword);
		String messageLogin = messageSource.getMessage("Login.message", null, locale);
		model.addAttribute("Login", messageLogin);
		return "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable int id, Locale locale) {
		
		if (result.hasErrors()) {
			return "registration";
		}

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/


		userService.updateUser(user);
		String messageGoTo = messageSource.getMessage("goTo.message", null, locale);
		model.addAttribute("Goto", messageGoTo);
		String messageUsersList = messageSource.getMessage("usersList.message", null, locale);
		model.addAttribute("UsersList", messageUsersList);
		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		return "registrationsuccess";
	}

	
	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{id}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable int id) {
		userService.deleteUserById(id);
		return "redirect:/list";
	}
	

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-item-{id}" }, method = RequestMethod.GET)
	public String editItem(@PathVariable int id, ModelMap model, Locale locale) {
		Item item = itemService.findById(id);
		model.addAttribute("item", item);
		model.addAttribute("edit", true);
		String messageEdit = messageSource.getMessage("edit.message", null, locale);
		model.addAttribute("edit", messageEdit);
		String messageNameItem = messageSource.getMessage("itemName.message", null, locale);
		model.addAttribute("nameItem", messageNameItem);
		String messageDescriptionItem = messageSource.getMessage("itemDescription.message", null, locale);
		model.addAttribute("descriptionItem", messageDescriptionItem);
		String messagePriceItem = messageSource.getMessage("itemPrice.message", null, locale);
		model.addAttribute("priceItem", messagePriceItem);
		String messageItemRegistrationForm = messageSource.getMessage("itemRegistrationForm.message", null, locale);
		model.addAttribute("ItemRegistrationForm", messageItemRegistrationForm);
		String messageCancel = messageSource.getMessage("cancel.message", null, locale);
		model.addAttribute("cancel", messageCancel);
		String messageOr = messageSource.getMessage("or.message", null, locale);
		model.addAttribute("choix", messageOr);
		String messageUpdate = messageSource.getMessage("update.message", null, locale);
		model.addAttribute("Update", messageUpdate);
		String messageRegister = messageSource.getMessage("register.message", null, locale);
		model.addAttribute("Register", messageRegister);
		model.addAttribute("item", item);
		model.addAttribute("edit", false);
		return "ajoutitem";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-item-{id}" }, method = RequestMethod.POST)
	public String editUser(@Valid Item item, BindingResult result, 
			ModelMap model, @PathVariable int id) {
		
		if (result.hasErrors()) {
			return "ajoutitem";
		}

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/


		itemService.updateItem(item);

		model.addAttribute("success", "Item " + item.getName() + " "+ item.getDescription() + item.getPrice() +" updated successfully");
		String messageListItem = messageSource.getMessage("itemsList.message", null, null);
		model.addAttribute("itemsList", messageListItem);
		return "itemsuccess";
	}
	@RequestMapping(value = { "/delete-item-{id}" }, method = RequestMethod.GET)
	public String deleteItem(@PathVariable int id) {
		itemService.deleteById(id);
		return "redirect:/listitems";
	}
	@ModelAttribute("countries")
	public List<String> initializeCountries() {

		List<String> language = new ArrayList<String>();
		language.add("english");
		language.add("french");
		return language;
	}
//	@RequestMapping(value = "/internationalisation", method = RequestMethod.GET)
//    public String index(Locale locale, Model model){
//
//        // add parametrized message from controller
//        String welcome = messageSource.getMessage("welcome.message", new Object[]{"John Doe"}, locale);
//        model.addAttribute("message", welcome);
//        
//        // obtain locale from LocaleContextHolder
//        Locale currentLocale = LocaleContextHolder.getLocale();
//        model.addAttribute("locale", currentLocale);
//
//        model.addAttribute("startMeeting", "10:30");
//        
//        return "index";
//    }
	@RequestMapping(value="/myCart-{login}", method = RequestMethod.GET)
	public String myCard(HttpServletRequest request,Model model, Locale locale,
			@PathVariable String login){
		 Cart myCart = CartUtil.getCartInSession(request);
		 for(ItemInCart p:myCart.getProducts())
			 System.out.println(p.getPrice());
		 	 String messageWelcome = messageSource.getMessage("welcome.message", null, locale);
		 	 model.addAttribute("Welcome", messageWelcome);
 			 model.addAttribute("items", myCart.getProducts());
 			 model.addAttribute("Login", login);
 			 model.addAttribute("Lyes", myCart.getTotalQuantity(myCart.getProducts()));
	 		 String messageNameItem = messageSource.getMessage("itemName.message", null, locale);
	 		 model.addAttribute("nameItem", messageNameItem);
 			 String msgQuantity = messageSource.getMessage("quantity.message", null, locale);
 			 model.addAttribute("quantity", msgQuantity);
 			 String messagePriceItem = messageSource.getMessage("itemPrice.message", null, locale);
 			 model.addAttribute("priceItem", messagePriceItem);
 			 String messagePriceTotal = messageSource.getMessage("totalPrice.message", null, locale);
			 model.addAttribute("totalPrice", messagePriceTotal);
	 		 String messageItemNumber = messageSource.getMessage("itemNumber.message", null, locale);
			 model.addAttribute("itemNumber", messageItemNumber);
			 String messageOrderMyCart = messageSource.getMessage("orderMyCart.message", null, locale);
			 model.addAttribute("OrderMyCart", messageOrderMyCart);

		return "mycart";
	}
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	public String addProduct(HttpServletRequest request,ModelMap model, 
			Locale locale, @RequestParam(value="id",defaultValue="") int id,
			@RequestParam("quantity") int q){
		if(id>0 && q>=1){
			Cart myCart = CartUtil.getCartInSession(request);
			 myCart.addProduct(itemService.findById(id), q);
		}
		else{
			//TODO
			System.out.println("error");
		}
		String messageNameItem = messageSource.getMessage("itemName.message", null, locale);
		model.addAttribute("nameItem", messageNameItem);
		String messageDescriptionItem = messageSource.getMessage("itemDescription.message", null, locale);
		model.addAttribute("descriptionItem", messageDescriptionItem);
		String messagePriceItem = messageSource.getMessage("itemPrice.message", null, locale);
		model.addAttribute("priceItem", messagePriceItem);
		String messageListofItems = messageSource.getMessage("listofItems.message", null, locale);
		model.addAttribute("ListofItems", messageListofItems);
		model.addAttribute("price", "DESC");
		String messageDelete = messageSource.getMessage("delete.message", null, locale);
		model.addAttribute("delete", messageDelete);
		String messageAddNewItem = messageSource.getMessage("addNewItem.message", null, locale);
		model.addAttribute("AddNewItem", messageAddNewItem);
		String messageId = messageSource.getMessage("id.message", null, locale);
		model.addAttribute("id", messageId);
		String messageWelcome = messageSource.getMessage("welcome.message", null, locale);
		model.addAttribute("Welcome", messageWelcome);
		String messageLogout = messageSource.getMessage("logout.message", null, locale);
		model.addAttribute("Logout", messageLogout);
		String messageMyCart = messageSource.getMessage("mycart.message", null, locale);
		model.addAttribute("MyCart", messageMyCart);
		List<Item> items = itemService.sortItemsByName();
		model.addAttribute("items", items);
		Locale currentLocale = LocaleContextHolder.getLocale();
	    model.addAttribute("locale", currentLocale);
		Cart myCart = CartUtil.getCartInSession(request);
		Integer qua = myCart.getTotalQuantity( myCart.getProducts());
		BigDecimal totalPrice=new BigDecimal(0);
		BigDecimal subtotal = new BigDecimal(0);
	 	for(ItemInCart qa:myCart.getProducts()){
	 		subtotal = qa.getSubtotal(); 
	 		totalPrice = totalPrice.add(subtotal);
	 	}
	 	model.addAttribute("quan", qua);
		model.addAttribute("articles", "items");
		model.addAttribute("logoEuro", "&euro;");
		model.addAttribute("priceCart", totalPrice);
		return "listitemspanierMyCart";
	}
	@RequestMapping(value = {"/delete-itemCart-{id}"}, method=RequestMethod.GET)
	public String deleteProduct(HttpServletRequest request, ModelMap model,
			Locale locale, @PathVariable int id, @RequestParam(value="login", 
			defaultValue="") String login){
		if(id>0){
			Cart myCart = CartUtil.getCartInSession(request);
			
			 myCart.deleteProduct(itemService.findById(id));
		
		 String messageWelcome = messageSource.getMessage("welcome.message", null, locale);
	 	 model.addAttribute("Welcome", messageWelcome);
		 model.addAttribute("items", myCart.getProducts());
		 model.addAttribute("Login", login); 
		 model.addAttribute("Lyes", myCart.getTotalQuantity(myCart.getProducts()));
 		 String messageNameItem = messageSource.getMessage("itemName.message", null, locale);
 		 model.addAttribute("nameItem", messageNameItem);
		 String msgQuantity = messageSource.getMessage("quantity.message", null, locale);
		 model.addAttribute("quantity", msgQuantity);
	     String messagePriceItem = messageSource.getMessage("itemPrice.message", null, locale);
	     model.addAttribute("priceItem", messagePriceItem);
	     String messagePriceTotal = messageSource.getMessage("totalPrice.message", null, locale);
		 model.addAttribute("totalPrice", messagePriceTotal);
 		 String messageItemNumber = messageSource.getMessage("itemNumber.message", null, locale);
		 model.addAttribute("itemNumber", messageItemNumber);
		 String messageOrderMyCart = messageSource.getMessage("orderMyCart.message", null, locale);
		 model.addAttribute("OrderMyCart", messageOrderMyCart);
		}
		return "mycart";
	}

	@RequestMapping(value = "/myOrder-{login}", method=RequestMethod.GET)
	public String orderMyCart(HttpServletRequest request, ModelMap model, 
			Locale locale, @PathVariable String login){
		BigDecimal totalPrice=new BigDecimal(0);
		BigDecimal subtotal = new BigDecimal(0);
		User user = userService.findByLogin(login);
		OrderHeader orderHeader = new OrderHeader();
		Cart myCart = CartUtil.getCartInSession(request);
		for(ItemInCart p:myCart.getProducts())
				
		System.out.println(p.getPrice());
		model.addAttribute("items", myCart.getProducts());
		for(ItemInCart q:myCart.getProducts()){
	 		subtotal = q.getSubtotal(); 
	 		totalPrice = totalPrice.add(subtotal);
	 	}
	 	
 		List<ItemInCart> listItems = myCart.getProducts();
 			orderHeader.setPrice(totalPrice);
	 		orderHeader.setDate(new Date());
	 		orderHeader.setUser(user);
	 		orderHeader.setNumberOfCartItems(myCart.getTotalQuantity(listItems));
	 		orderHeader.setDifferentNumberItems(myCart.getNumberDifferentItems(listItems));
	 		orderHeaderService.save(orderHeader);
	 		
	 		ProductOrder order = new ProductOrder();
	 		order.setOrderId("12");
			order.setProductName("Thinkpad T510");
			order.setStatus("confirmed");
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setName("Websystique Admin");
			customerInfo.setAddress("WallStreet");
			customerInfo.setEmail("lyesboufennara@outlook.fr");
			order.setCustomerInfo(customerInfo);
	 		orderService.sendOrderConfirmation(order);
	 		
	 		List <OrderItem> stock = new ArrayList<OrderItem>();
			for(int i=0; i<listItems.size(); i++){
				OrderItemId orderItemId = new OrderItemId();
				OrderItem orderItemTo = new OrderItem();
				ItemInCart idItem=  listItems.get(i);
				int id = (int) idItem.getId();
				Item it = itemService.findById(id);
				orderItemId.setItem(it);
				orderItemId.setOrderHead(orderHeader);
				orderItemTo.setNbItem(idItem.getQuantity());
				orderItemTo.setItem(it);
				orderItemTo.setOrderHeader(orderHeader);
				stock.add(orderItemTo);
			}
	 		for(int i=0;i<stock.size(); i++){
	 			System.out.println(stock.get(i).getIdOrderItem());
				orderItemService.save(stock.get(i));
	 		}
	 		model.addAttribute("priceOrder", orderHeader.getPrice());
	 		model.addAttribute("idOrder", orderHeader.getId());
	 		model.addAttribute("dateOrder", orderHeader.getDate());
	 		model.addAttribute("Login", login);
	 		String messagePriceItem = messageSource.getMessage("itemPrice.message", null, locale);
 			model.addAttribute("priceItem", messagePriceItem);
 			String messageItemNumber = messageSource.getMessage("itemNumber.message", null, locale);
			model.addAttribute("itemNumber", messageItemNumber);
			String messagePriceTotal = messageSource.getMessage("totalPrice.message", null, locale);
			model.addAttribute("totalPrice", messagePriceTotal);
			String messageDisplayItems = messageSource.getMessage("displayItems.message", null, locale);
			model.addAttribute("displayItems", messageDisplayItems);
			String messageDateOrder = messageSource.getMessage("dateOrder.message", null, locale);
			model.addAttribute("dateOrder", messageDateOrder);
			String messageReference = messageSource.getMessage("reference.message", null, locale);
			model.addAttribute("reference", messageReference);
			String messageOrderDescription = messageSource.getMessage("orderDesc.message", null, locale);
			model.addAttribute("orderDesc", messageOrderDescription);
			
		return "myorder";
	}
	/*
	public ProductOrder getDummyOrder() {
		ProductOrder order = new ProductOrder();
			order.setOrderId("1111");
			order.setProductName("Thinkpad T510");
			order.setStatus("confirmed");
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setName("Websystique Admin");
			customerInfo.setAddress("WallStreet");
			customerInfo.setEmail("lyesboufennara@outlook.fr");
			order.setCustomerInfo(customerInfo);
			return order;
		
	}*/
	@RequestMapping(value = "/myDisplayOrder-{id}", method=RequestMethod.GET)
	public String displayMyOrder(HttpServletRequest request, ModelMap model, Locale locale,
			@PathVariable int id) {
		OrderHeader orderHeader = orderHeaderService.findById(id);

		Cart myCart = CartUtil.getCartInSession(request);
		model.addAttribute("items", myCart.getProducts());
 		model.addAttribute("idOrder", orderHeader.getId());
 		model.addAttribute("dateOrder", orderHeader.getDate());
 		model.addAttribute("priceOrder", orderHeader.getPrice());
 		
		return "mydisplayorder";
	}
	
	@RequestMapping(value =  "/listOrders" , method=RequestMethod.GET)
	public String listOrder(ModelMap model, Locale locale) {
		
		String messageIdOrder = messageSource.getMessage("idOrder.message", null, locale);
		model.addAttribute("idOrder", messageIdOrder);
		String messageDateOrder = messageSource.getMessage("dateOrder.message", null, locale);
		model.addAttribute("dateOrder", messageDateOrder);
		String messagePriceOrder = messageSource.getMessage("priceOrder.message", null, locale);
		model.addAttribute("PriceOrder", messagePriceOrder);
		String messageNameUser = messageSource.getMessage("nameUser.message", null, locale);
		model.addAttribute("NameUser", messageNameUser);
		String messageEdit = messageSource.getMessage("edit.message", null, locale);
		model.addAttribute("edit", messageEdit);
		String messageDelete = messageSource.getMessage("delete.message", null, locale);
		model.addAttribute("delete", messageDelete);
		List <OrderHeader> ordersList = orderHeaderService.findAllOrders();
		model.addAttribute("Orders", ordersList);
		String messageWelcome = messageSource.getMessage("welcome.message", null, locale);
		model.addAttribute("Welcome", messageWelcome);
		String messageLogout = messageSource.getMessage("logout.message", null, locale);
		model.addAttribute("Logout", messageLogout);
		return "listOrdered";
	
	}
	@RequestMapping(value = "/display-order-{id}", method=RequestMethod.GET)
	public String displayOrder(HttpServletRequest request, ModelMap model, Locale locale,
			@PathVariable int id) {

		OrderHeader orderHeader = orderHeaderService.findById(id);
		List<OrderItem> itemsFromOrderHeader = orderItemService.findAllOrderItems(id);

		Cart myCart = new Cart();
		for(int i=0; i<itemsFromOrderHeader.size(); i++){
			//if(itemsFromOrderHeader.get(i).getIdOrderItem().getOrderHead().getId() == id){
				myCart.addProduct(itemsFromOrderHeader.get(i).getItem(),
						 itemsFromOrderHeader.get(i).getNbItem());
			//}
		}
		model.addAttribute("items",myCart.getProducts());
		model.addAttribute("quantityArticle", orderHeader.getNumberOfCartItems());	 
 		model.addAttribute("idOrder", orderHeader.getId());
 		model.addAttribute("dateOrderFromOrder", orderHeader.getDate());
 		model.addAttribute("priceOrder", orderHeader.getPrice());
 		String messagePriceItem = messageSource.getMessage("itemPrice.message", null, locale);
		model.addAttribute("priceItem", messagePriceItem);
		String messageItemNumber = messageSource.getMessage("itemNumber.message", null, locale);
		model.addAttribute("itemNumber", messageItemNumber);
		String messagePriceTotal = messageSource.getMessage("totalPrice.message", null, locale);
		model.addAttribute("totalPrice", messagePriceTotal);
		String messageDisplayItems = messageSource.getMessage("displayItems.message", null, locale);
		model.addAttribute("displayItems", messageDisplayItems);
		String messageDateOrder = messageSource.getMessage("dateOrder.message", null, locale);
		model.addAttribute("dateOrder", messageDateOrder);
		String messageReference = messageSource.getMessage("reference.message", null, locale);
		model.addAttribute("reference", messageReference);
		String messageOrderDescription = messageSource.getMessage("orderDesc.message", null, locale);
		model.addAttribute("orderDesc", messageOrderDescription);

		return "mydisplayOrderAdmin";
	}
	@RequestMapping(value="/myHistoryOrder-{login}", method = RequestMethod.GET)
	public String myHistoryOrders(HttpServletRequest request,Model model, 
			Locale locale,
			@PathVariable String login){
		
			User user = userService.findByLogin(login);
			List<OrderHeader>	ordersList = orderHeaderService.findAllOrders(user.getId());
		
		 	String messageIdOrder = messageSource.getMessage("idOrder.message", null, locale);
			model.addAttribute("idOrder", messageIdOrder);
			String messageDateOrder = messageSource.getMessage("dateOrder.message", null, locale);
			model.addAttribute("dateOrder", messageDateOrder);
			String messagePriceOrder = messageSource.getMessage("priceOrder.message", null, locale);
			model.addAttribute("PriceOrder", messagePriceOrder);
			String messageNameUser = messageSource.getMessage("nameUser.message", null, locale);
			model.addAttribute("NameUser", messageNameUser);
			String messageEdit = messageSource.getMessage("edit.message", null, locale);
			model.addAttribute("edit", messageEdit);
			String messageDelete = messageSource.getMessage("delete.message", null, locale);
			model.addAttribute("delete", messageDelete);
			model.addAttribute("Orders", ordersList);
			String messageWelcome = messageSource.getMessage("welcome.message", null, locale);
			model.addAttribute("Welcome", messageWelcome);
			String messageLogout = messageSource.getMessage("logout.message", null, locale);
			model.addAttribute("Logout", messageLogout);

		return "myHistoryOrderPage";
	}
	
}

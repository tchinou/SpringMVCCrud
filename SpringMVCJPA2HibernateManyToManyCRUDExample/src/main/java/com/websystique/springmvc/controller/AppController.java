package com.websystique.springmvc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import com.websystique.springmvc.model.Cart;
import com.websystique.springmvc.model.Item;
import com.websystique.springmvc.model.ItemInCart;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserProfile;
import com.websystique.springmvc.service.CartUtil;
import com.websystique.springmvc.service.ItemService;
import com.websystique.springmvc.service.UserProfileService;
import com.websystique.springmvc.service.UserService;



@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService userService;
	@Autowired
	ItemService itemService;
	@Autowired
	UserProfileService userProfileService;
	@Autowired
	MessageSource messageSource;
	private boolean parameter = false;
	/**
	 * This method will list all existing users.
	 */

	@RequestMapping(value = {"/listitems" }, method = RequestMethod.GET)
	public String listItems(ModelMap model, Locale locale) {
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
		
		return "itemslist";
	}
	@RequestMapping(value = {"/listitemspanier" }, method = RequestMethod.GET)
	public String listItemsPanier(ModelMap model, Locale locale) {
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
		List<Item> items = itemService.sortItemsByName();
		model.addAttribute("items", items);
		return "itemslistpanier";
	}
	@RequestMapping(value = {"/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model, Locale locale) {

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
		return "userslist";
	}
	@RequestMapping(value = {"/interfaceAdmin"}, method = RequestMethod.GET)
	public String displayAdminPage(ModelMap model, Locale locale) {
		 	String welcome = messageSource.getMessage("welcome.message", new Object[]{"John Doe"}, locale);
		 	String messageUser = messageSource.getMessage("user.message", null, locale);
		 	String messageOrder = messageSource.getMessage("order.message", null, locale);
			String messageItem = messageSource.getMessage("item.message", null, locale);
		 	model.addAttribute("message", welcome);
	        model.addAttribute("mUser", messageUser);
	        model.addAttribute("mOrder", messageOrder);
	        model.addAttribute("mItem", messageItem);
	        // obtain locale from LocaleContextHolder
	        Locale currentLocale = LocaleContextHolder.getLocale();
	        model.addAttribute("locale", currentLocale);
	        model.addAttribute("startMeeting", "10:30");
		return "admin";
	}
	//affiche en lançant l'application la vue panier et items utilisateur 
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
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
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model, Locale locale) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId, Locale locale) {
		
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
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
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
	@RequestMapping(value="/myCart", method = RequestMethod.GET)
	public String myCard(HttpServletRequest request,Model model){
		Cart myCart = CartUtil.getCartInSession(request);

		 for(ItemInCart p:myCart.getProducts())
			 System.out.println(p.getPrice());
			 model.addAttribute("items", myCart.getProducts());
		return "mycart";
	}
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	public String addProduct(HttpServletRequest request,ModelMap model,
			@RequestParam(value="id",defaultValue="") int id,
			@RequestParam("quantity") int q){
		if(id>0 && q>=1){
			Cart myCart = CartUtil.getCartInSession(request);
			
			 myCart.addProduct(itemService.findById(id), q);
		}
		else{
			//TODO
			System.out.println("error");
		}
		
		return "redirect:/listitemspanier";
	}
	@RequestMapping(value = {"/delete-itemCart-{id}"}, method=RequestMethod.GET)
	public String deleteProduct(HttpServletRequest request, ModelMap model, @PathVariable int id){
		if(id>0){
			Cart myCart = CartUtil.getCartInSession(request);
			
			 myCart.deleteProduct(itemService.findById(id));
		}
		
		return "redirect:/myCart";
	}

	@RequestMapping(value = { "/sort-item" }, method=RequestMethod.GET)
	public String ascSortListItemsPanier(ModelMap model, Locale locale, @RequestParam(value="sortfield", 
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

		if(field.equals("DESC")) model.addAttribute("price", "ASC");
		
		else  model.addAttribute("price", "DESC");
		
		List <Item> itemsList = itemService.sortItemByPriceAscDesc(field);
		model.addAttribute("items", itemsList);
		return "itemslistpanier";
	}
	
	@RequestMapping(value = "/myOrder", method=RequestMethod.GET)
	public String orderMyCart(HttpServletRequest request, ModelMap model, Locale locale) {

		Cart myCart = CartUtil.getCartInSession(request);
		
//		List<Item> itemsList = itemService.findAllItems();
//		model.addAttribute("itemsList", itemsList);
//		for(ItemInCart p:myCart.getProducts()){
//			
//		}
		
		model.addAttribute("price", myCart.getCartPrice());
		return "myorder";
	}
	

}

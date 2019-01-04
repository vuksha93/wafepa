package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.support.UserRegistrationDTOToUser;
import jwd.wafepa.support.UserToUserDTO;
import jwd.wafepa.web.dto.UserDTO;
import jwd.wafepa.web.dto.UserRegistrationDTO;

@RestController
@RequestMapping("api/users")
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserToUserDTO toUserDTO;
	
	@Autowired
	private UserRegistrationDTOToUser toUserByUserRegistrationDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers(
			@RequestParam(defaultValue="0") int page,
			@RequestParam(required=false) String email,
			@RequestParam(required=false) String firstName,
			@RequestParam(required=false) String lastName
			) {
		
		Page<User> usersPage = userService.findAll(page);
		List<User> users = usersPage.getContent(); 
		
		if(email == null && firstName == null && lastName == null) {
			usersPage = userService.findAll(page);
		} else if(firstName != null) {
			users = userService.findByFirstname(firstName);
		} else if(lastName != null) {
			users = userService.findByLastname(lastName);
		} else if(email != null) {
			users = userService.findByEmailContaining(email);
		}
		
		if(users == null || users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toUserDTO.convert(users), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<List<UserDTO>> changeUsers(
			@RequestParam(defaultValue="0") int page,
			@RequestBody List<UserRegistrationDTO> regUsers) {
		deleteUsers(page);
		
		List<User> users = toUserByUserRegistrationDTO.convert(regUsers);
		
		List<User> newUsers = userService.save(users);
		
		return new ResponseEntity<>(toUserDTO.convert(newUsers), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UserDTO> addUser(
			@Validated
			@RequestBody UserRegistrationDTO regUser) {
		
		User user = toUserByUserRegistrationDTO.convert(regUser);
		
		User newUser = userService.save(user);
		
		return new ResponseEntity<>(toUserDTO.convert(newUser), HttpStatus.CREATED);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void deleteUsers(@RequestParam(defaultValue="0") int page) {
		Page<User> usersPage = userService.findAll(page);
		
		List<User> allUsers = usersPage.getContent();
		
		for(User user : allUsers) {
			userService.delete(user.getId());
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toUserDTO.convert(user), HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<UserDTO> changeUser
		(@RequestBody UserRegistrationDTO regUser, @PathVariable Long id) {
		
		if(!id.equals(regUser.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = toUserByUserRegistrationDTO.convert(regUser);
		
		User edited = userService.save(user);
		
		return new ResponseEntity<>(toUserDTO.convert(edited), HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
		
		if(userService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		User deleted = userService.delete(id);
		
		return new ResponseEntity<>(toUserDTO.convert(deleted), HttpStatus.OK);
	}
	
}

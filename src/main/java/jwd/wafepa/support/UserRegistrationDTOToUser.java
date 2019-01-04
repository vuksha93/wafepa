package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.UserRegistrationDTO;

@Component
public class UserRegistrationDTOToUser 
	implements Converter<UserRegistrationDTO, User>{
	
	@Autowired
	private UserService userService;

	@Override
	public User convert(UserRegistrationDTO regUser) {
		
		if(!regUser.getPassword().equals(regUser.getRepeatedPassword())) {
			throw new IllegalArgumentException("Passwords are not the same!");
		}
		
		User user = null;
		
		if(regUser.getId() != null) {
			user = userService.findOne(regUser.getId());
		} 
		
		if (user == null) {
			user = new User();
		}
	
		user.setId(regUser.getId());
		user.setEmail(regUser.getEmail());
		user.setPassword(regUser.getPassword());
		user.setFirstName(regUser.getFirstName());
		user.setLastName(regUser.getLastName());
		
		return user;
	}
	
	public List<User> convert(List<UserRegistrationDTO> regUsers) {
		
		List<User> users = new ArrayList<>();
		
		for(UserRegistrationDTO regUser : regUsers) {
			users.add(convert(regUser));
		}
		
		return users;
	}

}

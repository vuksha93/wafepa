package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.User;
import jwd.wafepa.web.dto.UserRegistrationDTO;

@Component
public class UserToUserRegistrationDTO 
	implements Converter<User, UserRegistrationDTO> {

	@Override
	public UserRegistrationDTO convert(User user) {
		
		UserRegistrationDTO regUser = new UserRegistrationDTO();
		
		regUser.setId(user.getId());
		regUser.setEmail(user.getEmail());
		regUser.setPassword(user.getPassword());
		regUser.setFirstName(user.getFirstName());
		regUser.setLastName(user.getLastName());
		
		return regUser;
	}
	
	public List<UserRegistrationDTO> convert(List<User> users) {
		
		List<UserRegistrationDTO> regUsers = new ArrayList<>();
		
		for(User user : users) {
			regUsers.add(convert(user));
		}
		
		return regUsers;
	}

}

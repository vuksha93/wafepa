package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.User;
import jwd.wafepa.web.dto.UserDTO;

@Component
public class UserToUserDTO 
	implements Converter<User, UserDTO>{

	@Override
	public UserDTO convert(User user) {
		
		UserDTO userDto = new UserDTO();
		
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		
		return userDto;
	}
	
	public List<UserDTO> convert(List<User> users) {
		
		List<UserDTO> dtoUsers = new ArrayList<>();
		
		for(User user : users) {
			dtoUsers.add(convert(user));
		}
		
		return dtoUsers;
	}

}

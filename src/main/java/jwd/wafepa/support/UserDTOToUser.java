package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.UserDTO;

@Component
public class UserDTOToUser 
	implements Converter<UserDTO, User> {
	
	@Autowired
	private UserService userService;

	@Override
	public User convert(UserDTO userDto) {
		
		User user;
		
		if(userDto.getId() != null) {
			user = userService.findOne(userDto.getId());
		} else {
			user = new User();
		}
		
		user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		
		return user;
	}
	
	public List<User> convert(List<UserDTO> dtoUsers) {
		
		List<User> users = new ArrayList<>();
		
		for(UserDTO userDto : dtoUsers) {
			users.add(convert(userDto));
		}
		
		return users;
	}

}

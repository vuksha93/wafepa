package jwd.wafepa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Address;
import jwd.wafepa.model.User;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.AddressService;
import jwd.wafepa.service.UserService;

@Component
public class TestData {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	@PostConstruct
	public void init() {
		activityService.save(new Activity("Swimming"));
		activityService.save(new Activity("Running"));
		
		int userCount = 20;
		
		for(int i = 1; i <= userCount; i++) {
			User user = new User();
			user.setFirstName("First name " + i);
			user.setLastName("Last name " + i);
			user.setEmail("email" + i + "@example");
			user.setPassword("123");
			userService.save(user);
			
			for(int j = 1; j <= 3; j++) {
				Address address = new Address();
				address.setNumber(Integer.toString(j));
				address.setStreet("Street");
				
				addressService.save(address);
				user.addAddress(address);
				userService.save(user);
				addressService.save(address);
			}
		}
	}
}

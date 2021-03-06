package jwd.wafepa;

import java.text.ParseException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Address;
import jwd.wafepa.model.Record;
import jwd.wafepa.model.User;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.AddressService;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.service.UserService;

@Component
public class TestData {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private RecordService recordService;
	
	@PostConstruct
	public void init() throws ParseException {
		Activity activity1 = activityService.save(new Activity("Swimming"));
		Activity activity2 = activityService.save(new Activity("Running"));
		
		User user1 = new User();
		user1.setFirstName("First name " + 1);
		user1.setLastName("Last name " + 1);
		user1.setEmail("email" + 1 + "@example.com");
		user1.setPassword("123");
		user1 = userService.save(user1);

		User user2 = new User();
		user2.setFirstName("First name " + 2);
		user2.setLastName("Last name " + 2);
		user2.setEmail("email" + 2 + "@example.com");
		user2.setPassword("123");
		user2 = userService.save(user2);
		
		Address address1 = new Address();
		address1.setStreet("Street");
		address1.setNumber("1");
		address1.setUser(user1);
		addressService.save(address1);
		
		Address address2 = new Address();
		address2.setStreet("Street");
		address2.setNumber("2");
		address2.setUser(user2);
		addressService.save(address2);
		
//		String pattern = "dd/MM/yyyy hh:mm";
//		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		Record record1 = new Record();
		record1.setTime("20/11/2018 08:00");
		record1.setDuration(60);
		record1.setIntensity("LOW");
		record1.setUser(user1);
		record1.setActivity(activity1);
		recordService.save(record1);
		
		Record record2 = new Record();
		record2.setTime("21/11/2018 08:00");
		record2.setDuration(60);
		record2.setIntensity("EXTREME");
		record2.setUser(user2);
		record2.setActivity(activity2);
		recordService.save(record2);
	}	
}

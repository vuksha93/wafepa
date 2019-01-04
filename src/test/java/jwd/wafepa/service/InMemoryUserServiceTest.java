package jwd.wafepa.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import jwd.wafepa.model.User;
import jwd.wafepa.service.impl.InMemoryUserService;

@Service
public class InMemoryUserServiceTest {

	private UserService userService;
	
	@Before
	public void setUp() {
		userService = new InMemoryUserService();
		List<User> users = new ArrayList<>();
		
		User pera = new User("pera@pera", "pera", "Pera", "Peric");
		User mika = new User("mika@mika", "mika", "Mika", "Mikic");
		User zika = new User("zika@zika", "zika", "Zika", "Zikic");
		
		users.add(pera);
		users.add(mika);
		users.add(zika);
		
		userService.save(users);
	}
	
	@Test
	public void testFindOne() {
		User user = userService.findOne(1L);
		Assert.assertNotNull(user);
		Assert.assertEquals(1L, user.getId().longValue());
		Assert.assertEquals("pera@pera", user.getEmail());
		Assert.assertEquals("pera", user.getPassword());
		Assert.assertEquals("Pera", user.getFirstName());
		Assert.assertEquals("Peric", user.getLastName());
	}
	
	@Test
	public void testFindNone() {
		User user = userService.findOne(100L);
		Assert.assertNull(user);
	}
	
//	@Test
//	public void testFindAll() {
//		List<User> users = userService.findAll();
//		Assert.assertNotNull(users);
//		Assert.assertEquals(3, users.size());
//	}
	
//	@Test
//	public void testDeleteUser() {
//		User user = userService.delete(1L);
//		Assert.assertNotNull(user);
//		Assert.assertEquals(1L, user.getId().longValue());
//		Assert.assertEquals("pera@pera", user.getEmail());
//		Assert.assertEquals("pera", user.getPassword());
//		Assert.assertEquals("Pera", user.getFirstName());
//		Assert.assertEquals("Peric", user.getLastName());
//		List<User> users = userService.findAll();
//		Assert.assertEquals(2, users.size());
//	}
	
	@Test
	public void testFindByEmailContaining() {
		List<User> users = userService.findByEmailContaining("ka");
		Assert.assertNotNull(users);
		Assert.assertEquals(2, users.size());
		Assert.assertEquals(2L, users.get(0).getId().longValue());
		Assert.assertEquals("mika@mika", users.get(0).getEmail());
		Assert.assertEquals(3L, users.get(1).getId().longValue());
		Assert.assertEquals("zika@zika", users.get(1).getEmail());
	}
	
}

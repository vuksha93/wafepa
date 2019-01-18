package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;

//@Service
public class InMemoryUserService implements UserService {
	private Map<Long, User> users = new HashMap<>();
	private Long nextId = 1L;

//	@Override
//	public List<User> findAll() {
//		return new ArrayList<User>(users.values());
//	}

	@Override
	public User findOne(Long id) {
		return users.get(id);
	}

	@Override
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(nextId++);
		}
		
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public User delete(Long id) {
		if(!users.containsKey(id)) {
			throw new IllegalArgumentException("Tried to delete " 
					+ "non-existant user.");
		}
		return users.remove(id);
	}

	@Override
	public List<User> save(List<User> users) {
		List<User> retUsers = new ArrayList<>();
		
		for(User user : users) {
			User savedUser = save(user);
			
			if(savedUser != null) {
				retUsers.add(savedUser);
			}
			
		}
		return retUsers;
	}

	@Override
	public Page<User> findByEmailContaining(String email, int page) {
		
		List<User> retVal = new ArrayList<>();
		
		for(User user : users.values()) {
			if(user.getEmail().contains(email)) {
				retVal.add(user);
			}
		}
		
		return new PageImpl<>(retVal);
	}

	@Override
	public Page<User> findByFirstName(String firstName, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findByLastName(String lastName, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findAll(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findByFirstNameContainingOrLastNameContaining(String name, int page) {
		// TODO Auto-generated method stub
		return null;
	}

}

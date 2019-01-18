package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.User;

public interface UserService {

	Page<User> findAll(int page);
	User findOne(Long id);
	User save(User user);
	User delete(Long id);
	Iterable<User> save(List<User> users);
	Page<User> findByEmailContaining(String email, int page);
	Page<User> findByFirstName(String firstName, int page);
	Page<User> findByLastName(String lastName, int page);
	Page<User> findByFirstNameContainingOrLastNameContaining(
			String name,
			int page);
	
}

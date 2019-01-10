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
	Page<User> findByFirstname(String firstName, int page);
	Page<User> findByLastname(String lastName, int page);
	
}

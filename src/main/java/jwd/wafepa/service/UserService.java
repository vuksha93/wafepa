package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.User;

public interface UserService {

	Page<User> findAll(int page);
	User findOne(Long id);
	User save(User user);
	User delete(Long id);
	List<User> save(List<User> users);
	List<User> findByEmailContaining(String email);
	List<User> findByFirstname(String firstName);
	List<User> findByLastname(String lastName);
	
}

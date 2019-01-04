package jwd.wafepa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.User;
import jwd.wafepa.repository.UserRepository;
import jwd.wafepa.service.UserService;

@Service
@Transactional
public class JpaUserService 
	implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<User> findAll(int page) {
		return userRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User delete(Long id) {	
		User toDelete = userRepository.findOne(id);
		
		if(toDelete != null) {
			userRepository.delete(toDelete);
		}
		
		return toDelete;
	}

	@Override
	public List<User> save(List<User> users) {
		return userRepository.save(users);
	}
	
//	TODO implementirati posle 
	@Override
	public List<User> findByEmailContaining(String email) {
		return null;
	}
	
	@Override
	public List<User> findByFirstname(String firstName) {	
		return userRepository.findByFirstName(firstName);
	}

	@Override
	public List<User> findByLastname(String lastName) {		
		return userRepository.findByLastName(lastName);
	}
	
	//@PostConstruct
	public void userInit() {
		save(new User("pera@pera", "pera", "Pera", "Peric"));
		save(new User("mika@mika", "mika", "Mika", "Mikic"));
		save(new User("zika@zika", "zika", "Zika", "Zikic"));
	}

}

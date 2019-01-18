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
	
	private static final int PAGE_SIZE = 5;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<User> findAll(int page) {
		return userRepository.findAll(new PageRequest(page, PAGE_SIZE));
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
	public Iterable<User> save(List<User> users) {
		return userRepository.save(users);
	}
	 
	@Override
	public Page<User> findByEmailContaining(String email, int page) {
		return userRepository.findByEmailContaining(email, new PageRequest(page, PAGE_SIZE));
	}
	
	@Override
	public Page<User> findByFirstName(String firstName, int page) {
//		if(firstName != null) {
//			firstName = '%' + firstName + '%';
//		}
		return userRepository.findByFirstName(firstName, new PageRequest(page, PAGE_SIZE));
	}

	@Override
	public Page<User> findByLastName(String lastName, int page) {	
//		if(lastName != null) {
//			lastName = '%' + lastName + '%';
//		}
		return userRepository.findByLastName(lastName, new PageRequest(page, PAGE_SIZE));
	}
	
	//@PostConstruct
	public void userInit() {
		save(new User("pera@pera", "pera", "Pera", "Peric"));
		save(new User("mika@mika", "mika", "Mika", "Mikic"));
		save(new User("zika@zika", "zika", "Zika", "Zikic"));
	}

	@Override
	public Page<User> findByFirstNameContainingOrLastNameContaining(
			String name, int page) {
		
		return userRepository.findByFirstOrLastNameContaining(
				name, new PageRequest(page, PAGE_SIZE));
	}

}

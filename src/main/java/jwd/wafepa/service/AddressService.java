package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Address;
import jwd.wafepa.model.User;

public interface AddressService {

	Address findOne(Long id);
	List<Address> findAll();
	Address save(Address address);
	List<Address> save(List<Address> addresses);
	Address delete(Long id);
	void delete(List<Long> ids);
	List<Address> findByUserId(Long userId);
	List<Address> findByUser(User user);
	
}

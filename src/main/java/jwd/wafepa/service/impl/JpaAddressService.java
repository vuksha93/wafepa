package jwd.wafepa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Address;
import jwd.wafepa.repository.AddressRepository;
import jwd.wafepa.service.AddressService;

@Service
@Transactional
public class JpaAddressService
	implements AddressService{
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address findOne(Long id) {
		return addressRepository.findOne(id);
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public List<Address> save(List<Address> addresses) {
		return addressRepository.save(addresses);
	}

	@Override
	public Address delete(Long id) {
		Address toDelete = addressRepository.findOne(id);
		
		if(toDelete != null) {
			addressRepository.delete(toDelete);
		}
		
		return toDelete;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids) {
			addressRepository.delete(id);
		}
		
	}
	
	//@PostConstruct
	public void addressInit() {
		save(new Address("Karadjordjeva", "1"));
		save(new Address("Mose Pijade", "39"));
	}

	@Override
	public List<Address> findByUserId(Long userId) {
		return addressRepository.findByUserId(userId);
	}

}

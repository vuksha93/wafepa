package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Address;
import jwd.wafepa.model.User;
import jwd.wafepa.service.AddressService;
import jwd.wafepa.service.UserService;
import jwd.wafepa.support.AddressDTOToAddress;
import jwd.wafepa.support.AddressToAddressDTO;
import jwd.wafepa.web.dto.AddressDTO;

@RestController
@RequestMapping("api/users/{userId}/addresses")
public class ApiAddressController {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressDTOToAddress toAddress;
	
	@Autowired
	private AddressToAddressDTO toAddressDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AddressDTO>> getAddresses(@PathVariable Long userId) {
		
		List<Address> addresses = addressService.findByUserId(userId);
		
		if(addresses == null || addresses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toAddressDTO.convert(addresses), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<AddressDTO> getAddress(
			@PathVariable Long id,
			@PathVariable Long userId) {
		
		Address address = addressService.findOne(id);
		
		if(address == null || !userId.equals(address.getUser().getId())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toAddressDTO.convert(address), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<AddressDTO> deleteAddress(
			@PathVariable Long id,
			@PathVariable Long userId) {
		
		Address current = addressService.findOne(id);
		
		if(current == null || !userId.equals(current.getUser().getId())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Address deleted = addressService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toAddressDTO.convert(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<AddressDTO> addAddress(
			@PathVariable Long userId,
			@RequestBody AddressDTO dtoAddress) {
		
		User user = userService.findOne(userId);
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Address address = addressService.save(toAddress.convert(dtoAddress));
		user.addAddress(address);
		userService.save(user);
		address = addressService.save(address);
		
		return new ResponseEntity<>(toAddressDTO.convert(address), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<AddressDTO> editAddress(
			@PathVariable Long id,
			@PathVariable Long userId,
			@RequestBody AddressDTO dtoAddress) {
		
		if(id == null || !id.equals(dtoAddress.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Address current = addressService.findOne(id);
		if(current == null || !userId.equals(current.getUser().getId())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Address edited = addressService.save(toAddress.convert(dtoAddress));
		
		return new ResponseEntity<>(toAddressDTO.convert(edited), HttpStatus.OK);
	}
	
}

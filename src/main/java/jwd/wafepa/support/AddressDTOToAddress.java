package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Address;
import jwd.wafepa.web.dto.AddressDTO;

@Component
public class AddressDTOToAddress
	implements Converter<AddressDTO, Address>{

	@Override
	public Address convert(AddressDTO dto) {
		Address address = new Address();
		
		address.setId(dto.getId());
		address.setStreet(dto.getStreet());
		address.setNumber(dto.getNumber());
		
		return address;
	}
	
	public List<Address> convert(List<AddressDTO> dtoAddresses) {
		List<Address> addresses = new ArrayList<>();
		
		for(AddressDTO dto : dtoAddresses) {
			addresses.add(convert(dto));
		}
		
		return addresses;
	}

}

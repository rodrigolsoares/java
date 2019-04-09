package br.com.petruber.converter;

import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.petruber.contract.UserContract;
import br.com.petruber.service.entity.UserEntity;
import br.com.petruber.util.StringUtilites;

@Component
public class UserConverter {
	
	
	
	public final UserEntity parseContractToEntity(final UserContract userContract) {

		UserEntity registerEntity = new UserEntity();
		
		registerEntity.setId(userContract.getId());
		registerEntity.setName(userContract.getName());
		registerEntity.setDateUpdate(new Date());
		registerEntity.setEmail(userContract.getEmail());
		registerEntity.setPhoneNumber1(userContract.getPhoneNumber1());
		registerEntity.setPassword(userContract.getPassword());

		return registerEntity;

	}
	
	
	public final UserContract parseEntityToContract(final UserEntity userEntity) {

		UserContract registerContract = new UserContract();

		registerContract.setName(userEntity.getName());
		registerContract.setDateUpdate(StringUtilites.dataToStringMaskddMMyyyy(userEntity.getDateUpdate()));
		registerContract.setId(userEntity.getId());
		registerContract.setEmail(userEntity.getEmail());
		registerContract.setPhoneNumber1(userEntity.getPhoneNumber1());
		registerContract.setPassword(userEntity.getPassword());

		return registerContract;

	}
}

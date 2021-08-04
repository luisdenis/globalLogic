package cl.globallogic.bci.example.service;

import java.util.List;

import cl.globallogic.bci.example.data.entity.UserEntity;

public interface IUserService {

	UserEntity create(UserEntity user);
	
	List<UserEntity> getListAll();
	
}

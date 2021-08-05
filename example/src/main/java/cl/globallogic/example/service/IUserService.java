package cl.globallogic.example.service;

import java.util.List;

import cl.globallogic.example.data.entity.UserEntity;

public interface IUserService {

	UserEntity create(UserEntity user);
	
	List<UserEntity> getListAll();
	
}

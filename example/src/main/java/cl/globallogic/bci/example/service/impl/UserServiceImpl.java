package cl.globallogic.bci.example.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.globallogic.bci.example.data.IUserDAO;
import cl.globallogic.bci.example.data.entity.UserEntity;
import cl.globallogic.bci.example.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	

	@Override
	public UserEntity create(UserEntity user) {
		LocalDateTime now = LocalDateTime.now();
		user.setCreated(now);
		user.setLastLogin(now);
		user.setIsactive(true);
		user = userDAO.save(user);
		return user;
	}


	@Override
	public List<UserEntity> getListAll() {
		return (List<UserEntity>) userDAO.findAll();
	}
	
	
	
}

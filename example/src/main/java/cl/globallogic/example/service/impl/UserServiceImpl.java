package cl.globallogic.example.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.globallogic.example.application.security.JWTAuthorization;
import cl.globallogic.example.data.IUserDAO;
import cl.globallogic.example.data.entity.UserEntity;
import cl.globallogic.example.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private JWTAuthorization jwtAuthorization;
	

	@Override
	public UserEntity create(UserEntity user) {
		LocalDateTime now = LocalDateTime.now();
		user.setCreated(now);
		user.setLastLogin(now);
		user.setIsactive(true);
		user.setToken(jwtAuthorization.generateToken(user));
		user = userDAO.save(user);
		return user;
	}


	@Override
	public List<UserEntity> getListAll() {
		return (List<UserEntity>) userDAO.findAll();
	}
	
	
	
}

package cl.globallogic.example.service.impl;


import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.globallogic.example.application.security.JWTAuthorization;
import cl.globallogic.example.data.IUserDAO;
import cl.globallogic.example.data.entity.UserEntity;
import cl.globallogic.example.service.IUserService;
import cl.globallogic.example.web.rest.model.ErrorCode;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private JWTAuthorization jwtAuthorization;
	

	@Override
	@Transactional
	public UserEntity create(UserEntity user) {
		
		//validate email
		if(userDAO.countByEmail(user.getEmail()) > 0){
			throw new RuntimeException(ErrorCode.EMAIL_EXIST.getMessage());
		}
		
		LocalDateTime now = LocalDateTime.now();
		user.setCreated(now);
		user.setLastLogin(now);
		user.setIsactive(true);
		user.setToken(jwtAuthorization.generateToken(user));
		user = userDAO.save(user);
		return user;
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<UserEntity> getListAll() {
		return (List<UserEntity>) userDAO.findAll();
	}
	
	
}

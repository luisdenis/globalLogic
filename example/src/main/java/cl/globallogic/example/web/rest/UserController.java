package cl.globallogic.example.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.globallogic.example.data.entity.UserEntity;
import cl.globallogic.example.service.IUserService;

@RestController
@RequestMapping( path = "/api" , produces = MediaType.APPLICATION_JSON_VALUE )
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;

	@PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@Valid @RequestBody UserEntity user){
		logger.debug("Input User -> {}",user.toString());
		userService.create(user);
		logger.debug("Output User -> {}",user.toString());
		return new ResponseEntity<UserEntity>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/user")
	public @ResponseBody ResponseEntity<?> getListAll(){
		return new ResponseEntity<List<UserEntity>>(userService.getListAll(), HttpStatus.OK);
	}
	
}

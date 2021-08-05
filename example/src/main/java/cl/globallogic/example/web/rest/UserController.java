package cl.globallogic.example.web.rest;

import java.util.List;

import javax.validation.Valid;

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
@RequestMapping( path = "/api",  consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE )
public class UserController {
	
	@Autowired
	private IUserService userService;

	@PostMapping("/user")
	public ResponseEntity<?> create(@Valid @RequestBody UserEntity user){
		userService.create(user);
		return new ResponseEntity<UserEntity>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/user")
	public @ResponseBody ResponseEntity<?> getListAll(){
		return new ResponseEntity<List<UserEntity>>(userService.getListAll(), HttpStatus.OK);
	}
	
}

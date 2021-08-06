package cl.globallogic.example;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cl.globallogic.example.web.rest.UserController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExampleApplicationTests {

	@Autowired
	private UserController userController;
	
	@Test
	void contextLoads() {
		  assertNotNull(userController);
	}

}



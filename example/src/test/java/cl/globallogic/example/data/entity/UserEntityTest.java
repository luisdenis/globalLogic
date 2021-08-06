package cl.globallogic.example.data.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserEntityTest {

	@Autowired
	private LocalValidatorFactoryBean validator;

	
	@Description(value = "Validamos un email correcto")
	@Test
	void testEmailCorrect() throws Exception {

		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("jose@gmail.com");

		Set<ConstraintViolation<UserEntity>> errors = validator.validateProperty(userEntity, "email");
		assertTrue(errors.size() == 0);
	}
	
	@Description(value = "Validamos un email correcto")
	@Test
	void testPasswordCorrect() throws Exception {

		UserEntity userEntity = new UserEntity();
		userEntity.setPassword("Ld2n1ss");
		Set<ConstraintViolation<UserEntity>> errors = validator.validateProperty(userEntity, "password");
		
		assertTrue(errors.size() == 0);
	
	}
	
	
	@Description(value = "Validamos cuando el formato es incorrecto")
	@Test
	void testSetEmail() throws Exception {

		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("josegma¡dfsd");

		Set<ConstraintViolation<UserEntity>> errors = validator.validateProperty(userEntity, "email");
		
		assertTrue(errors.size() > 0);
		for (ConstraintViolation<UserEntity> constraintViolation : errors) {
			assertEquals("El correo tiene un formato incorrecto", constraintViolation.getMessage());
		}
	}

	@Description(value = "Validamos el formato incorrecto del password")
	@Test
	void testSetPassword() {
		UserEntity userEntity = new UserEntity();
		userEntity.setPassword("3nsd1sLL");
		Set<ConstraintViolation<UserEntity>> errors = validator.validateProperty(userEntity, "password");
		
		assertTrue(errors.size() > 0);
		
		for (ConstraintViolation<UserEntity> constraintViolation : errors) {
			assertEquals("La password tiene un formato incorrecto", constraintViolation.getMessage());
		}
	}

}

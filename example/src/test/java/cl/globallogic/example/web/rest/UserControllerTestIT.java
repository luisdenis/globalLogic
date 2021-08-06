package cl.globallogic.example.web.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.globallogic.example.data.IUserDAO;
import cl.globallogic.example.data.entity.PhoneEntity;
import cl.globallogic.example.data.entity.UserEntity;
import cl.globallogic.example.service.IUserService;
import cl.globallogic.example.web.rest.model.ErrorCode;
import cl.globallogic.example.web.rest.model.MessageError;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private IUserDAO userDao;

	@Description(value = "Se valida que se ingrese correctament el usuario")
	@Test
	void testCreate() throws Exception {

		UserEntity user = new UserEntity();
		user.setName("Juan Rodriguez");
		user.setEmail("juanrodriguez2@globallogic.cl");
		user.setPassword("Lunter21");
		List<PhoneEntity> phoneEntities = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			PhoneEntity input = new PhoneEntity();
			input.setCityCode("code" + i);
			input.setContryCode("chile" + i);
			input.setNumber("23982039" + i);
			phoneEntities.add(input);
		}
		user.setPhones(phoneEntities);
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		
		String inputJson = mapper.writeValueAsString(user);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/user").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultBody = result.getResponse().getContentAsString();
		assertNotNull(resultBody);

		user = mapper.readValue(resultBody, new TypeReference<UserEntity>() {
        });
		
		Optional<UserEntity> userTMP = userDao.findById(user.getId());
		assertTrue(userTMP.isPresent());

		assertNotNull(userTMP.get().getPhones());
		assertEquals(3, userTMP.get().getPhones().size());

		userDao.deleteAll();
	}

	@Description(value = "Se valida que el correo no se repita")
	@Test
	void testEmailNotRepeat() throws Exception {

		UserEntity user = new UserEntity();
		user.setName("Juan Rodriguez");
		user.setEmail("juanrodriguez2@globallogic.cl");
		user.setPassword("Lunter21");
		List<PhoneEntity> phoneEntities = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			PhoneEntity input = new PhoneEntity();
			input.setCityCode("code" + i);
			input.setContryCode("chile" + i);
			input.setNumber("23982039" + i);
			phoneEntities.add(input);
		}
		user.setPhones(phoneEntities);

		userDao.save(user);

		ObjectMapper mapper = new ObjectMapper();
		String inputJson = mapper.writeValueAsString(user);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/user").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError()).andReturn();

		String resultBody = result.getResponse().getContentAsString();
		assertNotNull(resultBody);

		MessageError messageError = mapper.readValue(resultBody, MessageError.class);
		assertEquals(ErrorCode.EMAIL_EXIST.getMessage(), messageError.getMensaje());
		
		userDao.deleteAll();

	}

}

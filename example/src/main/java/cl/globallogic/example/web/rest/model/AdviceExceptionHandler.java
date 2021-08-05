package cl.globallogic.example.web.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> notValidException(HttpServletRequest request, MethodArgumentNotValidException e) {

		Map<String, Object> response = new HashMap<>();

		BindingResult bindingResult = e.getBindingResult();
		List<String> errorMsgList = new ArrayList<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errorMsgList.add(fieldError.getDefaultMessage());
		}
		response.put("mensaje", errorMsgList);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> errorNotControllerException(Throwable ex) {
		MessageError messageError = new MessageError();
		messageError.setMensaje(ex.getMessage());
		return new ResponseEntity<>(messageError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<?> errorDataAccessException(Throwable ex) {
		MessageError messageError = new MessageError();
		messageError.setMensaje(ex.getMessage());
		return new ResponseEntity<>(messageError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

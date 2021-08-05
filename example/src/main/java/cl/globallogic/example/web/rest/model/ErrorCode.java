package cl.globallogic.example.web.rest.model;

public enum ErrorCode {

	ERROR_UNEXPECTED("Un error inesperado"),
	UNAUTHORIZED("no autorizado");
	
	private final String message;

	ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}

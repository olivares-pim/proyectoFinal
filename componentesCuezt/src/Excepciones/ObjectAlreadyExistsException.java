package Excepciones;

public class ObjectAlreadyExistsException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectAlreadyExistsException(String message) {
		super("Este " + message + " ya existe en la base de datos.");
	}

}

package main.java.demo.acs.logic;

public class IncorrectInputExeption extends IllegalArgumentException{

	private static final long serialVersionUID = 8107058929799552832L;

	public IncorrectInputExeption() {
		super();
	}

	public IncorrectInputExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectInputExeption(String message) {
		super(message);
	}

	public IncorrectInputExeption(Throwable cause) {
		super(cause);
	}
}

package foo.bar.backend;

public class ContactNotFoundException extends Exception{

	public ContactNotFoundException() {
		
	}
	
	public ContactNotFoundException(String message) {
		super(message);
	}
}

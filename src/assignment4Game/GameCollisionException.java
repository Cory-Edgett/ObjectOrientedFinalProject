package assignment4Game;

public class GameCollisionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GameCollisionException() {
		super();
	}
	
	public GameCollisionException(Exception e) {
		super(e);
	}
	
	public GameCollisionException(String message) {
		super(message);
	}
}

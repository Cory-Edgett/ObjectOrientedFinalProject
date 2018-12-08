package assignment4Game;

public class RightCommand implements Command{
	private Character character;
	
	public RightCommand(Character character) {
		this.character = character;
	}

	@Override
	public void execute() {
		character.moveRight();
		
	}
	
	@Override
	public void reverse() {
		character.moveLeft();
	}
	
	public void setCharacter(Character character) {
		this.character = character;
	}
}

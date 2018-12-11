package assignment4Game;

public class LeftCommand implements Command{
	private Character character;
	
	public LeftCommand(Character character) {
		this.character = character;
	}

	@Override
	public void execute() {
		character.moveLeft();		
	}
	
	@Override
	public void reverse() {
		character.moveRight();
	}
	
	public void setCharacter(Character character) {
		this.character = character;
	}
}

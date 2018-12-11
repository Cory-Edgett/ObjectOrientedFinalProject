package assignment4Game;

public class UpCommand implements Command{
	private Character character;
	
	
	public UpCommand(Character character) {
		this.character = character;
	}

	@Override
	public void execute() {
		character.moveUp();
		
	}
	
	@Override
	public void reverse() {
		character.moveDown();
	}
	
	public void setCharacter(Character character) {
		this.character = character;
	}
}

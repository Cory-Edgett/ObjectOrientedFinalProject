package command;

public class DownCommand implements Command{
	private Character character;
		
	public DownCommand(Character character) {
		this.character = character;
	}

	@Override
	public void execute() {
		character.moveDown();
	}
	
	@Override
	public void reverse() {
		character.moveUp();
	}
	
	public void setCharacter(Character character) {
		this.character = character;
	}
	
	
}

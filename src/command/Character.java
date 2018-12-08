package command;

import java.awt.Point;
import java.util.Observable;

public abstract class Character extends Observable{
	private int SPEED;
	private Point position;
	
	public Character(int speed) {
		this.SPEED = speed;
	}
		
	public void moveLeft() throws GameCollisionException {
		int newX = position.x - SPEED;
		if(newX < GameConfig.MIN_X) throw new GameCollisionException();
		position.move(newX, position.y);
	}
	
	public void moveRight() throws GameCollisionException {
		int newX = position.x + SPEED;
		if(newX >= GameConfig.MAX_X) throw new GameCollisionException();
		position.move(newX, position.y);
	}
	
	public void moveUp() throws GameCollisionException {
		int newY = position.y - SPEED;
		if(newY <= GameConfig.MIN_Y) throw new GameCollisionException();
		position.move(position.x, newY);
	}
	
	public void moveDown() throws GameCollisionException {
		int newY = position.y + SPEED;
		if(newY > GameConfig.MAX_Y) throw new GameCollisionException();
		position.move(position.x, newY);
	}
	
	public Point getPosition(){
		return position;
	}
	
	public void setPosition(int x, int y) {
		this.position = new Point(x, y);
	}

}

package command;

import java.util.Observer;
import java.awt.Point;
import java.util.List;
import java.util.Observable;

public class MirrorPlayer extends Player implements Observer {
	private DownCommand down;
	private UpCommand up;
	private LeftCommand left;
	private RightCommand right;
	private int targetX, targetY;
	
	public MirrorPlayer() {
		super();
		down = new DownCommand(this);
		up = new UpCommand(this);
		left = new LeftCommand(this);
		right = new RightCommand(this);
		targetX = GameConfig.MID_X;
		targetY = GameConfig.MID_Y;
	}
	
	@Override
	public void move(Command command) {
		try {
			command.execute();
			GameController.getInstance().updateTile(this);
		} catch(GameCollisionException e) {
			return;
		}
	}
	@Override
	public void update(Observable o, Object arg1) {
		Player player = (Player) o;
		Point playerPosition = player.getPosition();
		targetX = (GameConfig.MID_X - playerPosition.x) + GameConfig.MID_X;
		targetY = (GameConfig.MID_Y - playerPosition.y) + GameConfig.MID_Y; 
		if(targetX > this.getPosition().x) {
			move(right);
		} else if (targetX < this.getPosition().x) {
			move(left);
		} else if(targetY > this.getPosition().y) {
			move(down);
		} else if(targetY < this.getPosition().y) {
			move(up);
		} else {
			GameController.getInstance().updateTile(this);
		}
		
		
	}
}

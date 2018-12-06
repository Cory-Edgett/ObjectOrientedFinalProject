package command;

import java.util.Observer;
import java.awt.Point;
import java.util.Observable;

public class MirrorPlayer extends Player implements Observer {

	public MirrorPlayer() {
		super();
	}

	@Override
	public void update(Observable o, Object arg1) {
		Player player = (Player) o;
		Point playerPosition = player.getPosition();
	}
}

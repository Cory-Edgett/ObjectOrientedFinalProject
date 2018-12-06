package command;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.concurrent.Task;

public class Player extends Character {
	private static final int SPEED = 1;
	private static final int MAX_MOVES = 50;
	
	private List<Command> moveHistory;
	private final Player me;
	
	public Player() {
		super(Player.SPEED);
		this.setPosition(11, 11);
		moveHistory = new ArrayList<Command>();
		me = this;
	}

	public void move(Command command) {
		try {
			command.execute();
		} catch(GameCollisionException e) {
			return;
		}
		moveHistory.add(command);

		GameController.getInstance().updateTile(this);
		if(moveHistory.size() > MAX_MOVES) {
			recall();
		}
	}
	
	public void recall() {
		Task<Void> recallTask = createRecallTask();
		new Thread(recallTask).start();
	}
	
	private Task<Void> createRecallTask(){
		Task<Void> task = new Task<Void>(){
			@Override
			protected Void call() throws Exception {
				GameController.getInstance().setRecallState();
				Collections.reverse(moveHistory);
				for(Command command : moveHistory) {
					GameController.getInstance().updateTile(me);
					command.reverse();
					Thread.sleep(SPEED * 20);
				}
				moveHistory.removeAll(moveHistory);
				GameController.getInstance().setPlayState();
				return null;
			}
		
		};
		
		return task;
	}
	
}

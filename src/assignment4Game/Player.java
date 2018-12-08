package assignment4Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

import javafx.concurrent.Task;

public class Player extends Character {
	
	private List<Command> moveHistory;
	private final Player me;
	
	public Player() {
		super(GameConfig.SPEED);
		this.setPosition(GameConfig.MID_X, GameConfig.MID_Y);
		moveHistory = new ArrayList<Command>();
		me = this;
	}

	public void move(Command command) {
		try {
			command.execute();
			this.setChanged();
			this.notifyObservers();
		} catch(GameCollisionException e) {
			return;
		}
		moveHistory.add(command);

		GameController.getInstance().updateTile(this);
		if(moveHistory.size() > GameConfig.MAX_MOVES) {
			recall();
		}
	}
	
	public void recall() {
		Task<Void> recallTask = createRecallTask();
		new Thread(recallTask).start();
	}
	
	public void recall(Callable<Void> callback) {
		Task<Void> recallTask = createRecallTask();
		recallTask.setOnSucceeded(e->{
				try {
					GameController.getInstance().setPreviousState();
					callback.call();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		new Thread(recallTask).start();
	}
	
	private Task<Void> createRecallTask(){
		Task<Void> task = new Task<Void>(){
			@Override
			protected Void call() throws Exception {
				GameController.getInstance().setRecallState();
				
				//this notify tells the observers to switch the color of the tile they are currently on
				setChanged();
				notifyObservers();
				
				Collections.reverse(moveHistory);
				for(Command command : moveHistory) {
					GameController.getInstance().updateTile(me);
					command.reverse();
					setChanged();
					notifyObservers();
					Thread.sleep(GameConfig.RECALL_SLEEP);
				}
				moveHistory.removeAll(moveHistory);
				return null;
			}
		
		};
		task.setOnSucceeded(e -> {
			GameController.getInstance().setPreviousState();
		});
		return task;
	}
	// -------------Getter(s)--------------//
	public List<Command> getMoveHistory(){
		return moveHistory;
	}
}

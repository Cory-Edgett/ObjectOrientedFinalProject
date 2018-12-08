package assignment4Game;

import java.util.concurrent.Callable;

public class GameController extends GridController {
	


	private GameStates state;
	private GameStates previousState;
	private Player player;
	private MirrorPlayer mirrorPlayer;
	
	private DownCommand down;
	private UpCommand up;
	private LeftCommand left;
	private RightCommand right;
	
	private static GameController instance = null;
	
	public GameController() {
		super();
		player = new Player();
		mirrorPlayer = new MirrorPlayer();
		state = GameStates.PLAY_STATE;
		
		down = new DownCommand(player);
		up = new UpCommand(player);
		left = new LeftCommand(player);
		right = new RightCommand(player);
		
		
	}
	
	public static GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	public void setRecallState() {
		previousState = state;
		state = GameStates.RECALL_STATE;
	}
	
	public void setPlayState() {
		state = GameStates.PLAY_STATE;
	}
	
	private Void handleMirrorState() {
		if(state == GameStates.PLAY_STATE) {
			setMirrorState();
			player.addObserver(mirrorPlayer);
		} else {
			setPlayState();
			player.deleteObservers();
		}
		return null;
	}
	
	public void setPreviousState() {
		state = previousState;
	}
	
	public void setMirrorState() {
		state = GameStates.MIRROR_STATE;
	}
	
	public GameStates getState() {
		return state;
	}
	
	public void setListener() {
		this.getGridPane().setOnKeyPressed(e -> {
			if(state == GameStates.RECALL_STATE)
				return;
			
			switch(e.getCode()) {
				case SPACE:
					player.recall(new Callable<Void>(){
						@Override
						public Void call() throws Exception {
							return handleMirrorState();
						}
					});
					break;
				case KP_RIGHT:
				case RIGHT:
					player.move(right);
					break;
				case KP_LEFT:
				case LEFT:
					player.move(left);
					break;
				case KP_UP: 
				case UP:
					player.move(up);
					break;
				case KP_DOWN:
				case DOWN:
					player.move(down);
					break;
			default:
				break;
			}
		});
	}
}

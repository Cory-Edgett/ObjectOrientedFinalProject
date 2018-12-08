package command;

import java.awt.Point;
import java.util.HashMap;
import java.util.concurrent.Callable;

import javafx.scene.layout.GridPane;

public class GameController {
	


	private GameStates state;
	private GameStates previousState;
	private Player player;
	private MirrorPlayer mirrorPlayer;
	
	private DownCommand down;
	private UpCommand up;
	private LeftCommand left;
	private RightCommand right;
	
	private GridPane gridPane;
	private HashMap<Point, Tile> gameGrid;
	
	private static GameController instance = null;
	
	public GameController() {
		gameGrid = new HashMap<Point, Tile>();
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
	
	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
		initializeGameGrid();
		setListener();
		gridPane.requestFocus();

	}
	
	public GridPane getGridPane() {
		return gridPane;
	}
	
	public void refreshGridPane() {
		
	}
	
	public void updateTile(Character character) {
		Point position = character.getPosition();
		System.out.println(position);
		Tile tile = (Tile) gridPane.getChildren().get(position.x * GameConfig.MAX_X + position.y);
		tile.toggleColor();
		System.out.println(tile);
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
	
	private void initializeGameGrid() {
		for(int x = GameConfig.MIN_X; x < GameConfig.MAX_X; x++) {
			for(int y = GameConfig.MIN_Y; y < GameConfig.MAX_Y; y++) {
				Tile tile = new Tile();
				gridPane.add(tile, x, y);
				gameGrid.put(new Point(x, y), new Tile());
			}
		}	
	}
	
	private void setListener() {
		gridPane.setOnKeyPressed(e -> {
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

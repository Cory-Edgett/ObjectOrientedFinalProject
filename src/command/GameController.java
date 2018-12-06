package command;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javafx.scene.layout.GridPane;

public class GameController {
	public final static int MIN_X = 0;
	public final static int MAX_X = 21;
	public final static int MIN_Y = 0;
	public final static int MAX_Y = 21;


	private GameStates state;
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
		
		player.addObserver(mirrorPlayer);
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
		Tile tile = (Tile) gridPane.getChildren().get(position.x * MAX_X + position.y);
		tile.toggleColor();
		System.out.println(tile);
	}
	
	public void setRecallState() {
		if(state == GameStates.RECALL_STATE) return;
		toggleState();
	}
	
	public void setPlayState() {
		if(state == GameStates.PLAY_STATE) return;
		toggleState();
	}
	
	private void toggleState() {
		if(state == GameStates.PLAY_STATE) {
			state = GameStates.RECALL_STATE;
		} else {
			state = GameStates.PLAY_STATE;
		}
	}
	
	private void initializeGameGrid() {
		for(int x = MIN_X; x < MAX_X; x++) {
			for(int y = MIN_Y; y < MAX_Y; y++) {
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

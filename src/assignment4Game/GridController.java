package assignment4Game;

import java.awt.Point;
import java.util.HashMap;

import javafx.scene.layout.GridPane;

public abstract class GridController {
	private GridPane gridPane;
	private HashMap<Point, Tile> gameGrid;
	
	public GridController() {
		gameGrid = new HashMap<Point, Tile>();
	}
	
	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
		System.out.println("test");
		initializeGameGrid();
		gridPane.requestFocus();

	}
	
	public GridPane getGridPane() {
		return gridPane;
	}
	
	public void updateTile(Character character) {
		Point position = character.getPosition();
		System.out.println(position);
		Tile tile = (Tile) gridPane.getChildren().get(position.x * GameConfig.MAX_X + position.y);
		tile.toggleColor();
		System.out.println(tile);
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
}

package command;
import javafx.scene.layout.Pane;
public class Tile extends Pane {
	private final static int FILLED = 1;
	private final static int EMPTY = 0;
	private static final String FILL_COLOR = "red";
	private static final String EMPTY_COLOR = "white";
	
	private int fill;
	
	public Tile() {
		super();
		fill = EMPTY;
		setColor(EMPTY_COLOR);
	}
	
	public void toggleColor() {
		if(fill == EMPTY) {
			fill = FILLED;
			setColor(FILL_COLOR);
			return;
		}
		fill = EMPTY;
		setColor(EMPTY_COLOR);
	}
	
	private void setColor(String color) {
		this.setStyle("-fx-background-color: "+ color + ";");
	}
	
}

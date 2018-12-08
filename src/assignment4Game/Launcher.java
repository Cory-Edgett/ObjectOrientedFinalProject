package assignment4Game;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Launcher extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL fxmlFile = this.getClass().getResource("/view/CommandView.fxml");

		FXMLLoader loader = new FXMLLoader(fxmlFile);
		GridPane rootNode = loader.load();
		Scene scene = new Scene(rootNode);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Assignment4");
		primaryStage.setResizable(false);
		GameController.getInstance().setGridPane(rootNode);
		GameController.getInstance().setListener();
		primaryStage.show();
		

		
	}
}

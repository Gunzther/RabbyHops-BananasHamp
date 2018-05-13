package application;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	public static Stage stage;
	public static AudioClip hottoDogu;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			initialize();
			stage = primaryStage;
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("homeUI.fxml"));
//			Parent root = (Parent)FXMLLoader.load(getClass().getResource("waitingHostUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Rabby hops - Bananas Hamp");
			primaryStage.setScene(scene);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					System.exit(0);
				}
			});
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void initialize() {
		game.GameScreen.singleJump = KeyEvent.VK_UP;
		game.GameScreen.singleDash = KeyEvent.VK_DOWN;
		game.GameScreenTeam.playerJump1 = KeyEvent.VK_W;
		game.GameScreenTeam.playerDash1 = KeyEvent.VK_S;
		game.GameScreenTeam.playerJump2 = KeyEvent.VK_UP;
		game.GameScreenTeam.playerDash2 = KeyEvent.VK_DOWN;
	}
}

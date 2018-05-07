package application;

import java.awt.event.KeyEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SettingController {
	public static Stage stage;
	@FXML
	TextField singleJump;
	@FXML
	TextField singleDash;
	@FXML
	TextField playerOneJump;
	@FXML
	TextField playerOneDash;
	@FXML
	TextField playerTwoJump;
	@FXML
	TextField playerTwoDash;
	
	@FXML
	public void initialize() {
		HomeController.settingStage = true;
	}
	
	/** Set keys that player want to play and back to the first page */
	public void handleOK() {
		if (setImpossibleButton()) return;
		setPlayerButton();
		HomeController.stage.close();
		stage = new Stage();
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("homeUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Rabby hops - Bananas Hamp");
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					System.exit(0);
				}
			});
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPlayerButton() {
		boolean sjNotSet = true, sdNotSet = true, pj1NotSet = true, 
				pd1NotSet = true, pj2NotSet = true, pd2NotSet = true;
		// get text from textfield
		String sj = singleJump.getText().toUpperCase();
		String sd = singleDash.getText().toUpperCase();
		String pj1 = playerOneJump.getText().toUpperCase();
		String pd1 = playerOneDash.getText().toUpperCase();
		String pj2 = playerTwoJump.getText().toUpperCase();
		String pd2 = playerTwoDash.getText().toUpperCase();
		//check empty textfield
		if (sj.equals("")) sj = "W";
		if (sd.equals("")) sd = "S";
		if (pj1.equals("")) pj1 = "W";
		if (pd1.equals("")) pd1 = "S";
		if (pj2.equals("")) pj2 = "UP";
		if (pd2.equals("")) pd2 = "DOWN";
		//check symbol key
		if (sj.equals("UP") || sj.equals("DOWN") || sj.equals("SPACE")) {
			game.GameScreen.singleJump = symbolKeyCase(sj);
			singleJump.setText(sj);
			sjNotSet = false;
		}
		if (sd.equals("UP") || sd.equals("DOWN") || sd.equals("SPACE")) {
			game.GameScreen.singleDash = symbolKeyCase(sd);
			singleDash.setText(sd);
			sdNotSet = false;
		}
		if (pj1.equals("UP") || pj1.equals("DOWN") || pj1.equals("SPACE")) {
			game.GameScreenTeam.playerJump1 = symbolKeyCase(pj1);
			playerOneJump.setText(pj1);
			pj1NotSet = false;
		}
		if (pd1.equals("UP") || pd1.equals("DOWN") || pd1.equals("SPACE")) {
			game.GameScreenTeam.playerDash1 = symbolKeyCase(pd1);
			playerOneDash.setText(pd1);
			pd1NotSet = false;
		}
		if (pj2.equals("UP") || pj2.equals("DOWN") || pj2.equals("SPACE")) {
			game.GameScreenTeam.playerJump2 = symbolKeyCase(pj2);
			playerTwoJump.setText(pj2);
			pj2NotSet = false;
		}
		if (pd2.equals("UP") || pd2.equals("DOWN") || pd2.equals("SPACE")) {
			game.GameScreenTeam.playerDash1 = symbolKeyCase(pd2);
			playerTwoDash.setText(pd2);
			pd2NotSet = false;
		}
		if (sjNotSet) game.GameScreen.singleJump = sj.charAt(0);
		if (sdNotSet) game.GameScreen.singleDash = sd.charAt(0);
		if (pj1NotSet) game.GameScreenTeam.playerJump1 = pj1.charAt(0);
		if (pd1NotSet) game.GameScreenTeam.playerDash1 = pd1.charAt(0);
		if (pj2NotSet) game.GameScreenTeam.playerJump2 = pj2.charAt(0);
		if (pd2NotSet) game.GameScreenTeam.playerDash2 = pd2.charAt(0);
	}
	
	public int symbolKeyCase(String key) {
		if(key.equals("UP")) {
			return KeyEvent.VK_UP;
		}
		if(key.equals("DOWN")) {
			return KeyEvent.VK_DOWN;
		}
		return KeyEvent.VK_SPACE;
	}
	
	public boolean setImpossibleButton() {
		boolean result = false;
		if (singleJump.getText().equalsIgnoreCase(singleDash.getText()) && !singleJump.getText().equals("")) {
			singleJump.setStyle("-fx-border-color: red");
			singleDash.setStyle("-fx-border-color: red");
			result = true;
		}
		if (playerOneJump.getText().equalsIgnoreCase(playerOneDash.getText()) && !playerOneJump.getText().equals("")) {
			playerOneJump.setStyle("-fx-border-color: red");
			playerOneDash.setStyle("-fx-border-color: red");
			result = true;
		}
		if (playerTwoJump.getText().equalsIgnoreCase(playerTwoDash.getText()) && !playerTwoJump.getText().equals("")) {
			playerTwoJump.setStyle("-fx-border-color: red");
			playerTwoDash.setStyle("-fx-border-color: red");
			result = true;
		}
		if (!result) {
			singleJump.setStyle("-fx-border-color: default");
			singleDash.setStyle("-fx-border-color: default");
			playerOneJump.setStyle("-fx-border-color: default");
			playerOneDash.setStyle("-fx-border-color: default");
			playerTwoJump.setStyle("-fx-border-color: default");
			playerTwoDash.setStyle("-fx-border-color: default");
		}
		return result;
	}
}

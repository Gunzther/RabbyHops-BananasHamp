package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
		setPlayerButton();
		HomeController.stage.close();
		stage = new Stage();
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("homeUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Rabby hops - Bananas Hamp");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPlayerButton() {
		//TODO: 
	}
}

package application;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import serverAndClient.Client;

public class MultiplayerSelectionController {
	@FXML
	Button createRoom;
	@FXML
	Button back;
	@FXML
	ImageView backImage;
	@FXML
	ImageView createRoomImage;
	@FXML
	TextField gameID;
	@FXML
	TextField port;

	public static Stage stage;
	public static boolean playerNumberStage = false;
	
	Image image1 = new Image(this.getClass().getResourceAsStream("/buttons/BackToMode1.png"));
	Image image2 = new Image(this.getClass().getResourceAsStream("/buttons/BackToMode2.png"));
	Image image3 = new Image(this.getClass().getResourceAsStream("/buttons/createNewRoom1.png"));
	Image image4 = new Image(this.getClass().getResourceAsStream("/buttons/createNewRoom2.png"));
	
	@FXML
	public void initialize() {
		ThemeController.mutiSelectStage = true;
		EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				EventTarget target = event.getTarget();
				if(target == back) backImage.setImage(image2);
				else if(target == createRoom) createRoomImage.setImage(image4);
			}
		};
		EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				EventTarget target = event.getTarget();
				if(target == back) backImage.setImage(image1);
				else if(target == createRoom) createRoomImage.setImage(image3);
			}
		};
		back.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		back.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
		createRoom.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		createRoom.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
	}
	
	public void handleBack() {
		if(ThemeController.stage.isShowing()) ThemeController.stage.close();
		else if(playerNumberStage && PlayerNumberController.stage.isShowing()) PlayerNumberController.stage.close();
		try {
			stage = new Stage();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("modeSelection.fxml"));
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleCreate() {
		if(ThemeController.stage.isShowing()) ThemeController.stage.close();
		else if(playerNumberStage && PlayerNumberController.stage.isShowing()) PlayerNumberController.stage.close();
		try {
			stage = new Stage();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("playerNumberUI.fxml"));
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleOK() {
		int portNumber = Integer.parseInt(port.getText());
		Client client = new Client(gameID.getText(), portNumber);
		try {
			client.openConnection();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

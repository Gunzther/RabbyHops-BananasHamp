package application;

import java.io.File;

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

public class MultiplayerSelectionController {
	@FXML
	Button createRoom;
	@FXML
	Button back;
	@FXML
	ImageView backImage;
	@FXML
	TextField gameID;
	
	public static Stage stage;
	File file1 = new File("src/buttons/BackToMode1.png");
	File file2 = new File("src/buttons/BackToMode2.png");
	
	Image image1 = new Image(file1.toURI().toString());
	Image image2 = new Image(file2.toURI().toString());
	
	@FXML
	public void initialize() {
		ThemeController.mutiSelectStage = true;
		EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				EventTarget target = event.getTarget();
				if(target == back) backImage.setImage(image2);
			}
		};
		EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				EventTarget target = event.getTarget();
				if(target == back) backImage.setImage(image1);
			}
		};
		back.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		back.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
	}
	
	public void handleBack() {
		ThemeController.stage.close();
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
	
}

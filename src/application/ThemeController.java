package application;

import java.io.File;

import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ThemeController {
	@FXML
	Button white;
	@FXML
	Button black;
	@FXML
	Button back;
	@FXML
	ImageView whiteGif;
	@FXML
	ImageView blackGif;
	@FXML
	ImageView backImage;
	
	public static Stage stage;
	
	File file1 = new File("src/objects/whiteGIF.gif");
	File file2 = new File("src/objects/blackGIF.gif");
	File file3 = new File("src/buttons/back.png");
	File file4 = new File("src/buttons/(b)back.png");
	File file5 = new File("src/objects/frame.png");
	
	Image image1 = new Image(file1.toURI().toString());
	Image image2 = new Image(file2.toURI().toString());
	Image image3 = new Image(file3.toURI().toString());
	Image image4 = new Image(file4.toURI().toString());
	Image image5 = new Image(file5.toURI().toString());
	
	@FXML
	public void initialize() {
		ModeController.themeStage = true;
		EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				EventTarget target = event.getTarget();
				if (target == white) {
					whiteGif.setImage(image1);
					blackGif.setImage(image5);
				}
				else if (target == black) {
					blackGif.setImage(image2);
					whiteGif.setImage(image5);
				}
				else if (target == back) backImage.setImage(image4);
			}
		};
		EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				EventTarget target = event.getTarget();
				if (target == white) {
					whiteGif.setImage(null);
					blackGif.setImage(null);
				}
				else if (target == black) {
					blackGif.setImage(null);
					whiteGif.setImage(null);
				}
				else if (target == back) backImage.setImage(image3);
			}
		};
		black.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		white.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		back.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		
		black.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
		white.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
		back.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, event2);
	}
	
	public void handleBack() {
		ModeController.stage.close();
		try {
			stage = new Stage();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("modeSelection.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleWhite() {
		
	}
	
	public void handleBlack() {
		
	}
	
}

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

/**
 * Mode selection
 * 
 * @author Gunthee Tawewatmongkol
 */
public class ModeController {
	public static Stage stage;
	@FXML
	Button single;
	@FXML
	Button multi;
	@FXML
	Button team;
	@FXML
	Button back;
	@FXML
	ImageView singleImage;
	@FXML
	ImageView multiImage;
	@FXML
	ImageView teamImage;
	@FXML
	ImageView backImage;
	
	public static boolean themeStage = false;
	
	File file1 = new File("src/buttons/bigBack1.png");
	File file2 = new File("src/buttons/bigBack2.png");
	File file3 = new File("src/buttons/singleMode1.png");
	File file4 = new File("src/buttons/singleMode2.png");
	File file5 = new File("src/buttons/multiMode1.png");
	File file6 = new File("src/buttons/multiMode2.png");
	File file7 = new File("src/buttons/teamMode1.png");
	File file8 = new File("src/buttons/teamMode3.png");
	
	Image image1 = new Image(file1.toURI().toString());
	Image image2 = new Image(file2.toURI().toString());
	Image image3 = new Image(file3.toURI().toString());
	Image image4 = new Image(file4.toURI().toString());
	Image image5 = new Image(file5.toURI().toString());
	Image image6 = new Image(file6.toURI().toString());
	Image image7 = new Image(file7.toURI().toString());
	Image image8 = new Image(file8.toURI().toString());
	
	/** Set mouse event that make Imageview change when mouse is entered buttons. */
	@FXML
	public void initialize() {
		HomeController.startStage = true;
		EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				EventTarget target = event.getTarget();
				if(target == back) backImage.setImage(image2);
				else if(target == single) singleImage.setImage(image4);
				else if(target == multi) multiImage.setImage(image6);
				else if(target == team) teamImage.setImage(image8);
			}
		};
		EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				EventTarget target = event.getTarget();
				if(target == back) backImage.setImage(image1);
				else if(target == single) singleImage.setImage(image3);
				else if(target == multi) multiImage.setImage(image5);
				else if(target == team) teamImage.setImage(image7);
			}
		};
		//mouse entered target
		back.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		single.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		multi.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		team.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		//mouse exited target
		back.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
		single.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
		multi.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
		team.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
	}
	
	/** Back to the first page */
	public void handleBack() {
		if (HomeController.stage.isShowing()) HomeController.stage.close();
		else if (themeStage && ThemeController.stage.isShowing()) ThemeController.stage.close();
		try {
			stage = new Stage();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("homeUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleSingle() {
		createThemeStage();
	}
	
	public void handleMuti() {
		createThemeStage();
	}
	
	public void handleTeam() {
		createThemeStage();
	}
	
	public void createThemeStage() {
		if (HomeController.stage.isShowing()) HomeController.stage.close();
		else if (themeStage && ThemeController.stage.isShowing()) ThemeController.stage.close();
		try {
			stage = new Stage();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("themeUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

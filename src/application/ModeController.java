package application;

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
import javafx.stage.WindowEvent;

/**
 * Window makes player choose a game mode and handles events caused by user actions in the UI.
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
	public static boolean waitingStage = false;
	
	Image image1 = new Image(this.getClass().getResourceAsStream("/buttons/bigBack1.png"));
	Image image2 = new Image(this.getClass().getResourceAsStream("/buttons/bigBack2.png"));
	Image image3 = new Image(this.getClass().getResourceAsStream("/buttons/singleMode1.png"));
	Image image4 = new Image(this.getClass().getResourceAsStream("/buttons/singleMode2.png"));
	Image image5 = new Image(this.getClass().getResourceAsStream("/buttons/multiMode1.png"));
	Image image6 = new Image(this.getClass().getResourceAsStream("/buttons/multiMode2.png"));
	Image image7 = new Image(this.getClass().getResourceAsStream("/buttons/teamMode1.png"));
	Image image8 = new Image(this.getClass().getResourceAsStream("/buttons/teamMode2.png"));
	
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
	
	/** Back to the first page. */
	public void handleBack() {
		if (HomeController.stage.isShowing()) HomeController.stage.close();
		else if (themeStage && ThemeController.stage.isShowing()) ThemeController.stage.close();
		else if (ThemeController.mutiSelectStage && MultiplayerSelectionController.stage.isShowing()) MultiplayerSelectionController.stage.close();
		else if (waitingStage && WaitingHostController.stage.isShowing()) WaitingHostController.stage.close();
		try {
			stage = new Stage();
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Change mode to single player mode and go to theme selection page. */
	public void handleSingle() {
		ThemeController.mode = "single";
		createThemeStage();
	}
	
	/** Change mode to multiplayer mode and go to theme selection page. */
	public void handleMuti() {
		ThemeController.mode = "multi";
		createThemeStage();
	}
	
	/** Change mode to team mode and go to theme selection page. */
	public void handleTeam() {
		ThemeController.mode = "team";
		createThemeStage();
	}
	
	/** Create theme choosing window and go to theme selection page. */
	public void createThemeStage() {
		if (HomeController.stage.isShowing()) HomeController.stage.close();
		else if (themeStage && ThemeController.stage.isShowing()) ThemeController.stage.close();
		else if (ThemeController.mutiSelectStage && MultiplayerSelectionController.stage.isShowing()) MultiplayerSelectionController.stage.close();
		else if (waitingStage && WaitingHostController.stage.isShowing()) WaitingHostController.stage.close();
		try {
			stage = new Stage();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("themeUI.fxml"));
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

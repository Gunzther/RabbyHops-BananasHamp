package application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Control a starter window, handles events caused by user actions in the UI.
 * 
 * @author Gunthee Tawewatmongkol
 */
public class HomeController {
	public static Stage stage;
	public static boolean startStage = false; 
	public static boolean settingStage = false; 
	public static boolean creditsStage = false;
	@FXML
	Button start;
	@FXML
	Button setting;
	@FXML
	Button credits;
	
	/** Set mouse event that make buttons longer when mouse is entered buttons. */
	@FXML
	public void initialize() {
		EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getTarget() == start) {
					start.setText("       ");
				}
				else if(event.getTarget() == setting) {
					setting.setText("       ");
				}
				else if(event.getTarget() == credits) {
					credits.setText("       ");
				}
			}
		};
		
		EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getTarget() == start) {
					start.setText("");
				}
				else if(event.getTarget() == setting) {
					setting.setText("");
				}
				else if(event.getTarget() == credits) {
					credits.setText("");
				}
			}
		};
		//mouse entered target
		start.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		setting.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		credits.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		//mouse exited target
		start.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
		setting.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
		credits.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
	}
	
	/** Set play buttons. */
	public void handleSetting() {
		if(Main.stage.isShowing()) Main.stage.close();
		else if(startStage && ModeController.stage.isShowing()) ModeController.stage.close();
		else if(settingStage && SettingController.stage.isShowing()) SettingController.stage.close();
		else if(creditsStage && CreditsController.stage.isShowing()) CreditsController.stage.close();
		stage = new Stage();
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("settingUI.fxml"));
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
	
	/** Show credits */
	public void handleCredits() {
		if(Main.stage.isShowing()) Main.stage.close();
		else if(startStage && ModeController.stage.isShowing()) ModeController.stage.close();
		else if(settingStage && SettingController.stage.isShowing()) SettingController.stage.close();
		else if(creditsStage && CreditsController.stage.isShowing()) CreditsController.stage.close();
		stage = new Stage();
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("creditsUI.fxml"));
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
	
	/** Go to mode selection */
	public void handleStart() {
		if(Main.stage.isShowing()) Main.stage.close();
		else if(startStage && ModeController.stage.isShowing()) ModeController.stage.close();
		else if(settingStage && SettingController.stage.isShowing()) SettingController.stage.close();
		else if(creditsStage && CreditsController.stage.isShowing()) CreditsController.stage.close();
		stage = new Stage();
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

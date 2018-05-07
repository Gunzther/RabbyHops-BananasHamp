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

public class CreditsController {
	public static Stage stage;
	@FXML
	Button back;
	
	@FXML
	public void initialize() {
		HomeController.creditsStage = true;
		EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				back.setStyle("-fx-background-color: white;");
			}
		};
		EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				back.setStyle("-fx-background-color: #535353;");
			}
		};
		//mouse entered target
		back.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		//mouse exited target
		back.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
	}
	
	/** Back to the first page */
	public void handleBack() {
		HomeController.stage.close();
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
}

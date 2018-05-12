package application;

import game.GameWindow;
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
	public static String mode;
	public static boolean mutiSelectStage = false;
	
	Image image1 = new Image(this.getClass().getResourceAsStream("/objects/whiteGIF.gif"));
	Image image2 = new Image(this.getClass().getResourceAsStream("/objects/blackGIF.gif"));
	Image image3 = new Image(this.getClass().getResourceAsStream("/buttons/back.png"));
	Image image4 = new Image(this.getClass().getResourceAsStream("/buttons/(b)back.png"));
	Image image5 = new Image(this.getClass().getResourceAsStream("/objects/frame.png"));
	
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
	
	public void handleWhite() {
		game.GameScreen.theme = "w";
		game.GameScreenTeam.theme = "w";
		if(mode.equalsIgnoreCase("multi")) {
			ModeController.stage.close();
			try {
				stage = new Stage();
				Parent root = (Parent)FXMLLoader.load(getClass().getResource("multiplayerSelectionUI.fxml"));
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
		else {
			GameWindow game = new GameWindow();
			game.startGame();
		}
	}
	
	public void handleBlack() {
		game.GameScreen.theme = "b";
		game.GameScreenTeam.theme = "b";
		if(mode.equalsIgnoreCase("multi")) {
			ModeController.stage.close();
			try {
				stage = new Stage();
				Parent root = (Parent)FXMLLoader.load(getClass().getResource("multiplayerSelectionUI.fxml"));
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
		else {
			GameWindow game = new GameWindow();
			game.startGame();
		}
	}
}

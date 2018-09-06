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

/**
 * Control theme selection window and handles events caused by user actions in the UI.
 * 
 * @author Gunthee Tawewatmongkol
 */
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
	public static String mode = "multi";
	public static boolean mutiSelectStage = false;
	private GameWindow myGame;
	
	Image image1 = new Image(this.getClass().getResourceAsStream("/objects/whiteGIF.gif"));
	Image image2 = new Image(this.getClass().getResourceAsStream("/objects/blackGIF.gif"));
	Image image3 = new Image(this.getClass().getResourceAsStream("/buttons/back.png"));
	Image image4 = new Image(this.getClass().getResourceAsStream("/buttons/(b)back.png"));
	Image image5 = new Image(this.getClass().getResourceAsStream("/objects/frame.png"));
	
	/** Set mouse event that make Imageview change when mouse is entered buttons. */
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
		EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(myGame != null && !myGame.isShowing()) setEnableButton();
			}
		};
		
		black.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		white.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		back.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		
		black.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
		white.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
		back.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event2);
		
		ModeController.stage.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event3);
	}
	
	/** Back to the mode selection page. */
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
	
	/** Set GameScreen theme value to white and start game if mode is single player or team.
	 * Going to the multiplayer selection window if mode is multiplayer. 
	 */
	public void handleWhite() {
		game.GameScreen.theme = "w";
		game.GameScreenTeam.theme = "w";
		setDisableButton();
		if(mode.equalsIgnoreCase("multi")) {
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
			ModeController.stage.close();
		}
		if(mode.equals("single") || mode.equals("team")) {
			myGame = new GameWindow();
			myGame.startGame();
		}
	}
	
	/** Set GameScreen theme value to black and start game if mode is single player or team.
	 * Going to the multiplayer selection window if mode is multiplayer. 
	 */
	public void handleBlack() {
		game.GameScreen.theme = "b";
		game.GameScreenTeam.theme = "b";
		setDisableButton();
		if(mode.equalsIgnoreCase("multi")) {
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
			ModeController.stage.close();
		}
		if(mode.equals("single") || mode.equals("team")) {
			myGame = new GameWindow();
			myGame.startGame();
		}
	}
	
	public void setDisableButton() {
		white.setDisable(true);
		back.setDisable(true);
		black.setDisable(true);
	}
	
	public void setEnableButton() {
		white.setDisable(false);
		back.setDisable(false);
		black.setDisable(false);
	}
}

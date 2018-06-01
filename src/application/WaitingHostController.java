package application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import serverAndClient.*;

/**
 * Control waiting window for multiplayer mode 
 * and handles events caused by user actions in the UI.
 * 
 * @author Gunthee Tawewatmongkol
 */
public class WaitingHostController {
	@FXML
	Label ipNumber;
	@FXML
	Label portNumberUI;
	@FXML
	Label waitingMassege;
	@FXML
	Button closeRoom;
	@FXML
	Button openRoom;
	@FXML
	ImageView closeRoomImg;
	@FXML
	ImageView openRoomImg;
	
	private int portNumber;
	private int playerNumber;
	private String ip;
	private Server server;
	public static int playerNumberPb;
	public static Stage stage;
	public static int number = 10000;
	
	Image image1 = new Image(this.getClass().getResourceAsStream("/buttons/openRoom1.png"));
	Image image2 = new Image(this.getClass().getResourceAsStream("/buttons/openRoom2.png"));
	Image image3 = new Image(this.getClass().getResourceAsStream("/buttons/closeRoom1.png"));
	Image image4 = new Image(this.getClass().getResourceAsStream("/buttons/closeRoom2.png"));
	
	public WaitingHostController() {
		this.portNumber = number;
		this.playerNumber = playerNumberPb;
		server = new Server(this.portNumber, playerNumberPb);
	}

	/** Initialize label that show game ID and port number. */
	@FXML
	public void initialize() {
		EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				EventTarget target = event.getTarget();
				if(target == openRoom) openRoomImg.setImage(image2);
				else if(target == closeRoom) closeRoomImg.setImage(image4);
			}
		};
		EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				EventTarget target = event.getTarget();
				if(target == openRoom) openRoomImg.setImage(image1);
				else if(target == closeRoom) closeRoomImg.setImage(image3);
			}
		};
		openRoom.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		openRoom.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, event2);
		closeRoom.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event1);
		closeRoom.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, event2);
		
		ModeController.waitingStage = true;
		ip = getHostNumber();
		ipNumber.setText(ip);
		portNumberUI.setText(String.format("%d", this.portNumber));
		waitingMassege.setText(String.format("GAME FOR [ %d ] PLAYERS", this.playerNumber));
	}
	
	/** Get IP address. */
	public String getHostNumber() {
		InetAddress address = null;
		int x = 0;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		 int length = address.toString().length();
		 String addressSt = address.toString();
		 for(x = 0; x < length; x++) {
			 char cha = addressSt.charAt(x);
			 if(cha == '/') break;
		 }
		 String ip = addressSt.substring(x+1, length);
		 return ip;
	}
	
	/** Start server and create host Client. */
	public void handleOpenRoom() {
		serverStart(server);
		Client client = new Client(ip, portNumber);
		try {
			client.openConnection();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/** Close server and back to mode selection window. */
	public void handleCloseRoom() {
		serverStop();
		PlayerNumberController.stage.close();
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
	
	public void handlePlayAgain() {
		server.resetRank();
		server.sendToAllClients(String.format("again"));
	}
	
	public void serverStart(Server server) {
		try {
			server.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void serverStop() {
		server.sendToAllClients(String.format("close"));
		this.server.stopListening();
		try {
			this.server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



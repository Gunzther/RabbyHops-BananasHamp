package application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import game.GameScreen;
import game.GameWindow;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import serverAndClient.*;

public class WaitingHostController {
//	@FXML
//	Label ipNumber;
//	@FXML
//	Label portNumberUI;
//	@FXML
//	Label waitingMassege;
//	@FXML
//	Button closeRoom;
	
	private int portNumber;
	private int playerNumber;
	private Server server;
	private Client client;
	public static int playerNumberPb;
	public static Stage stage;
	
	public WaitingHostController(int portNumber, int playerNumber) {
		this.portNumber = portNumber;
		this.playerNumber = playerNumber;
		this.server = new Server(this.portNumber, this.playerNumber);
		System.out.println("new server");
		openWaitingHostScreen();
		initialize();
	}
	
	public void initialize() {
		System.out.println("init");
		playerNumberPb = playerNumber;
		System.out.println("player number: "+playerNumber);
		serverStart();
		String ip = getHostNumber();
		System.out.println("ip: "+ip);
//		ipNumber.setText(ip);
		if(ip == null) System.out.println("null");
//		portNumberUI.setText(String.format("%d", this.portNumber));
		System.out.println("port: "+ this.portNumber);
		client = new Client(ip, this.portNumber);
		int clientNumber = this.server.getNumberOfClients();
		System.out.println("num: "+clientNumber);
		try {
			client.openConnection();
			while(client.isConnected()) {
				int nowClient = this.server.getNumberOfClients();
				if(nowClient != clientNumber) {
					clientNumber = nowClient;
//					waitingMassege.setText(String.format("WAITING. . . [ %d ] PLAYERS", playerNumber-clientNumber));
					if(playerNumber-clientNumber == 0) break;
				}
			}
//			waitingMassege.setText("PLAYING. . .");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
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
	
	public void handleCloseRoom() {
		serverStop();
		//back to mode selection
	}
	
	public void handlePlayAgain() {
		server.sendToAllClients("ready");
	}
	
	public void serverStart() {
		try {
			server.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void serverStop() {
		//client sent to server to close all game
		this.server.stopListening();
		try {
			this.server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openWaitingHostScreen() {
//		if(ThemeController.stage.isShowing()) ThemeController.stage.close();
		try {
			stage = new Stage();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("waitingHostUI2.fxml"));
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



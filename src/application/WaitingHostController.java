package application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import serverAndClient.*;

public class WaitingHostController {
	@FXML
	Label ipNumber;
	@FXML
	Label portNumberUI;
	@FXML
	Label waitingMassege;
	@FXML
	Button closeRoom;
	
	private int portNumber;
	private int playerNumber;
	private String ip;
	private Server server;
	public static int playerNumberPb;
	public static Stage stage;
	public int number = 10000;
	
	public WaitingHostController() {
		this.portNumber = number;
		this.playerNumber = playerNumberPb;
		server = new Server(this.portNumber, playerNumberPb);
		number++;
	}

	@FXML
	public void initialize() {
		ip = getHostNumber();
		ipNumber.setText(ip);
		portNumberUI.setText(String.format("%d", this.portNumber));
		if(waitingMassege == null) System.out.println("null");
		waitingMassege.setText(String.format("GAME FOR [ %d ] PLAYERS", this.playerNumber));
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
	
	public void handleOpenRoom() {
		serverStart(server);
		Client client = new Client(ip, portNumber);
		try {
			client.openConnection();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void handleCloseRoom() {
		serverStop();
		//back to mode selection
	}
	
	public void handlePlayAgain() {
		server.sendToAllClients("ready");
	}
	
	public void serverStart(Server server) {
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
}



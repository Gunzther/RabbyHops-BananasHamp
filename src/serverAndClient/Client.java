package serverAndClient;

import java.io.IOException;

import com.lloseng.ocsf.client.AbstractClient;

import application.WaitingHostController;
import game.GameWindow;

public class Client extends AbstractClient {
	
	private String messageFromServer;
	private GameWindow game;

	public Client(String host, int port) {
		super(host, port);
		this.messageFromServer = "";
		this.game = new GameWindow();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		this.messageFromServer = msg.toString();
		if(messageFromServer.equals("ready")) {
			System.out.println("ready");
			main();
		}
	}
	
	public String getMessageFromServer() {
		return messageFromServer;
	}

	public void main() {
//		game.resetGame();
		setdefaultRank(WaitingHostController.playerNumberPb);
		game.startGame();
		try {
			sendToServer(new String("end"));
			if(messageFromServer.equals("3")) {
				game.setReplay("third.png");
			}
			if(messageFromServer.equals("2")) {
				game.setReplay("second.png");
			}
			if(messageFromServer.equals("1")) {
				game.setReplay("first.png");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void connectionEstablished() {
		super.connectionEstablished();
	}
	
	private void setdefaultRank(int numberOfPlayer) {
		if(numberOfPlayer == 4) game.setReplay("fourth.png");
		if(numberOfPlayer == 3) game.setReplay("third.png");
		if(numberOfPlayer == 2) game.setReplay("second.png");
		if(numberOfPlayer == 1) game.setReplay("first.png");
	}
}

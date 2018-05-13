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
			main();
		}
	}
	
	public void main() {
		game.resetGame();
		setdefaultRank(WaitingHostController.playerNumberPb);
		game.startGame();
		while(!game.getEndGame()) {
			if(messageFromServer.equals("3")) {
				game.setReplay("third.png");
			}
			if(messageFromServer.equals("2")) {
				game.setReplay("second.png");
			}
			if(messageFromServer.equals("1")) {
				game.setReplay("first.png");
			}
		}
		try {
			sendToServer(new String("end"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void connectionEstablished() {
		super.connectionEstablished();
		System.out.println("Connected");
	}
	
	private void setdefaultRank(int numberOfPlayer) {
		if(numberOfPlayer == 4) game.setReplay("fourth.png");
		if(numberOfPlayer == 3) game.setReplay("third.png");
		if(numberOfPlayer == 2) game.setReplay("second.png");
		if(numberOfPlayer == 1) game.setReplay("first.png");
	}
}

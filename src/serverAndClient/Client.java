package serverAndClient;

import java.io.IOException;

import com.lloseng.ocsf.client.AbstractClient;
import game.GameWindow;

public class Client extends AbstractClient {
	
	private String messageFromServer;
	private GameWindow game;
	public static int rankPb;
//	private int rank;

	public Client(String host, int port) {
		super(host, port);
		this.messageFromServer = "";
		this.game = new GameWindow();
//		this.rank = 2;
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		this.messageFromServer = msg.toString();
		if(messageFromServer.equals("close")) this.game.closeWindow();
		if(messageFromServer.equals("ready")) {
			this.game.startGame();
			try {
				while(true) {
					System.out.println("playing");
					if(game.isEnd()) {
						sendToServer(String.format("end"));
						break;
					}
				}
			} catch (IOException e) {}
		}
		if(messageFromServer.equals("4")) {
			rankPb = 4;
			this.game.setRank();
//			try { this.closeConnection(); } catch (IOException e) {}
		}
		if(messageFromServer.equals("3")) {
			rankPb = 3;
			this.game.setRank();
//			try { this.closeConnection(); } catch (IOException e) {}
		}
		if(messageFromServer.equals("2")) {
			rankPb = 2;
			this.game.setRank();
//			try { this.closeConnection(); } catch (IOException e) {}
		}
		if(messageFromServer.equals("1")) {
			rankPb = 1;
			this.game.setRank();
//			try { this.closeConnection(); } catch (IOException e) {}
		}
		if(messageFromServer.equals("again")) {
			this.game.reset();
		}
	}

	@Override
	protected void connectionEstablished() {
		super.connectionEstablished();
	}
	
	public GameWindow getGame() {
		return this.game;
	}
}

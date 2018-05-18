package serverAndClient;

import com.lloseng.ocsf.client.AbstractClient;
import game.GameWindow;

public class Client extends AbstractClient {
	
	private String messageFromServer;
	private GameWindow game;
	public static int rankPb;

	public Client(String host, int port) {
		super(host, port);
		this.messageFromServer = "";
		this.game = new GameWindow();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		this.messageFromServer = msg.toString();
		if(messageFromServer.equals("up")) {
			if(this.game.isEnd()) this.connectionClosed();
			else rankPb -= 1;
		}
		if(messageFromServer.equals("4")) rankPb = 4;
		if(messageFromServer.equals("3")) rankPb = 3;
		if(messageFromServer.equals("2")) rankPb = 2;
		if(messageFromServer.equals("ready")) {
			System.out.println("first rank: " + rankPb);
			this.game.startGame();
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
	
//	public void tellServerToUpdateRank() {
//		try {
//			while(this.game.isActive()) {
//				System.out.println("boolean end: "+game.isEnd());
//				if(game.isEnd() == true) break;
//			}
//			System.out.println("end: "+game.isEnd());
//			if(game.isEnd()) {
//				sendToServer(String.format("end"));
//				System.out.println("sent end");
//				this.connectionClosed();
//			}
//			System.out.println("done");
//		} catch (IOException e) {}
//	}
}

package serverAndClient;


import java.io.IOException;
import com.lloseng.ocsf.client.AbstractClient;
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
	
	public String getMessageFromServer() {
		return messageFromServer;
	}

	public void main() {
		this.game.startGame();
		try {
			sendToServer(new String("end"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void connectionEstablished() {
		super.connectionEstablished();
	}
}

package serverAndClient;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {
	
	private int playerNumber;
	private int num;
	
	public Server(int port, int playerNumber) {
		super(port);
		this.playerNumber = playerNumber;
		this.num = playerNumber;
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if(msg.toString().equals("end")) {
			sendToAllClients(String.format("up"));
		}
	}
	
	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		playerNumber -= 1;
		if(playerNumber <= 0) {
			sendToAllClients(String.format("%d", this.num));
			sendToAllClients(String.format("ready"));
		}
	}

	@Override
	protected void serverStarted() {
		super.serverStarted();
	}
}

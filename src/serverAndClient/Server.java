package serverAndClient;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {
	
	private int playerNumber;
	private int rank;
	static int number = 1;
	
	public Server(int port, int playerNumber) {
		super(port);
		this.playerNumber = playerNumber;
		this.rank = playerNumber;
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if(msg.toString().equals("end")) {
			rank--;
			sendToAllClients(String.format("%d", rank));
		}
	}
	
	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		client.setInfo("name", number++);
		System.out.println(client.getInfo("name") + " connected");
		playerNumber -= 1;
		if(playerNumber <= 0) {
			sendToAllClients(String.format("ready"));
		}
	}

	@Override
	protected void serverStarted() {
		super.serverStarted();
		System.out.println("Server started");
	}
}

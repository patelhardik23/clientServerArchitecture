package testing;

import com.multiThread.client.ThreadedUDPClient;
import com.multiThread.data.Connection;
import com.multiThread.data.Packet;
import com.multiThread.data.PacketHandler;
import com.multiThread.data.UID;
import com.multiThread.server.ThreadedUDPServer;

public class Test {

	private static ThreadedUDPServer server;
	private static ThreadedUDPClient client;

	public static void main(String[] args)
	{
		test();
	}

	public static void test() {
		
		// Create our server and client intances
		server = new ThreadedUDPServer(1337);

		// Set up a handler for receiving the packets
		server.receive(new PacketHandler() {

			@Override
			public void process(Packet packet) {
				String data = new String(packet.getData()).trim();
		
				if(data.equals("CON")) {
					ThreadedUDPServer.CLIENTS.add(packet.getConnection());
					reply(new Packet("OK".getBytes(), packet.getAddr(), packet.getPort()));
				}
			}
			
		});

		client = new ThreadedUDPClient("localhost", 1337);

		client.receive(new PacketHandler() {

			@Override
			public void process(Packet packet) {
				String data = new String(packet.getData()).trim();
				System.out.println(data.trim());
			}
			
		});
		
		client.send("CON".getBytes());
		
		

	}
	
	public static void reply(Packet packet) {
		server.broadcast(new String(packet.toString()).getBytes());
	}

}

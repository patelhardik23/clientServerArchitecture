package com.multiThread.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import com.multiThread.data.Connection;
import com.multiThread.data.Packet;
import com.multiThread.data.PacketHandler;
import com.multiThread.data.UID;

/**
 * A class for handling a multi-threaded instance of a UDP server
 * @author craig
 * @version 0.1
 */
public class ThreadedUDPServer implements Runnable {
	
	/* Server information */
	private int port;
	private DatagramSocket socket;
	private boolean running;
	
	/* Threads */
	private Thread send, receive, process;
	
	/* Client relevant */
	public static ArrayList<Connection> CLIENTS = new ArrayList<Connection>();
	
	
	/**
	 * Construct a new instance of a multi-threaded udp server
	 * @param port
	 */
	public ThreadedUDPServer(int port) {
		this.port = port;
		
		try {
			this.init();
		} catch (SocketException e) {
			System.err.println("Unable to initialise the server..." + e.getMessage());
		}
	}
	
	/**
	 * Initialise the server
	 * @throws SocketException 
	 */
	public void init() throws SocketException {
		this.socket = new DatagramSocket(this.port);
		process = new Thread(this, "server_process");
		process.start();
	}
	
	/**
	 * Get the port that the server is binded to
	 * @return port
	 */
	public int getPort() {
		return port;
	}
	
	
	/**
	 * Send a packet to a client
	 * @param packet
	 */
	public void send(final Packet packet) {
		send = new Thread("send_thread") {
			public void run() {
				System.out.println(send.getName()+" started");
				DatagramPacket dgpack = new DatagramPacket(
						packet.getData(), 
						packet.getData().length, 
						packet.getAddr(), 
						packet.getPort()
				);
	
				try {
					socket.send(dgpack);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		};
		
		send.start();
	}
	
	/**
	 * Send a packet to all connected clients
	 * @param data
	 */
	public void broadcast(byte[] data) {
		for(Connection c : CLIENTS) {
			send(new Packet(data, c.getAddress(), c.getPort()));
		}
	}
	
	/**
	 * Wait for input... and use a PacketHandler to process the packet
	 * @param handler The packet handler 
	 */
	public void receive(final PacketHandler handler) {
		receive = new Thread("server_receive_thread") {
			public void run() {
				System.out.println(receive.getName()+" started");
				while(running) {
					byte[] buffer = new byte[1024];
					DatagramPacket dgpacket = new DatagramPacket(buffer, buffer.length);
					
					try {
						socket.receive(dgpacket);
					} catch (IOException e) {
						e.printStackTrace();
					}
					handler.process(new Packet(dgpacket.getData(), new Connection(socket, dgpacket.getAddress(), dgpacket.getPort(), UID.getIdentifier())));
					//handler.process(new Packet(dgpacket.getResultSet(), dgpacket.getAddress(), dgpacket.getPort()));
				}
			}
		};
		
		receive.start();
	}
	
	/**
	 * The run method of this runnable thread
	 * object.
	 */
	public void run() {
		running = true;
		System.out.println("Server started on port " + port);
	}
}
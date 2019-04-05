package com.multiThread.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.multiThread.data.Connection;
import com.multiThread.data.Packet;
import com.multiThread.data.PacketHandler;

/**
 * A class for handling a multi-threaded instance of a UDP client
 * @author craig
 *
 */
public class ThreadedUDPClient implements Runnable {
	
	private Connection connection;
	private boolean running;
	
	private DatagramSocket socket;
	private Thread process, send, receive;
	
	public ThreadedUDPClient(String addr, int port) {
		try {
			socket = new DatagramSocket();
			connection = new Connection(socket, InetAddress.getByName(addr), port, 0);
			this.init();
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialise the client
	 */
	private void init() {
		process = new Thread(this, "client_process");
		process.start();
	}
	
	/**
	 * Send some data
	 * @param data
	 */
	public void send(final byte[] data) {
		send = new Thread("client_send_thread") {
			public void run() {
				System.out.println(send.getName()+" started");
				connection.send(data);
			}
		};
		send.start();
	}
	
	/**
	 * Receive data on the given server connection
	 */
	public void receive(final PacketHandler handler) {
		receive = new Thread("client_receive_thread") {
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
					
					handler.process(new Packet(dgpacket.getData(), dgpacket.getAddress(), dgpacket.getPort()));
				}
			}
		};
		
		receive.start();
	}
	
	/**
	 * Close the current connection for this client
	 */
	public void close() {
		connection.close();
		running = false;
	}
	
	@Override
	public void run() {
		running = true;
		System.out.println(process.getName() + " Main Thread started");
	}

}

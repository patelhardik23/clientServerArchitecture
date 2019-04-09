/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationquerysystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server implements Runnable
{
    /**
     * @param args the command line arguments
     */
    DatagramSocket socket;
    DatagramPacket packet;
    String requestMessage;
    Integer requestType;
    Data d = new Data();

    public static void main(String[] args) throws Exception
    {
        int port = 5050; //Port number on which server is listening
        DatagramSocket socket = new DatagramSocket(port); //a datagram socket and bound to the 'port'
        final int BUFFER_SIZE = 1000;
        byte[] buffer = new byte[BUFFER_SIZE];
        DatagramPacket packet; //used for sending data
        System.out.println("Server is running...");
        while (true)
        {
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String msg = new String(packet.getData(), 0, packet.getLength());// convert received data into String format
            System.out.println("Request : " + msg);
            String[] split = msg.split(">,<"); //split msg to identify the type of query

            split[0] = split[0].replace("<", "");
            split[1] = split[1].replace(">", "");

            Integer requestType = Integer.parseInt(split[0]);
            String data = split[1];

            //depending upon the type of query start a new thread
            Thread t1, t2, t3;

            if (split[0].equals("1"))
            {
                t1 = new Thread(new Server(socket, packet, data, requestType));
                t1.run();
            }
            else if (split[0].equals("2"))
            {
                //thread for getting the enrollment units
                t2 = new Thread(new Server(socket, packet, data, requestType));
                t2.start();
            }
            else if (split[0].equals("3"))
            {
                //thread for unit details
                t3 = new Thread(new Server(socket, packet, data, requestType));
                t3.start();
            }
            buffer = new byte[BUFFER_SIZE];
        }
    }

    public Server(DatagramSocket socket, DatagramPacket packet, String requestMsg, Integer requestType)
    {
        this.socket = socket;
        this.packet = packet;
        this.requestMessage = requestMsg;
        this.requestType = requestType;
    }

    @Override
    public void run()
    {
        try
        {
            String response = null;
            if (requestType == 1)
            {
                if (d.getContactDetails().containsKey(requestMessage))
                    response = d.getContactDetails().get(requestMessage);
            }
            else if (requestType == 2)
            {
                if (d.getEnrolledUnits().containsKey(requestMessage))
                    response = d.getEnrolledUnits().get(requestMessage);
            }
            else if (requestType == 3)
            {
                if (d.getUnitCode().containsKey(requestMessage))
                    response = d.getUnitCode().get(requestMessage);
            }
            else
            {
                response = "No record for " + requestMessage;
            }
            System.out.println("Response : " + response);
            byte[] buffer = response.getBytes(); // stores the message in byte array
            InetAddress address = packet.getAddress(); //get address of destination
            int port = packet.getPort(); //get the port number
            packet = new DatagramPacket(buffer, buffer.length, address, port); //packet that stores the message
            socket.send(packet); //send the packet
        }
        catch (Exception e)
        {
            System.out.println("Server Error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

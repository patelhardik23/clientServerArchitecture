package com.multiThread.client;

import com.multiThread.data.DataPacket;
import com.multiThread.data.RequestType;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author KEVAL
 * Date: 3/29/2019
 */
public class UdpBaseClient
{
    public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(System.in);

        // Step 1:Create the socket object for
        // carrying the data.
        DatagramSocket ds = new DatagramSocket();


        InetAddress ip = InetAddress.getLocalHost();
        ip = InetAddress.getByName("192.168.0.42");
        int port = 1234;

        byte buf[] = null;

        byte receive[] = new byte[65535];

        DatagramPacket DpReceive = null;
        // loop while user not enters "bye"
        while (true)
        {
            String inp = sc.nextLine();

            // convert the String input into the byte array.
            buf = inp.getBytes();

            // Step 2 : Create the datagramPacket for sending
            // the data.
            DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, port);

            // Step 3 : invoke the send call to actually send
            // the data.
            ds.send(DpSend);

            DpReceive = new DatagramPacket(receive,receive.length);
            ds.receive(DpReceive);

            System.out.println(data(receive));

            //Send Object as a stream -- START
            DataPacket dataPacket = new DataPacket();
            dataPacket.setRequestType(RequestType.GETID);
            dataPacket.setResultSet("Initial Testing");

            ByteArrayOutputStream baoStream = new ByteArrayOutputStream(5000);
            ObjectOutputStream ooStream = new ObjectOutputStream(new BufferedOutputStream((baoStream)));
            ooStream.flush();
            ooStream.writeObject(dataPacket);
            ooStream.flush();

            byte[] sendBuf = baoStream.toByteArray();
            DpSend = new DatagramPacket(sendBuf,sendBuf.length,ip,port);
            ds.send(DpSend);

            ooStream.close();
            baoStream.close();
            //Send Object as a stream -- END


            // break the loop if user enters "bye"
            if (inp.equals("bye"))
                break;
            receive = new byte[65535];
        }
    }

    // A utility method to convert the byte array
    // data into a string representation.
    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}

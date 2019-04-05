package com.multiThread.server;

import com.multiThread.data.DataPacket;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author KEVAL
 * Date: 3/29/2019
 */
public class UdpBaseServer
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        // Step 1 : Create a socket to listen at port 1234
        DatagramSocket ds = new DatagramSocket(1234);
        byte[] receive = new byte[65535];

        DatagramPacket DpReceive = null;
        DatagramPacket DpSend = null;
        while (true)
        {

            // Step 2 : create a DatgramPacket to receive the data.
            DpReceive = new DatagramPacket(receive, receive.length);

            // Step 3 : revieve the data in byte buffer.
            ds.receive(DpReceive);

            System.out.println(ds.getLocalPort()+"-> "+DpReceive.getPort()+" Client:-" + data(receive));
            String reply = "Hi "+data(receive);

            DpSend = new DatagramPacket(reply.getBytes(),reply.getBytes().length,DpReceive.getAddress(),DpReceive.getPort());

            ds.send(DpSend);


            //Receive Object as a stream -- START
            byte[] recvBuf = new byte[5000];
            DpReceive = new DatagramPacket(recvBuf,recvBuf.length);

            ds.receive(DpReceive);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(recvBuf);
            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(byteArrayInputStream));

            DataPacket dataPacket = (DataPacket )objectInputStream.readObject();

            objectInputStream.close();
            byteArrayInputStream.close();
            //Receive Object as a stream -- END

            // Exit the server if the client sends "bye"
            if (data(receive).toString().equals("bye"))
            {
                System.out.println("Client sent bye.....EXITING");
                break;
            }

            // Clear the buffer after every message.
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

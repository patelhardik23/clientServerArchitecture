package com.multiThread.client;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author KEVAL
 * Date: 4/2/2019
 */
public class Sender
{
    public void sendTo(Object o, String hostName, int desPort)
    {
        try
        {
            InetAddress address = InetAddress.getByName(hostName);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
            ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
            os.flush();
            os.writeObject(o);
            os.flush();
            //retrieves byte array
            byte[] sendBuf = byteStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, address, desPort);
            int byteCount = packet.getLength();
            //dSock.send(packet);
            os.close();
        }
        catch (UnknownHostException e)
        {
            System.err.println("Exception:  " + e);
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

package com.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author KEVAL
 * Date: 4/5/2019
 */
public class Server extends RemoteInterfaceImpl
{
    public static void main(String[] args)
    {
        try
        {
            RemoteInterfaceImpl remoteInterface = new RemoteInterfaceImpl();
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(remoteInterface,1234);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("RemoteInterface",stub);
            System.out.print("Server Ready");
        }
        catch (RemoteException e)
        {
            System.err.print("Remote Exception occurred: "+e.getMessage());
            e.printStackTrace();
        }
    }
}

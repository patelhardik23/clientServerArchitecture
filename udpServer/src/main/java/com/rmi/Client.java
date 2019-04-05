package com.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * @author KEVAL
 * Date: 4/5/2019
 */
public class Client
{
    public static void main(String[] args)
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry();
            RemoteInterface stub = (RemoteInterface) registry.lookup("RemoteInterface");
            List<Student> studentList = stub.getStudents();
            for(Student student:studentList)
            {
                System.out.println("ID: "+student.getId());
                System.out.println("Name: "+student.getName());
            }
        }
        catch (RemoteException e)
        {
            System.err.print("Remote Exception occurred: "+e.getMessage());
            e.printStackTrace();
        }
        catch (NotBoundException e)
        {
            System.err.print("NoBound Exception occurred: "+e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}

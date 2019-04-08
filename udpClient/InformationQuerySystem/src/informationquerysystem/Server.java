/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationquerysystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author saurabh
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    private static final HashMap<String, Details> student_info = new HashMap<>();
    public static void main(String[] args)throws Exception {
        // TODO code application logic here
        loadStudentInfo(); //load student information into HashMap
        int port = 8888; //Port number on which server is listening
        DatagramSocket socket = new DatagramSocket(port); //a datagram socket and bound to the 'port'
        byte[] buffer = new byte[10000]; 
        DatagramPacket pkt; //used for sending data
        System.out.println("Server is running...");
        while(true){
            pkt = new DatagramPacket(buffer, buffer.length);
            socket.receive(pkt);
            
            String msg = new String(pkt.getData(), 0, pkt.getLength());// convert received data into String format
            //System.out.println(msg);
            String[] msg_ar = msg.split(" "); //split msg to identify the type of query
            //depending upon the type of query start a new thread
            if(msg_ar[0].compareTo("1")==0){
                //System.out.println(msg);
                //thread for getting contact
                Thread t1 = new Thread(new Process(Integer.parseInt(msg_ar[0]), msg_ar[1], student_info, socket, pkt));
                t1.start();
                
            }
            else if(msg_ar[0].compareTo("2")==0){
                //thread for getting the enrollment units
                Thread t2 = new Thread(new Process(Integer.parseInt(msg_ar[0]), msg_ar[1], student_info, socket, pkt));
                t2.start();
            }
            else if(msg_ar[0].compareTo("3")==0) {
                //thread for unit details
                Thread t3 = new Thread(new Process(Integer.parseInt(msg_ar[0]), msg_ar[1], student_info, socket, pkt));
                t3.start();
            }
            else{
                //do nothing
            }
        }
    }
    //Demo Student Information put in the HashMap 'student_info'
    public static void loadStudentInfo(){
        Details d1 = new Details("Contact1","EnrolledUnits1","Unit1");
        student_info.put("S1000001", d1);
        
        Details d2 = new Details("Contact2","EnrolledUnits2","Unit2");
        student_info.put("S1000002", d2);
        
        Details d3 = new Details("Contact3","EnrolledUnits3","Unit3");
        student_info.put("S1000003", d3);
        
        Details d4 = new Details("Contact4","EnrolledUnits4","Unit4");
        student_info.put("S1000004", d4);
        
        Details d5 = new Details("Contact5","EnrolledUnits5","Unit5");
        student_info.put("S1000005", d5);
        
        Details d6 = new Details("Contact6","EnrolledUnits6","Unit6");
        student_info.put("S1000006", d6);
        
    }

static class Process implements Runnable { 
    int type; //type of the query
    String id; // Student ID
    String send_msg; // Reply from the server
    HashMap<String, Details> student_info; //Details of Students
    DatagramSocket socket; //Socket to carry the packet from Server to Client
    DatagramPacket pkt; // Packet which is carried
    //constructor to initialize the data items
    public Process(int type, String id, HashMap<String, Details> student_info, DatagramSocket s, DatagramPacket p){
        this.type = type;
        this.id = id;
        this.student_info = student_info;
        socket = s;
        pkt = p;
    }
    
    @Override
    public void run() { // process the query based on its type and stores in 'send_msg'
        try{ 
            if(student_info.containsKey(id)){
                switch (type) {
                    case 1:
                        send_msg = student_info.get(id).contact;
                        break;
                    case 2:
                        send_msg = student_info.get(id).enrolled_units;
                        break;
                    case 3:
                        send_msg = student_info.get(id).unit;
                        break;
                }
            }
            else // if student info is not present in HashMap
                send_msg = "No record for "+id+" found";
            
            byte[] buffer = send_msg.getBytes(); // stores the message in byte array
            InetAddress address = pkt.getAddress(); //get address of destination
            int port = pkt.getPort(); //get the port number 
            //System.out.println("Sending "+send_msg+" buffer length: "+buffer.length);
            pkt = new DatagramPacket(buffer, buffer.length, address, port); //packet that stores the message
            socket.send(pkt); //send the packet
        } 
        catch (Exception e) { 
            JOptionPane.showInputDialog(null, "ERROR", null);
        } 
    } 
} 

}
// class to store different information of Students
class Details{
    String contact;
    String enrolled_units;
    String unit;
    public Details(String a, String b, String c){
        contact = a;
        enrolled_units = b;
        unit = c;
    }
}
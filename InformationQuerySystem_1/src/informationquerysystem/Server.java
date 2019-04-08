/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informationquerysystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Hardik Patel
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    private static final HashMap<String, String> contact_details = new HashMap<>();
    private static final HashMap<String, ArrayList<String>> enrolled_units = new HashMap<>();
    private static final HashMap<String, String> unit_code = new HashMap<>();
    
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
                Thread t1 = new Thread(new Process(Integer.parseInt(msg_ar[0]), msg_ar[1], contact_details, enrolled_units, unit_code, socket, pkt));
                t1.start();
                
            }
            else if(msg_ar[0].compareTo("2")==0){
                //thread for getting the enrollment units
                Thread t2 = new Thread(new Process(Integer.parseInt(msg_ar[0]), msg_ar[1], contact_details, enrolled_units, unit_code, socket, pkt));
                t2.start();
            }
            else if(msg_ar[0].compareTo("3")==0) {
                //thread for unit details
                Thread t3 = new Thread(new Process(Integer.parseInt(msg_ar[0]), msg_ar[1], contact_details, enrolled_units, unit_code, socket, pkt));
                t3.start();
            }
            else{
                //do nothing
            }
        }
    }
    //Demo Student Information put in the HashMap 'student_info'
    public static void loadStudentInfo(){
        String id1 = "S1000001";
        contact_details.put(id1, "S1000001 Nirav Patel Melbourn Australia niravpatel@cqu.edu.au");
        ArrayList<String> al1 = new ArrayList<>();
        al1.add("COIT20262");al1.add("COIT20257");al1.add("COIT20259");
        enrolled_units.put(id1, al1);
        
        String id2 = "S1000002";
        contact_details.put(id2, "Contact2");
        ArrayList<String> al2 = new ArrayList<>();
        al2.add("Unit1");al2.add("Unit2");al2.add("Unit4");
        enrolled_units.put(id2, al2);
        
        String id3 = "S1000003";
        contact_details.put(id3, "Contact3");
        ArrayList<String> al3 = new ArrayList<>();
        al3.add("Unit1");al3.add("Unit3");
        enrolled_units.put(id3, al3);
        
        unit_code.put("Unit1", "Detail1");
        unit_code.put("Unit2", "Detail2");
        unit_code.put("Unit3", "Detail3");
        unit_code.put("Unit4", "Detail4");
        unit_code.put("COIT20262","COIT20262 Detaails of Subject: Level term1, term 2");
        unit_code.put("COIT20257","COIT20257 Distributed Systems: Principles and Development Level 3 Term1, Term2, Term3");
        unit_code.put("COIT20259","COIT20259 Details of subject: Level Terms");
        
    }

static class Process implements Runnable { 
    int type; //type of the query
    String id; // Student ID
    String send_msg; // Reply from the server
    HashMap<String, String> contact_details; //Contact Details of Students
    HashMap<String, ArrayList<String>> enrolled_units; //Details of enrolled units
    HashMap<String, String> unit_code; //detail of unit codes
    
    DatagramSocket socket; //Socket to carry the packet from Server to Client
    DatagramPacket pkt; // Packet which is carried
    //constructor to initialize the data items
    public Process(int type, String id, HashMap<String, String> contact_details, HashMap<String, ArrayList<String>> enrolled_units, HashMap<String, String> unit_code,DatagramSocket s, DatagramPacket p){
        this.type = type;
        this.id = id;
        this.contact_details = contact_details;
        this.enrolled_units = enrolled_units;
        this.unit_code = unit_code;
        socket = s;
        pkt = p;
    }
    
    @Override
    public void run() { // process the query based on its type and stores in 'send_msg'
        try{ 
                switch (type) {
                    case 1:
                        if(contact_details.containsKey(id))
                            send_msg = contact_details.get(id);
                        else
                            send_msg = "No record for "+id+" found";
                        break;
                    case 2:
                        if(enrolled_units.containsKey(id))
                            send_msg = enrolled_units.get(id).toString().replace('[', '\u0000').replace(']', '\u0000').replace(',', '\u0000');
                        else
                            send_msg = "No record for "+id+" found";
                        break;
                    case 3:
                        if(unit_code.containsKey(id))
                            send_msg = unit_code.get(id);
                        else
                            send_msg = "No record for "+id+" found";
                        break;
                }
             
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

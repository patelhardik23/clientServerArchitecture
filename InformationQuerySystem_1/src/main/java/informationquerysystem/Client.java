
package informationquerysystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Hardik Patel
 */
public class Client extends javax.swing.JFrame implements ActionListener
{
    String serverDetail; // the hostname of the server
    int portNumber; // the port number on which the server is listening
    final int BUFFER_SIZE=1000;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton contactQueryBtn;
    private javax.swing.JButton enrollUnitQueryBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel queryAnsLbl;
    private javax.swing.JTextArea queryAnsTA;
    private javax.swing.JLabel serverNameLbl;
    private javax.swing.JTextField serverNameTb;
    private javax.swing.JLabel serverPortLbl;
    private javax.swing.JTextField serverPortTb;
    private javax.swing.JButton setBtn;
    private javax.swing.JLabel stdEnrollUnitLbl;
    private javax.swing.JLabel studentContDetLbl;
    private javax.swing.JLabel studentIdLbl;
    private javax.swing.JLabel studentIdLbl1;
    private javax.swing.JTextField studentIdTb;
    private javax.swing.JTextField studentIdTb1;
    private javax.swing.JLabel unitCodeLbl;
    private javax.swing.JTextField unitCodeTb;
    private javax.swing.JLabel unitDetailLbl;
    private javax.swing.JButton unitQueryBtn;
    // End of variables declaration//GEN-END:variables

    public Client() {
        serverDetail = null;
        portNumber = 0;
        intiClientGUI();
    }
    @SuppressWarnings("unchecked")
    private void intiClientGUI()
    {
        serverNameLbl = new javax.swing.JLabel();
        serverNameTb = new javax.swing.JTextField();
        serverPortLbl = new javax.swing.JLabel();
        serverPortTb = new javax.swing.JTextField();
        setBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        studentContDetLbl = new javax.swing.JLabel();
        studentIdLbl = new javax.swing.JLabel();
        studentIdTb = new javax.swing.JTextField();
        contactQueryBtn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        stdEnrollUnitLbl = new javax.swing.JLabel();
        studentIdLbl1 = new javax.swing.JLabel();
        studentIdTb1 = new javax.swing.JTextField();
        enrollUnitQueryBtn = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        unitDetailLbl = new javax.swing.JLabel();
        unitCodeLbl = new javax.swing.JLabel();
        unitCodeTb = new javax.swing.JTextField();
        unitQueryBtn = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        queryAnsLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        queryAnsTA = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student and Unit Query");
        setName("Student and Unit Query"); // NOI18N

        serverNameLbl.setText("Server Name");

        serverPortLbl.setText("Server Port");

        setBtn.setText("Set");
        setBtn.addActionListener(this);

        studentContDetLbl.setText("Student Contact Details");

        studentIdLbl.setText("Student ID");

        contactQueryBtn.setText("Query");
        contactQueryBtn.addActionListener(this);

        stdEnrollUnitLbl.setText("Student Enrolled Units");

        studentIdLbl1.setText("Student ID");

        enrollUnitQueryBtn.setText("Query");
        enrollUnitQueryBtn.addActionListener(this);

        unitDetailLbl.setText("Unit Details");

        unitCodeLbl.setText("Unit Code");

        unitQueryBtn.setText("Query");
        unitQueryBtn.addActionListener(this);

        queryAnsLbl.setText("Query Answers");

        queryAnsTA.setColumns(20);
        queryAnsTA.setRows(5);
        jScrollPane1.setViewportView(queryAnsTA);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator4)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(serverNameLbl)
                                            .addComponent(serverPortLbl))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(serverNameTb, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(serverPortTb, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(unitCodeLbl)
                                        .addGap(31, 31, 31)
                                        .addComponent(unitCodeTb, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(studentIdLbl1)
                                        .addGap(27, 27, 27)
                                        .addComponent(studentIdTb1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(studentIdLbl)
                                        .addGap(27, 27, 27)
                                        .addComponent(studentIdTb, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(studentContDetLbl)
                                    .addComponent(stdEnrollUnitLbl)
                                    .addComponent(unitDetailLbl))
                                .addGap(0, 121, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(setBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(contactQueryBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(enrollUnitQueryBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(unitQueryBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(queryAnsLbl)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverNameLbl)
                    .addComponent(serverNameTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverPortLbl)
                    .addComponent(serverPortTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentContDetLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentIdLbl)
                    .addComponent(studentIdTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contactQueryBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stdEnrollUnitLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentIdLbl1)
                    .addComponent(studentIdTb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enrollUnitQueryBtn))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unitDetailLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unitCodeLbl)
                    .addComponent(unitCodeTb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitQueryBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(queryAnsLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == setBtn)
            setServerDetail();
        if(e.getSource() == contactQueryBtn)
            fetchContactDetails();
        if(e.getSource() == enrollUnitQueryBtn)
            fetchEnrolledUnits();
        if(e.getSource() == unitQueryBtn)
            fetchUnitDetails();
    }

    private void setServerDetail() {
        serverDetail = serverNameTb.getText();
        portNumber = Integer.parseInt(serverPortTb.getText());
        System.out.println(serverDetail + " : " + portNumber);
        setBtn.setEnabled(false);
    }

    private void fetchContactDetails() {
        try
        {
            String msg = "<1>,<" + studentIdTb.getText() + ">";
            String response = sendReceive(msg);
            queryAnsTA.setText(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void fetchEnrolledUnits() {
        try
        {
            String msg = "<2>,<" + studentIdTb1.getText() + ">";
            String response = sendReceive(msg);
            queryAnsTA.setText(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void fetchUnitDetails() {
        try
        {
            String msg = "<3>,<" + unitCodeTb.getText() + ">";
            String response = sendReceive(msg);
            queryAnsTA.setText(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //method to send message 'msg' to the server, receive the response from the server and convert to string
    public String sendReceive(String msg) throws Exception{
        DatagramSocket socket = new DatagramSocket();// carries packet from source to destination
        InetAddress address = InetAddress.getByName(serverDetail);// address of the server from the hostname

        byte[] buffer = msg.getBytes();// convert msg to byte array
        DatagramPacket pkt = new DatagramPacket(buffer, buffer.length, address, portNumber);//'msg' packet to be sent to server
        System.out.println("Request -> "+msg);
        socket.send(pkt);// send the packet

        byte[] recvBuffer = new byte[BUFFER_SIZE];// buffer to store response
        pkt = new DatagramPacket(recvBuffer, recvBuffer.length); //response packet from the server
        socket.receive(pkt);// receive the packet from the server
        String recvMsg = new String(pkt.getData(), 0, pkt.getLength()); // convert server response to string
        System.out.println("Response -> "+recvMsg);

        //Reset buffer
        recvBuffer = new byte[BUFFER_SIZE];
        return recvMsg;
    }
    
    public static void main(String args[])
    {
       java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Client c = new Client();
                c.pack();
                c.setLocationRelativeTo(null);
                c.setVisible(true);
            }
        });
    }




}

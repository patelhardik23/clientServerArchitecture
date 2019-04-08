package udpClient.udpClientCode;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class UDPClientUI extends JFrame
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JPanel serverPannel;
    JPanel queryPannel;
    JPanel answerPannel;

    public UDPClientUI()
    {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        serverPannel = new JPanel();
        queryPannel = new JPanel();
        answerPannel = new JPanel();

        setServerPannel();
    //    setQueryPanel();
     //   setAnswerPanel();

        this.add(serverPannel);
        
        this.add(new JSeparator());
        this.add(queryPannel);
        this.add(new JSeparator());
        this.add(answerPannel);

        this.setSize(800, 500);
        this.setName("Processed Food Assessor System");
        this.setTitle("Processed Food Assessor System");

        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

    }

    private void setAnswerPanel()
    {
    //    answerPannel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        answerPannel.setLayout(new BoxLayout(answerPannel,
                BoxLayout.LINE_AXIS));
        JLabel sNameLBL = new JLabel("Server Name");
        JTextField sNameTB = new JTextField(20);
        JLabel sPortLBL = new JLabel("Server Port");
        JTextField sPortTB = new JTextField(20);
        JButton setBtn = new JButton("Set");

        answerPannel.add(sNameLBL);
        answerPannel.add(sNameTB);
        answerPannel.add(sPortLBL);
        answerPannel.add(sPortTB);
        answerPannel.add(setBtn);

    }

    private void setQueryPanel()
    {
       // queryPannel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        queryPannel.setLayout(new BoxLayout(queryPannel,
                BoxLayout.LINE_AXIS));
        
        JLabel sContDetLBL = new JLabel("Student Content Details");
        JLabel sCdIdLBL = new JLabel("Student ID");
        JTextField sCdIdTB = new JTextField(20);
        JButton scdBTN = new JButton("Query");
        queryPannel.add(new JSeparator());
        JLabel sEnrolUntLBL = new JLabel("Student Enrolled Units");
        JLabel sEuIdLBL = new JLabel("Student ID");
        JTextField sEuIdTB = new JTextField(20);
        JButton seuBTN = new JButton("Query");
        
        JLabel sUntLBL = new JLabel("Unit Details");
        
        JLabel sUdIdLBL = new JLabel("Unit Code");
        JTextField sUdIdTB = new JTextField(20);
        JButton setUcBtn = new JButton("Query");

        queryPannel.add(sContDetLBL);
        queryPannel.add(sCdIdLBL);
        queryPannel.add(sCdIdTB);
        queryPannel.add(scdBTN);
        
        queryPannel.add(sEnrolUntLBL);
        queryPannel.add(sEuIdLBL);
        queryPannel.add(sEuIdTB);
        queryPannel.add(seuBTN);
        
        queryPannel.add(sUntLBL);
        queryPannel.add(sUdIdLBL);
        queryPannel.add(sUdIdTB);
        queryPannel.add(setUcBtn);

    }

    private void setServerPannel()
    {
   //     serverPannel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        serverPannel.setLayout(new GridLayout(2, 3));
        JLabel sNameLBL = new JLabel("Server Name");
        JTextField sNameTB = new JTextField(20);
        JLabel sPortLBL = new JLabel("Server Port");
        JTextField sPortTB = new JTextField(20);
        JButton setBtn = new JButton("Set");

        serverPannel.add(sNameLBL,0,0);
        serverPannel.add(sNameTB,1,0);
        
        serverPannel.add(sPortLBL,0,1);
        serverPannel.add(sPortTB,1,1);
        serverPannel.add(setBtn,2,1);

    }

    /*
     * main method to execute application
     */
    public static void main(String args[])
    {
        new UDPClientUI();
    }

}

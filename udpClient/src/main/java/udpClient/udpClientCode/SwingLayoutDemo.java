
package udpClient.udpClientCode;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SwingLayoutDemo extends JFrame  implements ActionListener {

    private static final Insets WEST_INSETS = new Insets(10, 0, 5, 15);
    private static final Insets EAST_INSETS = new Insets(10, 20, 5, 0);

    private JPanel topPanel;
    private JTextField userNameTxt;
    private JLabel userNameLbl;
    private JLabel cerealsLbl;
    private JComboBox<String> cerealsComboBox;
    private JComboBox<String> beveragesComboBox;
    private JLabel beveragesLbl;

    private JPanel middlePanel;
    private JTextArea textAreaForMsg;
    private JScrollPane displayDataScroll;

    private JPanel bottomPanel;
    private JLabel commandLbl;
    private JButton enterDataBtn;
    private JButton displayChoiceBtn;
    private JButton clearDisplayBtn;
    private JButton quitBtn;
    private JTable dataTable;
    private Font textFont = new Font("", 0, 15);

    String userMessage = "";
    String userName = "";
    String cerealsValue = "";
    String beveragesValue = "";

    /*
     * default constructor
     */
    SwingLayoutDemo() {
        intializeGUI();
    }

    /*
     * Load all component in frame and set constraints on frame
     */
    public void intializeGUI() {

        topPanel = new JPanel();
        middlePanel = new JPanel();
        bottomPanel = new JPanel();

        setTopPanel();
        setMiddlePanel();
        setBottomPanel();

        this.add(topPanel);
        this.add(middlePanel);
        this.add(bottomPanel);

        this.setSize(800, 500);
        this.setName("Processed Food Assessor System");
        this.setTitle("Processed Food Assessor System");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    /*
     * Set all component of top pannel.
     */
    private void setTopPanel() {

        topPanel.setLayout(new GridBagLayout());
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
        topPanel.setBorder(border);

        GridBagConstraints gbc = new GridBagConstraints();

        userNameLbl = new JLabel("User Name  ");
        gbc = createGbc(0, 0, 1);
        topPanel.add(userNameLbl, gbc);

        userNameTxt = new JTextField(10);
        gbc = createGbc(1, 0, 3);
        userNameLbl.setLabelFor(userNameTxt);
        topPanel.add(userNameTxt, gbc);

        cerealsLbl = new JLabel("Cereals ");
        gbc = createGbc(0, 1, 1);
        topPanel.add(cerealsLbl, gbc);

        cerealsComboBox = new JComboBox<String>();
        gbc = createGbc(1, 1, 1);
        cerealsLbl.setLabelFor(cerealsComboBox);
        cerealsComboBox.setSelectedIndex(-1);
        topPanel.add(cerealsComboBox, gbc);

        beveragesLbl = new JLabel("Beverages   ");
        gbc = createGbc(2, 1, 1);
        topPanel.add(beveragesLbl, gbc);

        beveragesComboBox = new JComboBox<String>();
        gbc = createGbc(3, 1, 1);
        beveragesLbl.setLabelFor(beveragesComboBox);
        beveragesComboBox.setSelectedIndex(-1);
        topPanel.add(beveragesComboBox, gbc);
    }

    /*
     * set constraints for grid bag layout.
     */
    private GridBagConstraints createGbc(int x, int y, int gridwidth) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = 1;
        gbc.anchor = (x == 0 || x == 2) ? GridBagConstraints.EAST : GridBagConstraints.WEST;
        gbc.fill = (x == 2 && y == 1) ? GridBagConstraints.FIRST_LINE_START : GridBagConstraints.HORIZONTAL;
        gbc.insets = (x == 0 || x == 2) ? EAST_INSETS : WEST_INSETS;
        gbc.weightx = (x == 0) ? 0.1 : 1.0;
        gbc.weighty = (x == 0) ? 0.1 : 1.0;
        return gbc;
    }

    private void setMiddlePanel() {
        middlePanel.removeAll();
        userMessage = "Hello User!!!\n\n" + "Welcome to Processed Food Assessor System\n\n"
                + "Please follow below mentione steps.\n" + "1. Enter your name.\n"
                + "2. Select cereals and Beverages of you choice.\n"
                + "3. Click the 'Enter Data' Button to enter you choice.\n\n" + "Thank you.";

        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
        middlePanel.setBorder(border);
        middlePanel.setBackground(Color.WHITE);

        textAreaForMsg = new JTextArea(userMessage, 20, 20);
        textAreaForMsg.setEditable(false);
        textAreaForMsg.setFont(textFont);
        textAreaForMsg.setMargin(new Insets(10, 10, 10, 10));
        textAreaForMsg.setLineWrap(true);
        textAreaForMsg.setWrapStyleWord(true);
        middlePanel.add(textAreaForMsg);
        middlePanel.revalidate();
        middlePanel.repaint();
    }

    /*
     * Set all components of bottom panel along with action listeners
     */
    private void setBottomPanel() {

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 182, 15), 8, true);
        bottomPanel.setBorder(border);
        commandLbl = new JLabel("Command Buttons");
        commandLbl.setLabelFor(bottomPanel);

        enterDataBtn = new JButton("Enter Data");

        displayChoiceBtn = new JButton("Display Choices");
        displayChoiceBtn.setEnabled(false);
        clearDisplayBtn = new JButton("Clear Display");
        clearDisplayBtn.setEnabled(false);
        quitBtn = new JButton("Quit");

        enterDataBtn.addActionListener(this::actionPerformed);
        displayChoiceBtn.addActionListener(this::actionPerformed);
        clearDisplayBtn.addActionListener(this::actionPerformed);
        quitBtn.addActionListener(this::actionPerformed);

        bottomPanel.add(commandLbl);
        bottomPanel.add(enterDataBtn);
        bottomPanel.add(displayChoiceBtn);
        bottomPanel.add(clearDisplayBtn);
        bottomPanel.add(quitBtn);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * List of action to be performed based on source of action
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == quitBtn) {

            quitButton();

        } else if (e.getSource() == clearDisplayBtn) {

            clearDisplayBtnFun();

        } else if (e.getSource() == enterDataBtn) {

      

        } else if (e.getSource() == displayChoiceBtn) {

      

        }
    }
    private void quitButton() {
        // TODO Auto-generated method stub
        System.exit(0);

    }

    private void clearDisplayBtnFun() {
        userName = null;
        cerealsValue = null;
        beveragesValue = null;

        userNameTxt.setEditable(true);

        userNameTxt.setText(null);
        cerealsComboBox.setSelectedIndex(-1);
        beveragesComboBox.setSelectedIndex(-1);

        setMiddlePanel();

        enterDataBtn.setEnabled(true);
        displayChoiceBtn.setEnabled(false);
        clearDisplayBtn.setEnabled(false);

    }





    /*
     * main method to execute application
     */
    public static void main(String args[]) {

        new SwingLayoutDemo();
    }
}

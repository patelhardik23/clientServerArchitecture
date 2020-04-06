package Worker;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker {

    public static void main(String[] args) {
        // TODO code application logic here
        int objectPort = 6789;
        int filePort = 6790;
        try {
            Client client = new Client("localhost", objectPort, filePort);
            client.test();
            client.closeConnection();
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

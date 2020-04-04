package Worker;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private Socket fileSocket, objectSocket;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private DataOutputStream dos;
    private InputStream is;

    public Client(String host, int objectPort, int filePort) throws IOException {
        fileSocket = new Socket(host, filePort);
        dos = new DataOutputStream(fileSocket.getOutputStream());
        is = fileSocket.getInputStream();

        objectSocket = new Socket(host, objectPort);
        ois = new ObjectInputStream(objectSocket.getInputStream());
        oos = new ObjectOutputStream(objectSocket.getOutputStream());
    }

    public String downloadClassFile(String className) {
        try {
            dos.writeUTF(className);
            String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "Contract/" + className + ".class";
            System.out.println(path);
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[8089];
            int readBytes = is.read(buffer, 0, buffer.length);
            bos.write(buffer, 0, buffer.length);
            bos.close();
            System.out.println("File downloaded");
            return "File Downloaded";
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void closeConnection() {
        try {
            objectSocket.close();
            fileSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

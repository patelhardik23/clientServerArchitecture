package Master;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileInputStream;

public class Master {

    public static void main(String[] args) {
        Integer objectPort = 6789;
        Integer filePort = 6790;
        try {
            ServerSocket objectServer = new ServerSocket(objectPort);
            ServerSocket fileServer = new ServerSocket(filePort);
            new Thread() {

                public void run() {
                    while (true) {
                        try {

                            Socket objectSocket = objectServer.accept();
                            Socket fileSocket = fileServer.accept();

                            Thread objectThread = new ObjectConnection(objectSocket);
                            Thread fileThread = new FileConnection(fileSocket);

                            objectThread.start();
                            fileThread.start();

                        } catch (IOException ex) {
                            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }.start();
            System.out.println("-------------------------");
            System.out.println("Master is listening on port " + objectPort + " for objcet transfer");
            System.out.println("Master is listening on port " + filePort + " for file transfer");
            System.out.println("-------------------------");
        } catch (IOException ex) {
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class ObjectConnection extends Thread {

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ObjectConnection(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Object socket port:" + socket.getPort());
        oos = new ObjectOutputStream(this.socket.getOutputStream());
        ois = new ObjectInputStream(this.socket.getInputStream());
    }

    public void run() {
        while (true) {
        }
    }
}

class FileConnection extends Thread {

    DataInputStream dis;
    OutputStream os;
    Socket socket;

    public FileConnection(Socket socket) throws IOException {
        System.out.println("Object socket port:" + socket.getPort());
        this.socket = socket;
        dis = new DataInputStream(this.socket.getInputStream());
        os = this.socket.getOutputStream();
    }

    public void run() {
        while (true) {
            try {
                String className = dis.readUTF();
                String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "Contract/" + className + ".class";
                File file = new File(path);
                byte[] buffer = new byte[(int) file.length()];
                //Read byte from file
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                bis.read(buffer, 0, buffer.length);

                //Send file bytes to worker
                os.write(buffer, 0, buffer.length);
                os.flush();
                bis.close();

            } catch (IOException ex) {
                Logger.getLogger(FileConnection.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
}

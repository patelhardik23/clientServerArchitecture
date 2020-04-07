package Master;

import Contract.CalculateGCD;
import Contract.CalculatePi;
import Contract.CalculatePrime;
import Contract.Task;
import Contract.TaskList;
import Contract.TaskObject;
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

    public static TaskList taskList;
    public static Object[] computeTasks;
    public static int[] credit;

    public static void main(String[] args) {
        Integer objectPort = 6789;
        Integer filePort = 6790;
        createTaskList();
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

    private static void createTaskList() {
        taskList = new TaskList();
        int size = 6;
        String[] availableTasks = new String[size];
        String[] taskClassName = new String[size];
        computeTasks = new Object[size];
        credit = new int[size];

        availableTasks[0] = "Calculating Pi to 50 decimal digits";
        taskClassName[0] = "CalculatePi";
        computeTasks[0] = new CalculatePi(50);
        credit[0] = 35;

        availableTasks[1] = "Calculating Prime from 1 to 70";
        taskClassName[1] = "CalculatePrime";
        computeTasks[1] = new CalculatePrime(1, 70);
        credit[1] = 20;

        availableTasks[2] = "Calculating GCD of 128 and 76";
        taskClassName[2] = "CalculateGCD";
        computeTasks[2] = new CalculateGCD(128, 76);
        credit[2] = 25;

        availableTasks[3] = "Calculating Pi to 70 decimal digits";
        taskClassName[3] = "CalculatePi";
        computeTasks[3] = new CalculatePi(70);
        credit[3] = 35;

        availableTasks[4] = "Calculating Prime from 1 to 100";
        taskClassName[4] = "CalculatePrime";
        computeTasks[4] = new CalculatePrime(1, 100);
        credit[4] = 25;

        availableTasks[5] = "Calculating GCD of 252 and 24";
        taskClassName[5] = "CalculateGCD";
        computeTasks[5] = new CalculateGCD(252, 24);
        credit[5] = 30;

        taskList.setAvailableTasks(availableTasks);
        taskList.setTaskClassName(taskClassName);

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
            try {
                TaskObject taskObject;
                int step = ois.readInt();
                //System.out.println("step: " + step);
                if (step == 1) {
                    //Provide taskList to the worker
                    TaskList taskList = (TaskList) ois.readObject();
                    taskList = Master.taskList;
                    oos.flush();
                    oos.writeObject(taskList);
                    System.out.println("The list of available compute-tasks has been transferred to a Worker");
                } else if (step == 2) {

                    //Receive taskObject with taskId 
                    //Returns taskObject with compute object
                    taskObject = (TaskObject) ois.readObject();
                    Task task = (Task) Master.computeTasks[taskObject.getTaskId()];
                    System.out.println("The task: <" + Master.taskList.getAvailableTasks()[taskObject.getTaskId()] + "> is asked by a Worker");
                    taskObject.settObject(task);
                    oos.writeObject(taskObject);
                } else if (step == 3) {

                    //Receive the taskObject which incudes the result
                    //Return the taskObject with credit
                    taskObject = (TaskObject) ois.readObject();
                    String className = Master.taskList.getTaskClassName()[taskObject.getTaskId()];
                    Object result = taskObject.gettObject().getResult();
                    System.out.println("The task: " + className + ".class is performed by a Worker, the result is: " + result + ".");
                    taskObject.setCredit(Master.credit[taskObject.getTaskId()]);
                    oos.writeObject(taskObject);
                    System.out.println("Award a credit of " + taskObject.getCredit() + " to a Worker");
                    System.out.println("-------------------------");
                } else {

                    System.out.println("Connection close from: " + socket.getPort());
                    socket.close();
                    break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ObjectConnection.class.getName()).log(Level.SEVERE, null, ex);
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ObjectConnection.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
}

class FileConnection extends Thread {

    DataInputStream dis;
    OutputStream os;
    Socket socket;

    public FileConnection(Socket socket) throws IOException {
        System.out.println("File socket port:" + socket.getPort());
        this.socket = socket;
        dis = new DataInputStream(this.socket.getInputStream());
        os = this.socket.getOutputStream();
    }

    public void run() {
        while (true) {
            try {
                String className = dis.readUTF();
                if (className.equalsIgnoreCase("EXIT")) {
                    System.out.println("Connection close from :" + socket.getPort());
                    socket.close();
                    break;
                }
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
                System.out.println("The class file of " + className + ".class has been transferred to a Worker");
            } catch (IOException ex) {
                Logger.getLogger(FileConnection.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
}

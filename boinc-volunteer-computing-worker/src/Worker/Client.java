package Worker;

import Contract.Task;
import Contract.TaskList;
import Contract.TaskObject;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private Socket fileSocket, objectSocket;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private DataOutputStream dos;
    private InputStream is;

    private TaskList taskList;

    public Client(String host, int objectPort, int filePort) throws IOException {
        fileSocket = new Socket(host, filePort);
        dos = new DataOutputStream(fileSocket.getOutputStream());
        is = fileSocket.getInputStream();

        objectSocket = new Socket(host, objectPort);
        ois = new ObjectInputStream(objectSocket.getInputStream());
        oos = new ObjectOutputStream(objectSocket.getOutputStream());
    }

    public void test() {
        taskList = readTaskList();
        //Download Class file
        int selectedTask = 0;
        downloadClassFile(taskList.getTaskClassName()[selectedTask]);
        System.out.println("The task (" + taskList.getAvailableTasks()[selectedTask] + ") is in progress...");
        int credit = computeTask(selectedTask);
        System.out.println("The task (" + taskList.getAvailableTasks()[selectedTask] + ") is done.");
        System.out.println("The received credit for (" + taskList.getAvailableTasks()[selectedTask] + ") is " + credit);
    }

    //Get taskId and returns task credit which is received from Master
    public int computeTask(int selectedTaskId) {
        TaskObject taskObject = new TaskObject();
        try {
            //Send blank taskObject with taskId
            taskObject.setTaskId(selectedTaskId);

            oos.writeInt(2);
            oos.flush();
            oos.writeObject(taskObject);
            oos.flush();

            //receive taskObject with computeObject for computation
            taskObject = (TaskObject) ois.readObject();
            Task task = (Task) taskObject.gettObject();
            task.executeTask();

            //send the taskObject with compute result
            oos.writeInt(3);
            oos.flush();
            oos.writeObject(taskObject);
            oos.flush();

            //receive the taskObject with credit from Master
            taskObject = (TaskObject) ois.readObject();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taskObject.getCredit();
    }

    public TaskList readTaskList() {
        taskList = new TaskList();
        try {
            oos.flush();
            oos.writeInt(1);
            oos.flush();
            oos.writeObject(taskList);
            oos.flush();
            taskList = (TaskList) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taskList;
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
            bos.write(buffer, 0, readBytes);
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
            dos.writeUTF("EXIT");
            oos.flush();
            oos.writeInt(0);
            oos.flush();
            objectSocket.close();
            fileSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

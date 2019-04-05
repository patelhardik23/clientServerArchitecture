package com.rmi;

import java.rmi.Remote;
import java.util.List;

/**
 * @author KEVAL
 * Date: 4/5/2019
 */
public interface RemoteInterface extends Remote
{
    public List<Student> getStudents() throws Exception;
}

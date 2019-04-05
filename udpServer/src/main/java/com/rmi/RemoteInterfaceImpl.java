package com.rmi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KEVAL
 * Date: 4/5/2019
 */
public class RemoteInterfaceImpl implements RemoteInterface
{
    @Override
    public List<Student> getStudents() throws Exception
    {
        List<Student> studentList = new ArrayList<>();
        Student s = new Student();
        s.setId(1);
        s.setName("Keval");
        s.setAddress("Vavol");
        s.setEmail("keval.p@ezdi.us");
        studentList.add(s);
        return studentList;
    }
}

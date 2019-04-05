package com.multiThread.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * @author KEVAL
 * Date: 4/1/2019
 */
public class DataPacket implements Serializable
{
    private static final long serialVersionUID = -7248562117639611537L;
    private RequestType requestType;
    private Object resultSet;
    private Map<String,Object> request;

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public RequestType getRequestType()
    {
        return requestType;
    }

    public void setRequestType(RequestType requestType)
    {
        this.requestType = requestType;
    }

    public Object getResultSet()
    {
        return resultSet;
    }

    public void setResultSet(Object resultSet)
    {
        this.resultSet = resultSet;
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
    }
}


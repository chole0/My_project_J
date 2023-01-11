package ITEM;

import java.io.Serializable;
import java.net.InetAddress;

public class Message implements Serializable{
    private String userId = null;
    private String password = null;
    private String type = null;
    private String text = null;
    private InetAddress toAddr = null;
    private int toPort;

    public String getUserId()//获取用户名
    {
        return this.userId;
    }

    public void setUserId(String userId)//设置用户名
    {
        this.userId = userId;
    }

    public String getPassword()//获取密码
    {
        return this.password;
    }

    public void setPassword(String password)//设置密码
    {
        this.password = password;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)//
    {
        this.type = type;
    }

    public String getText()//
    {
        return this.text;
    }

    public void setText(String text)//
    {
        this.text = text;
    }

    public InetAddress getToAddr()//
    {
        return this.toAddr;
    }

    public void setToAddr(InetAddress toAddr)//
    {
        this.toAddr = toAddr;
    }

    public int getToPort()//
    {
        return this.toPort;
    }

    public void setToPort(int toPort)//
    {
        this.toPort = toPort;
    }
}

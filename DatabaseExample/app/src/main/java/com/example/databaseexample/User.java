package com.example.databaseexample;

import java.io.Serializable;

public class User implements Serializable
{
    private String fname;
    private String lname;
    private String uname;

    public User()
    {

    }

    public User(String u, String f, String l)
    {
        fname = f;
        lname = l;
        uname = u;
    }
    public String getFname()
    {
        return fname;
    }

    public void setFname(String f)
    {
        fname = f;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String l)
    {
        lname = l;
    }

    public String getUname()
    {
        return uname;
    }

    public void setUname(String u)
    {
        uname = u;
    }


}

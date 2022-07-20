package me.gregterteryan;

import java.io.Serializable;
import java.util.ArrayList;

public class Server implements Serializable {
    public ArrayList<Profile> users = new ArrayList<Profile>();
    private String serverName;

    public Server() {
        serverName = "n/a";
    }
    public Server(String name) {
        serverName = name;
    }
    public Server(String name, ArrayList<Profile> users) {
        this(name);
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    public String getServerName() {
        return serverName;
    }
    public void setUsers(ArrayList<Profile> profiles) {
        users = profiles;
    }
}

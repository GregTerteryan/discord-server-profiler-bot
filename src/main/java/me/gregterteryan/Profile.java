package me.gregterteryan;

import java.io.Serializable;

public class Profile implements Serializable {
    private String serverName;
    private String name;
    private int age;
    private String status;
    private String aboutMe;
    private String pfp;
    public Profile(String servername) {
        serverName = servername;
        name = "n/a";
        age = -1;
        status = "";
        aboutMe = "";
    }
    public Profile(String userName, String servername) {
        this(servername);
        name = userName;
    }
    public Profile(String userName, String servername, int userAge) {
        this(servername, userName);
        age = userAge;
    }
    public Profile(String username, String servername, int userAge, String userStatus) {
        this(servername,username, userAge);
        status = userStatus;
    }
    public Profile(String username, String servername, int userAge, String userStatus, String userAM) {
        this(servername,username, userAge, userStatus);
        aboutMe = userAM;
    }
    public Profile(String username, String servername, int userAge, String userStatus, String userAM, String pfpURL) {
        this(servername,username, userAge, userStatus, userAM);
        pfp = pfpURL;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAboutMe() {
        return aboutMe;
    }
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
    public String getPfp() {
        return pfp;
    }
    public void setPfp(String pfp) {
        this.pfp = pfp;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }

    @Override
    public String toString() {
        return "Profile: " +
                "\nname: '" + name + '\'' +
                ", \nage: " + age +
                ", \nstatus: '" + status + '\'' +
                ", \nabout me: '" + aboutMe + '\'' +
                ", \nprofile image: " + pfp;
    }
}

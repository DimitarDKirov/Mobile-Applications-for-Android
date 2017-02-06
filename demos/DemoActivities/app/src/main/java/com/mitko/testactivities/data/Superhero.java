package com.mitko.testactivities.data;

/**
 * Created by dimki on 06.02.2017 г..
 */

public class Superhero {
    private String id;
    private  String name;
    private  String secterIdentity;

    public  Superhero (String id, String name, String secretIdentity){
        this.setId(id);
        this.setName(name);
        this.setSecterIdentity(secretIdentity);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecterIdentity() {
        return secterIdentity;
    }

    public void setSecterIdentity(String secterIdentity) {
        this.secterIdentity = secterIdentity;
    }
}

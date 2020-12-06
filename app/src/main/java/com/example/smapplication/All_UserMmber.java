package com.example.smapplication;

public class All_UserMmber {
    String  uid, name, bio, prof, url;

    public All_UserMmber(String uid, String name, String bio, String prof, String url) {
        this.uid = uid;
        this.name = name;
        this.bio = bio;
        this.prof = prof;
        this.url = url;
    }

    public All_UserMmber(){

    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

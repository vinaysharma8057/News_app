package com.example.vinaysharma.n;

/**
 * Created by vinay sharma on 28-01-2018.
 */

public class Data {
    public String titleinfo;
    public String imageur;
    public String text;

    public void setname(String name) {
        this.imageur = name;
    }

    public void textinfo(String text) {
        this.text = text;
    }

    public void info(String in) {
        this.titleinfo = in;
    }

    public String gettext() {
        return text;
    }

    public String getName() {
        return imageur;
    }

    public String getinfo() {
        return titleinfo;
    }
}

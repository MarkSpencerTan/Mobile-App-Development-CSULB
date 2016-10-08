package edu.csulb.android.zooapp;

/**
 * Created by Mark on 9/30/2016.
 */
public class Animal {
    private static String name;
    private static String image;
    public Animal(String n, String p){
        name=n;
        image=p;
    }

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }
}

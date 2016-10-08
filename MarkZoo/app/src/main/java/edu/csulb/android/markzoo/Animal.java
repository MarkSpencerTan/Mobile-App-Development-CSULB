package edu.csulb.android.markzoo;

public class Animal {
    private String name;
    private String image;
    private String description;

    public Animal(String n, String p, String d){
        name=n;
        image=p;
        description=d;
    }

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }

    public String getDescription(){ return description;}
}

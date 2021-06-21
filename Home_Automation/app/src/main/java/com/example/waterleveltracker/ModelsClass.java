package com.example.waterleveltracker;

public class ModelsClass {

    private String Name;
    private String imageUrl;

    public ModelsClass() {

    }

    public ModelsClass(String name, String imageUrl) {
        Name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

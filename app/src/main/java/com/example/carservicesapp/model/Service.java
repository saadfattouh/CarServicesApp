package com.example.carservicesapp.model;

public class Service {

    int imageId;
    String title;
    double price;

    public Service(int imageId, String title, double price) {
        this.imageId = imageId;
        this.title = title;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

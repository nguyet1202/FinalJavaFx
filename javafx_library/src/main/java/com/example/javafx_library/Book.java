package com.example.javafx_library;

public class Book {
    int id;
    public String name;
    public String image;
    public String author;
    public String Category;
    public float price;
    public int quantityNow;

    public Book( int id , String name, String image,String author,String Category,float price,int quantityNow){
        this.id=id;
        this.name=name;
        this.image=image;
        this.author=author;
        this.Category=Category;
        this.price=price;
        this.quantityNow=quantityNow;

    }
    public Book( String name, String image,String author,String Category,float price,int quantityNow){
        this.id=id;
        this.name=name;
        this.image=image;
        this.author=author;
        this.Category=Category;
        this.price=price;
        this.quantityNow=quantityNow;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


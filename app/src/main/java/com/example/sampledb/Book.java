package com.example.sampledb;

public class Book {
    private int id;
    private String name, desc, image_url;

    public Book() {
//        this.id = id;
    }

    public Book(int id, String name, String desc, String image_url) {
        this.id= id;
        this.name = name;
        this.desc = desc;
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

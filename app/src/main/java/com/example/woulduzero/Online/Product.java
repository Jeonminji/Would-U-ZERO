package com.example.woulduzero.Online;

public class Product {
    private String main_category;
    private String sub_category;
    private String siteName;
    private String name;
    private String price;
    private String img;
    private String link;

    public Product() {

    }

    public Product(String main_category, String sub_category, String siteName, String name, String price, String img, String link) {
        this.main_category = main_category;
        this.sub_category = sub_category;
        this.siteName = siteName;
        this.name = name;
        this.price = price;
        this.img = img;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMain_category() {
        return main_category;
    }

    public void setMain_category(String main_category) {
        this.main_category = main_category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

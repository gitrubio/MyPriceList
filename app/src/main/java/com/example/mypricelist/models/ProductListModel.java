package com.example.mypricelist.models;

public class ProductListModel {
    public ProductListModel(String name, Double price){
        this.price = price;
        this.name = name;
    }


    private String name;
    private Double price;
}

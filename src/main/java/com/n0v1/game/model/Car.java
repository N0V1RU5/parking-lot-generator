package com.n0v1.game.model;

import javafx.scene.paint.Color;

public class Car {
    private int x;
    private int y;
    private double width;
    private double height;
    private String type;
    private Color color;
    private double rotation;

    public Car(double width, double height, String type, Color color, double rotation) {
        this.width = width;
        this.height = height;
        this.type = type;
        this.color = color;
        this.rotation = rotation;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}

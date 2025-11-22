package com.n0v1.game.model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Random;


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

    public static void generateCars(int count, ArrayList<Car> cars) {
        Random rnd = new Random();
        rnd.setSeed(21);
        for(int i = 0; i <= count; i++){
            int x = rnd.nextInt(500);
            int y = rnd.nextInt(500);

            Car car = Car.generateRandomCar();

            car.setX(x);
            car.setY(y);

            if(cars.isEmpty()){
                cars.add(car);
            } else {
                for (int again = 0; again < 300; again++){
                    if (!Car.isCarColliding(car, cars)){
                        cars.add(car);
                        FXGL.spawn("car", new SpawnData(x, y).put("car", car));
                        break;
                    } else {
                        x = rnd.nextInt(500);
                        y = rnd.nextInt(500);
                        car.setX(x);
                        car.setY(y);
                    }
                }
            }
        }
    }

    public static boolean isCarColliding(Car car,ArrayList cars){
        boolean collided = false;
        for (Object o : cars) {
            Car car1 = (Car) o;
            double carx1 = car.getX();
            double cary1 = car.getY();
            double carx2 = car.getX() + car.getWidth();
            double cary2 = car.getY() + car.getHeight();
            double car1x1 = car1.getX();
            double car1y1 = car1.getY();
            double car1x2 = car1.getX() + car1.getWidth();
            double car1y2 = car1.getY() + car1.getHeight();

            boolean cond1 = (carx1 <= car1x2);
            boolean cond2 = (carx2 >= car1x1);
            boolean cond3 = (cary1 <= car1y2);
            boolean cond4 = (cary2 >= car1y1);
            boolean cond5 = (carx1 < 0 || carx2 > 500 ||
                            cary1 < 0 || cary2 > 500);

            if ((cond1 && cond2 && cond3 && cond4) || cond5 ) {
                collided = true;
                break;
            }
        }
        return collided;
    }


    public static Car generateRandomCar(){
        Random rnd = new Random();
        final String[] types = {"sedan", "SUV", "van", "sport"};
        String type = types[rnd.nextInt(types.length)];
        double width = 30;
        double height = 50;

        switch (type){
            case "sedan":
                width = 31;
                height = 50;
                break;
            case "SUV":
                width = 35;
                height = 55;
                break;
            case "van":
                width = 40;
                height = 60;
                break;
            case "sport":
                width = 27;
                height = 44;
                break;
        }

        Color color = Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
        if (color == Color.GREY){
            color = Color.HOTPINK;
        }
        double rotation = 0; //rnd.nextInt(360);

        return new Car(width, height, type, color, rotation);
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

package com.n0v1.game.model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class CarSpawner {

    public static void generateCars(int count, ArrayList<Car> cars, int seed) {
        Random rnd = new Random();
        rnd.setSeed(seed);
        // pick random int aka x,y on screen
        for(int i = 0; i <= count; i++){
            int x = rnd.nextInt(500);
            int y = rnd.nextInt(500);

            // generate random car
            Car car = CarSpawner.generateRandomCar();
            // set cars position
            car.setX(x);
            car.setY(y);

            // placing cars in an array + spawning cars
            if(cars.isEmpty()){
                cars.add(car);
            } else {
                // trying 300x to place a car in a random position
                for (int again = 0; again < 300; again++){
                    // if car is not colliding, add car to array of cars and spawn a car
                    if (!CarSpawner.isCarColliding(car, cars)){
                        cars.add(car);
                        FXGL.spawn("car", new SpawnData(x, y).put("car", car));
                        break;
                    } else {
                        // try new random position, and set it to a car, for next checking
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
            // x,y postitions of a car we are checking
            double carx1 = car.getX();
            double cary1 = car.getY();
            double carx2 = car.getX() + car.getWidth();
            double cary2 = car.getY() + car.getHeight();

            // x,y positions of a car from array of already placed cars
            double car1x1 = car1.getX();
            double car1y1 = car1.getY();
            double car1x2 = car1.getX() + car1.getWidth();
            double car1y2 = car1.getY() + car1.getHeight();

            // checking conditions
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
        // pick random type of an car
        final String[] types = {"sedan", "SUV", "van", "sport"};
        String type = types[rnd.nextInt(types.length)];
        double width = 5;
        double height = 5;

        // set car type (width and height)
        switch (type){
            case "sedan":
                width = 30;
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
        // pick random color for car
        Color color = Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
        if (color == Color.GREY){
            color = Color.HOTPINK;
        }
        double rotation = 0; //rnd.nextInt(360);
        // return car
        return new Car(width, height, type, color, rotation);
    }
}
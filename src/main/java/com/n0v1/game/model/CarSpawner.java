package com.n0v1.game.model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class CarSpawner {

    private static final int LOT_SIZE = 500;
    private static final int MAX_ATTEMPTS = 300;

    public static void generateCars(int count, ArrayList<Car> cars, int seed) {
        // one seeded RNG for everything, so the same seed gives the same parking lot
        Random rnd = new Random(seed);
        for (int i = 0; i < count; i++) {
            // generate random car
            Car car = CarSpawner.generateRandomCar(rnd);

            // trying 300x to place a car in a random position
            for (int again = 0; again < MAX_ATTEMPTS; again++) {
                // pick random int aka x,y on screen and set it to a car
                int x = rnd.nextInt(LOT_SIZE);
                int y = rnd.nextInt(LOT_SIZE);
                car.setX(x);
                car.setY(y);

                // if car is not colliding, add car to array of cars and spawn a car
                if (!CarSpawner.isCarColliding(car, cars)) {
                    cars.add(car);
                    FXGL.spawn("car", new SpawnData(x, y).put("car", car));
                    break;
                }
            }
        }
    }

    public static boolean isCarColliding(Car car, ArrayList<Car> cars) {
        // x,y positions of a car we are checking
        double carx1 = car.getX();
        double cary1 = car.getY();
        double carx2 = car.getX() + car.getWidth();
        double cary2 = car.getY() + car.getHeight();

        // car has to be fully inside the parking lot
        if (carx1 < 0 || carx2 > LOT_SIZE || cary1 < 0 || cary2 > LOT_SIZE) {
            return true;
        }

        for (Car car1 : cars) {
            // x,y positions of a car from array of already placed cars
            double car1x1 = car1.getX();
            double car1y1 = car1.getY();
            double car1x2 = car1.getX() + car1.getWidth();
            double car1y2 = car1.getY() + car1.getHeight();

            // AABB overlap
            if (carx1 <= car1x2 && carx2 >= car1x1 && cary1 <= car1y2 && cary2 >= car1y1) {
                return true;
            }
        }
        return false;
    }


    public static Car generateRandomCar(Random rnd) {
        // pick random type of an car
        final String[] types = {"sedan", "SUV", "van", "sport"};
        String type = types[rnd.nextInt(types.length)];
        double width = 5;
        double height = 5;

        // set car type (width and height)
        switch (type) {
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
        // pick random color for car (grey would blend into the parking lot)
        Color color = Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        if (color.equals(Color.GREY)) {
            color = Color.HOTPINK;
        }
        double rotation = 0; //rnd.nextInt(360);
        // return car
        return new Car(width, height, type, color, rotation);
    }
}

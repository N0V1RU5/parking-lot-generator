package com.n0v1.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.n0v1.game.model.Car;
import com.sun.javafx.scene.control.InputField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class ParkPCGApp extends GameApplication {

    private ArrayList<Car> cars = new ArrayList<>();
    public int carsNum = 10;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(500);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Park PCG");
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new SimpleFactory());
        FXGL.getGameWorld().addEntityFactory(new CarFactory());

        FXGL.spawn("parkingLot", 0, 0);

        Car.generateCars(carsNum, cars);
    }

    @Override
    protected void initUI() {
        Button addCarButton = new Button("Add Car");
        Button deleteCarButton = new Button("Delete Car");
        Button generateAgainButton = new Button("Generate Again");
        TextField seedField = new TextField();

        Label label = new Label("Trying to generate " + carsNum + " cars.");
        label.setTranslateX(180);
        label.setTranslateY(540);

        addCarButton.setTranslateX(350);
        addCarButton.setTranslateY(535);

        deleteCarButton.setTranslateX(70);
        deleteCarButton.setTranslateY(535);

        generateAgainButton.setTranslateX(200);
        generateAgainButton.setTranslateY(565);

        seedField.setTranslateX(330);
        seedField.setTranslateY(565);

        addCarButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(carsNum+1 <= 100){
            carsNum++;

            // Delete all entities type "car" from world
            FXGL.getGameWorld().getEntitiesByType(CarFactory.EntityType.CAR)
                    .forEach(Entity::removeFromWorld);

            // Clear ArrayList<Car>
            cars.clear();

            //Generate new cars
            Car.generateCars(carsNum, cars);
            label.setText("Trying to generate " + carsNum + " cars.");
            } else {
                label.setText("Maximum cars is 100");
            }
        });

        deleteCarButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(carsNum-1 > 0) {
                carsNum--;

                // Delete all entities type "car" from world
                FXGL.getGameWorld().getEntitiesByType(CarFactory.EntityType.CAR)
                        .forEach(Entity::removeFromWorld);

                // Clear ArrayList<Car>
                cars.clear();

                //Generate new cars
                Car.generateCars(carsNum, cars);
                label.setText("Trying to generate " + carsNum + " cars.");
            } else {
                label.setText("Minimum cars is 1");
            }
        });

        generateAgainButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            // Delete all entities type "car" from world
            FXGL.getGameWorld().getEntitiesByType(CarFactory.EntityType.CAR)
                    .forEach(Entity::removeFromWorld);

            // Clear ArrayList<Car>
            cars.clear();

            //Generate new cars
            Car.generateCars(carsNum, cars);
        });

        getGameScene().addUINodes(label, addCarButton, deleteCarButton, generateAgainButton, seedField);

    }



    public static void main(String[] args) {
        launch(args);
    }
}

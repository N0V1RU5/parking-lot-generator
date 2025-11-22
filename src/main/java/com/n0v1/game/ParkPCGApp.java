package com.n0v1.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.n0v1.game.model.Car;
import com.n0v1.game.model.CarSpawner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.util.ArrayList;

public class ParkPCGApp extends GameApplication {

    private final ArrayList<Car> cars = new ArrayList<>();
    private int carsNum = 10;
    private int seed = 1;

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

        CarSpawner.generateCars(carsNum, cars, seed);
    }

    @Override
    protected void initUI() {
        Label label = new Label("Trying to generate " + carsNum + " cars.");

        // all useful buttons
        Button addCarButton = new Button("Add Car");
        Button deleteCarButton = new Button("Delete Car");
        Button generateAgainButton = new Button("Generate Again");

        TextField seedField = new TextField();
        seedField.setPromptText("Seed (default is 1)");

        int HorizontalPos = 0;
        int VerticalPos = 500;

        HBox buttons = new HBox(10,deleteCarButton, label, addCarButton);
        HBox lower = new HBox(10, seedField, generateAgainButton);

        VBox panel = new VBox(10, buttons, lower);
        panel.setStyle("-fx-padding: 20; -fx-background-color: rgba(0,0,0,0.3);");

        addCarButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(carsNum+1 <= 100){
            carsNum++;

            // Delete all entities type "car" from world
            FXGL.getGameWorld().getEntitiesByType(CarFactory.EntityType.CAR)
                    .forEach(Entity::removeFromWorld);

            // Clear ArrayList<Car>
            cars.clear();

            //Generate new cars
            CarSpawner.generateCars(carsNum, cars, seed);
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
                CarSpawner.generateCars(carsNum, cars, seed);
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
            CarSpawner.generateCars(carsNum, cars, seed);
        });

        seedField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // if pressed key is not a number, change it to '1'
                if(!newValue.matches("\\d*")) {
                    seedField.setText(newValue.replaceAll("[^\\d]", "1"));
                }
                // rightness checking for seed
                if(newValue.isEmpty() ||
                        !newValue.matches("\\d*") ||
                        newValue.length() > 9) {
                    seed = 1;
                } else {
                    seed = Integer.parseInt(newValue);
                }
            }
        });

        FXGL.addUINode(panel, HorizontalPos, VerticalPos);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

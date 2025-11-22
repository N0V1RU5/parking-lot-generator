package com.n0v1.game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.n0v1.game.model.Car;
import javafx.scene.shape.Rectangle;

public class CarFactory implements EntityFactory {

    public enum EntityType {
        CAR,
    }

    @Spawns("car")
    public Entity newCar(SpawnData data) {
        Car car = (Car) data.get("car");
        return FXGL.entityBuilder(data)
                .type(EntityType.CAR)
                .view(new Rectangle(car.getWidth(), car.getHeight(), car.getColor()))
                .rotate(car.getRotation())
                .build();
    }
}



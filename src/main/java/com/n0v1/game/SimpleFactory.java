package com.n0v1.game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SimpleFactory implements EntityFactory {

    @Spawns("parkingLot")
    public Entity newPL(SpawnData data) {
        return FXGL.entityBuilder(data)
                .view(new Rectangle(500,500, Color.GREY))
                .build();
    }
}

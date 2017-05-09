package com.projectdgdx.game.model;


import com.badlogic.gdx.math.Vector3;
import com.projectdgdx.game.utils.Vector3d;

/**
 * Created by Hampus on 2017-04-03.
 */
public abstract class Character extends Entity {

    public Character(Vector3d position, Vector3d scale, Vector3d rotation, String id) {
        super(position, scale, rotation, id);
    }

    public abstract void beenCaught();

    public boolean hasBeenCaught(){
        return true;
    }

}

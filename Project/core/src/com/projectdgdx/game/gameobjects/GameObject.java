package com.projectdgdx.game.gameobjects;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by Hampus on 2017-03-26.
 */
public abstract class GameObject {
    private Vector3 position;
    private Vector3 scale;
    private Vector3 rotation;
    private String id;

    public GameObject(Vector3 position, Vector3 scale, Vector3 rotation, String id) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
        this.id = id;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setScale(Vector3 scale) {
        this.scale = scale;
    }

    public void setRotation(Vector3 rotation) {
        this.rotation = rotation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getRotation() {
        return rotation;
    }

    public Vector3 getScale() {
        return scale;
    }

    public String getId() {
        return id;
    }

    public void addPositionX(float x){
        this.position.x += x;
    }

    public void addPositionY(float y){
        this.position.y += y;
    }

}

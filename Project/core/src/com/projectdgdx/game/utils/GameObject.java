package com.projectdgdx.game.utils;

/**
 * Created by Hampus on 2017-03-26.
 */
public class GameObject {
    private Vector3 position;
    private Vector3 size;
    private Vector3 scale;
    private Vector3 rotation;
    private String id;

    public GameObject(Vector3 position, Vector3 scale, Vector3 size, Vector3 rotation, String id) {
        this.position = position;
        this.size = size;
        this.scale = scale;
        this.rotation = rotation;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getScale() {
        return size;
    }

    public Vector3 getSize() {
        return scale;
    }

    public Vector3 getRotation() {
        return rotation;
    }

    public Vector3 getId() {
        return rotation;
    }
}

package com.projectdgdx.game.model;

import com.projectdgdx.game.Config;
import com.projectdgdx.game.utils.Vector3d;

import java.util.List;

/**
 * The Saboteur is the dishonest character in our game with the mission to bring down the factory.
 * It can sabotage the machines and if detected escape by "blackout" a part of the factory around him.
 */
public class Saboteur extends PlayableCharacter{

    private int blakoutsLeft = 2;

    public Saboteur(Vector3d position, Vector3d scale, Vector3d rotation, String id) {
        super(position, scale, rotation, id);
    }

    @Override
    public void dishonestInteract(List<iDishonestInteractable> dishonestInteractables) {
        for(iDishonestInteractable di : dishonestInteractables){
            if(this.canDishonestInteract(di)){
                di.dishonestInteract(this);
            }
        }
    }

    /**
     * Verfies if the Saboteur is in distance to any object that is possible to dishonest interact with.
     * @param di , the object that the the Saboteur want to know if it can dishonest interact with.
     * @return
     */
    private boolean canDishonestInteract(iDishonestInteractable di){
        float value = this.getPosition().distanceTo(di.getPosition()) - Config.HONEST_ACT_DISTANCE;
        return value < 0;
    }

    @Override
    public void useAbility(){
        if(this.blakoutsLeft > 0){
            ModelDataHandler.getBlackout().setPosition(this.getPosition());
            ModelDataHandler.getBlackout().activate();
            this.blakoutsLeft--;
        }
    }

    @Override
    public void beenCaught() {
        // Here we want to change state to "EndGameState" probably by using an "EndGameListener"-isch kind of solution
    }

    @Override
    public boolean isColliding(Vector3d vec) {
        return false;
    }
}

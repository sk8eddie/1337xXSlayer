package com.projectdgdx.game.model;

import com.projectdgdx.game.utils.Vector3d;

/**
 * All objects that can be destroyed/sabotaged by the saboteur should implement this interface.
 */
public interface iDishonestInteractable {
    /**
     * Here is what happens when the saboteur destroys the machine.
     * @param player the current saboteur interacting with the machine.
     */
    void dishonestInteract(PlayableCharacter player);
    Vector3d getPosition();

    /**
     * This method is used to change the machines internal state when sabotaged.
     * @param newState , the new machine state that should be set when sabotaged.
     */
    void setState(iMachineState newState);

}

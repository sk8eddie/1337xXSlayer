package com.projectdgdx.game.model;

import com.projectdgdx.game.utils.Timer;
import com.projectdgdx.game.utils.iTimerListener;
import com.projectdgdx.game.utils.Vector3d;

/**
 * The Machine class is the interactable machine object that is a main part of the game logic.
 * The Supervisors quest is to save the Machines from getting destroyed by the Saboteur.
 */

public class Machine extends StaticObject implements iHonestInteractable, iDishonestInteractable, iTimerListener {

    private Timer machineCounter;
    private Spotlight spot;

    protected iMachineState state;

    public Machine(Vector3d position, Vector3d scale, Vector3d rotation, String id) {
        super(position, scale, rotation, id);
        this.state = new UnusedMachineState();

        this.spot = new Spotlight(new Vector3d(position.x, 30, position.z),
                new Vector3d(1, 1, 1),
                new Vector3d(1, 1, 1), 5, 50, "spotlight.machine") ;
        this.spot.setColor(new Vector3d(0, 1, 0));

        this.machineCounter = new Timer(30, 1000);
        machineCounter.addListener(this);
    }

    public Spotlight getSpotLight() {
        return spot;
    }

    public void setSpotLight(Spotlight spot) {
        this.spot = spot;
    }

    @Override
    public void honestInteract(PlayableCharacter player) { //TODO  test commit
        state.honestInteract(player, this);
        this.spot.setColor(new Vector3d(0,0,1));
    }

    @Override
    public void dishonestInteract(PlayableCharacter player) {
        state.dishonestInteract(player, this);
        this.spot.setColor(new Vector3d(1,0,0));
    }

    @Override
    public void setState(iMachineState newState) {
        this.state = newState;
    }

    @Override
    public void timeIsUp() {
        this.setState(new DestroyedMachineState(this.getPosition()));
    }

    /**
     * Thi method is used to update the internal timer of the machine. If the timer goes down to 0
     * the machine will set itself to destroyed.
     */
    public void updateTimer() {
        this.machineCounter.setTimerValue(30);
    } // TODO instead add a new timer. This is how the timer works.

    // TODO We need to have a listener that will listen to how many machines that are destroyed so
    // TODO that the game will end when a certain amount of machines have been destroyed.
}

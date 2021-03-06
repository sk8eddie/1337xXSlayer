package com.projectdgdx.game.model.AI;

import com.projectdgdx.game.Config;
import com.projectdgdx.game.model.Worker;
import com.projectdgdx.game.utils.Timer;
import com.projectdgdx.game.utils.iTimerListener;
import com.projectdgdx.game.utils.Vector3d;

import java.util.Random;

/**
 * Created by Emil Jansson on 2017-05-09.
 */
public class NormalWorkerState implements iWorkerState, iTimerListener {

    private Timer timer;

    private boolean isWaiting = false;

    public NormalWorkerState(){
        //timer = new Timer(new Random().nextInt(40) + 10, 1000);
        startTimerAndListen(new Random().nextInt(10), 1000);
    }

    @Override
    public void reactOnUpdate(Worker worker) {
        if (!isWaiting){
            act(worker);
        }
    }

    private void act(Worker worker){
        if (isAtTargetNode(worker)){
            changeNodes(worker);
        }

        Vector3d vectorToTarget = worker.getPosition().subtractVectorFrom(worker.getTargetNode().getPosition());

        Vector3d moveVector = vectorToTarget.normalised();
        // TODO remove if problem free: moveVector.y = 0;
        worker.setRotation(new Vector3d(0, moveVector.getXZAngle() - 90, 0)); //TODO currently broken

//        worker.move(moveVector);
        worker.setMoveForce(moveVector);
    }

    private void changeNodes(Worker worker){
        worker.setLastNode(worker.getTargetNode());
        worker.setTargetNode(worker.getTargetNode().getNextNode());
    }

    private boolean isAtTargetNode(Worker worker){
        return worker.getPosition().isInRadius(worker.getTargetNode().getPosition(), Config.WORKER_NODE_RADIUS);
    }

    private void startTimerAndListen(int timerValue, long ticTime ){
        timer = new Timer(timerValue, ticTime);
        timer.addListener(this);
        timer.start();
    }

    @Override
    public void timeIsUp() {
        if (!isWaiting){
            isWaiting = true;
            startTimerAndListen(new Random().nextInt(200), 10);
        }else{
            isWaiting = false;
            startTimerAndListen(new Random().nextInt(10), 1000);
        }
    }
}

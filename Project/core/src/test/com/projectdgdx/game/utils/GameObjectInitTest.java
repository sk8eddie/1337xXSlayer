package test.com.projectdgdx.game.utils;

import com.projectdgdx.game.model.GameObject;
import com.projectdgdx.game.model.Machine;
import com.projectdgdx.game.utils.GameObjectInit;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Hampus on 2017-04-07.
 */
public class GameObjectInitTest {
    @Test
    public void convert() throws Exception {
        GameObjectInit gameObjectInit = new GameObjectInit("Machine");
        assertTrue(gameObjectInit.convert() instanceof Machine);

        gameObjectInit = new GameObjectInit("SomethingThatDoesNotExists");
        assertTrue(gameObjectInit.convert() == null);
    }

    @Test
    public void changeValue() throws Exception {
        int newXPosition = 21032103;
        int newYPosition = 99332103;
        int newZPosition = 59434103;

        GameObjectInit gameObjectInit = new GameObjectInit("Machine");
        gameObjectInit.changeValue("x", Integer.toString(newXPosition));
        gameObjectInit.changeValue("y", Integer.toString(newYPosition));
        gameObjectInit.changeValue("z", Integer.toString(newZPosition));

        GameObject gameObject = gameObjectInit.convert();
        assertTrue(gameObject instanceof Machine);
        assertTrue(gameObject.getPosition().x == newXPosition);
        assertTrue(gameObject.getPosition().y == newYPosition);
        assertTrue(gameObject.getPosition().z == newZPosition);

    }

}
package inheritamon.model.world.assets;

import inheritamon.model.data.DataHandler;

/**
 * @author Stanislav
 * A class to represent the door object
 */
public class ObjectDoor extends SuperObject {

    /**
     * Constructor for the door object
     */
    public ObjectDoor() {
        name = "Door";
        DataHandler dh = DataHandler.getInstance();
        image = dh.getObjectSprite("door1");
        collision = true;
    }
}

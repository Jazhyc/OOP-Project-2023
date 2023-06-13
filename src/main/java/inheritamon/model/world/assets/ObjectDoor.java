package inheritamon.model.world.assets;

import inheritamon.model.data.DataHandler;

public class ObjectDoor extends SuperObject {

    public ObjectDoor() {
        name = "Door";
        DataHandler dh = DataHandler.getInstance();
        image = dh.getObjectSprite("door1");
        collision = true;
    }
}

package inheritamon.model.world.assets;

import inheritamon.model.data.DataHandler;

/**
 * @author Stanislav
 * A class to represent the key object
 */
public class ObjectKey extends SuperObject{

    /**
     * Constructor for the key object
     */
    public ObjectKey() {
        name = "Key";
        DataHandler dh = DataHandler.getInstance();
        image = dh.getObjectSprite("keyidiot");
    }
}

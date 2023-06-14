package inheritamon.model.world.assets;

import inheritamon.model.data.DataHandler;

/**
 * @author Stanislav
 */
public class ObjectKey extends SuperObject{
    public ObjectKey() {
        name = "Key";
        DataHandler dh = DataHandler.getInstance();
        image = dh.getObjectSprite("keyidiot");
    }
}

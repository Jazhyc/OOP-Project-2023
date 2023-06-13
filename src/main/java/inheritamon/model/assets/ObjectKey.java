package inheritamon.model.assets;

import inheritamon.model.data.DataHandler;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends SuperObject{
    public ObjectKey() {
        name = "Key";
        DataHandler dh = DataHandler.getInstance();
        image = dh.getObjectSprite("keyidiot");
    }
}

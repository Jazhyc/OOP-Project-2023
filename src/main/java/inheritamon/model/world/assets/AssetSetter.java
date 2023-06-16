package inheritamon.model.world.assets;

import inheritamon.view.world.WorldPanel;

/**
 * @author Stanislav
 * A class to set the objects in the world
 */
public class AssetSetter {
    WorldPanel gp;

    /**
     * Constructor for the AssetSetter
     * @param gp The world panel
     */
    public AssetSetter(WorldPanel gp) {
        this.gp = gp;
    }

    /**
     * Places the objects in the world
     */
    public void setObject() {
        gp.obj[0] = new ObjectKey();
        gp.obj[0].worldX = 25 * gp.tileSize;
        gp.obj[0].worldY = 31 * gp.tileSize;

        gp.obj[1] = new ObjectDoor();
        gp.obj[1].worldX = 44 * gp.tileSize;
        gp.obj[1].worldY = 11 * gp.tileSize;

    }
}

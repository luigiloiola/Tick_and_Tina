package Model;

import java.awt.*;

public class Tile extends Entity{
    public boolean solid;

    public Tile (int posX, int posY, int width, int height, boolean solid) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.solid = solid;
    }

}

package Model;

import java.awt.*;

public class Tile extends Entity{
    public boolean solid;

    public String tileType;

    public Tile (int posX, int posY, int width, int height, String tileType) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.tileType = tileType;
    }

}

package Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import view.GamePannel;

import javax.imageio.ImageIO;

public class TileManager{
    private int stifness;
    public TileManager next;

    public int mapMaxRow = 20;
    public int mapMaxCol = 30;
    public Tile[] tileList = new Tile[mapMaxRow *mapMaxCol];
    public int lastTile = 0;

    public Tile[] sceneryTileList = new Tile[20];
    public int lastSceneryTile = 0;


    BufferedImage wallRight, wallLeft, ground, platform, platformBlud, longPlatform, longPlatformBlud, treeBlud;


    Model model;

    public HashMap <Integer, Integer> hashGrid = new HashMap<>();



    public TileManager(Model model) {
        this.model = model;
        getTileImages();

    }



    public  void add(int posX, int posY, int width, int height) {
        int size = model.size;

        tileList[lastTile] = new Tile(posX, posY, width*size, height*size, "block");
        lastTile++;

    }

    public void add(int posX , int posY,String tileType) {
        int size = model.size;
        switch (tileType) {

            case"wall left":
                tileList[lastTile] = new Tile(posX, posY, wallLeft.getWidth()*model.scale, wallLeft.getHeight()*model.scale, "wall left");
                lastTile++;
                break;
            case"wall right":
                tileList[lastTile] = new Tile(posX, posY, wallRight.getWidth()*model.scale, wallRight.getHeight()*model.scale, "wall right");
                lastTile++;
                break;
            case "ground":
                tileList[lastTile] = new Tile(posX, posY, ground.getWidth()*model.scale, ground.getHeight()*model.scale, "ground");
                lastTile++;
                break;
            case "platform":
                tileList[lastTile] = new Tile(posX, posY, platform.getWidth()*model.scale, platform.getHeight()*model.scale, "platform");
                lastTile++;
                break;
            case "platform blud":
                tileList[lastTile] = new Tile(posX, posY, platformBlud.getWidth()*model.scale, platformBlud.getHeight()*model.scale, "platform blud");
                lastTile++;
                break;
            case "long platform blud":
                tileList[lastTile] = new Tile(posX, posY, longPlatformBlud.getWidth()*model.scale, longPlatformBlud.getHeight()*model.scale, "long platform blud");
                lastTile++;
                break;
            case "tree blud":
                sceneryTileList[lastSceneryTile] = new Tile(posX, posY, treeBlud.getWidth()* model.scale, treeBlud.getHeight()*model.scale, "tree blud");
                lastSceneryTile++;
                break;

        }
    }

//    public void loadMap() {
//        try{
//            InputStream is = getClass().getResourceAsStream("/Maps/map.txt");
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            int col = 0;
//            int row = 0;
//            while(col < mapMaxCol && row < mapMaxRow){
//
//                String line = br.readLine();
//                while(col < mapMaxCol){
//                    String numbers[] =  line.split(" ");
//                    int num = Integer.parseInt(numbers[col]);
//
//                    mapTileNum[col][row] = num;
//                    col++;
//                }
//                if(col == mapMaxCol){
//                    col = 0;
//                    row++;
//                }
//            }
//            br.close();
//
//        }catch(Exception e){
//
//        }
//    }


    public void draw(Graphics2D g2) {
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0,0,GamePannel.maxScreenCol*Model.getInstance().size,1000);

            for (Tile tile : Model.getInstance().tileManager.tileList) {
                if(tile!=null && tile.tileType!=null){
                    switch (tile.tileType) {
                        case "wall right":
                            g2.drawImage(wallRight,tile.posX, tile.posY,tile.width, tile.height,null);
                            break;
                        case "wall left":
                            g2.drawImage(wallLeft,tile.posX, tile.posY,tile.width, tile.height,null);
                            break;
                        case "ground":
                            g2.drawImage(ground,tile.posX, tile.posY-10,tile.width, tile.height,null);
                            break;
                        case "platform":
                            g2.drawImage(platform,tile.posX, tile.posY,tile.width, tile.height,null);
                            break;
                        case "platform blud":
                            g2.drawImage(platformBlud,tile.posX, tile.posY,tile.width, tile.height,null);
                            break;
                        case "long platform blud":
                            g2.drawImage(longPlatformBlud,tile.posX, tile.posY,tile.width, tile.height,null);
                            break;
                        default:
                            g2.setColor(new Color(30,30,30));
                            g2.fillRect(tile.posX, tile.posY, tile.width, tile.height);
                    }
                }
            }

            for(Tile tile: Model.getInstance().tileManager.sceneryTileList){
                if(tile!= null && tile.tileType != null){
                    switch (tile.tileType){
                        case "tree blud":
                            g2.drawImage(treeBlud, tile.posX, tile.posY,tile.width, tile.height, null);
                            break;
                    }
                }
            }


    }

    void getTileImages() {
        try{
            wallRight =  ImageIO.read(getClass().getResourceAsStream("/DARK-tiles/wall-right.png"));
            wallLeft =  ImageIO.read(getClass().getResourceAsStream("/DARK-tiles/wall-left.png"));
            ground =  ImageIO.read(getClass().getResourceAsStream("/DARK-tiles/ground.png"));
            platform =  ImageIO.read(getClass().getResourceAsStream("/DARK-tiles/thin-platform.png"));
            platformBlud =  ImageIO.read(getClass().getResourceAsStream("/DARK-tiles/thin-platform-blud.png"));
            longPlatformBlud = ImageIO.read(getClass().getResourceAsStream("/DARK-tiles/long-platform-blud.png"));
            treeBlud =  ImageIO.read(getClass().getResourceAsStream("/DARK-tiles/red-tree.png"));

//            ground =  ImageIO.read(getClass().getResourceAsStream("/DARK-tiles/ground.png"));
//            ground =  ImageIO.read(getClass().getResourceAsStream("/DARK-tiles/ground.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

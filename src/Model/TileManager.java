package Model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class TileManager{
    private int stifness;
    public TileManager next;

    public int mapMaxRow = 20;
    public int mapMaxCol = 30;
    public Tile[] tileList = new Tile[mapMaxRow *mapMaxCol];
    public int lastTile = 0;

    public int mapTileNum[][];


    Model model;

    public HashMap <Integer, Integer> hashGrid = new HashMap<>();



    public TileManager(Model model) {
        mapTileNum = new int[mapMaxCol][mapMaxRow];
        this.model = model;

    }



    public  void add(int posX, int posY, int width, int height, boolean solid) {
        int size = model.size;

        tileList[lastTile] = new Tile(posX, posY, width*size, height*size, solid);
        lastTile++;




    }

    public void loadMap() {
        try{
            InputStream is = getClass().getResourceAsStream("/Maps/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < mapMaxCol && row < mapMaxRow){

                String line = br.readLine();
                while(col < mapMaxCol){
                    String numbers[] =  line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == mapMaxCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){

        }
    }

    public void addTiles(){

        int size = model.size;

        for (int y = 0; y<= mapMaxRow-1;y++) {

            for (int x = 0; x<= mapMaxCol-1;x++) {
                switch(mapTileNum[x][y]) {
                    case 0:
                        break;
                    case 1:
                        add(x ,y ,1,1,true);
                        break;
                    case 2:
                        add(x ,y ,1,1,true);
                        break;
                    case 3:
                        add(x ,y ,1,1,true);
                        break;

                }

            }
        }
//        while (currRow != GamePannel.maxScreenRow){
//            currRow++;
//            while(currCol != GamePannel.maxScreenCol){
//                add(currCol*size, currRow*size, 1 , 1, 1);
//                currCol++;
//            }
//
//        }
    }
}

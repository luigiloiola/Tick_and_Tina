package Model;

import Controller.KeyHandler;
import view.GamePannel;

import java.util.ArrayList;

public class Model implements Runnable {

    public Thread modelThread;

    public int gameState;

    final int originalSize = 16;
    final int scale = 3;

    public final int size = originalSize * scale;
    final double tickRate = 144;

    public ArrayList<KeyHandler> keyHList;

    private static volatile Model INSTANCE = null;

    CollisionChecker cChecker;

    public TileManager tileManager;



    private Model() {
        modelThread = new Thread(this);
        keyHList = new ArrayList<>();
        gameState = 0;

        cChecker = new CollisionChecker(this);
        tileManager = new TileManager(this);

        tileManager.add(0,size*19,1500, 200);
        tileManager.add(300,size*19,"ground");
        tileManager.add(650,size*19,"ground");
        tileManager.add(850,size*19,"ground");
        tileManager.add(1000,size*19,"ground");
//        tileManager.add(0,0,"wall left");
//        tileManager.add(0,335,"wall left");
        tileManager.add(300,630,"wall left");
        tileManager.add(1000,size*19,"ground");
        tileManager.add(-45,size*19-tileManager.wallLeft.getHeight()*scale+size,"ground");


        tileManager.add((GamePannel.maxScreenCol-1)*size,0,"wall right");
        tileManager.add((GamePannel.maxScreenCol-1)*size,335,"wall right");
        tileManager.add((GamePannel.maxScreenCol-1)*size,630,"wall right");

        tileManager.add(730-tileManager.platform.getWidth()*scale, 650, "platform");
        tileManager.add(570+tileManager.platform.getWidth()*scale, 650, "platform");
        tileManager.add(650, 650, "platform blud");
        tileManager.add(650,650-tileManager.treeBlud.getHeight()*scale,"tree blud");





    }

    public static Model getInstance() {
        if (INSTANCE == null) {
            synchronized (Model.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Model();
                }
            }
        }
        return INSTANCE;
    } 

    public void addKeyHandler(KeyHandler keyH) {
        keyHList.add(keyH);
    }


    //game loop
    @Override
    public void run() {
        double paintInterval = 1000000000/ tickRate;
        double nextPaintTime = System.nanoTime() + paintInterval;


        while(modelThread != null) {

            update();

            try {
                double remainingTime = nextPaintTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0 ){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
//                System.out.println("model" + " " + remainingTime);
                nextPaintTime += paintInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public void update() {

        try {

            for(KeyHandler i: Model.getInstance().keyHList) {
                cChecker.check(i.player);
                i.player.routine();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
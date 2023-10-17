package Model;

import Controller.KeyHandler;

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
        modelThread.start();
        keyHList = new ArrayList<>();
        gameState = 0;

        cChecker = new CollisionChecker(this);
        tileManager = new TileManager(this);
//        tileManager.add(400,100,3,1,true);
//        tileManager.add(500,300,3,1,true);
//        tileManager.add(600,200,3,1,true);
        tileManager.add(0,size*19,30,1,true);
        tileManager.add(100,size*16,3,1,true);
        tileManager.add(200,size*13,3,1,true);
        tileManager.add(300,size*10,3,1,true);
        tileManager.add(400,size*7,3,1,true);
        tileManager.add(500,size*4,3,1,true);



        tileManager.addTiles();


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
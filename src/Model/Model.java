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

    Tile tile = new Tile(400,450,600,30,1);


    private Model() {
        modelThread = new Thread(this);
        modelThread.start();
        keyHList = new ArrayList<>();
        gameState = 0;
        Tile.head.next = null;
        Tile.add(400,450,600,30,1);
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



    private void keyHandle(){
        for(KeyHandler i: keyHList) {
            if (i.player.posY >= 420 || i.player.collision) {
                i.player.velocityY = 0;
                i.jumpCount = 2;
            } else{
//                if (i.gliding){
//                    System.out.println("gliding");
//                    i.player.velocityY+=0.5;
//
//                } else{
//                    i.player.velocityY+=0.05;
//                }
                i.player.velocityY+=0.2;

            }

//            if(i.downPressed) {
//                i.player.posY += i.player.velocityX;
//            }
            if(i.leftPressed) {
                i.player.posX -= i.player.velocityX;
            }
            if(i.rightPressed) {
                i.player.posX += i.player.velocityX;
            }
            i.player.posY += i.player.velocityY;
            ;
        }

//
//            if (checkForCollision(player1, player2)) {
//                if(player1.getCooldown("cooldown") == 0 && player1.state == Player.State.VULNERABLE) {
//                    player1.setCooldown((int)(tickRate*0.5));
//                }
//            }
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
            keyHandle();
            for(KeyHandler i: Model.getInstance().keyHList) {
                i.player.routine();
                CollisionChecker.check(keyHList.get(0).player, tile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
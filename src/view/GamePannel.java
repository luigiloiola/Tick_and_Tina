package view;

import Controller.KeyHandler;
import Model.Model;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class GamePannel extends JPanel implements Runnable{

    Thread viewThread;
    public int FPS = 144;

    public double animationFrameDuration = (double) FPS/6;

    public final static int maxScreenCol = 30;
    public final static int maxScreenRow = 25;

    final int screenWidth = Model.getInstance().size * maxScreenCol;
    final int screenHeight = Model.getInstance().size * maxScreenRow;



    public GamePannel (KeyHandler keyH) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        startViewThread();

    }


    public void startViewThread() {
        viewThread = new Thread(this);
        viewThread.start();
    }



    @Override
    public void run() {


        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currntTime;
        long timer = 0;
        int drawCount = 0;

        while(viewThread != null) {
            currntTime = System.nanoTime();

            delta += (currntTime - lastTime) / drawInterval;
            timer += (currntTime - lastTime);
            lastTime = currntTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount ++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

//            PointerInfo info = MouseInfo.getPointerInfo();
//            Point pos = info.getLocation();
//            int x = (int) pos.getX();
//            int y = (int) pos.getY();
//            System.out.println(x);
//            System.out.println(y);

        }
    }
    public void update() {
    }
    public void paintComponent(Graphics g) {

        switch (Model.getInstance().gameState) {

            case 0:
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D)g;
                g2.setColor(Color.DARK_GRAY);
                g2.fillRect(0,0,maxScreenCol*Model.getInstance().size,1000);
                int i;
                Model.getInstance().tileManager.draw(g2);

                for(Player ii : Model.getInstance().playerList) {

                    ii.draw(g2);

                    if(ii.displayHitbox){
                        ii.drawHitbox(g2);
                    }

                }

                break;

            case 1:
                break;
        }



    }
}
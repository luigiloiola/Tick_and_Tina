package view;

import Controller.KeyHandler;
import Model.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Model.Tile;

public class GamePannel extends JPanel implements Runnable{

    Thread viewThread;
    public static double FPS = 50;

    final int maxScreenCol = 25;
    final int maxScreenRow = 12;

    final int screenWidth = Model.getInstance().size * maxScreenCol;
    final int screenHeight = Model.getInstance().size * maxScreenRow;



    public GamePannel (KeyHandler keyH) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        startViewThread();

    }

    public void startViewThread() {
        viewThread = new Thread(this);
        viewThread.start();
    }

    private void drawTiles(Graphics2D g2) {
        Tile curr = Tile.head;
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0,0,1000,1000);

        while (curr!= null) {
            g2.setColor(Color.WHITE);
            g2.fillRect(curr.posX, curr.posY, curr.posXEnd, curr.PosYEnd);
            curr = curr.next;
        }
    }

    private void drawPlayer(Graphics2D g2) {
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
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        switch (Model.getInstance().gameState) {
            case 0:
                drawTiles(g2);

                for(KeyHandler i : Model.getInstance().keyHList) {

                    i.player.draw(g2);
                }

                break;

            case 1:
                break;
        }



    }
}
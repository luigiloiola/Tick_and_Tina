package Controller;

import Model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.Player;

public class KeyHandler implements KeyListener, MouseListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, shooting;
    public double[] cursorPos;
    public Player player;

    public int jumpCount = 2;

    public boolean gliding = false;

    public boolean cooldown = false;
    public KeyHandler() {
        Model.getInstance().addKeyHandler(this);
        player = new Player();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            this.player.up = true;
            gliding = true;

            if(!cooldown && jumpCount > 0) {
                System.out.println("aaaaaa");
                this.player.velocityY = this.player.jumpForce;
                this.player.posY -=3;
                upPressed = true;
                cooldown = true;
                jumpCount--;
            }

        }
        if(code == KeyEvent.VK_S) {
            downPressed= true;
            this.player.down = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
            this.player.left = true;

        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
            this.player.right = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            cooldown = false;
            upPressed = false;
            gliding = false;
            this.player.up = false;
            System.out.println(cooldown);
        }
        if(code == KeyEvent.VK_S) {
            downPressed= false;
            this.player.down = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
            this.player.left = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
            this.player.right = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        cursorPos[0] = e.getX();
        cursorPos[1]= e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

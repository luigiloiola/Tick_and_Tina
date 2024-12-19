package Controller;

import Model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.Player;
import view.GamePannel;

public class KeyHandler implements KeyListener, MouseListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed;
    public double[] cursorPos;
    public Player player;


    public boolean gliding = false;

    public GamePannel gamePannel;
    public KeyHandler() {
        Model.getInstance().addKeyHandler(this);
        this.gamePannel = new GamePannel(this);
        gamePannel.addKeyListener(this);
        gamePannel.FPS = 144;
        player = new Player(gamePannel, this);
        System.out.println(player);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SHIFT){
            System.out.println("dodge");
            shiftPressed = true;
        }
        if (code == KeyEvent.VK_W) {
            this.player.up = true;
            gliding = true;

            if(!player.jumpCooldown && player.jumpCount > 0) {
                upPressed = true;
            }

        }
        if(code == KeyEvent.VK_S) {
            downPressed= true;
            this.player.down = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
            player.left = true;

        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
            player.right = true;
        }
        if(code == KeyEvent.VK_H) {
            player.displayHitbox = !player.displayHitbox;
            Model.getInstance().tileManager.showHitbox = !Model.getInstance().tileManager.showHitbox;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_SHIFT){
            shiftPressed=false;
        }

        if(code == KeyEvent.VK_W) {
            player.jumpCooldown = false;
            upPressed = false;
            player.up = false;
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
            player.right = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        cursorPos[0] = e.getX();
        cursorPos[1]= e.getY();

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

package Controller;

import Model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.GamePannel;

public class KeyHandler implements KeyListener, MouseListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, mouseLeft, mouseRight, devTools;
    public double[] cursorPos;

    public boolean gliding = false;

    public GamePannel gamePannel;
    public KeyHandler() {
        this.gamePannel = new GamePannel(this);
        gamePannel.addKeyListener(this);
        gamePannel.FPS = 144;
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
            gliding = true;
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed= true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;

        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_H) {
            Model.getInstance().tileManager.showHitbox = !Model.getInstance().tileManager.showHitbox;
            devTools = !devTools;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_SHIFT){
            shiftPressed=false;
        }

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed= false;

        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        cursorPos[0] = e.getX();
        cursorPos[1]= e.getY();
        mouseLeft = true;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseLeft = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

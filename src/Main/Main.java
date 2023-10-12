
package Main;

import Controller.KeyHandler;
import view.GamePannel;

import javax.swing.*;
import java.security.Key;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("game");

        KeyHandler keyH1 = new KeyHandler();
//        KeyHandler keyH2 = new KeyHandler();


        GamePannel gamePannel = new GamePannel(keyH1);
        window.add(gamePannel);

        window.pack();



        window.setLocationRelativeTo(null);
        window.setVisible(true);

        JFrame window2 = new JFrame();
        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window2.setResizable(false);
        window2.setTitle("game");


//        GamePannel gamePannel2 = new GamePannel(keyH2);
//        gamePannel2.FPS = 60;
//        window2.add(gamePannel2);
//
//        window2.pack();
//
//
//
//        window2.setLocationRelativeTo(null);
//        window2.setVisible(true);


    }
}

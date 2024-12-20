
package Main;

import Controller.KeyHandler;
import Model.Model;
import Model.characteres.ShotgunGuy;
import Model.characteres.TheBloodKing;
import view.GamePannel;
import Model.Player;

import javax.swing.*;
import java.security.Key;


public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("game");

        KeyHandler keyH1 = new KeyHandler();
        Player player1 = new Player(new ShotgunGuy(keyH1.gamePannel, keyH1));
//        KeyHandler keyH2 = new KeyHandler();
        Model.getInstance().addPlayer(player1);
        Model.getInstance().modelThread.start();
        window.add(keyH1.gamePannel);

        window.pack();



        window.setLocationRelativeTo(null);
        window.setVisible(true);
//
//        JFrame window2 = new JFrame();
//        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window2.setResizable(false);
//        window2.setTitle("game");
//
//
//        window2.add(keyH2.gamePannel);
//
//        window2.pack();
//
//
//
//        window2.setLocationRelativeTo(null);
//        window2.setVisible(true);
//
    }
}

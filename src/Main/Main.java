
package Main;

import Controller.KeyHandler;
import Model.Model;
import Model.Player.characteres.ShotgunGuy.ShotgunGuy;
import Model.Player.characteres.TheBloodKing.TheBloodKing;
import Model.Player.Player;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("game");

        KeyHandler keyH1 = new KeyHandler();
        Player player1 = new Player(keyH1.gamePannel, keyH1);
        player1.selectCharacter(new ShotgunGuy(player1));
        Model.getInstance().addPlayer(player1);
        window.add(keyH1.gamePannel);

        window.pack();



        window.setLocationRelativeTo(null);
        window.setVisible(true);



        KeyHandler keyH2 = new KeyHandler();
        Player player2 = new Player(keyH2.gamePannel, keyH2);
        player2.selectCharacter(new TheBloodKing(player2));
        Model.getInstance().addPlayer(player2);

        JFrame window2 = new JFrame();
        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window2.setResizable(false);
        window2.setTitle("game");


        window2.add(keyH2.gamePannel);

        window2.pack();


        window2.setLocationRelativeTo(null);
        window2.setVisible(true);
//
        Model.getInstance().modelThread.start();
//
    }
}

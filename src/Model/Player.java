package Model;

import view.GamePannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity{

    public enum State {VULNERABLE, IMUNE, DEBUFFED, BUFFED};

    public boolean down, left, right = false;
    public boolean up = true;
    public boolean idle;

    public boolean airBorn;
    public boolean yDrection = true;
    public boolean xDirection = true;

    public boolean collision = false;

    BufferedImage airBornImage;
    BufferedImage[] runningImages = new BufferedImage[5];
    int runningFrame = 0;

    BufferedImage[] idleImages = new BufferedImage[6];
    int idleFrame = 0;

    BufferedImage[] standingImages = new BufferedImage[5];
    int standingFrame = 0;

    BufferedImage[] standingImagesReversed = new BufferedImage[5];

    BufferedImage[] runningImagesReversed = new BufferedImage[5];


    int idleCooldown = 0;



    State state;
    private static int lastPlayerId = 0;
    public int playerId;
    private  int imuneDuration;

    public double glidingVelocity;

    public int jumpForce;
    private final Map<String, Integer> cooldowns = new HashMap<>();

    public Player() {
        this.playerId = lastPlayerId;
        lastPlayerId +=1;
        this.posX = 100 + 100*playerId;
        this.posY = 420;
        this.velocityX = 2;
        this.velocityY = 0;
        this.cooldowns.put("cooldown", 0);
        this.state = State.IMUNE;
        this.imuneDuration = 0;
        this.jumpForce = -6;
        this.glidingVelocity = -0.25;
        getEntityImage();


    }


    public void getEntityImage() {

        try {
            idleImages[0] = ImageIO.read(getClass().getResourceAsStream("/player/tile014.png"));
            idleImages[1] = ImageIO.read(getClass().getResourceAsStream("/player/tile015.png"));
            idleImages[2] = ImageIO.read(getClass().getResourceAsStream("/player/tile016.png"));
            idleImages[3] = ImageIO.read(getClass().getResourceAsStream("/player/tile017.png"));
            idleImages[4] = ImageIO.read(getClass().getResourceAsStream("/player/tile018.png"));
            idleImages[5] = ImageIO.read(getClass().getResourceAsStream("/player/tile019.png"));

            standingImages[0] = ImageIO.read(getClass().getResourceAsStream("/player/tile000.png"));
            standingImages[1] = ImageIO.read(getClass().getResourceAsStream("/player/tile001.png"));
            standingImages[2] = ImageIO.read(getClass().getResourceAsStream("/player/tile002.png"));
            standingImages[3] = ImageIO.read(getClass().getResourceAsStream("/player/tile003.png"));
            standingImages[4] = ImageIO.read(getClass().getResourceAsStream("/player/tile004.png"));

            runningImages[0] = ImageIO.read(getClass().getResourceAsStream("/player/tile021.png"));
            runningImages[1] = ImageIO.read(getClass().getResourceAsStream("/player/tile022.png"));
            runningImages[2] = ImageIO.read(getClass().getResourceAsStream("/player/tile023.png"));
            runningImages[3] = ImageIO.read(getClass().getResourceAsStream("/player/tile024.png"));
            runningImages[4] = ImageIO.read(getClass().getResourceAsStream("/player/tile025.png"));

            runningImagesReversed[0] = ImageIO.read(getClass().getResourceAsStream("/player-reversed/tile023.png"));
            runningImagesReversed[1] = ImageIO.read(getClass().getResourceAsStream("/player-reversed/tile024.png"));
            runningImagesReversed[2] = ImageIO.read(getClass().getResourceAsStream("/player-reversed/tile025.png"));
            runningImagesReversed[3] = ImageIO.read(getClass().getResourceAsStream("/player-reversed/tile026.png"));
            runningImagesReversed[4] = ImageIO.read(getClass().getResourceAsStream("/player-reversed/tile027.png"));


        } catch(IOException e) {
            e.printStackTrace();
        }
    }



//    this should e updateded by the Model not by the View
    public void draw (Graphics2D g2) {
        BufferedImage image;
        image = standingImages[0];

        if(left && !right) {
            if(runningFrame == 20){
                runningFrame = 0;
            }
            image = runningImagesReversed[runningFrame/4];
            runningFrame++;
            idleCooldown=0;
            idleFrame=0;
        }
        else if(right && !left) {
            if(runningFrame == 20){
                runningFrame = 0;
            }
            image = runningImages[runningFrame/4];
            runningFrame++;
            idleCooldown = 0;
            idleFrame = 0;
        }
        else {
            if(idleCooldown > 200) {
                if(idleFrame != 24) {
                    idleFrame ++;
                }
                image = idleImages[idleFrame/4];
                idleFrame++;
            }
            else{
                if(standingFrame == 20) {
                    standingFrame = 0;
                }
                standingFrame++;
                idleCooldown++;
                image = standingImages[standingFrame/4];
            }


            System.out.println(idleCooldown);
            System.out.println(idleFrame);
        }

        g2.drawImage(image, posX, posY, Model.getInstance().size*2, Model.getInstance().size*2, null);
    }

    public void routine() {


    }


}

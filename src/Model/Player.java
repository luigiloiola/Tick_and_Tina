package Model;

import Controller.KeyHandler;
import view.GamePannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity{

    public enum State {VULNERABLE, IMUNE, DEBUFFED, BUFFED};

    public boolean down, left, right, up = false;
    public boolean idle;

    public boolean airBorne = false;
    public boolean yDrection = true;
    public boolean xDirection = true;

    public boolean colliding = false;

    BufferedImage airBornImage;
    BufferedImage[] runningImages = new BufferedImage[5];
    int runningFrame;

    BufferedImage[] idleImages = new BufferedImage[6];
    int idleFrame;

    BufferedImage[] standingImages = new BufferedImage[5];
    int standingFrame;

    BufferedImage[] standingImagesReversed = new BufferedImage[5];

    BufferedImage[] runningImagesReversed = new BufferedImage[5];


    File folder = new File("res/idle");
    File[] listOfFiles = folder.listFiles();
    BufferedImage[] idleImages2 = new BufferedImage[listOfFiles.length];


    int idleCooldown;

    public boolean displayHitbox;


    State state;
    private static int lastPlayerId = 0;
    public int playerId;
    private  int imuneDuration;

    public double glidingVelocity;

    public int jumpForce;

    public int jumpCount = 2;

    public boolean jumpCooldown = false;
    private final Map<String, Integer> cooldowns = new HashMap<>();

    GamePannel gamePannel;
    KeyHandler keyH;


    public Player(GamePannel gamePannel, KeyHandler keyH) {
        this.playerId = lastPlayerId;
        lastPlayerId +=1;
        this.height = Model.getInstance().size;
        this.width = Model.getInstance().size;
        this.posX = Model.getInstance().size + Model.getInstance().size*playerId;
        this.posY = 0;
        this.velocityX = 2;
        this.velocityY = 0;
        this.cooldowns.put("cooldown", 0);
        this.state = State.IMUNE;
        this.imuneDuration = 0;
        this.jumpForce = -6;
        this.glidingVelocity = -0.25;
        this.idleCooldown = 0;
        this.idleFrame=0;
        this.runningFrame=0;
        this.standingFrame = 0;
        this.gamePannel = gamePannel;
        this.keyH = keyH;
        getEntityImage();

    }


    public void getEntityImage() {


        try {
            if(playerId == 0) {

                int i = 0;
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        idleImages2[i] = ImageIO.read(file);
                    }
                }
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

            }else{
                idleImages[0] = ImageIO.read(getClass().getResourceAsStream("/player2/tile014.png"));
                idleImages[1] = ImageIO.read(getClass().getResourceAsStream("/player2/tile015.png"));
                idleImages[2] = ImageIO.read(getClass().getResourceAsStream("/player2/tile016.png"));
                idleImages[3] = ImageIO.read(getClass().getResourceAsStream("/player2/tile017.png"));
                idleImages[4] = ImageIO.read(getClass().getResourceAsStream("/player2/tile018.png"));
                idleImages[5] = ImageIO.read(getClass().getResourceAsStream("/player2/tile019.png"));

                standingImages[0] = ImageIO.read(getClass().getResourceAsStream("/player2/tile000.png"));
                standingImages[1] = ImageIO.read(getClass().getResourceAsStream("/player2/tile001.png"));
                standingImages[2] = ImageIO.read(getClass().getResourceAsStream("/player2/tile002.png"));
                standingImages[3] = ImageIO.read(getClass().getResourceAsStream("/player2/tile003.png"));
                standingImages[4] = ImageIO.read(getClass().getResourceAsStream("/player2/tile004.png"));

                runningImages[0] = ImageIO.read(getClass().getResourceAsStream("/player2/tile021.png"));
                runningImages[1] = ImageIO.read(getClass().getResourceAsStream("/player2/tile022.png"));
                runningImages[2] = ImageIO.read(getClass().getResourceAsStream("/player2/tile023.png"));
                runningImages[3] = ImageIO.read(getClass().getResourceAsStream("/player2/tile024.png"));
                runningImages[4] = ImageIO.read(getClass().getResourceAsStream("/player2/tile025.png"));

                runningImagesReversed[0] = ImageIO.read(getClass().getResourceAsStream("/player2-reversed/tile023.png"));
                runningImagesReversed[1] = ImageIO.read(getClass().getResourceAsStream("/player2-reversed/tile024.png"));
                runningImagesReversed[2] = ImageIO.read(getClass().getResourceAsStream("/player2-reversed/tile025.png"));
                runningImagesReversed[3] = ImageIO.read(getClass().getResourceAsStream("/player2-reversed/tile026.png"));
                runningImagesReversed[4] = ImageIO.read(getClass().getResourceAsStream("/player2-reversed/tile027.png"));
            }


        } catch(IOException e) {
            e.printStackTrace();
        }
    }



//    this should e updateded by the Model not by the View
    public void draw (Graphics2D g2) {
        BufferedImage image;
        image = standingImages[0];
          //

        if(left && !right) {

            if(runningFrame == (int) (runningImages.length * gamePannel.animationFrameDuration)){
                runningFrame = 0;
            }
            image = runningImagesReversed[(int)(runningFrame / gamePannel.animationFrameDuration)];
            runningFrame++;
            idleCooldown=0;
            idleFrame=0;
        }
        else if(right && !left) {

            if(runningFrame == (int) (runningImages.length * gamePannel.animationFrameDuration)){
                runningFrame = 0;
            }
            image = runningImages[(int) (runningFrame/ gamePannel.animationFrameDuration)];
            runningFrame++;
            idleCooldown = 0;
            idleFrame = 0;
        }

        else if(up || down) {
            idleCooldown = 0;
        }

        else {

            if(idleCooldown > gamePannel.FPS*5) {
                if(idleFrame != ((idleImages.length-1) * gamePannel.animationFrameDuration)) {
                    idleFrame ++;
                }
                image = idleImages[ (int) (idleFrame/ gamePannel.animationFrameDuration)];
            }

            else{

                if(standingFrame ==  (int) (standingImages.length * gamePannel.animationFrameDuration)) {
                    standingFrame = 0;
                }
                image = standingImages[(int) (standingFrame/gamePannel.animationFrameDuration)];
                standingFrame++;
                idleCooldown++;
            }


        }

        g2.drawImage(image, posX-width/2, posY-height, width*2, height*2, null);
    }

    public void routine() {
        if(playerId == 1) {


        }
        if(!airBorne) {
            velocityY = 0;
            jumpCount = 2;
        }
        if (keyH.upPressed && !jumpCooldown) {
            velocityY = jumpForce;
            jumpCooldown = true;
            jumpCount--;

        } else if(airBorne){
//                if (i.gliding){
//                    System.out.println("gliding");
//                    i.player.velocityY+=0.5;
//
//                } else{
//                    i.player.velocityY+=0.05;
//                }
            velocityY+=0.2;

        }

//            if(i.downPressed) {
//                i.player.posY += i.player.velocityX;
//            }
        if(keyH.leftPressed && !colliding) {
            posX -= velocityX;

        }
        if(keyH.rightPressed && !colliding) {
            posX += velocityX;
        }
        posY += velocityY;

    }

    public void drawHitbox(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(posX, posY, width,height);
    }


}

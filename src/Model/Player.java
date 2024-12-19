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


    public boolean down, left, right, up, dodgeAttacking = false;
    public boolean idle;

    public boolean yDrection = true;
    public boolean xDirection = true;

    public boolean[] colliding = {false, false, false, false};
    public boolean airBorne = !this.colliding[0];

    BufferedImage airBornImage;
    BufferedImage[] runningImages,runningImagesReversed, idleImages, jumpImages, jumpToFallImages, fallImages, dodgeAttackImages;

    int runningFrame, idleFrame, jumpFrame, jumpToFallFrame, fallFrame, dodgeAttackFrame = 0;

    boolean animatingJumpTransition = false;


    public boolean displayHitbox;


    private static int lastPlayerId = 0;
    public int playerId;
    private  int imuneDuration;

    public double glidingVelocity;

    public int jumpForce;

    public int jumpCount = 2;
    public boolean dodgeAvaliable;

    public boolean jumpCooldown = false;
    private final Map<String, Integer> cooldowns = new HashMap<>();

    GamePannel gamePannel;
    KeyHandler keyH;

    public int tempPosX;


    public Player(GamePannel gamePannel, KeyHandler keyH) {
        System.out.println("player created");
        this.playerId = lastPlayerId;
        lastPlayerId +=1;
        this.height = Model.getInstance().size;
        this.width = Model.getInstance().size;
        this.posX = Model.getInstance().size + Model.getInstance().size*playerId;
        this.posY = 0;
        this.velocityX = 2;
        this.velocityY = -0.1;
        this.cooldowns.put("cooldown", 0);
        this.animating = false;
        this.imuneDuration = 0;
        this.jumpForce = -6;
        this.glidingVelocity = -0.25;
        this.idleFrame=0;
        this.runningFrame=0;
        this.idleFrame = 0;
        this.gamePannel = gamePannel;
        this.keyH = keyH;
        this.dodgeAvaliable = true;
        getEntityImage();

    }


    public void getEntityImage() {

        runningImages = getImageFiles("res/run");
        idleImages = getImageFiles("res/idle");
        runningImagesReversed = getImageFiles("res/reversed/run");
        jumpImages = getImageFiles("res/jump");
        jumpToFallImages = getImageFiles("res/jump-to-fall");
        fallImages = getImageFiles("res/fall");
        dodgeAttackImages = getImageFiles("res/dodge-charge-attack");
    }



//    this should be updated by the Model not by the View

    private BufferedImage[] getImageFiles(String pathName) {

        File folder = new File(pathName);
        File[] listOfFiles = folder.listFiles();
        System.out.println(listOfFiles);
        BufferedImage[] images = new BufferedImage[listOfFiles.length];

        try{
            int i = 0;
            for (File file : listOfFiles) {
                if (file.isFile()) {

                        images[i] = ImageIO.read(file);
                        i++;

                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return images;
    };

    BufferedImage animate(BufferedImage[] imageList, int imageFrame) {
        BufferedImage image;
        if(imageFrame ==  (int) (imageList.length * gamePannel.animationFrameDuration)) {
            imageFrame = 0;
        }
        image = imageList[(int) (imageFrame /gamePannel.animationFrameDuration)];
        imageFrame++;
        return image;
    }
    public void draw (Graphics2D g2) {
        BufferedImage image;
        image = idleImages[0];


//        multiplying and then dividing it back after the increment, makes so that the animation spends more time in each frame


        if(dodgeAttacking) {
            if(dodgeAttackFrame != (int)(dodgeAttackImages.length* gamePannel.animationFrameDuration)){
                image=dodgeAttackImages[(int) (dodgeAttackFrame/ gamePannel.animationFrameDuration)];
                dodgeAttackFrame+=2;
                if(dodgeAttackFrame == (int) (dodgeAttackImages.length* gamePannel.animationFrameDuration)-2) animating = false;


            }else{
                animating = false;
                dodgeAttacking = false;
                dodgeAttackFrame = 0;
            }
        }

        else if(velocityY < 0 && !colliding[1]) {
            jumpToFallFrame = 0;
            if(jumpFrame ==  (int) (jumpImages.length * gamePannel.animationFrameDuration)) {
                jumpFrame = 0;
            }
            image = jumpImages[(int) (jumpFrame /gamePannel.animationFrameDuration)];
            jumpFrame++;

        }
        else if(velocityY > 0 && !colliding[1]) {

            if(jumpToFallFrame != (int) (jumpToFallImages.length* gamePannel.animationFrameDuration)-1){
                image=jumpToFallImages[(int)(jumpToFallFrame/ gamePannel.animationFrameDuration)];
                jumpToFallFrame++;

            }else{

                if(fallFrame == (int)(fallImages.length * gamePannel.animationFrameDuration)){
                        fallFrame = 0;
                    }
                    image = fallImages[(int)(fallFrame/gamePannel.animationFrameDuration)];
                    fallFrame++;
            }
        }
        else if(left && !right) {

            if(runningFrame == (int) (runningImages.length * gamePannel.animationFrameDuration)){
                runningFrame = 0;
            }
            image = runningImagesReversed[(int)(runningFrame / gamePannel.animationFrameDuration)];
            runningFrame++;

        }
        else if(right && !left) {

            if(runningFrame == (int) (runningImages.length * gamePannel.animationFrameDuration)){
                runningFrame = 0;
            }
            image = runningImages[(int)(runningFrame/ gamePannel.animationFrameDuration)];
            runningFrame++;

        }
        else {
            if(idleFrame ==  (int) (idleImages.length * gamePannel.animationFrameDuration)) {
                idleFrame = 0;
            }
            image = idleImages[(int) (idleFrame /gamePannel.animationFrameDuration)];
            idleFrame++;
        }
//        colocar posX e posY em função do image.getWidht() e image.getHieght()
        g2.drawImage(image, tempPosX-image.getWidth(), posY-height, image.getWidth()*Model.getInstance().scale, image.getHeight()*3, null);
    }

    public void routine() {
        if(!animating){
            tempPosX = posX;

            if(!colliding[1]) {
                posY += velocityY;
                velocityY+=0.12;
            }else{
                jumpCount = 2;
                dodgeAvaliable = true;
                velocityY = 0.12;
            }
            if (keyH.upPressed && !jumpCooldown) {
                velocityY = jumpForce;
                if(!colliding[0]){
                    jumpCooldown = true;
                    jumpCount--;
                }

            }
            if(keyH.leftPressed) {
                if(velocityX > 0){
                    velocityX = velocityX * -1;
                }
                if(!colliding[2]){
                    posX += velocityX;
                }
            }
            if(keyH.rightPressed) {
                if(velocityX < 0){
                    velocityX = velocityX * -1;
                }
                if(!colliding[3]){
                    posX += velocityX;
                }
            }
            if(keyH.shiftPressed && dodgeAvaliable) {
                animating = true;
                dodgeAttacking = true;
            }
        }
        else{
            velocityY = 0;
            if( dodgeAttacking ) {
                switch ((int)(dodgeAttackFrame/ gamePannel.animationFrameDuration)){
                    case 4:
                        posX = tempPosX-34*Model.getInstance().scale;
                        break;
                    case 9:
                        posX = tempPosX+30*Model.getInstance().scale;
                        break;
                    case 14:
                        posX = tempPosX+34*Model.getInstance().scale;
                        break;
                }
                dodgeAvaliable = false;
            }
        }
    }

    public void drawHitbox(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(posX, posY, width,height);
    }


}

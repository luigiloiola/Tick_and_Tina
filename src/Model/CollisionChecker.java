package Model;

public class CollisionChecker {

    Model model;
    int[] p1 = new int[2];
    int[] p2 = new int[2];

    int playerTop;
    int playerBottom;
    int playerLeft;
    int playerRight ;

    public CollisionChecker(Model model){
        this.model = model;
    }



    public void check(Player player){
        int size = Model.getInstance().size;

        playerTop = player.posY;
        playerBottom = player.posY+player.height;
        playerLeft = player.posX;
        playerRight = player.posX + player.width;

//        int topRow = topPosY/Model.getInstance().size;
//        int bottomRow = bottomPosY/Model.getInstance().size;
//        int leftCol = leftPosX/Model.getInstance().size;
//        int rightCol = rightPosX/Model.getInstance().size;
        player.airBorne = true;
        player.colliding = false;
        for (Tile tile: Model.getInstance().tileManager.tileList) {
            if(tile != null){
//playerBottom >= tile.posY && playerRight >= tile.posX && playerLeft >= tile.posX*tile.width

                if (playerBottom >= tile.posY && playerBottom <= tile.posY+player.velocityY && playerRight >= tile.posX && playerLeft <= tile.posX+tile.width) {
                    if(playerBottom> tile.posY){
                        player.posY = tile.posY- player.height;
                    }
                    player.airBorne = false;
                }
//                && playerTop >= tile.posY+ tile.height+player.velocityY
                if(playerRight > tile.posX && playerLeft < tile.posX+tile.width && playerTop + player.velocityY <= tile.posY+tile.height && playerTop + player.velocityY >= tile.posY){
                    player.posY = tile.posY+tile.height+1;
                    player.velocityY = 0;
                    System.out.println("colliding top");
                }
                if(playerRight >= tile.posX && playerLeft <= tile.posX+tile.width && playerBottom > tile.posY && playerTop < tile.posY+tile.height){
                    player.colliding = true;
                }
            }

        }







    }
}

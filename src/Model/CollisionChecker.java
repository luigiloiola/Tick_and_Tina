package Model;

public class CollisionChecker {

    Model model;
    int[] p1 = new int[2];
    int[] p2 = new int[2];

    int playerTop;
    int playerBottom;
    int playerLeft;
    int playerRight ;
    int tileTop;
    int tileBottom;
    int tileLeft;
    int tileRight;

    public CollisionChecker(Model model){
        this.model = model;
    }



    public void check(Player player){
        int size = Model.getInstance().size;

        playerTop = player.posY;
        playerBottom = player.posY+player.height;
        playerLeft = player.posX;
        playerRight = player.posX + player.width;
        player.colliding[0] = false;
        player.colliding[1] = false;
        player.colliding[2] = false;
        player.colliding[3] = false;
        for (Tile tile: Model.getInstance().tileManager.tileList) {

            if(tile != null){
                tileTop = tile.posY;
                tileBottom = tile.posY+tile.height;
                tileLeft = tile.posX;
                tileRight = tile.posX+tile.width;

                if(playerRight >= tileLeft && playerLeft <= tileRight){
                    if( playerTop >= tileBottom && playerTop + player.velocityY <= tileBottom) {
                        player.posY = tileBottom;
                        player.colliding[0]= true;
                        player.velocityY = 0;
                    }
                    if(playerBottom <= tileTop && playerBottom + player.velocityY >= tileTop){
                        player.colliding[1] = true;
                        player.posY = tileTop - player.height;
                    }
                }

                if ((playerBottom >= tileTop && playerTop <= tileBottom)){
                    if( playerLeft >= tileRight && playerLeft + player.velocityX <= tileRight){
                        player.colliding[2] = true;
                        player.posX = tileRight;
                    }
                    if( playerRight <= tileLeft && playerRight + player.velocityX >= tileLeft){
                        player.colliding[3] = true;
                        player.posX = tileLeft - player.width;
                    }
                }
            }

        }

    }
}

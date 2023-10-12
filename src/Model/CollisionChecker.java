package Model;

public class CollisionChecker {

    Model model;
    int[] p1 = new int[2];
    int[] p2 = new int[2];
    public CollisionChecker(Model model){
        this.model = model;
    }



    public static void check(Player player, Tile tile){
        int size = Model.getInstance().size*2;
//        if(player.velocityY < 0) {
//            p1[0] = player.posX;
//            p1[1] = player.posY;
//            p2 = {player.posX + size, player.posY};
//        }
//        else{
//            p1 = {player.posX, player.posY+size};
//            p2 = {player.posX + size, player.posY+size};
//        }

        if(player.posY + player.velocityY + size >= tile.posY
                && player.posX + player.velocityX + size >= tile.posX){
            player.collision = true;
        }
    }
}

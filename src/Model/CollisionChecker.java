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

        int TopPosY = player.posY;
        int BottomPosY = player.posY+size;
        int leftPosX = player.posX;
        int rightPosX = player.posX + size;


        if(player.posY + player.velocityY + size >= tile.posY
                && player.posX + player.velocityX + size >= tile.posX){
            player.collision = true;
        }else{
            player.collision = false;
            System.out.println("not colliding");
        }
    }
}

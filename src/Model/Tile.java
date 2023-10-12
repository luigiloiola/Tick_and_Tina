package Model;

public class Tile extends Entity{
    private final int stiffnes;
    public Tile next;
    public final static Tile head = new Tile(0,0,0,0,0);



    public static void add(int posX, int posY, int width, int height, int stiffnes) {
        Tile curr = head;
        while (curr.next != null) {
            curr.next = curr;
        }
        curr.next = new Tile(posX, posY, width, height, stiffnes);
        curr.next.next = null;
    }
    public Tile(int posX, int posY, int width, int height, int stiffnes) {
        this.posX = posX;
        this.posY = posY;
        this.stiffnes = stiffnes;
        this.posXEnd = width;
        this.PosYEnd = height;


    }


}

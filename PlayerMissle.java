import javax.swing.ImageIcon;

public class PlayerMissle extends Missles {
    private int dx; 

    public PlayerMissle() {
        super();
    }

    public PlayerMissle(int x, int y, int w, int h, int key, ImageIcon i) {
        super(x, y, w, h, i, 0, 0); 

     
        if (key == 39) { 
            dx = 5;
        } else if (key == 37) { 
            dx = -5; 
        }
    }

    @Override
    public void move() {
        setX(dx); 
    }
}
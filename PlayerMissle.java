import javax.swing.ImageIcon;

public class PlayerMissle extends Missles {
    private int dx; // Variable to store the missile's horizontal direction

    public PlayerMissle() {
        super();
    }

    public PlayerMissle(int x, int y, int w, int h, int key, ImageIcon i) {
        super(x, y, w, h, i, 0, 0); // Set dx and dy to 0 in the super constructor

        // Set the missile's horizontal direction based on the key pressed
        if (key == 39) { // Right arrow key
            dx = 5; // Move missile to the right
        } else if (key == 37) { // Left arrow key
            dx = -5; // Move missile to the left
        }
    }

    @Override
    public void move() {
        setX(dx); // Use the setX() method to update the missile's x coordinate
    }
}
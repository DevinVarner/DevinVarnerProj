import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Missles {
    private int x, y;
    private int dx;
    private int w, h;
    public ImageIcon shipImg;

    public Missles() {
        x = 0;
        y = 0;
        dx = 0;
        w = 300;
        h = 500;
        shipImg = new ImageIcon("Pm1.png");
    }

    public Missles(int xV, int yV, int width, int height, ImageIcon i, int dxV, int dyV) {
        x = xV;
        y = yV;
        dx = dxV;
        w = width;
        h = height;
        shipImg = i;
    }

    public Missles(int xV, int yV, ImageIcon i) {
        x = xV;
        y = yV;
        w = 6;
        h = 18;
        shipImg = i;
        dx = 3;
    }

    public void setX(int xV) {
        x += xV;
    }

    public void setY(int yV) {
        y += yV;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setW(int width) {
        w = width;
    }

    public void setH(int height) {
        h = height;
    }

    public void setImage(ImageIcon i) {
        shipImg = i;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void vert() {
        dx *= -1;
    }

    public void move() {
        x += dx;
    }

    public ImageIcon getImg() {
        return shipImg;
    }

    public boolean GhostsCollision(Enemies s) {
		
		Rectangle Ene = new Rectangle(s.getX(), s.getY(), s.getW(), s.getH());
		Rectangle proj = new Rectangle(getX(), getY(), getW(), getH());
		
		if(Ene.intersects(proj)) {
			vert();
		
			return true;
		}
		return false;
	}
	public boolean playerCollision(Enemies s) {
		
		Rectangle Ene = new Rectangle(s.getX(), s.getY(), s.getW(), s.getH());
		Rectangle proj = new Rectangle(getX(), getY(), getW(), getH());
		
		if(Ene.intersects(proj)) {
			vert();
		
			return true;
		}
		return false;
	}
}
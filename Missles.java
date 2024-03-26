import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missles {

	private int x, y;
	private int dy;
	private int w, h;
	private ImageIcon shipImg;
	
	public Missles() {
		x=0;
		y=0;
		dy=0;
		w=300;
		h=500;
		shipImg = new ImageIcon("");
	}
	
	public Missles(int xV, int yV, int width, int height, ImageIcon i, int dxV, int dyV) {
		x=xV;
		y=yV;
		dy=dyV;
		w=width;
		h=height;
		shipImg = i;
	}
	
	public Missles(int xV, int yV, ImageIcon i) {
		x=xV;
		y=yV;
		w=6;
		h=18;
		shipImg = i;
		dy=3;
	}
	
	public void setX(int xV) {
		x+=xV;
	}
	public void setY(int yV) {
		y+=yV;
	}
 	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	public void setW(int width) {
		w=width;
	}
	public void setH(int height) {
		h=height;
	}
	public void setImage(ImageIcon i) {
		shipImg= i;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
	public void vert () {
		dy*=-1;
	}
	public void move()
	{
		y+=dy;
		
	}
	public ImageIcon getImg() {
		return shipImg;
	}
	public boolean alienCollision(SpaceShip s) {
		
		Rectangle Space = new Rectangle(s.getX(), s.getY(), s.getW(), s.getH());
		Rectangle proj = new Rectangle(getX(), getY(), getW(), getH());
		
		if(Space.intersects(proj)) {
			vert();
		
			return true;
		}
		return false;
	}
	public boolean playerCollision(SpaceShip s) {
		
		Rectangle Space = new Rectangle(s.getX(), s.getY(), s.getW(), s.getH());
		Rectangle proj = new Rectangle(getX(), getY(), getW(), getH());
		
		if(Space.intersects(proj)) {
			vert();
		
			return true;
		}
		return false;
	}
}
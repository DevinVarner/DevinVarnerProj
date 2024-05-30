import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemies {

	private int x, y;
	private int dx, dy;
	private int w, h;
	private ImageIcon shipImg;
	
	public Enemies() {
		x=200;
		y=300;
		dx=0;
		dy=0;
		w=300;
		h=500;
		shipImg = new ImageIcon("");
	}
	
	public Enemies(int xV, int yV, int width, int height, ImageIcon i, int dxV, int dyV) {
		x=xV;
		y=yV;
		dx=dxV;
		dy=dyV;
		w=width;
		h=height;
		shipImg = i;
	}
	
	public Enemies(int xV, int yV, ImageIcon i) {
		x=xV;
		y=yV;	
		w=50;
		h=50;
		shipImg = i;
		dx=1;
		dy=0;
	}
	
	public void setX(int xV) {
		x=xV;
	}
	public void setY(int yV) {
		y=yV;
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
	public void setdy(int newdy) {
		dy = newdy;
	}
	public void reverseHorz () {
		dx=dx*-1;
	}
	public void speedUp () {
		dx=dx+1;
	}
	public void stop (){
		dx=0;
	}
	public void slowDown () {
		dx=dx-2;
	}
	public void Hmove() {
		dx=3;
		x = x + dx;
		
	}
	public void Hmove2() {
		dx=-3;
		x = x - dx;
		
	}
	public void move() {
		x+=dx;
		
	}
	public void move2() {
		x-=dx;
		
	}
	public ImageIcon getImg() {
		return shipImg;
	} 
	public boolean playerCollision(Pictures s) {
		
		Rectangle Ene = new Rectangle(s.getx(), s.gety(), s.getW(), s.getH());
		Rectangle proj = new Rectangle(getX(), getY(), getW(), getH());
		
		if(Ene.intersects(proj)) {
			
		
			return true;
		}
		return false;
	}
}
	


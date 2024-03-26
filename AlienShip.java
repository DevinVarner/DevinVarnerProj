import java.awt.Image;

import javax.swing.ImageIcon;

public class AlienShip extends SpaceShip{
	
public Image getshipImg;
public int getX;
public int getY;
public int getW;
public int getH;


	public AlienShip() {
		super();
	}
	
	public AlienShip(int x, int y) {
		super(x, y, new ImageIcon("Alien.png"));
	}
}

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ghosts extends Enemies{
	
public Image getshipImg;
public int getX;
public int getY;
public int getW;
public int getH;


	public Ghosts() {
		super();
	}
	
	public Ghosts(int x, int y) {
		super(x, y, new ImageIcon("Alien.png"));
	}
}

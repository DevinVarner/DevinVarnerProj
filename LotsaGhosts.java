import java.awt.Image;

import javax.swing.ImageIcon;

public class LotsaGhosts extends Enemies{
	
public Image getshipImg;
public int getX;
public int getY;
public int getW;
public int getH;


	public LotsaGhosts() {
		super();
	}
	
	public LotsaGhosts(int x, int y) {
		super(x, 280, new ImageIcon("Ghost.gif"));
	}
}

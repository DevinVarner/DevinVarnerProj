import java.awt.Image;

import javax.swing.ImageIcon;

public class LotsaGhosts2 extends Enemies{
	
public Image getshipImg;
public int getX;
public int getY;
public int getW;
public int getH;


	public LotsaGhosts2() {
		super();
	}
	
	public LotsaGhosts2(int x, int y) {
		super(x, 280, new ImageIcon("Ghost2.gif"));
	}
}

import javax.swing.ImageIcon;

public class PlayerShip extends SpaceShip{
	
	
	public PlayerShip() {
		super();
	}
	
	public PlayerShip(int x, int y, int dx, int dy) {
		super(x, y,new ImageIcon("PlayerShip.gif"));
	}
}

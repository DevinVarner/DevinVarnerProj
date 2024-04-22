import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sound extends JFrame{
	private static final int WIDTH =1024;
	private static final int HEIGHT=500;
	
	public Sound () {
		super("Pumkin");
		setSize(WIDTH, HEIGHT);
		Game play = new Game();
		((Component) play).setFocusable(true);
		setBackground(Color.WHITE);
		getContentPane().add(play);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	public static void main(String[] args) {
		Sound run = new Sound();
		

	}


}
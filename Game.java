import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class Game extends JPanel implements Runnable, KeyListener{
	private BufferedImage back;
    private ImageIcon background;
    private ImageIcon title;
    private ImageIcon title2;
	private boolean start;
    private boolean gameStarted;
        private boolean collide;
        private Sound p;
        private double time;
        private double currtime;
        private int key;
        private Pictures player;
        
        
    public Game() {
        p=new Sound();
        new Thread(this).start();    
        background = new ImageIcon("background.gif");
        title = new ImageIcon("Title1.png");
        title2 = new ImageIcon("Title2.png");
        player=new Pictures("player.png",500,355,0,0, 10,20);
        start=true;
        
        gameStarted = true;
        this.addKeyListener(this);
        //p.playmusic("mario.wav");
        
    }
	
	public void run() {
		try {
			while(true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		}
		catch(Exception e) {}
	}
	
	public void paint (Graphics g)
	{
		Graphics2D twoDgraph = (Graphics2D)g;
	//take a snap shop of the current screen and same it as an image
	//that is the exact same width and height as the current screen
		if (back==null) {
			back =(BufferedImage) (createImage(getWidth(), getHeight()));
				}

	//create a graphics reference to the back ground image
	//we will draw all changes on the background image
		Graphics g2d = back.createGraphics();
		
		//this clears the old image, like an EtchASketch. If you see the old image when we learn motion, you deleted this line.
		g2d.clearRect(0, 0, getSize().width, getSize().height); 
		
		
		//START CODING GRAPHICS HERE
        Color mynewcolor = new Color(236,88,6);
       
        System.out.println(start);
        if(start==false ){
        g2d.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
        g2d.drawImage(title.getImage(), 62, 0, 900, 50, this);
        
        g2d.drawImage(new ImageIcon(player.getPic()).getImage(),(player.getx()),(player.gety()),85,100, this);	
        }
        if(start==true && gameStarted){
        g2d.drawImage(background.getImage(), -600, 0, 2000, 1500, this);
        g2d.drawImage(title.getImage(), 62, 170, 900, 50, this);
        g2d.drawImage(title2.getImage(), 287, 225, 450, 25, this);
        }
        Color mynewColor2 = new Color(255,179,64);

		
      

		//creates a new color
	
		//sets the new color. Think of the computer picking up a pen
        
    
        

		//sets a new font
		g2d.setFont(new Font ("Times New Roman", Font.PLAIN, 36));
		
		//draws a String starting at x coordinate 20 and y coordinate 30
        
    
		
		//This line tells the program to draw everything above. If you delete this, nothing will show up.
		twoDgraph.drawImage(back, 0, 0, null);
        

      
	}
    public void move() {
            
        player.move();
    }
	private int getBackground(int width) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBackground'");
    }

    



    public void keyTyped(KeyEvent e ) {

    }
   
    public void keyPressed(KeyEvent e) {
    key=e.getKeyCode();
    System.out.println(key);
    if (key == 32 ) {
        currtime = 0;
        start = false;
        gameStarted = false;
            
    }
    key = e.getKeyCode();
    if (key == 82) { // Reset the game when 'R' is pressed
        currtime = 0;
        start = false;
        gameStarted = false;
    }
    if(key==39) {
        player.setDx(2);
    }
    if(key==37) {
        player.setDx(-2);
        }

if(key==38) {

}
if(key==87) {

if(key==82) {

}
}
}


public void keyReleased(KeyEvent e){
key=e.getKeyCode();
if(key==39) {
    player.setDx(0);
}



if(key==37) {
    player.setDx(0);
    }

if(key==40) {

}
if(key==83) {

}
if(key==32) {


}
if(key==65) {

}
if(key==69) {

}
if(key==82) {

currtime=0;
System.out.println(start);
System.out.println(gameStarted);
}
}
}

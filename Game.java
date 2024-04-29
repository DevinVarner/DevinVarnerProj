import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Game extends JPanel implements Runnable, KeyListener{
	private BufferedImage back;
    private ImageIcon background;
    private ImageIcon title;
    private ImageIcon title2;
	private boolean start;
    private boolean gameStarted;
    private boolean spacebar;
        private boolean collide;
        private Sound p;
        private double time;
        private double currtime;
        private int key;
        private Pictures player;
        private Pictures player2;
        private ArrayList <PlayerMissle> playerMissiles;

        
        
    public Game() {
        p=new Sound();
        new Thread(this).start();    
        background = new ImageIcon("background.gif");
        title = new ImageIcon("Title1.png");
        title2 = new ImageIcon("Title2.png");
        player=new Pictures("player.png",500,250,0,0, 20,40);
        playerMissiles= new ArrayList <PlayerMissle> ();
        start=true;
        spacebar = false;
        key=-1;
        gameStarted = true;
        this.addKeyListener(this);
        p.playmusic("mario.wav");
        
    }
	
	public void run() {
		try {
			while(true) {
				Thread.currentThread().sleep(5);
                move();
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
        
       
        System.out.println(key);
        if(spacebar) {
            
        }
        if(!playerMissiles.isEmpty()) {
        	drawPlayerMissiles(g2d);
        }
        for(PlayerMissle pp: playerMissiles) {
            pp.setY(-1);
        }
 
        if(start==false ){
        g2d.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
        g2d.drawImage(title.getImage(), 62, 0, 900, 50, this);
        
        g2d.drawImage(new ImageIcon(player.getPic()).getImage(),(player.getx()),(player.gety()),170,200, this);	
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
    private void drawPlayerMissiles(Graphics g2d) {
        for(PlayerMissle pm : playerMissiles) {
            g2d.drawImage(pm.getImg().getImage(), pm.getX(), pm.getY(), pm.getW(), pm.getH(), this);
        }
    }
 
	private int getBackground(int width) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBackground'");
    }

    



    public void keyTyped(KeyEvent e ) {

    }
   
    public void keyPressed(KeyEvent e) {
    key=e.getKeyCode();
    System.out.println(player.getdx());
    if (key == 83 ) {
        currtime = 0;
        start = false;
        gameStarted = false;
            
    }
    if (key == 32 ) {
        
            playerMissiles.add(new PlayerMissle(player.getx()+22, player.gety()));
            
        
    
            
    }
    key = e.getKeyCode();
    if(key==65) {
        spacebar = true;
        playerMissiles.add(new PlayerMissle(player.getx()+22, player.gety()-20));

    }

    if(key==39) {
        player.setDx(3);
        move();
        player=new Pictures("player2.png",player.getx(),player.gety(),player.getdx(),player.getdy(), 20,40);

    }
    if(key==37) {
        player.setDx(-3);
        move();
        player=new Pictures("player.png",player.getx(),player.gety(),player.getdx(),player.getdy(), 20,40);

        }

if(key==38) {

}
if(key==87) {

if(key==82) {

}
}
}


public void keyReleased(KeyEvent e){
    spacebar = false;
key=e.getKeyCode();
if(key==39) {
    player.setDx(0);
    move();
}



if(key==37) {
    player.setDx(0);
    move();
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

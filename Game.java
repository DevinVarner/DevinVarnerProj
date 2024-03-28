import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class Game  extends JPanel implements Runnable, KeyListener {
	private BufferedImage back;
    private int key;
    private boolean spacebar;
    private SpaceShip playerShip;
    private ArrayList <AlienShip> aliens;
    private ArrayList <PlayerMissle> playerMissiles;
    private ArrayList <AlienProj> alienMissiles;
    private long currentTime;
    private ImageIcon background;
    private long startTime;
    private long endTime;
    private double elapsedTime;
    private boolean stopTime;
    private int lives;
    private boolean playMusic;
    private Player p;
    private boolean sadmusic;

    
    public Game() {
        new Thread(this).start();    
        this.addKeyListener(this);
        //this.addMouseMotionListener(this);
        //this.addMouseListener(this);
        key =-1;
        spacebar = false;
        playerShip = new PlayerShip(0,500,0,0);
        background = new ImageIcon("background.gif");
        aliens = setAliens();
        playerMissiles= new ArrayList <PlayerMissle> ();
        alienMissiles= new ArrayList <AlienProj> ();
        currentTime = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();
        elapsedTime = 0;
        stopTime = false;
        lives = 3;
        p = new Player();
        playMusic = true;
        sadmusic = true;
    }

    private ArrayList<AlienShip> setAliens() {
        ArrayList <AlienShip> temp = new ArrayList() ;
        
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                temp.add(new AlienShip (j*75, i*50));
            }
        }
        return temp;
    }
    
    public void run()
       {
           try
           {
               while(true)
               {
                  
                repaint();
             }
          }
               catch(Exception e)
          {
          }
          }
    



   
    
    
    public void paint(Graphics g){
        
        Graphics2D twoDgraph = (Graphics2D) g;
        if( back ==null)
            back=(BufferedImage)( (createImage(getWidth(), getHeight())));
        


        collision();
        collisionPlayer();
       Graphics g2d = back.createGraphics();
    
        g2d.clearRect(0,0,getSize().width, getSize().height);
        
        g2d.setFont( new Font("Broadway", Font.BOLD, 50));
        
        
    
        g2d.clearRect(0, 0, getSize().width, getSize().height);
        
        alienShoot(g2d);
        g2d.drawImage(background.getImage(), 0, 0, 1700, getHeight(), this);
        g2d.drawImage(playerShip.getImg().getImage(), playerShip.getX(),playerShip.getY(), playerShip.getW(), playerShip.getH(), this);
        Color white = new Color(255,255,255);
        g2d.setColor(white);
        if(spacebar) {
            
        }
        if(!aliens.isEmpty()) {
        	if((System.currentTimeMillis()-currentTime)%75==0) {
        	alienMissiles.add(new AlienProj(aliens.get((int)(Math.random()*aliens.size()-1)).getX(), aliens.get(aliens.size()-1).getY()));
        	currentTime = System.currentTimeMillis();
        	}
        	drawAliens(g2d);
        	moveAliens();
        	 g2d.setColor(Color.white);
        	 g2d.fill3DRect(26, 590, 53, 25, playMusic);
        	g2d.setColor(Color.BLACK);
        	 g2d.setFont( new Font("Times New Roman", Font.BOLD, 20));
        	 g2d.draw3DRect(26, 590, 53, 25, playMusic);
        	
        	g2d.drawString("HP: "+lives, 29, 610);
        
        }
        if(aliens.isEmpty()) {
        	 g2d.setFont( new Font("Times New Roman", Font.BOLD, 50));
             g2d.drawString("YOU WONNN!!!!", 500, 200);
             if (!stopTime) {
            	 endTime = System.currentTimeMillis();
            	 stopTime = true;
            	 if(playMusic) {
         			p.playmusic("level-win-6416.wav");
         			p.playmusic("stop");
         		}
             }
             elapsedTime = (endTime - startTime)/1000.0;
             g2d.drawString("It took you " + elapsedTime + " seconds to win!", 300, 500);
        }
        
        if (lives <= 0) {
        	g2d.clearRect(0,0, getWidth(), getHeight());
        	g2d.setFont( new Font("Times New Roman", Font.BOLD, 50));
            g2d.drawString("YOU LOSE :(", 500, 200);
            if(!stopTime) {
                
     		 endTime = System.currentTimeMillis();
           	 stopTime = true;
     		
            if (playMusic) {
           	
             p.playmusic("wah-wah-sad-trombone-6347.wav");
     			playMusic=false;
     		}
        
            }
            elapsedTime = (endTime - startTime)/1000.0;
            g2d.drawString("It took you " + elapsedTime + " seconds to lose!", 300, 500);
            
        }
        
        if(!playerMissiles.isEmpty()) {
        	drawPlayerMissiles(g2d);
        }
       for(PlayerMissle pp: playerMissiles) {
    	   pp.setY(-1);
       }
       if(!alienMissiles.isEmpty()) {
    	   drawAlienMissiles(g2d);
       }
      for(AlienProj pp: alienMissiles) {
   	   pp.setY(-1);
      }
        twoDgraph.drawImage(back, null, 0, 0);
        for(AlienShip a: aliens) {
        	if(a.getY() + a.getH() == playerShip.getY()) {
        		g2d.clearRect(0,0, getWidth(), getHeight());
            	g2d.setFont( new Font("Times New Roman", Font.BOLD, 50));
                g2d.drawString("YOU LOSE :(", 500, 200);
                if(playMusic=true) {
         			p.playmusic("wah-wah-sad-trombone-6347.wav");
         			
         		}
                playMusic=false;
                if (!stopTime) {
               	 endTime = System.currentTimeMillis();
               	 stopTime = true;
               	 
                }
                elapsedTime = (endTime - startTime)/1000.0;
                g2d.drawString("It took you " + elapsedTime + " seconds to lose!", 300, 500);
                playMusic=false;
             }
 
           }
        
		

   }
    
    public int getRandomNumber() {
 	   return (int)(Math.random()*aliens.size());
    }
   private void drawPlayerMissiles(Graphics g2d) {
	   for(PlayerMissle pm : playerMissiles) {
		   g2d.drawImage(pm.getImg().getImage(), pm.getX(), pm.getY(), pm.getW(), pm.getH(), this);
       }
   }
   
   private void collision() {
		
		for (int i = 0; i<playerMissiles.size(); i++) { 
			for (int j = 0; j<alienMissiles.size(); j++) {
				if (playerMissiles.get(i).alienCollision(aliens.get(j))) {
					aliens.remove(aliens.get(j));
					if(playMusic) {
						p.playmusic("hq-explosion-6288.wav");
						p.playmusic("stop");
					}
					playerMissiles.remove(playerMissiles.get(i));
					for(AlienShip inv : aliens) {
						
						inv.speedUp();
						
						
					}
				
			}
		
		}	
	}

} 
   private void collisionPlayer() {
		for (int i = 0; i<alienMissiles.size(); i++) {
                if (alienMissiles.get(i).playerCollision(playerShip)) {
					lives--;
                    System.out.println("collision");
                    p.playmusic("hq-explosion-6288.wav");
                    System.out.println(playerShip.getY());
					alienMissiles.remove(alienMissiles.get(i));
				
			
		
		}	
	}

} 

   private void alienShoot(Graphics g2d) {
	   
	   if((System.currentTimeMillis()-currentTime)%200==0) {
		   alienMissiles.add(new AlienProj(aliens.get(getRandomNumber()).getX(), aliens.get(getRandomNumber()).getY));
		   drawAlienMissiles(g2d);
		   currentTime = System.currentTimeMillis();
	   }
   }
   private void drawAlienMissiles(Graphics g2d) {
	   for(AlienProj am : alienMissiles) {
		   g2d.drawImage(am.getImg().getImage(), am.getX(), am.getY(), am.getW(), am.getH(), this);
		   am.move();
       }
   }


   private void drawAliens(Graphics g2d) {
	   for(AlienShip a : aliens) {
       	
       	g2d.drawImage(a.getImg().getImage(), a.getX(), a.getY(), a.getW(), a.getH(), this);
       	a.move();
       }
   }

  

   private boolean checkWall() {
		for(AlienShip inv : aliens) {
			if(inv.getX()<0 || inv.getX()+inv.getW()>=1350) {
				return true;
		}
			
		}
		return false;
		
	}
	private void moveAliens() {
		if(checkWall() == true) {
			for(AlienShip inv : aliens) {
			
				inv.reverseHorz();
				inv.setY(inv.getY());
				
			}
			
		}
		
		
		for(AlienShip inv : aliens) {
			inv.Hmove();
		}
	}
   //DO NOT DELETE
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
       //don't use
    }






//DO NOT DELETE
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
        key= e.getKeyCode();
        System.out.println(key);
        if(key==32) {
            spacebar = true;
    		playerMissiles.add(new PlayerMissle(playerShip.getX()+22, playerShip.getY()-20));

        }
    
        if(key==86) {
        	for(AlienShip inv : aliens) {
    			
    			inv.slowDown();
    			
    			
    		}
        }
        if(key==68){
            playerShip.setDx(1);
            
        }

        
        
    }




    //DO NOT DELETE
    @Override
    public void keyReleased(KeyEvent e) {
        spacebar = false;
        
        
        
    }





    
    
    



   
}
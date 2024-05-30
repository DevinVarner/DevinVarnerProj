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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel implements Runnable, KeyListener {
    private BufferedImage back;
    private ImageIcon background;
    private ImageIcon title;
    private ImageIcon starttext;
    private ImageIcon LIVES;
    private ImageIcon Points;
    private ImageIcon wave1;
    private ImageIcon wave2;
    private ImageIcon wave3;
    private ImageIcon firerate;
    private ImageIcon guidebut;
    private ImageIcon Guidetext;
    private ImageIcon Restart;
    private ImageIcon youwin;
    private ImageIcon youloose;
    private boolean start;
    private boolean gameStarted;
    private boolean manual;
    private boolean spacebar;
    private boolean pd;
    private boolean firerates;
    private boolean InsturctionScreen;
    private boolean collide;
    private Sound p;
    private double time;

    private int key;
    private Pictures player;
    private Pictures player2;
    private ArrayList<PlayerMissle> playerMissiles;
    private ArrayList<PlayerMissle> playerMissiles2;
    private ArrayList<LotsaGhosts> Ghosts;
    private ArrayList<LotsaGhosts2> Ghosts2;
    private long currentTime;
    private long startTime;
    private long endTime;
    private double elapsedTime;
    private boolean stopTime;
    private int lives;
    private int liveslost;
    private int points;
    private boolean playMusic;
    private boolean stop;
    private long lastFiredTime = 0;
    private long reloadDelay = 150;
    private long music0 =0;
    private long musicdelay = 10000000;
    private boolean musicPlayed = false;

    public Game() {
        p = new Sound();
        new Thread(this).start();
        background = new ImageIcon("background.gif");
        title = new ImageIcon("Title1.gif");
        starttext = new ImageIcon("starttext.gif");
        LIVES = new ImageIcon("LIVES.png");
        wave1 = new ImageIcon("wave1.gif");
        wave2 = new ImageIcon("wave2.gif");
        wave3 = new ImageIcon("wave3.gif");
        firerate = new ImageIcon("firerate.gif");
        Restart = new ImageIcon("restart.gif");
        guidebut = new ImageIcon("guidebut.gif");
        Guidetext = new ImageIcon("Guidetext.gif");
        youwin = new ImageIcon("youwin.gif");
        youloose = new ImageIcon("youloose.gif");
        Points = new ImageIcon("points.png");
        player = new Pictures("player.png", 422, 250, 0, 0, 20, 40);
        playerMissiles = new ArrayList<>();
        playerMissiles2 = new ArrayList<>();
        start = true;
        firerates = false;
        InsturctionScreen = false;
        spacebar = false;
        key = -1;
        gameStarted = true;
        manual = true;
        Ghosts = setGhosts();
        Ghosts2 = setGhosts2();
        this.addKeyListener(this);
        p.playmusic("mario.wav");
        pd = true;
        currentTime = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();
        elapsedTime = 0;
        stopTime = false;
        lives = 4;
        liveslost=0; 
        points = 0;
        stop = false;
        playMusic = true;
        setFocusable(true);
    }

    private ArrayList<LotsaGhosts> setGhosts() {
        ArrayList<LotsaGhosts> temp = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 7; j++) {
                temp.add(new LotsaGhosts((j * 75) - 500, i * 50));
                temp.add(new LotsaGhosts((j * 75) - 2150, i * 50));
                temp.add(new LotsaGhosts((j * 75) - 4400, i * 50));
            }
        }
        return temp;
    }

    private ArrayList<LotsaGhosts2> setGhosts2() {
        ArrayList<LotsaGhosts2> temp = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 7; j++) {
                temp.add(new LotsaGhosts2((j * 75) + 864, i * 50));
                temp.add(new LotsaGhosts2((j * 75) + 2414, i * 50));
                temp.add(new LotsaGhosts2((j * 75) + 4764, i * 50));
            }
        }
        return temp;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(5);
                move();
                repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playSoundWithVolume(String soundFilePath, float volume) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
    
           
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            
            
            gainControl.setValue(volume); 
    
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D twoDgraph = (Graphics2D) g;
        collision1();
        collision2();
        collisionPlayer();
        collisionPlayer2();

        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        Graphics g2d = back.createGraphics();
        g2d.clearRect(0, 0, getSize().width, getSize().height);

        if (spacebar && pd) {
            
        }

        for (PlayerMissle pp : playerMissiles) {
            if (player.getPic().equals("player.png") || player.getPic().equals("player2.png")) {
                pp.setX(-50);
            }
        }

        for (PlayerMissle pp2 : playerMissiles2) {
            if (player.getPic().equals("player2.png") || player.getPic().equals("player.png")) {
                pp2.setX(50);
                player.getx();
            }
        }

        if (!start) {
            InsturctionScreen=false;
            g2d.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            g2d.drawImage(title.getImage(), 275, 0, 500, 95, this);
            g2d.drawImage(new ImageIcon(player.getPic()).getImage(), player.getx(), player.gety(), 170, 200, this);
            drawGhosts(g2d);
            drawGhosts2(g2d);
            currentTime = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();
        elapsedTime = 0;
        stopTime = false;
        }
        if (manual == false) {
            g2d.drawImage(background.getImage(), -600, 0, 2000, 1500, this);
            g2d.drawImage(title.getImage(), 0, 100, 1000, 190, this);
            g2d.drawImage(Guidetext.getImage(), 250, 225, 500, 95, this);
        }
        if (start && gameStarted) {
            g2d.drawImage(background.getImage(), -600, 0, 2000, 1500, this);
            g2d.drawImage(title.getImage(), 0, 100, 1000, 190, this);
            g2d.drawImage(Guidetext.getImage(), 250, 225, 500, 95, this);
        }
        if(InsturctionScreen){
            g2d.drawImage(background.getImage(), -600, 0, 2000, 1500, this);
            g2d.drawImage(guidebut.getImage(), 100, 0, 750, 400, this);
            g2d.drawImage(starttext.getImage(), 200, 375, 600, 95, this);
        }
        if (!start) {
            if(firerates){
            g2d.drawImage(firerate.getImage(), 350, 150, 300, 50, this);
        }
    }

        if (!playerMissiles.isEmpty()) {
            drawPlayerMissiles(g2d);
        }
        if (!playerMissiles2.isEmpty()) {
            drawPlayerMissiles2(g2d);
        }

        if (!Ghosts.isEmpty() && !start) {
            if ((System.currentTimeMillis() - currentTime) % 75 == 0) {
                currentTime = System.currentTimeMillis();
            }

            g2d.setFont(new Font("Roboto", Font.BOLD, 50));
            g2d.setColor(Color.WHITE);
            g2d.drawString("       " + lives, 350, 110);
            g2d.drawImage(LIVES.getImage(), 320, 68, 125, 50, this);
            g2d.drawImage(Points.getImage(), 535, 68, 150, 50, this);
            g2d.drawString("       " + points, 590, 110);
        }
        
        if ( !Ghosts2.isEmpty() && !start) {
            if ((System.currentTimeMillis() - currentTime) % 75 == 0) {
                currentTime = System.currentTimeMillis();
            }

            ;
            g2d.setFont(new Font("Roboto", Font.BOLD, 50));
            g2d.setColor(Color.WHITE);
            g2d.drawString("       " + lives, 350, 110);
            g2d.drawImage(LIVES.getImage(), 320, 68, 125, 50, this);
            g2d.drawImage(Points.getImage(), 535, 68, 150, 50, this);
            g2d.drawString("       " + points, 590, 110);
        }
        if (points + liveslost  >= 0 && points + liveslost  < 14 && !start) {
            for (LotsaGhosts2 inv : Ghosts2) {
                for (LotsaGhosts env : Ghosts) {
            g2d.drawImage(wave1.getImage(), 430, 120, 150, 50, this);
                   
        }
    }
}
if ( points + liveslost >= 14 && points + liveslost < 28) {
    g2d.drawImage(wave2.getImage(), 430, 120, 150, 50, this);
    for (LotsaGhosts2 inv : Ghosts2) {
        inv.setX(inv.getX() - 1 ); 
    }
    for (LotsaGhosts env : Ghosts) {
        env.setX(env.getX() + 1); 
    }
}

if (points + liveslost  >= 28 && points + liveslost  < 42) {
    g2d.drawImage(wave3.getImage(), 430, 120, 150, 50, this);
    for (LotsaGhosts2 inv : Ghosts2) {
        inv.setX(inv.getX() - 2 ); 
    }
    for (LotsaGhosts env : Ghosts) {
        env.setX(env.getX() + 2); 
    }
}
if (points + liveslost  >= 42 ) {
    g2d.setFont(new Font("Roboto", Font.BOLD, 170));
    g2d.drawImage(background.getImage(), -600, 0, 2000, 1500, this);
    g2d.drawImage(youwin.getImage(), 0, 150, 1000, 190, this);
    g2d.drawImage(Restart.getImage(), 360, 400, 300, 50, this);
    g2d.setColor(Color.WHITE);
    g2d.drawString("       " + lives, 280, 135);
        g2d.drawImage(LIVES.getImage(), 225, 0, 375, 150, this);
    if (!musicPlayed) { 
        if (!stopTime) {
            endTime = System.currentTimeMillis();
            stopTime = true;
            if (currentTime - music0 > musicdelay) {
                if (playMusic) {
                    p.playmusic("level-win-6416.wav");
                    currentTime = currentTime; 
                }
                musicPlayed = true; 
            }
            elapsedTime = (endTime - startTime) / 1000.0;
        }
    }
}
        if (lives <= 0) {
            for (LotsaGhosts2 inv : Ghosts2) {
                inv.setX(inv.getX() + 1000 ); 
            }
            for (LotsaGhosts env : Ghosts) {
                env.setX(env.getX() - 1000); 
            }
            g2d.drawImage(background.getImage(), -600, 0, 2000, 1500, this);
            g2d.drawImage(youloose.getImage(), 0, 100, 1000, 250, this);
            g2d.drawImage(Restart.getImage(), 360, 400, 300, 50, this);
            if (!musicPlayed) { 
            if (!stopTime) {
                endTime = System.currentTimeMillis();
                stopTime = true;
                if (currentTime - music0 > musicdelay) {
                    if (playMusic) {
                        p.playmusic("wah-wah-sad-trombone-6347.wav");
                        currentTime = currentTime; 
                    }
                    musicPlayed = true; 
                }
                elapsedTime = (endTime - startTime) / 1000.0;
            }
        }
        }
    

        twoDgraph.drawImage(back, 0, 0, null);

        for (LotsaGhosts e : Ghosts) {
            for (LotsaGhosts2 p : Ghosts2) {
                if (e.getY() + e.getH() == player.gety() && p.getY() + p.getH() == player.gety()) {
                    g2d.clearRect(0, 0, getWidth(), getHeight());
                    g2d.setFont(new Font("Times New Roman", Font.BOLD, 50));
                    g2d.drawString("YOU LOSE :(", 500, 200);
                    if (playMusic) {
                        // p.playmusic("wah-wah-sad-trombone-6347.wav");
                        // p.playmusic("stop");
                    }
                    if (!stopTime) {
                        endTime = System.currentTimeMillis();
                        stopTime = true;
                    }
                    elapsedTime = (endTime - startTime) / 1000.0;
                    g2d.drawString("It took you " + elapsedTime + " seconds to lose!", 300, 500);
                }
            }
        }
    }

    public void move() {
        player.move();

        for (PlayerMissle pm : playerMissiles) {
            pm.move();
        }

        for (PlayerMissle pm2 : playerMissiles2) {
            pm2.move();
        }

        
    }

    private void drawPlayerMissiles(Graphics g2d) {
        for (PlayerMissle pm : playerMissiles) {
            g2d.drawImage(pm.getImg().getImage(), pm.getX(), pm.getY(), 60, 60, this);
        }
    }

    private void drawGhosts(Graphics g2d) {
        for (LotsaGhosts g : Ghosts) {
            g2d.drawImage(g.getImg().getImage(), g.getX(), g.getY(), 175, 100, this);
            g.move();
        }
    }

    private void drawGhosts2(Graphics g2d) {
        for (LotsaGhosts2 p : Ghosts2) {
            g2d.drawImage(p.getImg().getImage(), p.getX(), p.getY(), 175, 100, this);
            p.move2();
        }
    }

    private void drawPlayerMissiles2(Graphics g2d) {
        for (PlayerMissle pm2 : playerMissiles2) {
            g2d.drawImage(pm2.getImg().getImage(), pm2.getX(), pm2.getY(), 60, 60, this);
        }
    }

    private void collision1() {
        for (int i = 0; i < playerMissiles.size(); i++) {
            for (int j = 0; j < Ghosts.size(); j++) {
                PlayerMissle pm = playerMissiles.get(i);
                pm.move();
                if (pm.getX() <= -70 || pm.getX() >= 900) {
                    playerMissiles.remove(i);
                }
                if (playerMissiles.get(i).GhostsCollision(Ghosts.get(j))) {
                    Ghosts.remove(Ghosts.get(j));
                    points++;
                    if (playMusic) {
                        
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    playerMissiles.remove(playerMissiles.get(i));
                    for (LotsaGhosts inv : Ghosts) {
                        
                    }
                }
            }
        }
    }

    private void collision2() {
        for (int i = 0; i < playerMissiles2.size(); i++) {
            for (int j = 0; j < Ghosts2.size(); j++) {
                PlayerMissle pm2 = playerMissiles2.get(i);
                pm2.move();
                if (pm2.getX() <= 0 || pm2.getX() >= 900) {
                    playerMissiles2.remove(i);
                }
                if (playerMissiles2.get(i).GhostsCollision(Ghosts2.get(j))) {
                    Ghosts2.remove(Ghosts2.get(j));
                    points ++;
                    if (playMusic) {
                        playSoundWithVolume("bleh.wav", -40.0f);
                    }
                    playerMissiles2.remove(playerMissiles2.get(i));
                    for (LotsaGhosts2 inv : Ghosts2) {
                        
                    }
                }
            }
        }
    }

    private void collisionPlayer() {
        for (int i = 0; i < Ghosts.size(); i++) {
            if (Ghosts.get(i).playerCollision(player)) {
                lives--;
                liveslost++;
                if (playMusic) {
                    p.playmusic("ow.wav");
                    p.playmusic("stop");
                }
                System.out.println("collision");
                System.out.println(player.gety());
                Ghosts.remove(Ghosts.get(i));
            }
            
        }
    }
    private void collisionPlayer2() {
        for (int i = 0; i < Ghosts2.size(); i++) {
            if (Ghosts2.get(i).playerCollision(player)) {
                lives--;
                liveslost++;
                System.out.println("collision");
                System.out.println(player.gety());
                Ghosts2.remove(Ghosts2.get(i));
            }
            
        }
    }
    

    private boolean checkWall() {
        for (LotsaGhosts inv : Ghosts) {
            if (inv.getX() < 0 || inv.getX() + inv.getW() >= 1350) {
                return true;
            }
        }
        return false;
    }
    
        
        
    private void moveGhosts() {
        if (checkWall() == true) {
            for (LotsaGhosts inv : Ghosts) {
                inv.reverseHorz();
                inv.setY(inv.getY() + 5);
            }
        }

        for (LotsaGhosts inv : Ghosts) {
            inv.Hmove();
        }

        for (LotsaGhosts2 inv : Ghosts2) {
            inv.Hmove2();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
        System.out.println(key);
        if (key == 82) {  // Reset the game state
            Ghosts.clear(); 
            Ghosts2.clear(); 
            points = 0;
            lives = 4;
            liveslost = 0;
            start = true;
            gameStarted = true;
            manual = true;
            player = new Pictures("player.png", 422, 250, 0, 0, 20, 40);
            playerMissiles.clear();
            playerMissiles2.clear();
            currentTime = System.currentTimeMillis();
            startTime = System.currentTimeMillis();
            endTime = System.currentTimeMillis();
            elapsedTime = 0;
            stopTime = false;
            musicPlayed = false;
            Ghosts = setGhosts();
            Ghosts2 = setGhosts2();
            repaint();  
        }
        if (key == 78) {
           InsturctionScreen=true;
         }
        if (key == 83) {
            
            start = false;
            gameStarted = false;
        }
        if (key == 77) {
            
           manual=false;
        }
        if (key == 70 && !start) { 
        if (reloadDelay == 0) {
            reloadDelay = 150;
            firerates=false;
        } else {
            reloadDelay = 0;
            firerates=true;
        }

    }

        if (key == 32) {
            long currentTime = System.currentTimeMillis();
            if (playMusic) {
                
                
            }
            if (currentTime - lastFiredTime > reloadDelay && !start ) { 
                if (player.getPic().equals("player.png")) {
                    playerMissiles.add(new PlayerMissle(player.getx(), player.gety() + 50, 20, 20, key, new ImageIcon("Pm2.png")));
                    p.playmusic("hq-explosion-6288.wav");
                } else {
                    playerMissiles2.add(new PlayerMissle(player.getx(), player.gety() + 50, 20, 20, key, new ImageIcon("Pm1.png")));
                    p.playmusic("hq-explosion-6288.wav");
                }
                lastFiredTime = currentTime; 
            }
        }

        if (key == 65) {
            spacebar = true;
        }

        if (key == 39) {
            player.setDx(6);
          move();
            player = new Pictures("player2.png", player.getx(), player.gety(), player.getdx(), player.getdy(), 20, 40);
            pd = false;
        }
        if (key == 37) {
            player.setDx(-6);
            move();
            player = new Pictures("player.png", player.getx(), player.gety(), player.getdx(), player.getdy(), 20, 40);
            pd = true;
        }
    }

    public void keyReleased(KeyEvent e) {

        key = e.getKeyCode();
        if (key == 39) {
            player.setDx(0);
            move();
        }

        if (key == 37) {
            player.setDx(0);
            move();
        }

        if (key == 40) {
        }
        if (key == 83) {
        }
        if (key == 32) {
        }
        if (key == 65) {
        }
        if (key == 69) {
        }
        if (key == 70 ) {
            

        }
        if (key == 80 ) {
           points=43;
        }
        
        
        
        
         
            if (key == 82) { 
                
            }
        }
    }

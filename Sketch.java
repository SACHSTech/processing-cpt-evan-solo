import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // Images
	PImage background;
  PImage background2;
  PImage sonicRun;
  PImage sonicAttack;
  PImage[] sonicRunFrames;
  PImage[] sonicAttackFrames;
  PImage drEggman;
  PImage[] drEggmanFrames;
  PImage lives;
  PImage gameOverScreen;
  PImage spritesheet;

  int sonic_runFrames = 8;
  int sonic_runFrameWidth = 90;

  // Player
  int speed = 2;
  int invulnerability = 0;
  int invulnerable = 0;
  float playerX = 80;
  float playerY = 400;
  float playerWidth = 12;
  float playerHeight = 18;
  float playerLives = 3;

  // Enemy
  float enemyX;
  float enemyY;
  float enemyLives = 3;

  // Game over
  boolean gameover = false;

  // Key pressed variables
  boolean wPressed;
  boolean aPressed;
  boolean sPressed;
  boolean dPressed;
  boolean spacePressed;
  boolean attack;

  // Moon and Sun
  double sunX = 0;
  double sunY = 250;
  float sunWidth = 40;
  float sunHeight = 40;
  double sunSpeed = 3;
  boolean sun = true;
  boolean moon = false;
  float inverse;
	
  
  public void settings() {
    size(1000, 500);
  }

 
  public void setup() {
    // Load Spritesheet
    spritesheet = loadImage("spritesheet.png");
    
    // Load images
    background = loadImage("background.jpg");
    background.resize(width, height);

    background2 = spritesheet.get(16,133,295,147);
    background2.resize(width, height);

    sonicRun = spritesheet.get(20,20,sonic_runFrames*sonic_runFrameWidth,90);
    
    sonicAttack = spritesheet.get(5,289,374,118);

    drEggman = spritesheet.get(338,130,588,120);

    lives = spritesheet.get(14,445,170,170);

    gameOverScreen = spritesheet.get(481,299,393,216);
    gameOverScreen.resize(width, height);

    // Running animation sonic
    sonicRunFrames = new PImage[sonic_runFrames];
    for(int i = 0; i < sonic_runFrames; i++){
      sonicRunFrames[i] = spritesheet.get(sonic_runFrameWidth*i, 0, sonic_runFrameWidth, sonicRun.height);
    }
    

  }

  public void draw() {
	  image(background, 0, 0);
  }

  public void keyPressed(){
    // Detecting movement
    if(key == 'w'){
      wPressed = true;
    }
    if(key == 'a'){
      aPressed = true;
    }
    if(key =='s'){
      sPressed = true;
    }
    if(key == 'd'){
      dPressed = true;
    }
    if(key == ' '){
      spacePressed = true;
    }
  }
  
  public void mousePressed(){
    attack = true;
  }

  public void mouseReleased(){
    attack = false;
  }

  public void dayCycle(){
    // Day
    if(sun == true && gameover == false){
      image(background, 0, 0);

      fill(245, 255, 50);
      ellipse(inverse, (float)sunY, sunWidth, sunHeight);
      sunX += sunSpeed;
      sunY = (0.0007*(Math.pow(sunX - width/2, 2))) + 40;
      inverse = width - (float) sunX;
      
      if (sunY >= 300) {
        moon = true;
        sun = false;
        sunX = 0;
        sunY = 250;
      }
    }
    else if(moon == true && gameover == false){
      background2 = spritesheet.get(16,133,295,147);

      // Night
      fill(255, 255, 255);
      ellipse(inverse, (float)sunY, sunWidth, sunHeight);
      sunX += sunSpeed;
      sunY = (0.0007*(Math.pow(sunX - width/2, 2))) + 40;
      inverse = width - (float) sunX;

      if (sunY >= 300) {
        moon = false;
        sun = true;
        sunX = 0;
        sunY = 250;
      }
    else if(gameover == true){
        
    }
    }
  }
  
}
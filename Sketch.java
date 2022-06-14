import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // Images
	PImage background;
  PImage background2;

  // Player
  int speed = 2;
  int invulnerability = 0;
  int invulnerable = 0;
  float playerX = 80;
  float playerY = 400;
  float playerWidth = 12;
  float player height = 18;
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
  float sunWidth = 40
  float sunHeight = 40;
  double sunSpeed = 3;
  boolean sun = true;
  boolean moon = false;
  float inverse;
	
  
  public void settings() {
    size(1000, 500);
  }

 
  public void setup() {
    background(210, 255, 173);
    background = loadImage("background.jpg");
    background.resize(width, height);

  }

  public void draw() {
	  image(img, 0, 0);
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
      background = loadImage(background.jpg);

      fill(245, 255, 50);
      ellipse(inverse, (float)sunY, sunWidth, sunHeight);
      sunX += sunSpeed;
      sunY = (0.0007*(Math.pow(sunX - width/2, 2))) + 40;
      inverse = width - (float) sunX;
      
      if (SunMoonY >= 300) {
        moon = true;
        sun = false;
        sunX = 0;
        sunY = 250;
      }
    }
    else if(moon == true && gameover == false){
      background2

      // Night
      fill(255, 255, 255);
      ellipse(inverse, (float)sunY, sunWidth, sunHeight);
      sunX += sunSpeed;
      sunY = (0.0007*(Math.pow(sunX - width/2, 2))) + 40;
      inverse = width - (float) sunX;

      if (SunMoonY >= 300) {
        moon = false;
        sun = true;
        sunX = 0;
        sunY = 250;
    }
  }
  
}
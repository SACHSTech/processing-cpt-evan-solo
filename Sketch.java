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
  
}
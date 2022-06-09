import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // Images
	PImage img;

  // Player
  int speed = 2;
  int invulnerability = 0;
  int invulnerable = 0;
  float playerX = 80;
  float playerY = 400;
  float playerWidth = 12;
  float player height = 18;

  // Game over
  boolean gameover = false;

  // Key pressed variables
  boolean WPressed;
  boolean APressed;
  boolean SPressed;
  boolean DPressed;
  boolean SpacePressed;
	
  
  public void settings() {
    size(1000, 500);
  }

 
  public void setup() {
    background(210, 255, 173);
    img = loadImage("background.jpg");
    img.resize(width, height);

  }

  public void draw() {
	  image(img, 0, 0);
  }
  
}
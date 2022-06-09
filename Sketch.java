import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // Images
	PImage img;
	
  
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
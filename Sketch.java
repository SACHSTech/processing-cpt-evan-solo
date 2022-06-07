import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	PImage img;
	
  
  public void settings() {
	// put your size call here
    size(1600, 800);
  }

 
  public void setup() {
    background(210, 255, 173);
    img = loadImage("cptbackground.jpg");

  }

  public void draw() {
	  image(img, 0, 0);
  }
  
  // define other methods down here.
}
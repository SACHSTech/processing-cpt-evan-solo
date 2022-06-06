import processing.core.PApplet;

public class Sketch extends PApplet {
	public PImage img;
	
  
  public void settings() {
	// put your size call here
    size(1600, 800);
  }

  img = loadImage("cptbackground.jpg");

  public void setup() {
    background(210, 255, 173);
  }

  public void draw() {
	   
  }
  
  // define other methods down here.
}
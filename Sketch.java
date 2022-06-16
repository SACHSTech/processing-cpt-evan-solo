import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // Images
	PImage background;
  PImage background2;
  PImage sonicRunRight;
  PImage sonicRunLeft;
  PImage sonicAttack;
  PImage[] sonicRunLeftFrames;
  PImage[] sonicRunRightFrames;
  PImage[] sonicAttackFrames;
  PImage lives;
  PImage gameOverScreen;
  PImage spritesheet;
  PImage winScreen;
  PImage quandaleDingle;

  int sonic_runFrames = 8;
  int sonic_runFrameWidth = 90;
  int sonic_attackFrames = 4;
  int sonic_attackWidth = 98;

  // Player
  int speed = 2;
  int invulnerability = 0;
  int invulnerable = 0;
  float playerX = 80;
  float playerY = 400;
  float playerWidth = 12;
  float playerHeight = 18;
  float playerLives = 3;
  float playerSpeedY = 0;
  boolean jumping = false;

  // Game over
  boolean gameover = false;

  // Enemy
  float enemyX = 875;
  float enemyY = 340;
  float enemyHeight = 50;
  float enemyLives = 2;
  float enemyWidth = 5;

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
  double sunSpeed = 5;
  boolean sun = true;
  boolean moon = false;
  float inverse;

  // Lives
  float heartY = 450;
  float oneHeartX = 940;
  float twoHeartX = 920;
  float threeHeartX = 900;
	
  
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

    winScreen = loadImage("winscreen.jpg");
    winScreen.resize(width, height);

    quandaleDingle = loadImage("quandaledingle.png");
    quandaleDingle.resize(quandaleDingle.width/5,quandaleDingle.height/5);

    sonicRunLeft = spritesheet.get(20,20,sonic_runFrames*sonic_runFrameWidth,90);

    sonicRunRight = spritesheet.get(245,563,sonic_runFrames*sonic_runFrameWidth,90);
    
    sonicAttack = spritesheet.get(5,289,374,118);

    lives = spritesheet.get(14,445,170,170);
    lives.resize(lives.width/3,lives.height/3);

    gameOverScreen = spritesheet.get(481,299,393,216);
    gameOverScreen.resize(width, height);

    // Running animation sonic going left
    sonicRunLeftFrames = new PImage[sonic_runFrames];
    for(int i = 0; i < sonic_runFrames; i++){
      sonicRunLeftFrames[i] = sonicRunLeft.get(sonic_runFrameWidth*i, 0, sonic_runFrameWidth, sonicRunLeft.height);
    }

    // Running animation sonic going right
    sonicRunRightFrames = new PImage[sonic_runFrames];
    for(int i = 0; i < sonic_runFrames; i++){
      sonicRunRightFrames[i] = sonicRunRight.get(sonic_runFrameWidth*i, 0, sonic_runFrameWidth, sonicRunRight.height );
    }

    // Attack animation sonic
    sonicAttackFrames = new PImage[sonic_attackFrames];
    for(int i = 0; i < sonic_attackFrames; i++){
      sonicAttackFrames[i] = sonicAttack.get(sonic_attackWidth*i, 0, sonic_attackWidth, sonicAttack.height );
    }
 }
    

  

  public void draw() {
    dayCycle();
    sonicRunner();
    quandaleDingle();
  }

  public void keyPressed(){
    // Detecting movement

    if(keyCode == SHIFT){
      speed = 4;
    }
    else if(key == 'a'){
      aPressed = true;
    }
    else if(key == 'd'){
      dPressed = true;
    }
    else if(key == ' '){
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
    if(sun == true && playerLives > 0){
      image(background, 0, 0);

      fill(245, 255, 50);
      ellipse(inverse, (float)sunY, sunWidth, sunHeight);
      sunX += sunSpeed;
      sunY = (0.0009*(Math.pow(sunX - width/2, 2))) + 40;
      inverse = width - (float) sunX;
      
      if (sunY >= 300) {
        moon = true;
        sun = false;
        sunX = 0;
        sunY = 250;
      }
    }
    else if(moon == true && playerLives > 0){
      background2 = spritesheet.get(16,133,295,147);

      // Night
      fill(255, 255, 255);
      ellipse(inverse, (float)sunY, sunWidth, sunHeight);
      sunX += sunSpeed;
      sunY = (0.0009*(Math.pow(sunX - width/2, 2))) + 40;
      inverse = width - (float) sunX;

      if (sunY >= 300) {
        moon = false;
        sun = true;
        sunX = 0;
        sunY = 250;
      }
  }
}
  
  public void sonicRunner(){

    playerY += playerSpeedY;
    //Drawing running sonic
    if(aPressed == true  && playerLives > 0){   
      image(sonicRunLeftFrames[(frameCount/5)%sonic_runFrames], playerX, playerY);
      playerX -= speed;
      jumping = true;
    }

    else if(dPressed == true && playerLives > 0){ 
      image(sonicRunLeftFrames[(frameCount/5)%sonic_runFrames], playerX, playerY);
      playerX += speed;
      jumping = true;
    }

    else if(spacePressed == true && playerLives > 0){
      if(!jumping){
        playerSpeedY = -10;
        jumping = true;

      if(gameover == true){
        enemyX = 250;
        playerX = 80;
        playerY = 400;
        playerLives = 3;
        enemyLives = 2; 
        sun = true;
        moon = false;
      }
      }
    }

    // Ground collision
    if(playerY + playerHeight > 340 && 23 < playerX && playerX < 150){
      playerY = 340;
      playerSpeedY = 0;
      jumping = false;
    }

    if(playerY + playerHeight > 300 && 220 < playerX && playerX < 450){
      playerY = 300;
      playerSpeedY = 0;
      jumping = false;
    }

    if(playerY + playerHeight > 405 && 530 < playerX && playerX < 600){
      playerY = 405;
      playerSpeedY = 0;
      jumping = false;
    }

    if(playerY + playerHeight > 340 && 640 < playerX && playerX < 765){
      playerY = 340;
      playerSpeedY = 0;
      jumping = false;
    }

    if(playerY + playerHeight > 385 && 850 < playerX && playerX < 969){
      playerY = 385;
      playerSpeedY = 0;
      jumping = false;
    }

    if(playerY + playerHeight > 0){
      playerLives = playerLives - 1;
      playerX = 80;
      playerY = 400;
    }
    
    
  }

  public void gameover(){
    if(playerLives == 0){
      gameover = true;
    } image(gameOverScreen, 0, 0);
  }

  public void win(){
    if(enemyLives == 0){
      image(winScreen, 0, 0);
    }
  }

  public void lives(){
    if(playerLives == 3){
      image(lives, oneHeartX, heartY);
      image(lives, twoHeartX, heartY);
      image(lives, threeHeartX, heartY);
    }
    if(playerLives == 2){
      image(lives, oneHeartX, heartY);
      image(lives, twoHeartX, heartY);
    }
    if(playerLives == 1){
      image(lives, oneHeartX, heartY);
    }
  }

  public void quandaleDingle(){
    fill(255, 0, 0);
    rect(enemyX + 10, enemyY, enemyWidth -5, enemyHeight - 5);
    image(quandaleDingle, enemyX, enemyY);

    }
  }
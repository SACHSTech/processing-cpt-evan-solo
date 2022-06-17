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
  PImage enemyHeart;
  PImage gameOverScreen;
  PImage spritesheet;
  PImage winScreen;
  PImage quandaleDingle;

  // Sonic 
  int sonic_runFrames = 8;
  int sonic_runFrameWidth = 90;
  int sonic_attackFrames = 4;
  int sonic_attackWidth = 98;
  int speed = 3;
  int invulnerability = 0;
  int invulnerable = 0;
  float playerX = 23;
  float playerY = 200;
  float playerWidth = 12;
  float playerHeight = 15;
  float playerLives = 3;
  float playerSpeedY = 0;
  boolean jumping = false;

  // Game over
  boolean gameover;

  // quandale
  float enemyX = 550;
  float enemyY = 270;
  float enemyHeight = 50;
  float enemyLives = 2;
  float enemyWidth = 5;

  // Key pressed variables
  boolean spacePressed;
  boolean attack = false;
  boolean aPressed;
  boolean dPressed;

  // Moon and Sun
  double sunX = 0;
  double sunY = 250;
  float sunWidth = 40;
  float sunHeight = 40;
  double sunSpeed = 3;
  boolean sun = true;
  boolean moon = false;
  float inverse;

  // Lives
  float heartY = 350;
  float oneHeartX = 600;
  float twoHeartX = 560;
  float threeHeartX = 520;
  
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    size(650, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    // Load Spritesheet
    spritesheet = loadImage("spritesheet.png");
    
    // Grab images from spritesheet and resize them
    background2 = spritesheet.get(16,133,295,147);
    background2.resize(width, height);

    sonicRunLeft = spritesheet.get(20,20,sonic_runFrames*sonic_runFrameWidth,90);

    sonicRunRight = spritesheet.get(245,563,sonic_runFrames*sonic_runFrameWidth,90);
    
    sonicAttack = spritesheet.get(5,289,374,118);

    lives = spritesheet.get(14,445,170,170);
    lives.resize(lives.width/4,lives.height/4);

    gameOverScreen = spritesheet.get(481,299,393,216);
    gameOverScreen.resize(width, height);

    // load other images and resize them
    winScreen = loadImage("winscreen.jpg");
    winScreen.resize(width, height);

    gameOverScreen = loadImage("gameover.jpg");
    gameOverScreen.resize(width, height);

    quandaleDingle = loadImage("quandaledingle.png");
    quandaleDingle.resize(quandaleDingle.width/5,quandaleDingle.height/5);
    
    enemyHeart = loadImage("quandaleheart.png");
    enemyHeart.resize(enemyHeart.width/14, enemyHeart.height/14);
    
    background = loadImage("background.jpg");
    background.resize(width, height);

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
  
  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    ground();
    dayCycle();
    sonicAttack();
    lives();
    quandaleDingle();
    sonicRunner();
    win();
  }
  
  /**
   * 
   * detects when key is pressed
   */
  public void keyPressed(){
    if(key == 'f'){
      attack = true;
    }
    if(key == 'a'){
      aPressed = true;
    }
    if(key == 'd'){
      dPressed = true;
    }
    if(key == ' '){
      spacePressed = true;
    }
  }
  
  /**
   * 
   * detects when key is released
   */
  public void keyReleased(){
    if(key == 'f'){
      attack = false;
    }
    if(key == 'a'){
      aPressed = false;
    }
    if(key == 'd'){
      dPressed = false;
    }
    if(key == ' '){
      spacePressed = false;
    }
  }
  
  /**
   * 
   * detects when mouse is pressed
   */
  public void mousePressed(){
    attack = true;
  }

  /**
   * 
   * detects when mouse is released
   */
  public void mouseReleased(){
    attack = false;
  }

  /**
   * 
   * draws the sun and moon, as well as the background for day and night
   */
  public void dayCycle(){
    // Day
    if(sun == true && playerLives > 0 && enemyLives > 0){
      image(background, 0, 0);

      fill(245, 255, 50);
      ellipse(inverse, (float)sunY, sunWidth, sunHeight);
      sunX += sunSpeed;
      sunY = (0.0006 * (Math.pow(sunX - width/2, 2))) + 40;
      inverse = width - (float) sunX;
      
      if (sunY >= 300) {
        moon = true;
        sun = false;
        sunX = 0;
        sunY = 250;
      }
    }
    else if(moon == true && playerLives > 0 && enemyLives > 0){
      image(background2, 0, 0);

      // Night
      fill(255, 255, 255);
      ellipse(inverse, (float)sunY, sunWidth, sunHeight);
      sunX += sunSpeed;
      sunY = (0.0006*(Math.pow(sunX - width/2, 2))) + 40;
      inverse = width - (float) sunX;

      if (sunY >= 300) {
        moon = false;
        sun = true;
        sunX = 0;
        sunY = 250;
      }
    }
  }
  
  /**
   * 
   * moves sonic when keys are pressed
   */
  public void sonicRunner(){
    // gravity
    playerY += playerSpeedY;
    
    // movement for sonic
    if(aPressed == true){   
        playerX -= speed;
        jumping = true;
    }

    if(dPressed == true){ 
        playerX += speed;
        jumping = true;
    }

    if(spacePressed == true){
      if(!jumping){
          playerSpeedY = -20;
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
 }
  
  /**
   * 
   * drraws the animation for sonic's attack
   */
  public void sonicAttack(){
    // draws attacking sonic and detects if he hits quandale
    if(attack == true && enemyLives > 0){ image(sonicAttackFrames[(frameCount/4)%sonic_attackFrames], playerX, playerY);
      if(attack == true && playerX > 460){
        enemyLives = enemyLives - 1;
        playerX = 23;
        playerY = 240;
    }
  }
    // drawing runnning sonic
    if(attack == false && enemyLives > 0){   
      image(sonicRunRightFrames[(frameCount/4)%sonic_runFrames], playerX, playerY);
    }
 }
  
  /**
   * 
   * draws the win screen when the player wins
   */
  public void win(){
    // win screen
    if(enemyLives == 0){
      image(winScreen, 0, 0);
    }
  }

  /**
   * 
   * draws the amount of lives sonic and quandale has
   */
  public void lives(){
    // drawing sonic lives
    if(playerLives == 3 && enemyLives > 0){
      image(lives, oneHeartX, heartY);
      image(lives, twoHeartX, heartY);
      image(lives, threeHeartX, heartY);
    }
    if(playerLives == 2 && enemyLives > 0){
      image(lives, oneHeartX, heartY);
      image(lives, twoHeartX, heartY);
    }
    if(playerLives == 1 && enemyLives > 0){
      image(lives, oneHeartX, heartY);
    }
    if(playerLives == 0 && enemyLives > 0){
      image(gameOverScreen, 0, 0);
    }
    // drawing quandale lives
    if(playerLives > 0 && enemyLives == 2){
      image(enemyHeart, 0, heartY);
      image(enemyHeart, 40, heartY);
    }
    if(playerLives > 0 && enemyLives == 1){
      image(enemyHeart, 0, heartY);
    }
  }

  /**
   * 
   * draws quandale dingle
   */
  public void quandaleDingle(){
    // drawing quandale
    if(playerLives > 0 && enemyLives > 0){
      fill(255, 0, 0);
      rect(enemyX + 10, enemyY, enemyWidth - 5, enemyHeight - 5);
      image(quandaleDingle, enemyX, enemyY);
    }
  }
  /**
   * 
   * creates barriers on the ground and applies gravity
   */
  public void ground(){
    // Ground collision
    if(playerY >= 320){
      playerLives = playerLives - 1;
       playerX = 23;
       playerY = 240;
      }
    if(playerY > 264 && 475 < playerX && playerX < 625){
      playerY = 264;
      playerSpeedY = 0;
      jumping = false;
      }
    if(playerY > 230 && 385 < playerX && playerX < 475){
      playerY = 230;
      playerSpeedY = 0;
      jumping = false;
      }
    if(playerY > 235 && 300 < playerX && playerX < 385){
      playerY = 235;
      playerSpeedY = 0;
      jumping = false;
      }
    if(playerY > 194 && 118 < playerX && playerX < 269){
      playerY = 194;
      playerSpeedY = 0;
      jumping = false;
      }
    else if(playerY > 230 && 0 < playerX && playerX < 78){
      playerY = 230;
      playerSpeedY = 0;
      jumping = false;
      }
    else{
      playerSpeedY++;
      }
    }
}
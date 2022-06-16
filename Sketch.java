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
  float playerX = 23;
  float playerY = 200;
  float playerWidth = 12;
  float playerHeight = 15;
  float playerLives = 3;
  float playerSpeedY = 0;
  boolean jumping = false;

  // Game over
  boolean gameover;

  // Enemy
  float enemyX = 550;
  float enemyY = 270;
  float enemyHeight = 50;
  float enemyLives = 2;
  float enemyWidth = 5;

  // Key pressed variables
  boolean spacePressed;
  boolean attack = false;

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

  // Ground
  float groundA = 275;
  float groundB = 240;
  float groundC = 327;
  float groundD = 453;
  float groundE = 305;
	
  
  public void settings() {
    size(650, 400);
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
    lives.resize(lives.width/4,lives.height/4);

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
    ground();
    dayCycle();
    sonicAttack();
    //sonicRunner();
    lives();
    
  }

  public void keyPressed(){
    if(keyPressed){
      if(keyCode == SHIFT){
        speed = 4;
      }
      else if(keyCode == 'A'){   
        playerX -= speed;
        jumping = true;
    }

      else if(keyCode == 'D'){ 
        playerX += speed;
        jumping = true;
    }

      else if(keyCode == ' '){
        if(!jumping){
          playerSpeedY = -15;
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
    if(key == 'f'){
      attack = true;
    }
}

  public void keyReleased(){
    if(key == 'f'){
      attack = false;
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
      sunY = (0.0006 * (Math.pow(sunX - width/2, 2))) + 40;
      inverse = width - (float) sunX;
      
      if (sunY >= 300) {
        moon = true;
        sun = false;
        sunX = 0;
        sunY = 250;
      }
    }
    else if(moon == true && playerLives > 0){
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
  
  public void sonicRunner(){
     

    
    
    
  }

  public void sonicAttack(){
    
    if(attack == true){ image(sonicAttackFrames[(frameCount/4)%sonic_attackFrames], playerX, playerY + 50);
      if(attack == true && playerX == enemyX && playerY == enemyY){
      enemyLives = enemyLives - 1;
    }
  }
    if(attack == false){ image(sonicRunRightFrames[(frameCount/4)%sonic_runFrames], playerX, playerY + 50);
    }
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

  public void groundd(){
    // Ground collision
    if(playerY + playerHeight > groundA && 12 < playerX && playerX < 96){
      playerY = 272;
      playerSpeedY = 0;
      jumping = false;
    }

    else if(playerY + playerHeight > groundB && 140 < playerX && playerX < 278){
      playerY = 198;
      playerSpeedY = 0;
      jumping = false;
    }

    else if(playerY + playerHeight > groundC && 340 < playerX && playerX < 390){
      playerY = 324;
      playerSpeedY = 0;
      jumping = false;
    }

    else if(playerY + playerHeight > groundD && 410 < playerX && playerX < 495){
      playerY = 272;
      playerSpeedY = 0;
      jumping = false;
    }

    else if(playerY + playerHeight > groundE && 550 < playerX && playerX < 630){
      playerY = 385;
      playerSpeedY = 0;
      jumping = false;
    }

    else if(playerY + playerHeight > 0){
      playerLives = playerLives - 1;
      playerX = 23;
      playerY = 200;
    }
    else{
      playerSpeedY++;
    }
  }

  public void ground(){
    fill(0, 0, 0);
    line(16, 277, 95, 277);
    line(144, 240, 280, 240);
    line(344, 327, 390, 327);
    line(415, 276, 497, 276);
    line(550, 310, 628, 310);
  }
  }
PImage Bird;
PImage DeadBird;
PImage Background;
PImage RoofPipe1;
PImage RoofPipe2;
PImage FloorPipe1;
PImage FloorPipe2;

int mapX = 0;

int Score = 0;

// Bird
int BirdWidth = 40;
int BirdHeight = 40;

int xPos = 50;
int yPos = 140;

// Pipe Variables
int xPosPipes1 = -100;
int yPosRP1 = 0;
int yPosRP2 = 0;

int xPosPipes2 = -100;
int yPosFP1 = 0;
int yPosFP2 = 0;

int pipeFrame = 0;
int pipeSpeed = 2;

// Falling
int dropSpeed = 1;
int dropFrame = 0;

boolean Jumping = false;
boolean CanJump = true;
boolean LetGo = false;
boolean Lost = false;

void setup(){
  Background = loadImage("FlappyBirdBackground.jpg");
  Bird = loadImage("Flappy Bird.png");
  DeadBird = loadImage("Dead Flappy Bird.png");
  RoofPipe1 = loadImage("Roof Pipe.png");
  RoofPipe2 = loadImage("Roof Pipe.png");
  FloorPipe1 = loadImage("Floor Pipe.png");
  FloorPipe2 = loadImage("Floor Pipe.png");
  
  size(500,350);
  Background.resize(500,350);
  
  Bird.resize(BirdWidth,BirdHeight);
  DeadBird.resize(BirdWidth,BirdHeight);
  background(Background);
  
  int pipeHeight = BirdWidth*6;
  
  RoofPipe1.resize(BirdWidth,pipeHeight);
  RoofPipe2.resize(BirdWidth,pipeHeight);
  FloorPipe1.resize(BirdWidth,pipeHeight);
  FloorPipe2.resize(BirdWidth,pipeHeight);
  
}

void move(){
  int dropNum = 0;
  if(dropFrame<=-5){
    dropNum = -4;
  }else if(dropFrame<=-2){
    dropNum = -3;
  }else if(dropFrame<0){
    dropNum = -2;
  } else if(dropFrame==0){
    dropNum = 0;
    CanJump = true;
    Jumping = false;
  }else if(dropFrame<=10 && dropFrame>1){
    dropNum = 1;
  } else if(dropFrame<=20){
    dropNum = 2;
  } else if(dropFrame>20 && dropFrame<30){
    dropNum = 3;
  } else if(dropFrame>=30 && dropFrame<50){
    dropNum = 4;
  } else {
    dropSpeed = -(dropFrame/10);
  }
  if(dropNum>0){
    dropNum = dropNum*dropSpeed;
  }
  yPos += dropNum;
  
  dropFrame += 1;
}

Boolean touchingPipe(PImage Pipe,int PipesNum,Boolean IsFP){
  Boolean touching = false;
  
  int xPosPipe;
  int yPosPipe;
  
  if(PipesNum == 1){
    xPosPipe = xPosPipes1;
    if(IsFP) {
      yPosPipe = yPosFP1;
    } else {
      yPosPipe = yPosRP1;
    }
  } else {
    xPosPipe = xPosPipes2;
    if(IsFP) {
      yPosPipe = yPosFP2;
    } else {
      yPosPipe = yPosRP2;
    }
  }
  
  int yPosMinPipe = yPosPipe;
  int yPosMaxPipe = yPosPipe+Pipe.height;
  
  int xPosMin = xPos-(Bird.width/2);
  int xPosMax = xPos+(Bird.width/2);
  
  int yPosMin = yPos;
  int yPosMax = yPos+Bird.height;
  
  int xPosMinPipe = xPosPipe-(Bird.width/2);
  int xPosMaxPipe = xPosPipe+(Bird.width/2);
  
  if(xPosMinPipe<=xPosMax && xPosMaxPipe>=xPosMin){
    if(IsFP){
      if(yPosMinPipe<=yPosMax){
        touching = true;
      }
    } else {
      if(yPosMaxPipe>=yPosMin){
        touching = true;
      }
    }
  }
  
  return(touching);
}

void checkIfCollide(){
  Boolean touched = touchingPipe(FloorPipe1,1,true) || touchingPipe(FloorPipe2,2,true) || touchingPipe(RoofPipe1,1,false) || touchingPipe(RoofPipe2,2,false);
  if (touched) {
    Lost = true;
  }
}

void movePipes(){
  int pipeDistance = Bird.height*2+10;
  
  int minFPyPos = 350-FloorPipe1.height;
  int maxFPyPos = 350-10-pipeDistance;
  
  if (!Lost) {
    if (xPosPipes1<=0-FloorPipe1.width) {
      float yPosFP = random(minFPyPos,maxFPyPos);
      int INTyPosFP = int(yPosFP);
      xPosPipes1 = 500+FloorPipe1.width;
      yPosFP1 = INTyPosFP;
    }
    if (xPosPipes2<=0-FloorPipe2.width && pipeFrame>250) {
      float yPosFP = random(minFPyPos,maxFPyPos);
      int INTyPosFP = int(yPosFP);
      xPosPipes2 = 500+FloorPipe2.width;
      yPosFP2 = INTyPosFP;
    } 
  }
  
  if (pipeFrame>250 && !Lost) {
    image(FloorPipe2,xPosPipes2,yPosFP2);
    yPosRP2 = yPosFP2-RoofPipe1.height-pipeDistance;
    image(RoofPipe2,xPosPipes2,yPosRP2);
    xPosPipes2 -= pipeSpeed;
  }
  
  if (!Lost) {
    image(FloorPipe1,xPosPipes1,yPosFP1);
    yPosRP1 = yPosFP1-RoofPipe1.height-pipeDistance;
    image(RoofPipe1,xPosPipes1,yPosRP1);
    pipeFrame += pipeSpeed;
    xPosPipes1 -= pipeSpeed;
    checkIfCollide();
  }
}

String returnScore(){
  String strScore = str(Score);
  int Length = strScore.length();
  
  for(int i=0;i<3-Length;i++){
    strScore = "0"+strScore;
  }
  
  return(strScore);
}

void displayScore(){
  String strScore = returnScore();
  int sizeOfText = 40;
  
  textSize(sizeOfText);
  textAlign(CENTER, CENTER);
  
  fill(0,0,0);
  text(strScore, 250, sizeOfText+12); 
  
  fill(255, 255, 255);
  text(strScore, 250, sizeOfText+10); 
}

void draw() {
  if (!Lost) {
    image(Background,mapX,0,500,350);
    image(Background,500+mapX,0,500,350);
    image(Bird,xPos,yPos,BirdWidth,BirdHeight);
    
    mapX -= pipeSpeed*2;
    
    if(xPos==xPosPipes1 || xPos==xPosPipes2){
      Score += 1;
    }
    
    movePipes();
    
    if (mapX <= -500) {
      mapX = 0;
    }
    
    if(yPos<250){
      move();
    } else {
      Lost = true;
    }
  } else {
    image(Background,mapX-500,0,500,350);
    image(Background,0+mapX,0,500,350);
    image(FloorPipe1,xPosPipes1,yPosFP1);
    image(FloorPipe2,xPosPipes2,yPosFP2);
    image(RoofPipe1,xPosPipes1,yPosRP1);
    image(RoofPipe2,xPosPipes2,yPosRP2);
    image(Bird, 5000, yPos, Bird.height, Bird.width);
    image(DeadBird, xPos, yPos, Bird.height, Bird.width);
    
    if(yPos<250){
      move();
    }
  }
  displayScore();
}

void onJumpStart(){
  if(CanJump && !LetGo && !Lost){
      LetGo = true;
      CanJump = false;
      Jumping = true;
      dropFrame = -10;
  }
}

void mouseReleased(){
  LetGo = false;
}

void mousePressed(){
  onJumpStart();
}

void keyReleased(){
  LetGo = false;
}

void keyPressed(){
  if(keyCode == 32){
    onJumpStart();
  }
}

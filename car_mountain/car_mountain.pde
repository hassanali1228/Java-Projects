/*
~ must be one character in the scene
~ use one of each primitive shapes
~ use named variables when constructing the visual elements to allow for their location or size to be easily changed
~ String class to display name on the bottom corners
~allow user to influence the sketchs visual appearance through both keyboard and mouse input
~
*/

float car;

int darkBlue = #FFCDA8;
int darkGrey = #525355;
int darkGrey2 = #53575F;
int lightGrey = #EDF2FF; 
int lightGrey2 = #E1E5F2;

void setup(){
  size(1280, 720);
  noStroke();
}


void draw(){
  background(darkBlue);
  
  //car movement
  car = car + 2;
  if(car >= width+100) {
    car = 0;
  }

  //SUN
  fill(247, 247, 156);
  ellipse(1100, 100, 100, 100);
  fill(247, 247, 156, 180);
  ellipse(1100, 100, 110, 110);
  fill(247, 247, 156, 150);
  ellipse(1100, 100, 120, 120);
  
  //MOUNTAIN1
  fill(darkGrey2);
  triangle(41, 254, -13, 500, -150, 500);
  fill(lightGrey2);
  triangle(41, 254, -13, 500, 275, 500);
  
  //MOUNTAIN5
  fill(lightGrey2);
  triangle(510, 366, 456, 500, 396, 500);
  fill(darkGrey2);
  triangle(510, 366, 456, 500, 600, 500);
  
  //LARGEMOUNTAIN2
  fill(darkGrey);
  triangle(248, 158, 154, 500, -111, 500);
  fill(lightGrey);
  triangle(248, 158, 154, 500, 591, 500);
  
  //MOUNTAIN3
  fill(darkGrey2);
  triangle(108, 350, 93, 500, -30, 500);
  fill(lightGrey2);
  triangle(108, 350, 93, 500, 253, 500);
  
  //MOUNTAIN4
  fill(darkGrey2);
  triangle(295, 323, 229, 500, 153, 500);
  fill(lightGrey2);
  triangle(295, 323, 229, 500, 420, 500);
  
  //CAR
  fill(169, 169, 169);
  rect(car, 445, 15, 10);
  
  //Snow on Bridge
  fill(225, 150);
  rect(0, 428, 1280, 2);
  //Bridge
  fill(0);
  rect(0, 430, 1280, 2);
  rect(0, 450, 1280, 15);
  rect(50, 430, 5, 20);
  rect(100, 430, 5, 20);
  rect(150, 430, 5, 20);
  rect(200, 430, 5, 20);
  rect(250, 430, 5, 20);
  rect(300, 430, 5, 20);
  rect(350, 430, 5, 20);
  rect(400, 430, 5, 20);
  rect(450, 430, 5, 20);
  rect(500, 430, 5, 20);
  rect(550, 430, 5, 20);
  rect(600, 430, 5, 20);
  rect(650, 430, 5, 20);
  rect(700, 430, 5, 20);
  rect(750, 430, 5, 20);
  rect(800, 430, 5, 20);
  rect(850, 430, 5, 20);
  rect(900, 430, 5, 20);
  rect(950, 430, 5, 20);
  rect(1000, 430, 5, 20);
  rect(1050, 430, 5, 20);
  rect(1100, 430, 5, 20);
  rect(1150, 430, 5, 20);
  rect(1200, 430, 5, 20);
  rect(1250, 430, 5, 20);

  //REFLECTIONS
  fill(0);
  rect(0, 505, 1280, 15);
  
  //GROUND
  fill(#EDEFF7);
  ellipse(0, 500, 150, 75);
  ellipse(100, 500, 100, 30);
  ellipse(1250, 500, 150, 100);
  ellipse(1180, 500, 90, 50);
  fill(#9BA8E8, 200);
  rect(0, 500, 1280, 300);
  
  
  
}

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class downhill_ski extends PApplet {

//Downhill Ski
//Hassan Ali Amjad
//CS30

//Interactive program to show a human skiing downhill

int white = 0xffecf0f1;
int snowC = 0xffFFFAFA;
int bush = 0xff218c74;
int red = 0xffe74c3c;
int brown = 0xff6E2C00;
int blue = 0xff3498db;
int sky = 0xff7ed6df;
int nightSky = 0;
int horizon = 0xff8eb3d1;
PFont f;

int playerC = red;

int currentBack = 0;
float skiX = 0;
float skiY = 250;
float snow = 180;

public void setup() {
  
  noStroke();
}

public void draw() {
  //change background when currentBack changes
  if (currentBack == 0) {
    background(sky);
  } else if (currentBack == 1) {
    background(nightSky);
  } else if (currentBack == 2) {
    background(horizon);
  } else if (currentBack == 3) {
    //trailMode
  }

  //water
  fill(blue, 200);
  rectMode(CORNER);
  rect(0, 600, 1280, 200);

  //show sun if not night
  if (currentBack != 1) {
    fill(247, 247, 156);
    ellipse(1200, 65, 100, 100);
    fill(247, 247, 156, 180);
    ellipse(1200, 65, 110, 110);
    fill(247, 247, 156, 150);
    ellipse(1200, 65, 120, 120);
  }

  //clouds
  clouds(180, 150, 1);

  //ski Character
  rectMode(CENTER);
  //color change functionality
  if (playerC != blue) {
    fill(playerC);
  }
  rect(skiX, skiY, 20, 50);

  //downwards hill
  fill(snowC);
  triangle(0, 250, 0, 720, 550, 480);

  //flat valley
  fill(snowC);
  rectMode(CORNER);
  rect(0, 470, 950, 720);

  //trees
  tree(40, 295, 30);
  tree(120, 335, 25);
  tree(75, 428, 35);
  tree(100, 600, 32);
  tree(160, 685, 30);
  tree(300, 640, 40);
  tree(238, 450, 35);
  tree(435, 580, 35);
  tree(650, 482, 35);
  tree(520, 482, 30);
  tree(365, 442, 30);
  tree(620, 620, 30);
  tree(725, 595, 30);
  tree(800, 650, 30);
  tree(820, 520, 30);

  //snowfall
  snow();
  snow = snow + 5;
  //reset snow position when reach end
  if (snow == 1280) {
    snow = 180;
  }

  //credits
  fill(0);
  f = createFont("Arial", 14, true);
  textFont(f);
  text ("By Hassan Ali", 1190, 715);
}

public void clouds(float x, float y, float size) {
  //draw clouds based on the first clouds set position
  fill(white, 235);
  ellipse(x, y, size*192, size*130);
  ellipse(x+106, y-35, size*250, size*200);
  ellipse(x+740, y-30, size*230, size*184);
  ellipse(x+905, y+15, size*290, size*187);
}

public void tree(float x, float y, float size) {
  //draw trees based on position of trunk
  rectMode(CENTER);
  fill(brown);
  rect(x, y, size/1.5f, 3*size);
  fill(bush);
  circle(x, y-(size), size*2+5);
}

public void snow() {
  //set color and transparency before drawing snowflakes
  fill(snowC, 135);
  circle(190, snow, 15);
  circle(220, snow-40, 15);
  circle(290, snow-20, 15);
  circle(910, snow+40, 15);
  circle(930, snow+15, 15);
  circle(1055, snow+30, 15);
  circle(1070, snow-40, 15);
}

public void mouseDragged() {
  //skier movement correlating with position of player
  skiX = mouseX;
  if (skiX <= 550) {
    //follow line with (-) slope
    skiY = (0.41818f)*skiX + 250;
  } else if (skiX <= 950) {
    //follow line with 0 slope
    skiY = 470;
  } else if (skiX > 950) {
    //follow downwards parabolic path
    skiY = 0.0026f*sq(skiX-950)+470;
  }
}

public void keyPressed() {
  //choose random player color when key 'z' pressed
  if (key == 'z') {
    playerC = color(random(1, 255), random(1, 255), random(1, 255));
  }
}

public void mousePressed() {
  //currentBack goes up with right click uptil 3
  if (mouseButton == RIGHT) {
    if (currentBack != 3) {
      currentBack++;
    } else {
      currentBack = 0;
    }
  }
}
  public void settings() {  size(1280, 720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "downhill_ski" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

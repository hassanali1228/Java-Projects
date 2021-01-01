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

public class sqr_first extends PApplet {

//Downhill Ski
//Hassan Ali Amjad
//CS30

//Interactive square rollover program

//declare initial color, transparency, and switch values for all quadrants
int rect1 = color(40, 116, 166);
int rect2 = color(35, 155, 86);
int rect3 = color(192, 57, 43);
int rect4 = color(108, 52, 131);
float a1, a2, a3, a4;
int s1, s2, s3, s4;

//declare booleans for clicking quadrants
boolean click;
boolean lightSwitch = true;

//declare value by which transparencies will fade
int wheelAccumulator = 10;

public void setup() {
  
}

public void draw() {
  background(255);

  //create 4 quadrant squares with borders and no transparency
  stroke(0);

  fill(rect1, a1);
  rect(0, 0, width/2, height/2);

  fill(rect2, a2);
  rect(width/2, 0, width/2, height/2);

  fill(rect3, a3);
  rect(0, height/2, width/2, height/2);

  fill(rect4, a4);
  rect(width/2, height/2, width/2, height/2);

  //show color of first 3 quadrants when mouse on top
  if (mouseX < width/2 && mouseY < height/2) {
    a1 = 255;
  } else if (mouseX > width/2 && mouseY < height/2) {
    a2 = 255;
  } else if (mouseX < width/2 && mouseY > height/2) {
    a3 = 255;
  }

  //fade the 3 quadrants when mouse not on top
  a1 -= wheelAccumulator;
  a2 -= wheelAccumulator;
  a3 -= wheelAccumulator;

  //show all colors when mouse clicked in top left quadrant
  if (click == true && mouseX < width/2 && mouseY < height/2){
    a1 = 255;
    a2 = 255;
    a3 = 255;
    a4 = 255;      
  } else {
    //set boolean to initial state
    click = false;
  }

  //show color only when clicked in bottom-right quadrant, or when entering quadrant
  if (lightSwitch && mouseX > width/2 && mouseY > height/2){
    a4 = 255;
  //if clicked again (sets lightSwitch to false), do not show color
  } else if (mouseX > width/2 && mouseY > height/2) {
    a4 = 0;
  //if mouse not in quadrant, fade the color and set boolean to initial state
  } else {
    a4 -= wheelAccumulator;
    lightSwitch = true;
  }
}

public void mousePressed() {
  
  if (mouseButton == LEFT) {
    //set click to true when left clicked in top left quadrant
    if (mouseX < width/2 && mouseY < height/2) {
      click = true;
    }
    //invert current state of lightSwitch when left clicked in bottom right quadrant
    if (mouseX > width/2 && mouseY > height/2) {
      lightSwitch = !lightSwitch;
    }
  }

  //choose random colors for all boxes from a range when right button clicked
  if (mouseButton == RIGHT) {
    rect1 = color(random(20, 80), random(90, 150), random(130, 180));
    rect2 = color(random(20, 60), random(100, 160), random(60, 110));
    rect3 = color(random(140, 200), random(30, 70), random(30, 80));
    rect4 = color(random(75, 120), random(25, 60), random(85, 155));    
  }
}

public void keyPressed(){
  //copy colors over vertically depending on where mouse is
  if (key == 'y' && mouseY < height/2){
    rect3=rect1;
    rect4=rect2;
  } else if (key == 'y' && mouseY > height/2) {
    rect1=rect3;
    rect2=rect4;
  //copy colors over horizontally depending on where mouse is
  } else if (key == 'x' && mouseX < width/2) {
    rect2=rect1;
    rect4=rect3;
  } else if (key == 'x' && mouseX > width/2) {
    rect1=rect2;
    rect3=rect4;
  //switch all colors over clockwise
  } else if (key == 'z'){
    s1 = rect1;
    s2 = rect2;
    s3 = rect3;
    s4 = rect4;

    rect1 = s3;
    rect2 = s1;
    rect4 = s2;
    rect3 = s4;
  }
}

public void mouseWheel(MouseEvent event){
  //use mousewheel to control speed of fade; scroll up will increase speed (limited between 1 and 50)
  wheelAccumulator = constrain(wheelAccumulator - event.getCount(), 1, 50);
  println("wheel: " + wheelAccumulator);
}
  public void settings() {  size(720, 720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sqr_first" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

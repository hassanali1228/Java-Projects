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

public class multicolor_grid extends PApplet {

//declare two size variables so the box can adjust to any ratio of canvas size
int sizeX = 30;
int sizeY = 30;
ArrayList<Float> colors = new ArrayList<Float>();
int i;
boolean colorChange = true;

public void setup() {
  
  //to deal with if canvise size is smaller than original box size
  if (sizeX > width) {
    sizeX = width;
  }
  if (sizeY > height) {
    sizeY = height;
  }
  //if boxes do not fit on screen, resize them by finding a size divisible by canvas size
  while (width%sizeX != 0){
    sizeX--;
  }
  while (height%sizeY != 0) {
    sizeY--;
  }
  grid();
}

/*
void draw() {
  //to process commands from mouse and key presses
}
*/
public void grid() {
  background(0);

  for (float y = 0; y < height; y+=sizeY) {
    for (float x = 0; x < width; x+=sizeX) {
      stroke(0);
      i+=3;
      if (colorChange) {
        if (i == 0){
          colors.clear();
        }
        float red = dist(x + sizeX/2, y + sizeY/2, width/2, height/2) + random(-20, 50);
        float green = dist(x + sizeX/2, y + sizeY/2, width/2, height/2) + random(-20, 50);
        float blue = dist(x + sizeX/2, y + sizeY/2, width/2, height/2) + random(-20, 50);
        fill(red, green, blue);
        colors.add(red);
        colors.add(green);
        colors.add(blue);
      } else if (colorChange == false) {
        println(colors.size());
        if (i+2 < colors.size()){
          fill(colors.get(i), colors.get(i+1), colors.get(i+2));
        }
      }
      rect(x, y, sizeX, sizeY);
    }
  }
  i = 0;
  colorChange = false;
}

public void mousePressed() {
  //decrease size of boxes with right button and ensure box xize does not decrease to pixel size
  if (mouseButton == LEFT && sizeX > 6 && sizeY > 6) {
    sizeX -= 5;
    sizeY -= 5;
    //ensure the boxes fit by finding a smaller size divisible by the width or height
    while (width%sizeX != 0) {
      sizeX--;
    }
    while (height%sizeY != 0) {
      sizeY--;
    }
    grid();
  }

  //decrease size of boxes with right button and ensure box xize does not increase more than canvas size
  if (mouseButton == RIGHT && sizeX <= width-5 && sizeY <= height-5) {
    sizeX +=5;
    sizeY += 5;
    //ensure the boxes fit by finding a bigger size divisible by the width or height
    while (width%sizeX != 0) {
      sizeX++;
    }
    while (height%sizeY != 0) {
      sizeY++;
    }
    grid();
  }
}

public void keyPressed() {
  //refresh the grid with new colors
  colorChange = true;
  grid();
}
  public void settings() {  size(720, 720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "multicolor_grid" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

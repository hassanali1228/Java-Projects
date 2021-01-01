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

public class sprite_animation extends PApplet {

//Sprite Animation
//Hassan Ali Amjad
//CS30

//Interactive program to show character movement in all directions

//create new array for all sprite positions and variable for background
PImage [] sprite = new PImage [15];
PImage imgBg;

//set initial positions and font
int x = 65;
int y = 15;
PFont f;

//start of sprite frames for each directions
int forward = 0;
int right = 4;
int left = 8;
int backward = 12;

//adjusts difficulty level of game
int wheelAccumulator = 8;

public void setup() {
    
    imageMode(CENTER);

    //load background and all sprite positions in array
    imgBg = loadImage("maze.jpeg");
    for (int i = 0; i < 15; i++) {
        sprite[i] = loadImage(i + ".png");
        sprite[i].resize(30,0);
    }
}

public void draw() {
    background(imgBg);
    //adjust frameRate, impacting character speed and animation, with scroll wheel
    frameRate(wheelAccumulator);

    //display difficulty (frameRate) in top right
    fill(0);
    f = createFont("Arial", 8, true);
    textFont(f);
    text ("Difficulty: " + wheelAccumulator, 390, 10);        
    
    //initial display of sprite
    if (x == 65 && y == 15) {
        image(sprite[1], x, y);
    }
    
    //movement of sprite with animation to cycle through frames
    if (key == 's') {
        y += 5;
        image(sprite[forward], x, y);
        //cycle through 0 - 3
        forward ++;
        if (forward > 3) {
            forward = 0;
        }
    } else if (key == 'd') {
        x += 5;
        image(sprite[right], x, y);
        right ++;
        if (right > 7) {
            right = 4;
        }
    } else if (key == 'a') {
        x -= 5;
        image(sprite[left], x, y);
        left ++;
        if (left > 11) {
            left = 8;
        }
    } else if (key == 'w') {
        y -= 5;
        image(sprite[backward], x, y);
        backward ++;
        if (backward > 14) {
            backward = 12;
        }
    }

    //respawn to opposite side when character goes off scren
    if (x > width) {
        x = 0;
    } else if (x < 0) {
        x = width;
    } else if (y > height) {
        y = 0;
    } else if (y < 0) {
        y = width;
    }
 
}

public void keyPressed() {
    //reset button
    if (key == 'z'){
        x = 65;
        y = 15;
    }
}

public void mouseWheel(MouseEvent event) {
  //scroll up will increase speed (limited between 1 and 60)
  wheelAccumulator = constrain(wheelAccumulator - event.getCount(), 1, 60);
}
  public void settings() {  size(441,441); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sprite_animation" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

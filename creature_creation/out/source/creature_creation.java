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

public class creature_creation extends PApplet {

//Creature Creation
//Hassan Ali Amjad
//CS30

//Automated monster interaction program with user ability to create and destroy monsters

ArrayList<Monster> monsters = new ArrayList<Monster>();

public void setup() {
  
}

public void draw() {
  background(255);

  //more than one monster will always be active
  if (monsters.size()<2) {
    monsters.add(new Monster(width/2+300, height/2, 100, 150));
    monsters.add(new Monster(width/2-300, height/2, 100, 150));
    monsters.add(new Monster(width/2, height/2-300, 100, 150));
  }

  //actions for every monster in the array
  for (int i = 0; i < monsters.size(); i++) {
    //remove monster if lifetime over
    if (monsters.get(i).lifetime < 1 && monsters.size() > 3) {
      monsters.remove(i);
    } else {
      //default actions for all monsters
      monsters.get(i).display();
      monsters.get(i).move();
      monsters.get(i).pupils();

      //if too many monsters, vibrate
      if (monsters.size()>10) {
        monsters.get(i).vibrate();
      }

      monsters.get(i).changeColor(0);
      //check for collision with every other monster in array
      for (int j = i + 1; j < monsters.size(); j++) {
        if (monsters.get(i).overlap(monsters.get(j))) {
          println("i: " + i);
          println("j: " + i);
          monsters.get(j).changeColor(1);
          monsters.get(i).changeColor(2);
        }
      }

      //grow or shrink based on the monster position in array
      if (i%4 == 0) {
        monsters.get(i).grow();
      } else if (i%3 == 0) {
        monsters.get(i).shrink();
      }
    }
  }
}

public void mousePressed() {
  //add new monster to array everytime mouse is pressed
  monsters.add(new Monster(random(300,1600), random(200, 700), 100, 150));
}

public void keyPressed() {
  //remove a random monster from array when z pressed and more than 2 monsters exist
  if (key == 'z' && monsters.size()>3) {
    int oop = (int)random(monsters.size());
    monsters.remove(oop);
  }
}
class Monster {
  //positioning variables
  float x, y, widthSize, heightSize;
  //color of entire monster body
  int monsterC;
  //movement variables
  int lifetime, xSpeed, ySpeed;
  //eye positioning variables
  float x_eye, y_eye, xIntheEye, yIntheEye, spacing, eyeSize;

  Monster(float tempX, float tempY, float tempWidthSize, float tempHeightSize) {
    x = tempX;
    y = tempY;
    widthSize = tempWidthSize;
    heightSize = tempHeightSize;

    lifetime = (int)random(400, 500);
    monsterC = 0xffffffff;

    //choose random direction of movement when spawned
    int direction = (int)random(4);
    if (direction == 0) {
      xSpeed = 2;
      ySpeed = 2;
    } else if (direction == 1) {
      xSpeed = 2;
      ySpeed = -2;
    } else if (direction == 2) {
      xSpeed = -2;
      ySpeed = -2;
    } else if (direction == 3) {
      xSpeed = -2;
      ySpeed = 2;
    }
  }

  public void display() {
    stroke(0);
    fill(monsterC);
    rectMode(CENTER);

    //arms
    triangle(x+widthSize*0.5f, y+heightSize*0.55f, x+widthSize*0.5f, y+heightSize*1.2f, x+widthSize*1.8f, y+heightSize);
    triangle(x-widthSize*0.5f, y+heightSize*0.55f, x-widthSize*0.5f, y+heightSize*1.2f, x-widthSize*1.8f, y+heightSize);

    //leg
    triangle(x-widthSize*0.5f, y+heightSize*2.33f, x+widthSize*0.5f, y+heightSize*2.33f, x, y+heightSize*1.3f);

    //main body
    ellipse(x, y+heightSize, widthSize*2, heightSize*1.5f);

    //head
    circle(x, y, (widthSize+heightSize)/2);
    rect(x, y, widthSize/6, heightSize/5);
    ellipse(x, y+heightSize*0.233f, widthSize/1.67f, heightSize/7.5f);
    line(x-widthSize*0.2f, y+heightSize*0.233f, x+widthSize*0.2f, y+heightSize*0.233f);
  }

  public void move() {
    //decrease lifetime every frame until it reaches 0
    lifetime --;
    x += xSpeed;
    y += ySpeed;
    //invert direction if reached edge of screen
    if (x > width-(widthSize+heightSize)/4 || x < 0+(widthSize+heightSize)/4) {
      xSpeed *= -1;
    }
    if (y > height-(widthSize+heightSize)/4 || y < 0+(widthSize+heightSize)/4) {
      ySpeed *= -1;
    }
  }

  //swith color between white, green, and red depending on state passed
  public void changeColor(int state) {
    if (state == 0) {
      monsterC = 0xffffffff;
    } else if (state == 1) {
      //green
      monsterC = 0xff33d47e;
    } else if (state == 2) {
      //red
      monsterC = 0xffe03131;
    }
  }

  public void vibrate() {
    xSpeed *= -1;
    ySpeed *= -1;
  }

  public void grow() {
    //grow with original ratio till certain limits
    if (widthSize < 150 && heightSize < 225) {
      widthSize+=2;
      heightSize+=3;
    }
  }

  public void shrink() {
    //shrink with original ratio till certain limits
    if (widthSize > 50 && heightSize > 75) {
      widthSize-=2;
      heightSize-=3;
    }
  }

  //collision detection engine
  public boolean overlap(Monster other) {
    float d = dist(x, y, other.x, other.y);
    //overlapping if the sum of radius of both heads is bigger than the distance between them
    if (d < (widthSize+heightSize)/4 + (other.widthSize+other.heightSize)/4) {
      return true;
    } else {
      return false;
    }
  }

  public void pupils() {
    stroke(0);
    //x and y position of eyes
    x_eye = x-widthSize*0.25f;
    y_eye = y-heightSize*0.233f;
    //spacing between both eyes
    spacing = widthSize/2;
    //spawn eyes
    fill(255);
    circle(x_eye, y_eye, heightSize/5);
    circle(x_eye+spacing, y_eye, heightSize/5);
    //map the position of mouse to the posiition of pupil in eyes
    xIntheEye = map(mouseX, 0, width, x_eye-heightSize*0.08f, x_eye+heightSize*0.08f);
    yIntheEye = map(mouseY, 0, height, y_eye-heightSize*0.08f, y_eye+heightSize*0.08f);
    //increase size of pupils as mouse approaches them and vice versa
    float tempEye = dist(xIntheEye, yIntheEye, mouseX, mouseY);
    eyeSize = map(tempEye, 0, width, heightSize/40, heightSize/5); 
    //spawn pupils
    fill(0);
    circle(xIntheEye, yIntheEye, eyeSize);
    circle(xIntheEye+spacing, yIntheEye, eyeSize);
  }
}
  public void settings() {  size(1920, 1080); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "creature_creation" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

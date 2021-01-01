class Monster {
  //positioning variables
  float x, y, widthSize, heightSize;
  //color of entire monster body
  color monsterC;
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
    monsterC = #ffffff;

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

  void display() {
    stroke(0);
    fill(monsterC);
    rectMode(CENTER);

    //arms
    triangle(x+widthSize*0.5, y+heightSize*0.55, x+widthSize*0.5, y+heightSize*1.2, x+widthSize*1.8, y+heightSize);
    triangle(x-widthSize*0.5, y+heightSize*0.55, x-widthSize*0.5, y+heightSize*1.2, x-widthSize*1.8, y+heightSize);

    //leg
    triangle(x-widthSize*0.5, y+heightSize*2.33, x+widthSize*0.5, y+heightSize*2.33, x, y+heightSize*1.3);

    //main body
    ellipse(x, y+heightSize, widthSize*2, heightSize*1.5);

    //head
    circle(x, y, (widthSize+heightSize)/2);
    rect(x, y, widthSize/6, heightSize/5);
    ellipse(x, y+heightSize*0.233, widthSize/1.67, heightSize/7.5);
    line(x-widthSize*0.2, y+heightSize*0.233, x+widthSize*0.2, y+heightSize*0.233);
  }

  void move() {
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
  void changeColor(int state) {
    if (state == 0) {
      monsterC = #ffffff;
    } else if (state == 1) {
      //green
      monsterC = #33d47e;
    } else if (state == 2) {
      //red
      monsterC = #e03131;
    }
  }

  void vibrate() {
    xSpeed *= -1;
    ySpeed *= -1;
  }

  void grow() {
    //grow with original ratio till certain limits
    if (widthSize < 150 && heightSize < 225) {
      widthSize+=2;
      heightSize+=3;
    }
  }

  void shrink() {
    //shrink with original ratio till certain limits
    if (widthSize > 50 && heightSize > 75) {
      widthSize-=2;
      heightSize-=3;
    }
  }

  //collision detection engine
  boolean overlap(Monster other) {
    float d = dist(x, y, other.x, other.y);
    //overlapping if the sum of radius of both heads is bigger than the distance between them
    if (d < (widthSize+heightSize)/4 + (other.widthSize+other.heightSize)/4) {
      return true;
    } else {
      return false;
    }
  }

  void pupils() {
    stroke(0);
    //x and y position of eyes
    x_eye = x-widthSize*0.25;
    y_eye = y-heightSize*0.233;
    //spacing between both eyes
    spacing = widthSize/2;
    //spawn eyes
    fill(255);
    circle(x_eye, y_eye, heightSize/5);
    circle(x_eye+spacing, y_eye, heightSize/5);
    //map the position of mouse to the posiition of pupil in eyes
    xIntheEye = map(mouseX, 0, width, x_eye-heightSize*0.08, x_eye+heightSize*0.08);
    yIntheEye = map(mouseY, 0, height, y_eye-heightSize*0.08, y_eye+heightSize*0.08);
    //increase size of pupils as mouse approaches them and vice versa
    float tempEye = dist(xIntheEye, yIntheEye, mouseX, mouseY);
    eyeSize = map(tempEye, 0, width, heightSize/40, heightSize/5); 
    //spawn pupils
    fill(0);
    circle(xIntheEye, yIntheEye, eyeSize);
    circle(xIntheEye+spacing, yIntheEye, eyeSize);
  }
}

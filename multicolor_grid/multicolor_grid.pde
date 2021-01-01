//Multicolor-Grid
//Hassan Ali Amjad
//CS30

//Patterned randomization of a colored grid

//declare two size variables so the box can adjust to any ratio of canvas size
int sizeX = 30;
int sizeY = 30;

void setup() {
  size(720, 720);
  //to deal with if canvise size is smaller than original box size
  if (sizeX > width) {
    sizeX = width;
  }
  if (sizeY > height) {
    sizeY = height;
  }
  //if boxes do not fit on screen, resize them by finding a size divisible by canvas size
  while (width%sizeX != 0) {
    sizeX--;
  }
  while (height%sizeY != 0) {
    sizeY--;
  }
  grid();
}

void draw() {
  //to process commands from mouse and key presses
}

void grid() {
  background(0);

  //two for loops to fill the rows and columns with rectangles
  for (float y = 0; y < height; y+=sizeY) {
    for (float x = 0; x < width; x+=sizeX) {
      stroke(0);
      //produce color values based on the distance between center of boxes and center of canvas
      float red = dist(x + sizeX/2, y + sizeY/2, width/2, height/2) + random(-20, 50);
      float green = dist(x + sizeX/2, y + sizeY/2, width/2, height/2) + random(-20, 50);
      float blue = dist(x + sizeX/2, y + sizeY/2, width/2, height/2) + random(-20, 50);
      fill(red, green, blue);
      rect(x, y, sizeX, sizeY);
    }
  }
}

void mousePressed() {
  //decrease size of boxes with right button and ensure box xize does not decrease below 1
  if (mouseButton == LEFT && sizeX >= 6 && sizeY >= 6) {
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

void keyPressed() {
  //refresh the grid with new colors
  grid();
}

//Balloon Tree
//Hassan Ali Amjad
//CS30

//Fractal tree with balloons, interactive angles, and random color + size control

int realDepth = 7;
int leafBranch = 5;
float scale = 20;
float mx;
float leafSize;

void setup() {
  size(720, 720);
}

void draw() {
  background(255);
  //map from screen width to the range of angles wanted for branches
  mx = map(mouseX, 0, width, 5, 50);
  //set random seed only once before drawing tree
  randomSeed(1512);
  drawTree(width/2, height, 90, realDepth);
}

void drawLine(int x1, int y1, int x2, int y2, int depth) {
  //increase weight of line based on how close it is to the main trunk
  strokeWeight(depth);
  stroke(0);
  line(x1, y1, x2, y2);
}

void drawLeaf(int x1, int y1, int depth) {
  //condition to control which branches get leaves
  if (depth < leafBranch) {
    fill(random(255), random(255), random(255));
    //randomize leafSize with the leaves on the edges being the biggest
    leafSize = random((realDepth*1.2-depth)*2, (realDepth*1.7-depth)*3.5);
    noStroke();
    circle(x1, y1, leafSize);
  }
}

void drawTree(int x1, int y1, float angle, int depth) {
  if (depth > 0) {
    //calculate coordinates of point b on the line
    int x2 = (int)(x1 + (cos(radians(angle))*depth*scale)); 
    int y2 = (int)(y1 - (sin(radians(angle))*depth*scale));  

    //draw line from point a to point b and draw leaf on point b
    drawLine(x1, y1, x2, y2, depth);
    drawLeaf(x2, y2, depth);

    //draw a 3-branch tree, with the angle between branches adjusted by mapped mouseX
    drawTree(x2, y2, angle+mx, depth-1);
    drawTree(x2, y2, angle-mx, depth-1);
    drawTree(x2, y2, angle, depth-1);
  }
}

void keyPressed() {
  //Increase or decrease the amount of branches leaves are drawn on
  if (key == 'x'  && leafBranch <= realDepth) {
    leafBranch++;
  } else if (key == 'z' && leafBranch > 1) {
    leafBranch--;
  }
}

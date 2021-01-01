class Particle {

  PVector location;
  PVector velocity;
  PVector acceleration;

  int size, time, colorX, colorY, LIFETIME;
  boolean alive;

  Particle(int x_, int y_) {
    //declare random initial velocities
    velocity = new PVector(random(-2, 2), random(-2, 2));
    //location set to x and y passed into class
    location = new PVector(x_, y_);
    size = 20;
    //randomly choose amount of frames a particle will be displayed
    LIFETIME = (int)random(100, 150);
    acceleration = new PVector(0, 0.07);
    alive = true;
    time = 0;
  }

  void display() {
    //map the cursor position to color of particle
    colorX = (int)map(location.x, 0, width, 0, 255);
    colorY = (int)map(location.y, 0, height, 0, 255);
    fill(colorX, colorY, (colorX+colorY)/2, 200);
    noStroke();
    //create new matrix with particle at origin and manipulate that through matrix transformations
    pushMatrix();
    translate(location.x, location.y);
    //reduce size of matrix depending on frames passed
    scale(map(time, 0, LIFETIME, 1, 0));
    circle(0, 0, size);
    popMatrix();
    //keep track of frames
    time++;
  }

  void move() {
    velocity.add(acceleration);
    location.add(velocity);
    //reset acceleration
    acceleration.mult(0);

    //bounce off bottom with opposity lower velocity
    if (location.y > height) {
      velocity.y = velocity.y *= -0.75;
      location.y = height;
    }
  }

  void applyGravity(PVector gravity) {
    //acceleration = force/mass, and mass = size/20
    gravity.div(size/20);
    //add gravity to acceleration
    acceleration.add(gravity);
  }

  //declares particle as dead if frames exceed lifetime
  boolean isAlive() {
    if (time > LIFETIME) {
      alive = false;
    }
    return alive;
  }
}

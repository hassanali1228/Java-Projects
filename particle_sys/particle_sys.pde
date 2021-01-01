//Particle System
//Hassan Ali Amjad
//CS30

//Particles with gravity that spawn at your mouse cursor

ArrayList<Particle> particles = new ArrayList<Particle>();

void setup() {
  size(720, 720);
}

void draw() {
  background(0);
  //gravity is a downwards vector
  PVector gravity = new PVector(0, 0.12);
  //spawn particles at mouse cursor
  particles.add(new Particle(mouseX, mouseY));

  for (int i = 0; i < particles.size(); i++) {
    if (particles.get(i).isAlive() == false) {
      //remove dead particles
      particles.remove(i);
    } else {
      //display, move, and apply gravity on all alive particles
      particles.get(i).move();
      particles.get(i).display();
      particles.get(i).applyGravity(gravity);
    }
  }
}

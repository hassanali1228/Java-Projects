//Creature Creation
//Hassan Ali Amjad
//CS30

//Automated monster interaction program with user ability to create and destroy monsters

ArrayList<Monster> monsters = new ArrayList<Monster>();

void setup() {
  size(1920, 1080);
}

void draw() {
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

void mousePressed() {
  //add new monster to array everytime mouse is pressed
  monsters.add(new Monster(random(300, 1600), random(200, 700), 100, 150));
}

void keyPressed() {
  //remove a random monster from array when z pressed and more than 2 monsters exist
  if (key == 'z' && monsters.size()>3) {
    int oop = (int)random(monsters.size());
    monsters.remove(oop);
  }
}

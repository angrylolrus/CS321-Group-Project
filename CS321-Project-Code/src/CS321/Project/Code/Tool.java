package CS321.Project.Code;

import java.util.Random;

public class Tool extends Item {
  public String name;
  //public int defense;

  public Tool(String type, int id, String name, double volume, double weight, double damage,
      double visDamage, int age, int created) {
    super("Tool", id, name, volume, weight, damage, visDamage, age, created);
  }

  @Override
  public void takeDamage(double amt) {
	  setDamage(getDamage() + amt);
  }

//  public int getDefense() {
//    return defense;
//  }
  
  /*
  @Override
  public void inspect(double closeness) {
    Random inspectProbability = new Random();
    inspectProbability.nextInt(100);
  }//*/

  @Override
  public String[] getUses() {
    // if(type == "Tool")
    String[] uses = {"Equip", "Drop", "Use"};
    return uses;
  }

}

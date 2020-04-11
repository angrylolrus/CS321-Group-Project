package CS321.Project.Code;

import java.util.Random;

public class Tool extends Item {
  public String name;
  public String[] uses;
  public int defense;
  public String type = "Tool";

  public Tool() {
    Item tool = new Tool();
  }

  public void takeDamage(int amt, int type) {

  }

  public int getDefense() {
    return defense;
  }

  public void inspect(int closeness) {
    Random inspectProbability = new Random();
    inspectProbability.nextInt(100);
  }

  public String[] getUses(String type) {
    if(tool.type == "Tool") 
      uses = ["Equip", "Drop", "Use"];
    return uses;
  }

}

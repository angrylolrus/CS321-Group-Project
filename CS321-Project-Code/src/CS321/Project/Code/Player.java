package CS321.Project.Code;

public class Player {
    final int MAX_HEALTH = 25;
    int current_Health;
    int defense;
    Item[] equipped = new Item[5];
    Inventory inventory;

    public Player() {
        current_Health = MAX_HEALTH;
        defense = 0;
        inventory = new Inventory(25, 50);
    }

    public void takeDamage(int dmg) {
        current_Health = current_Health - (dmg - defense);
    }

    public void heal_Player(int h) {
        current_Health = current_Health + h;
    }

    // item needs to be checked somewhere that it is of a clothing type
    public void equip_Item(Item i, int type) {
        Item temp = equipped[type];
        equipped[type] = i;
        //change_Defense(i.getDefense() - temp.getDefense());
    }

    public void change_Defense(int df) {
        defense = defense + df;
    }

    public int getHealth()
    {
        return current_Health;
    }

    public int getDefense()
    {
        return defense;
    }
    
    public Inventory getInventory() {
    	return inventory;
    }

    public void getEquipped()
    {
        for(int x = 0; x < equipped.length; x++)
        {
            System.out.println(equipped[x]);
        }
    }
}

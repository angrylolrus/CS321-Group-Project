package CS321.Project.Code;

public class Player {
    private final int MAX_HEALTH = 25;
    private int current_Health;
    private int defense;
    private Item[] equipped = new Item[5];
    private Inventory inventory;
    private GameMenu parent;
    private double satiety;

    public Player(GameMenu p) {
        current_Health = MAX_HEALTH;
        defense = 0;
        inventory = new Inventory(25, 50);
        parent = p;
        satiety = 50;
    }
    
    public void age(long time) {
    	for(Item i : equipped)
    		if(i != null)	
    			i.age(time);
    	inventory.age(time);
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
        // change_Defense(i.getDefense() - temp.getDefense());
    }

    public boolean useItem(Item i) {
        if (i instanceof Food) {
            satiety += ((Food) i).getFoodValue();
            return true;
        }

        return false;
    }

    public void change_Defense(int df) {
        defense = defense + df;
    }

    public int getHealth() {
        return current_Health;
    }

    public int getDefense() {
        return defense;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void getEquipped() {
        for (int x = 0; x < equipped.length; x++) {
            System.out.println(equipped[x]);
        }
    }
}

package CS321.Project.Code;

public class Player {
    private final int MAX_HEALTH = 25;
    private int current_Health;
    private int defense;
    private Item[] equipped = new Item[5];
    private Inventory inventory;
    private GameMenu parent;
    private double satiety; //How much the player has eaten (doesn't go over certain amount)
    private double energy; // How many calories the player has stored (kinda, more granular)
    private long lastUpdated;

    public Player(GameMenu p) {
        current_Health = MAX_HEALTH;
        defense = 0;
        inventory = new Inventory(25, 50);
        parent = p;
        satiety = 50;
        energy = 100;
        lastUpdated = parent.getTime();
    }
    public long getLastUpdated() {
    	return lastUpdated;
    }
    public GameMenu getParent() {
    	return parent;
    }
    public int getEnergy() {
    	return (int)(Math.round(energy));
    }
    public int getSatiety() {
    	return (int)(Math.round(satiety));
    }
    
    public void age(long time) {
    	for(Item i : equipped)
    		if(i != null)	
    			i.age(time);
    	inventory.age(time);
    	
    	
    	satiety -= (.21 * (time-lastUpdated));
    	
    	if(satiety > 1) {
    		energy += .21 * (time-lastUpdated);
    	}
    	else {
    		energy -= (.01 * (time-lastUpdated));
    		satiety = 0;
    	}
    	
    	//System.out.println("F " + fedness + " S: " + satiety);
    	
    	if(energy < 0)
    		parent.lose();
    	
    	
    	lastUpdated = time;
    }

    public void takeDamage(int dmg) {
        current_Health = current_Health - (dmg - defense);
    }

    public void heal_Player(int h) {
        current_Health = current_Health + h;
    }

    // item needs to be checked somewhere that it is of a clothing type
    public void equip_Item(Item i, String type) {
        //Item temp = equipped[type];
        //equipped[type] = i;
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

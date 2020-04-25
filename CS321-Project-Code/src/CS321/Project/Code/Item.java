package CS321.Project.Code;

public abstract class Item {
    protected String type; // The type of object (food, tool, etc)
    protected int ID; // The ID of the type of object
    protected String name; // The simple name of the object
    protected double volume;// How big the item is
    protected double weight; // How much it weighs
    protected double damage; // How damaged it is
    protected double visDamage; // How visibly damaged it is
    protected int age; // How long the object has existed
    protected int created; // When the object was created

    // Subclasses will need to have their own (static) methods to
    // read the item ID from an XML file (or whatever we decide
    // to use) so that they can read from different files. I do
    // think there should still be unique ID's for
    public Item(String type, int ID, String name, double volume, double weight, double damage,
    		double visDamage, int age, int created) {
    	this.type = type; this.ID= ID; this.name = name;
    	this.volume = volume; this.weight = weight; 
    	this.damage = damage; this.visDamage = visDamage;
    	this.age = age; this.created = created;
    	
    }

    // Subclasses will need to have their own (static) methods to
    // read the item ID from an XML file (or whatever we decide
    // to use) so that they can read from different files. I do
    // think there should still be unique ID's for

    public Item(String t, int id, String nm, double vol, double w, int day)
    {
        type = t;
        ID = id;
        name = nm;
        volume = vol;
        weight = w;
        damage = 0;
        visDamage = 0;
        age = 0;
        created = day;
    }

    //Setter Methods
    public void setVisDamage(double amt)
    {
        visDamage = amt;
    }

    public void setDamage(double amt)
    {
        damage = amt;
    }

    public void adjustAge(int time) {
        age += time;
    }

    // This is for an object to be damaged, whether by time or
    // age. It will obviously vary depending on the type of object
    public abstract void takeDamage(double amt);

    public abstract int getDefense();

    // This is for an object to be inspected by the player.
    // Different objects will have different inspection styles
    // and results, so it is abstract
    public abstract void inspect(int closeness);

    // Returns an array of strings for what this object/tool can be
    // used for. For tools it may be crafting uses or scavenging
    // uses, for food it may be recipes
    public abstract String[] getUses(String type);

    //Getter Methods
    public String getType()
    {
        return type;
    }
    
    /* I don't think we actually need this yet
    //Returns an abbreviated (4char long) version of type
    //For display in InfoPanel & inventory menu
    public String getTypeAbrv() {
    	switch(type) {
    	case "Clothing":return "clth";
    	case "Tool":	return "tool";
    	case "Food":	return "food";
    	default:		return "ERR ";
    	}
    }//*/

    public int getID()
    {
        return ID;
    }

    public String getName()
    {
        return name;
    }

    public double getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public double getDamage()
    {
        return damage;
    }

    public double getVisDamage()
    {
        return visDamage;
    }

    public int getAge()
    {
        return age;
    }

    public int getCreated()
    {
        return created;
    }

    @Override
    public String toString()
    {
        return type + " " + ID + ": " + name + "\n\tVolume: " + volume + ", Weight: " + weight +
               "\n\tDamage: " + damage + ", Visaible Damage: " +visDamage + "\n\tAge: "+ age + ", Created: " + created;
    }
}
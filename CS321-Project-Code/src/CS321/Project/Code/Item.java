package CS321.Project.Code;

public abstract class Item {
    protected int type; // The type of object (food, tool, etc)
    protected int ID; // The ID of the type of object
    protected String name; // The simple name of the object
    protected double volume;// How big the item is
    protected double weight; // How much it weighs
    protected double damage; // How damaged it is
    protected double visDamage; // How visible damaged it is
    protected double wetness; // How wet it is
    protected int age; // How long the object has existed
    protected int created; // When the object was created

    // Subclasses will need to have their own (static) methods to
    // read the item ID from an XML file (or whatever we decide
    // to use) so that they can read from different files. I do
    // think there should still be unique ID's for

    public void adjustAge(int time) {
        age += time;
    }

    // This is for an object to be damaged, whether by time or
    // age. It will obviously vary depending on the type of object
    public abstract void takeDamage(int amt, int type);

    public abstract int getDefense();

    // This is for an object to be inspected by the player.
    // Different objects will have different inspection styles
    // and results, so it is abstract
    public abstract void inspect(int closeness);

    // Returns an array of strings for what this object/tool can be
    // used for. For tools it may be crafting uses or scavenging
    // uses, for food it may be recipes
    public abstract String[] getUses();

    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }
}

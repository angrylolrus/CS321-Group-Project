package CS321.Project.Code;

// why
abstract class Item {
    int ID;
    String name;
    int maxDurability;
    double weight;
    int durability;
    int durabilityRevealed;
    // lastDamaged; I don't know the type yet!!!
    // int size; implement later?

    abstract void takeDamage();

    abstract void getUses();

    abstract double inspect();

    abstract void repair();
}

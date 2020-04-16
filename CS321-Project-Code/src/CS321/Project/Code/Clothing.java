package CS321.Project.Code;

public class Clothing extends Item 
{
    public Clothing(String type, int id, String name, double volume, double weight,
            double damage, double visDamage, int age, int created) 
    {
        super("Clothing", id, name, volume, weight, damage, visDamage, age, created);
    }

    @Override
    public void takeDamage(int amt)
    {
        super.damage = amt;
    }

    @Override
    public int getDefense()
    {
        return 0;
    }

    @Override
    public void inspect(int closeness)
    {
        
    }

    @Override
    public String[] getUses()
    {
        String[] s = new String[5];
        return s;
    }
}
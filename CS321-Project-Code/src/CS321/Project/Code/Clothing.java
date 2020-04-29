package CS321.Project.Code;

public class Clothing extends Item {
	int defense;

	public Clothing(String type, int id, String name, double volume, double weight, double damage, double visDamage,
			int age, int created)// , def)
	{
		super("Clothing", id, name, volume, weight, damage, visDamage, age, created);
		// this.setDefense(def)
		defense = 0;
	}

	@Override
	public void takeDamage(double amt) {
		setDamage(getDamage() + amt);
	}

	public int getDefense() {
		return defense;
	}

	/**
	 * @param defense
	 *            the defense to set
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}

	@Override
	public void inspect(double closeness) {
		
	}

	@Override
	public String[] getUses() {
		String[] s = new String[5];
		return s;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tDefense: " + defense;
	}
}

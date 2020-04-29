package CS321.Project.Code;

public class Food extends Item {
	private double wetness;
	public Food(String type, int ID, String name, double volume, double weight, double damage,
    		double visDamage, int age, int created, double wetness) {
		super(type,ID,name,volume,weight,damage,visDamage,age,created);
		this.setWetness(wetness);
	}
	@Override
	public void takeDamage(double amt) {
		// TODO Auto-generated method stub
		setDamage(getDamage() + amt);

	}

	@Override
	public void inspect(double closeness) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getUses() {
		// TODO Auto-generated method stub
		switch(this.name) {
			case "milk":
				return new String[] {"milk, oatmeal"};
			case "oats":
				return new String[] {"oatmeal"};
			case "water":
				return new String[] {"water, oatmeal, soup"};
			case "berries":
				return new String[] {"berries"};
			case "carrot":
				return new String[] {"carrot"};
			case "chicken":
				return new String[] {"chicken, soup"};
			case "fish":
				return new String[] {"fish"};
			case "bread":
				return new String[] {"bread"};
			default:
				return new String[] {this.name};
		}
	}
	public double getWetness() {
		return wetness;
	}
	public void setWetness(double wetness) {
		this.wetness = wetness;
	}

	@Override
    public String toString()
    {
        return super.toString() + "\n\tWetness: " + wetness;
    }
}

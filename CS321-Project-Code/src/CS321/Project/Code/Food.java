package CS321.Project.Code;

public class Food extends Item {
	private double wetness;
	public Food(String type, int ID, String name, double volume, double weight, double damage,
    		double visDamage, int age, int created, double wetness) {
		super(type,ID,name,volume,weight,damage,visDamage,age,created);
		this.setWetness(wetness);
	}
	@Override
	public void takeDamage(int amt, int type) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void inspect(int closeness) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getUses() {
		// TODO Auto-generated method stub
		return null;
	}
	public double getWetness() {
		return wetness;
	}
	public void setWetness(double wetness) {
		this.wetness = wetness;
	}

}

package CS321.Project.Code;
import java.util.Arrays;

public class TestClass {
	private static int id = 1;
	private static double volume = 2.0;
	private static double weight = 3.0;
	private static double damage = 4.0;
	private static double visDamage = 5.0;
	private static int age = 6;
	private static int day = 7;
	private static double wetness = 8.0;
	private static String clothingToString = "Clothing 1: Shoes\n\tVolume: 2.0, Weight: 3.0\n\tDamage: 4.0, Visible Damage: 5.0\n\tAge: 6, Created: 7\n\tDefense: 0";
	private static String foodToString = "Food 1: Bread\n\tVolume: 2.0, Weight: 3.0\n\tDamage: 4.0, Visible Damage: 5.0\n\tAge: 6, Created: 7\n\tWetness: 8.0";

	public static void main(String[] args) {
		System.out.println("Our first test works!");

		try {
			//Clothing class test
			clothingTest();
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			//Tool class test
			toolTest();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			//Food Class test
			foodTest();
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			//Player class test
			playerTest();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			//Inventory class test
			inventoryTest();
		} catch (Exception e) {
			System.out.println(e);
		}

		try{
			//JSON File Worker test
			jsonTest();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public static void clothingTest()
	{
		System.out.println("Clothing Test Starting");

		//Testing the constructor and getter methods
		Clothing c = new Clothing("Clothing", id, "Shoes", volume, weight, damage, visDamage, age, day);
		if(c.getType().equals("Clothing") && c.getID() == 1 && c.getName().equals("Shoes") && 
		   c.getVolume() == 2.0 && c.getWeight() == 3.0 && c.getDamage() == 4.0 && 
		   c.getVisDamage() == 5.0 && c.getAge() == 6 && c.getCreated() == 7)
		{
			System.out.println("\tConstructor and Getter Methods Test: Successful");
		}
		else
		{
			System.out.println("\tConstructor Test: Failed");
		}

		//Testing toString method
		String s = c.toString();
		if(s.equals(clothingToString))
		{
			System.out.println("\ttoString Method Test: Successful");
		}
		else
		{
			System.out.println("\ttoString Method Test: Failed");
		}

		//Testing the setter methods
		c.takeDamage(4.0);
		c.setDefense(10);
		c.setVisDamage(10.0);
		c.adjustAge(1);
		if(c.getDamage() == 8.0 && c.getDefense() == 10 && c.getVisDamage() == 10.0 && c.getAge() == 7)
		{
			System.out.println("\tSetter Methods Test: Successful");
		}
		else
		{
			System.out.println("\tSetter Methods Test: Failed");
		}
		System.out.println("\tClothing Test Complete");
	}

	public static void toolTest()
	{
		System.out.println("Tool Test Starting");

		//Testing the constructor and getter methods
		Tool t = new Tool("Tool", id, "Axe", volume, weight, damage, visDamage, age, day);
		if(t.getType().equals("Tool") && t.getID() == 1 && t.getName().equals("Axe") && 
		   t.getVolume() == 2.0 && t.getWeight() == 3.0 && t.getDamage() == 4.0 && t.getVisDamage() == 5.0 && 
		   t.getAge() == 6 && t.getCreated() == 7 && Arrays.toString(t.getUses()).equals("[Equip, Drop, Use]"))
		{
			System.out.println("\tConstructor and Getter Methods Test: Successful");
		}
		else
		{
			System.out.println("\tConstructor Test: Failed");
		}

		//Testing the setter methods
		t.takeDamage(4.0);
		t.setVisDamage(10.0);
		if(t.getDamage() == 8.0 && t.getVisDamage() == 10.0)
		{
			System.out.println("\tSetter Methods Test: Successful");
		}
		else
		{
			System.out.println("\tSetter Methods Test: Failed");
		}
		System.out.println("\tTool Test Complete");
	}

	public static void foodTest()
	{
		System.out.println("Food Test Starting");

		//Testing the constructor and getter methods
		Food f = new Food("Food", id, "Bread", volume, weight, damage, visDamage, age, day, wetness);
		if(f.getType().equals("Food") && f.getID() == 1 && f.getName().equals("Bread") && 
		   f.getVolume() == 2.0 && f.getWeight() == 3.0 && f.getDamage() == 4.0 && f.getVisDamage() == 5.0 && 
		   f.getAge() == 6 && f.getCreated() == 7 && f.getWetness() == 8.0)
		{
			System.out.println("\tConstructor and Getter Methods Test: Successful");
		}
		else
		{
			System.out.println("\tConstructor Test: Failed");
		}

		//Testing toString method
		String s = f.toString();
		System.out.println("the food string:" + s);
		if(s.equals(foodToString))
		{
			System.out.println("\ttoString Method Test: Successful");
		}
		else
		{
			System.out.println("\ttoString Method Test: Failed");
		}

		//Testing the setter methods
		f.takeDamage(4.0);
		f.setVisDamage(10.0);
		f.setWetness(10.0);
		f.adjustAge(1);
		if(f.getDamage() == 8.0 && f.getVisDamage() == 10.0 && f.getWetness() == 10.0 && f.getAge() == 7)
		{
			System.out.println("\tSetter Methods Test: Successful");
		}
		else
		{
			System.out.println("\tSetter Methods Test: Failed");
		}
		System.out.println("\tFood Test Complete");
	}

	public static void playerTest()
	{
		System.out.println("Player Test Starting");
	}

	public static void inventoryTest()
	{
		System.out.println("Inventory Test Starting");
	}

	public static void jsonTest()
	{
		System.out.println("JSON Test Starting");

		boolean jTest = JsonFileWorker.init();
		if(jTest)
		{
			Clothing c = (Clothing) JsonFileWorker.getItem("Clothing", 0, true);
			//Shouldn't it be or? Changed from &&
			if(c == null || !(c instanceof Clothing))
			{
				System.out.println("JSON test failed!");
				return;
			}
		}
		else
		{
			System.out.println("JSON Init function failed!");
			return;
		}
		System.out.println("JSON test successful!");
	}
}

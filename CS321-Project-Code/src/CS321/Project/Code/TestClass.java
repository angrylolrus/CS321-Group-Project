package CS321.Project.Code;

public class TestClass {
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
	}

	public static void toolTest()
	{
		System.out.println("Tool Test Starting");
	}

	public static void foodTest()
	{
		System.out.println("Food Test Starting");
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
			if(c == null && !(c instanceof Clothing))
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

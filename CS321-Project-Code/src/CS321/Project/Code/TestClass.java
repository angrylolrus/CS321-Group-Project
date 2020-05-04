package CS321.Project.Code;
import java.awt.Font;
import java.util.Arrays;

import CS321.Project.Code.ActionPanel;
import CS321.Project.Code.Controller;
import CS321.Project.Code.GameMenu;
import CS321.Project.Code.MainMenu;
//import jdk.dynalink.linker.support.Guards;

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
	private static String toolToString = "Tool 1: Axe\n\tVolume: 2.0, Weight: 3.0\n\tDamage: 4.0, Visible Damage: 5.0\n\tAge: 6, Created: 7";
	private static String itemToString = "Tool 3: Stick\n\tVolume: 1.0, Weight: 1.0\n\tDamage: 5.0, Visible Damage: 2.0\n\tAge: 6, Created: 7";


   public static void main(String[] args) {
     
      JsonFileWorker.init();
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
      	//Location class test
         locationTest();
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
      	//UI Element class test
         uiElementTest();
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
   
      try{
      	//InfoPanel test
         infoTest();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   
      try{
      	//GameMenu test
         gameMenuTest();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   
      try{
      	//ActionPanel test
         actionTest();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   
      try{
      	//World test
         worldTest();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   
      try{
      	//MainMenu test
         mainMenuTest();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
      try{
      	//Item test
         itemTest();
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
      System.out.println("Clothing Test Complete");
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
      
      //Testing toString method
      String s = t.toString();
      if(s.equals(toolToString))
      {
         System.out.println("\ttoString Method Test: Successful");
      }
      else
      {
         System.out.println("\ttoString Method Test: Failed");
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
      System.out.println("Tool Test Complete");
   }

  


   public static void inventoryTest()
   {
      System.out.println("Inventory Test Starting");
      
      //Make inventory and other inventory to transfer items between
      Inventory I = new Inventory(25, 50);
      Inventory other = new Inventory(50, 50);      
      
      //Check if constructor arugments worked
      if(I.maxVolume == 25.0 && I.maxWeight== 50.0){
         System.out.println("\tConstructor and setting weights/volume test: Successful");
      }
      else{
         System.out.println("\tConstructor and Getter Methods Test: Failed");
      }
      
      //Make items to add to inventories and transfer between them
      Food bread = new Food("Food", id, "Bread", volume, weight, damage, visDamage, age, day, wetness);
      Tool axe = new Tool("Tool", 2, "Axe", 2.0, 5.0, 5.0, 0.0, age, day);
      other.addItem(bread);
      other.addItem(axe);
      
      //Check if bread has been added to inventory
      if(other.contains(bread) == true && other.contains(axe)){
         System.out.println("\tContains method test: Successful");
      }
      else{
         System.out.println("\tContains method test: Failed");
      }
   
      //Check if volume of bread + axe is reflected in inventory: bread v: 2.0, w: 3.0, axe v: 2.0, w: 5.0
      if(other.getCurrentVolume() == 4.0 && other.getCurrentWeight() == 8.0){
         System.out.println("\tVolume/Weight Getter Methods Test: Successful");
      }
      else{
         System.out.println("\tVolume/Weight Getter Methods Test: Failed");
      }
      
      other.removeItem(axe);
      
      //Check if axe has been removed from inventory
      if(other.contains(axe) == false){
         System.out.println("\tRemove method test: Successful");
      }
      else{
         System.out.println("\tRemove method test: Failed");
      }
      
      I.transfer(other, bread);
      
      //check if transferring between inventories works
      if(I.contains(bread) == true && other.contains(bread) == false){
         System.out.println("\tTransfer item test between inventories: Successful");
      }
      else{
         System.out.println("\tTransfer item test between inventories: Failed");
      }
      
      other.adjustMaxVolume(60); //beginning max volume is 50.0
      other.adjustMaxWeight(60); //beginning max weight is 50.0
      
      //Check if we can adjust the max weights and volumes of the inventory limits
      if(other.maxVolume == 110.0 && other.maxWeight == 110.0){
         System.out.println("\tAdjusting Max Volume/Weight test for inventory: Successful");
      }
      else{
         System.out.println("\tAdjusting Max Volume/Weight test for inventory: Failed");
      }
      
      System.out.println("Inventory Test Complete");
      
      /************************************
      Other methods were tested manually:
      * age, scroll, resetHighlight, 
      highlightedIndex, highlightedItem, 
      selectItemAt and drawSelf
      *************************************/
   }

   public static void uiElementTest() {
	   System.out.println("UI Element Test Starting"); 
	   UIElement l = new UIElement.Label("TestLabel", true, 10, 10);
	   ((UIElement.Label)l).rewrite("New name");
	   if(((UIElement.Label)l).contents.equals("New name"))
		   System.out.println("\trewrite successful!");
	   else
		   System.out.println("\trewrite failed!");
	   ((UIElement.Label)l).rewrite("New name");
	   ((UIElement.Label)l).setFont("Arial", Font.PLAIN, 16);
	   if(((UIElement.Label)l).font.getFontName().equals("Arial"))
		   System.out.println("\tsetFont successful!");
	   else
		   System.out.println("\tsetFont failed!");
	   UIElement m = new UIElement.MouseCoords();
	   if(m instanceof UIElement.MouseCoords)
		   System.out.println("\tMouse coords constructor successful!");
	   else
		   System.out.println("\tMouse coords constructor failed!");
	   UIElement b = new UIElement.Button(10,10,10,10, "TestButton");
	   if(b instanceof UIElement.Button && ((UIElement.Button) b).isClickable())
		   System.out.println("\tButton constructor and getter successful!");
	   else
		   System.out.println("\tButton constructor and getter failed!");
	   ((UIElement.Button)b).updateLabel("new label");
	   if( ((UIElement.Button)b).label.contents.equals("new label") 
			   && !((UIElement.Button)b).isClickable() )
		   System.out.println("\tButton update successful!");
	   else
		   System.out.println("\tButton update failed!");
	   System.out.println("UI Element Test Complete");
   }
   public static void locationTest() {
	   
	   System.out.println("Location Test Starting");
	   Location l = new Location(50,50);
	   l.age(5);
	   Location l2 = new Location (53,50);
	   l.addLink(l2);
	   if(l.adjacentTo(l2) == true)
		   System.out.println("\tAdjacent to test: Successful");
	   else
		   System.out.println("\tAdjacent to test: Failed");
	   if(l.distanceTo(l2) == 3.0)
		   System.out.println("\tDistance to test: Successful");
	   else
		   System.out.println("\tDistance to test: Failed");
	   if(l.getInventory() != null)
		   System.out.println("\tgetInventory test: Successful");
	   else
		   System.out.println("\tgetInventory test: Failed");
	   if(l.toString().equals("[Location: X: 50.0 Y: 50.0]"))
		   System.out.println("\ttoString test: Successful");
	   else
		   System.out.println("\ttoString test: Failed");
	   System.out.println("Location Test Complete");
   }
   public static void playerTest() {
	   System.out.println("Player Test Starting");
	   
	   Controller c = new Controller();
	   GameMenu g = new GameMenu(c);
	   Player p = new Player(g);
	   if(p.getParent() != null && p.getEnergy() == 100 && p.getSatiety() == 50 && p.getLastUpdated() > 0 
			   && p.getHealth() == 25 && p.getDefense() == 0 && p.getInventory() != null)
		   System.out.println("\tGetters Successful");
	   else
		   System.out.println("\tGetters Failed");
	   p.change_Defense(5);
	   p.takeDamage(10);
	   p.heal_Player(3);
	   
	   if(p.getDefense() == 5 && p.getHealth() == 23)
		   System.out.println("\tSetters Successful");
	   else
		   System.out.println("\tSetters Failed");
	   boolean u = p.useItem(new Food("Food", 0, "Bread", 1.0, 2.0, 0.0, 0.0, 0, 0, 0.0));
	   if(u && p.getSatiety() == 80) //use item should have added 30 to satiety
		   System.out.println("\tuseItem Successful");
	   else
		   System.out.println("\tuseItem Failed");
	   p.age(500);
	   if(p.getLastUpdated() == 500 && p.getSatiety() == 76 && p.getEnergy() == 104)
		   System.out.println("\tAge test Successful");
	   else
	   	   System.out.println("\tAge test Failed");
	   System.out.println("Player Test Complete");
	   
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
		System.out.println("Food Test Complete");
	}


	public static void jsonTest()
	{
		System.out.println("JSON Test Starting");

		boolean jTest = JsonFileWorker.init();
		if(jTest)
		{
			System.out.println("\tJSON Init function successful!");
			Clothing c = (Clothing) JsonFileWorker.getItem("Clothing", 0, true);
			Item randItem = JsonFileWorker.getRandomNewItem();
			if(randItem != null && c != null && c instanceof Clothing)
				System.out.println("\tJSON Get item methods successful!");
			else
				System.out.println("\tJSON Get item methods failed!");
		}
		else
		{
			System.out.println("\tJSON Init function failed!");
			return;
		}
		System.out.println("JSON Test Complete");
	}

   public static void infoTest()
   {
      System.out.println("InfoPanel Test Starting");
   
      Controller c = new Controller();
      GameMenu g = new GameMenu(c);
      InfoPanel ip = new InfoPanel(g);
   
      if(ip.parent != null)
      {
         System.out.println("\tConstructor test: Success");
      }
      else
      {
         System.out.println("\tConstructor test: Failed");
         return;
      }
   
      System.out.println("InfoPanel Test Complete");
   }

   public static void gameMenuTest()
   {
      System.out.println("GameMenu Test Starting");
   
      Controller c = new Controller();
      GameMenu g = new GameMenu(c);
   
      //Constructor test
      if(g.time == 480 && g.controller != null && g.player != null 
        && g.infoPanel != null && g.actionPanel != null 
        && g.worldSize == 1200 && g.world != null && g.zoomLevel == 2 
        && g.mapCenter != null && g.playerLocation != null && g.elements != null)
      {
         System.out.println("\tContructor test: Successful");
      }
      else
      {
         System.out.println("\tConstructor test: Failed");
         return;
      }
   
      //time test
      g.advanceTime(20);
      if(g.time == 500 && g.getTime() == 500 && g.timeSince(480) == 20)
      {
         System.out.println("\tTime methods test: Successful");
      }
      else
      {
         System.out.println("\tTime methods test: Failed");
         return;
      }
   
      System.out.println("GameMenu Test Complete");
   }

   public static void actionTest()
   {
      System.out.println("ActionPanel Test Starting");
   
      Controller c = new Controller();
      GameMenu g = new GameMenu(c);
      ActionPanel ap = new ActionPanel(g);
   
      //Constructor test
      if(ap.parent != null && ap.elements != null && ap.equip != null 
         && ap.use != null && ap.inspectShort != null && ap.inspectMed != null
         && ap.inspectLong != null && ap.travel != null && ap.transfer != null
         && ap.wait1 != null && ap.wait4 != null && ap.wait8 != null 
         && ap.search != null)
      {
         System.out.println("\tConstructor test: Successful");
      }
      else
      {
         System.out.println("\tConstructor test: Failed");
      }
   
      System.out.println("ActionPanel Test Complete");
   }

   public static void worldTest()
   {
      System.out.println("World Test Starting");
   
      Controller c = new Controller();
      GameMenu g = new GameMenu(c);
      World w = new World(g, 40, 40);
   
      if(w.parent != null && w.locs != null && w.xSize == 40 && w.ySize == 40 && w.locuses != null)
      {
         System.out.println("\tConstructor test: Successful");
      }
      else
      {
         System.out.println("\tConstructor test: Failed");
      }
   
      System.out.println("World Test Complete");
   }

   public static void mainMenuTest()
   {
      System.out.println("MainMenu Test Starting");
   
      Controller c = new Controller();
      MainMenu m = new MainMenu(c);
   
      if(m.controller != null && m.elements != null && m.elements.size() == 10)
      {
         System.out.println("\tConstructor test: Successful");
      }
      else
      {
         System.out.println("\tConstructor test: Failed");
      }
   
      System.out.println("MainMenu Test Complete");
   }
   
   public static void itemTest()
   {
      System.out.println("Item Test Starting");
      
      Item thing = new Tool("Tool", 3, "Stick", 1.0, 1.0, 0, 0, age, day); //created at 5:18pm;   
   
      //Tests for get volume/weight methods
      if(thing.getVolume() == 1.0 && thing.getWeight() == 1.0){
         System.out.println("\tVolume/Weight Getter Methods Test: Successful");
      }
      else{
         System.out.println("\tVolume/Weight Getter Methods Test: Failed");
      }
      
      //Tests for get and set damage/visdamage methods
      if(thing.getDamage() == 0 && thing.getVisDamage() == 0){
         System.out.println("\tDamage and Visible Damage Getter Methods Test: Successful");
      }
      else{
         System.out.println("\tDamage and Visible Damage Getter Methods Test: Failed");
      }
   
      
      thing.setVisDamage(2);
      thing.setDamage(5);
      
     
      if(thing.getDamage() == 5&& thing.getVisDamage() == 2){
         System.out.println("\tDamage and Visible Damage Getter Methods Test: Successful");
      }
      else{
         System.out.println("\tDamage and Visible Damage Getter Methods Test: Failed");
      }
      
      //Test for get type method     
      if(thing.getType().equals("Tool")){
         System.out.println("\tType Getter Method Test: Successful");
      }
      else{
         System.out.println("\tType Getter Method Test: Failed");
      } 
     //Test for get name method
      if(thing.getName().equals("Stick")){
         System.out.println("\tName Getter Method Test: Successful");
      }
      else{
         System.out.println("\tName Getter Method Test: Failed");
      } 
      
      //testing Age/created getter method
      if(thing.getAge() == 6 && thing.getCreated() == 7){
         System.out.println("\tAge Getter Method Test: Successful");
      }
      else{
         System.out.println("\tAge Getter Method Test: Failed");
      } 
      
      
      //Testing toString method
      String s = thing.toString();
      if(s.equals(itemToString))
      {
         System.out.println("\ttoString Method Test: Successful");
      }
      else
      {
         System.out.println("\ttoString Method Test: Failed");
      }
      System.out.println("Item Test Complete");
   }
   
}

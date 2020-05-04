package CS321.Project.Code;

import javax.xml.namespace.QName;

import CS321.Project.Code.ActionPanel;
import CS321.Project.Code.GameMenu;
import jdk.dynalink.linker.support.Guards;

public class TestClass {
   private static int id = 1;
   private static double volume = 2.0;
   private static double weight = 3.0;
   private static double damage = 4.0;
   private static double visDamage = 5.0;
   private static int age = 6;
   private static int day = 7;
   private static double wetness = 8.0;
   private static String clothingToString = "Clothing 1: Shoes\n\tVolume: 2.0, Weight: 3.0\n\tDamage: 4.0, Visable Damage: 5.0\n\tAge: 6, Created: 7\n\tDefense: 0";
   private static String foodToString = "Food 1: Bread\n\tVolume: 2.0, Weight: 3.0\n\tDamage: 4.0, Visable Damage: 5.0\n\tAge: 6, Created: 7\n\tWetness: 8.0";

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
   
   	/*//Testing the constructor and getter methods
   	Tool f = new Tool("Tool", id, "Axe", volume, weight, damage, visDamage, age, day, wetness);
   	if(f.getType().equals("Tool") && f.getID() == 1 && f.getName().equals("Axe") && 
   	   f.getVolume() == 2.0 && f.getWeight() == 3.0 && f.getDamage() == 4.0 && f.getVisDamage() == 5.0 && 
   	   f.getAge() == 6 && f.getCreated() == 7 && f.getWetness() == 8.0)
   	{
   		System.out.println("\tConstructor and Getter Methods Test: Successful");
   	}
   	else
   	{
   		System.out.println("\tConstructor Test: Failed");
   	}
   
   	//Testing the setter methods
   	f.takeDamage(4.0);
   	f.setVisDamage(10.0);
   	if(f.getDamage() == 8.0 && f.getVisDamage() == 10.0)
   	{
   		System.out.println("\tSetter Methods Test: Successful");
   	}
   	else
   	{
   		System.out.println("\tSetter Methods Test: Failed");
   	}
   	System.out.println("\tTool Test Complete");*/
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
      System.out.println("\tFood Test Complete");
   }

   public static void playerTest()
   {
      System.out.println("Player Test Starting");
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
      
      
      /************************************
      Other methods were tested manually:
      * age, scroll, resetHighlight, 
      highlightedIndex, highlightedItem, 
      selectItemAt and drawSelf
      *************************************/
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
      System.out.println("\tJSON test successful!");
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

      System.out.println("\tInfoPanel test successful! ");
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

      System.out.println("\tGameMenu test successful!");
   }

   public static void actionTest()
   {
      System.out.println("GameMenu Test Starting");

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

      System.out.println("\tActionPanel test successful!");

   }

}

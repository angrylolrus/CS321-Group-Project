package CS321.Project.Code;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.FileSystems;
import java.lang.Math;

// class to read a json file and grab the base attributes of an Item
public class JsonFileWorker {
    static JSONObject readerObj;
    static JSONObject readerObj2;
    //static String config = "\\CS321\\Project\\config\\Item_config.json";
    static String config = "../src/CS321/Project/config/Item_config.json";
    static String lootItemsPath = "../src/CS321/Project/config/Loot_items.json";
    static ArrayList<Food> foodList = new ArrayList<Food>();
    static ArrayList<Tool> toolList = new ArrayList<Tool>();
    static ArrayList<Clothing> clothingList = new ArrayList<Clothing>();
    

    // needs to be run before any json reading or writing will happen
    public static boolean init() {
        try {
            readerObj = (JSONObject) new JSONParser().parse(new FileReader(config));
            if (readerObj == null) {
                return false;
            }
            readerObj2 = (JSONObject) new JSONParser().parse(new FileReader(lootItemsPath));
            if (readerObj2 == null) {
                return false;
            }
            initItemsArray("Food");
            initItemsArray("Clothing");
            initItemsArray("Tool");
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    public static void initItemsArray(String type) {
       
        int index = 0;
        JSONArray jArray = (JSONArray) readerObj2.get(type);
        JSONObject jObj;
        while(index < jArray.size()) {
            try {
            	jObj = (JSONObject) jArray.get(index);
                String name = (String) jObj.get("name");
                double volume = (double) jObj.get("volume");
                double weight = (double) jObj.get("weight");
                           
                
                if(type == "Clothing")
                	clothingList.add(new Clothing(type,  0,  name,  volume,  weight,  0,  0.0, 0, 0));
                else if(type == "Food") {
                	foodList.add( new Food(type, 0, name, volume, weight, 0, 0.0, 0, 0, 0.0));
                }
            	else {
            		toolList.add(new Tool ( type,  0,  name, volume, weight, 0.0, 0.0, 0, 0));
            	}
                
            } catch (Exception e) {
                System.out.println("Something went wrong with reading JSON file!\n" + e);
                return;
            }
            index++;
            
        }

    }
    
    public static Item getRandomNewItem() {
    	int type = (int) (Controller.random.nextDouble() * 3);
    	int index = 0;
    	if(type == 0) {
    		index = (int)(Controller.random.nextDouble() * (foodList.size()));
    		return foodList.get(index);
    	}
    	else if(type == 1) {
    		index = (int)(Controller.random.nextDouble() * (clothingList.size()));
    		return clothingList.get(index);
    	}
    	else {
    		index = (int)(Controller.random.nextDouble() * (toolList.size()));
    		return toolList.get(index);
    	}
    }
    // returns either a Clothing, Tool or Food object by whatever type is passed in
    public static <T> T getItem(String type, int id, boolean newItem) {
        Clothing c;
        Food f;
        // Tool t;
        String name;
        double volume, weight, wetness, damage, visDamage;
        wetness = 0;
        int age, created;

        JSONArray jArray = (JSONArray) readerObj.get(type);
        JSONObject jObject = (JSONObject) jArray.get(id);
        try {
            name = (String) jObject.get("name");
            volume = (double) jObject.get("volume");
            weight = (double) jObject.get("weight");
            damage = (double) jObject.get("damage");
            visDamage = (double) jObject.get("visDamage");
            age = Math.toIntExact((long) jObject.get("age"));
            if (!newItem) {
                created = (int) jObject.get("created");
            } else {
                created = 0; // need to create a day/hour variable
            }
            if (type.equals("Food"))
                wetness = (double) jObject.get("wetness");
        } catch (Exception e) {
            System.out.println("Something went wrong with reading JSON file!\n" + e);
            return null;
        }

        if (type.equals("Clothing")) {
            c = new Clothing(type, id, name, volume, weight, damage, visDamage, age, created); // need
                                                                                               // to
                                                                                               // add
                                                                                               // day
                                                                                               // variable
            return (T) c;
        } else if (type.equals("Food")) {
            f = new Food(type, id, name, volume, weight, damage, visDamage, age, created, wetness); // need
                                                                                                    // to
                                                                                                    // add
                                                                                                    // day
                                                                                                    // variable
            return (T) f;
        } else {
            // t = new Tool(id, name, volume, weight, 0) //need to add day variable
            // return (T) t;
        }
        return null;
    }

    public static void saveState(Clothing[] c, Player p) {
        System.out.println("Saving state");
    }
}

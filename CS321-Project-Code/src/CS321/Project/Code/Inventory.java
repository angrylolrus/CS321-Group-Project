package CS321.Project.Code;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> contents;
	double maxVolume, maxWeight;
	
	public Inventory(double v, double w) {
		maxVolume = (v == -1 ? Integer.MAX_VALUE : v);
		maxWeight = (w == -1 ? Integer.MAX_VALUE : w);
		
	}
	
	public void transfer(Inventory other, Item i) {
		if(getCurrentVolume() + i.getVolume() < maxVolume)
			if(getCurrentWeight() + i.getWeight() < maxWeight) {
				other.removeItem(i);
				contents.add(i);
			}//End nested if
	}
	
	// N.B. If there isn't space/weight, the item simply will not
	// be added. Be careful with this to make sure items don't get
	// deleted
	public void addItem(Item i) {
		if(getCurrentVolume() + i.getVolume() < maxVolume)
			if(getCurrentWeight() + i.getWeight() < maxWeight) 
				contents.add(i);
	}
	
	//Removes the given item from the inventory if it contains it
	public void removeItem(Item i) {
		if(contents.contains(i))
			contents.remove(i);
	}
	
	public boolean contains(Item i) {
		return contents.contains(i);
	}
	
	public double getCurrentVolume() {
		double sum = 0;
		for(Item i : contents)
			sum += i.getVolume();
		return sum;
	}
	
	public double getCurrentWeight() {
		double sum = 0;
		for(Item i : contents)
			sum += i.getWeight();
		return sum;
	}
	
	public void adjustMaxVolume(double v) throws IllegalArgumentException{
		if(maxVolume + v < 0)
			throw new IllegalArgumentException("Container max volume cannot be negative");
		maxVolume += v;
	}
	
	public void adjustMaxWeight(double w) throws IllegalArgumentException{
		if(maxWeight + w < 0)
			throw new IllegalArgumentException("Inventory max weight cannot be negative");
		maxWeight += w;
	}
	
	
}

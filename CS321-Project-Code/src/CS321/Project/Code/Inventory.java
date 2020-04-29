package CS321.Project.Code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import CS321.Project.Code.UIElement.Label;

public class Inventory {
	private ArrayList<Item> contents;
	double maxVolume, maxWeight;
	
	
	//A value for how much the list has been scrolled
	//Relates to display and selection
	double scroll;
	int selectedItem;
	
	public Inventory(double v, double w) {
		maxVolume = (v == -1 ? Integer.MAX_VALUE : v);
		maxWeight = (w == -1 ? Integer.MAX_VALUE : w);
		scroll = 0;
		contents = new ArrayList<Item>();
		selectedItem = -1;
	}
	
	public void age(long time) {
		for(Item i: contents) {
			i.age(time);
		}
	}
	
	public void transfer(Inventory other, Item i) {
		if(getCurrentVolume() + i.getVolume() < maxVolume)
			if(getCurrentWeight() + i.getWeight() < maxWeight) {
				other.removeItem(i);
				contents.add(i);
				other.resetHighlight();
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
	
	//A positive scroll value indicates upwards motion
	public void scroll(int dir) {
		scroll += 0.2 * dir;
		//Ensures the player doesn't scroll the top below the header
		scroll = Math.max(scroll, 0);
		//Ensures the player doesn't scroll the last entry above the header
		scroll = Math.min(scroll, contents.size() - 1);
	}
	
	
	public void resetHighlight() {
		selectedItem = -1;
	}
	
	public int highlightedIndex() {
		return selectedItem;
	}
	
	public Item highlightedItem() {
		return contents.get(selectedItem);
	}
	
	//How tall an inventory entry is. Used for drawing and retrieving
	private static final int ENTRY_HEIGHT = 20;
	private static final int START_HEIGHT = 60;
	
	public Item selectItemAt(int x, int y) {
		int index = (int)Math.floor((y + (scroll*ENTRY_HEIGHT) - START_HEIGHT)/ENTRY_HEIGHT);
		if(selectedItem == index || index >= contents.size() || index < 0) {
			selectedItem = -1;
			return null;
		}
		selectedItem = index;
		return contents.get(selectedItem);
	}
	
	public void drawSelf(Graphics g) {
		Font font = new Font("Courier New", Font.PLAIN, 14);
		Label name, weight, vol;
		for(int a = 0; a < contents.size(); a++) {
			Item curItem = contents.get(a);
			
			name = new Label(curItem.getName(), true, 35, (int)(START_HEIGHT + (ENTRY_HEIGHT*a) - (ENTRY_HEIGHT*scroll) + ENTRY_HEIGHT/3));
			name.setFont(font);
			
			weight = new Label((curItem.getWeight()+"00000").substring(0, 4), true, 100, (int)(START_HEIGHT + (ENTRY_HEIGHT*a) - (ENTRY_HEIGHT*scroll) + ENTRY_HEIGHT/3));
			weight.setFont(font);
		
			vol = new Label((curItem.getVolume()+"00000").substring(0, 4), true, 150, (int)(START_HEIGHT + (ENTRY_HEIGHT*a) - (ENTRY_HEIGHT*scroll)+ ENTRY_HEIGHT/3));
			vol.setFont(font);
			
			if(a == selectedItem) {
				name.setColor(Color.black);
				weight.setColor(Color.black);
				vol.setColor(Color.black);
				g.setColor(Color.white);
				g.fillRect(0, START_HEIGHT + (ENTRY_HEIGHT*a) - (int)(ENTRY_HEIGHT*scroll), 200, ENTRY_HEIGHT);
			}
			
			name.update(g);
			weight.update(g);
			vol.update(g);
		}
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 200, START_HEIGHT);
		g.setColor(Color.white);
		Label headers;
		
		//Headers for total weight and volume
		headers = new Label("Totals", true, 35, 25+ENTRY_HEIGHT);
		headers.setFont(font);
		headers.update(g);
		
		headers = new Label((getCurrentWeight()+"00000").substring(0, 4), true, 100, 25+ENTRY_HEIGHT);
		headers.setFont(font);
		headers.update(g);
		
		headers = new Label((getCurrentVolume()+"00000").substring(0, 4), true, 150, 25+ENTRY_HEIGHT);
		headers.setFont(font);
		headers.update(g);
		
		
		font = new Font("Courier New", Font.PLAIN, 18);
		//Headers for titles (done second because we change the font)
		headers = new Label("Name", true, 35, 25);
		headers.setFont(font);
		headers.update(g);
		
		headers = new Label(" Wt.", true, 100, 25);
		headers.setFont(font);
		headers.update(g);
		
		headers = new Label("Vol", true, 150, 25);
		headers.setFont(font);
		headers.update(g);
		
		//Bounding box for header
		g.drawLine(0, START_HEIGHT, 200, START_HEIGHT);
		
	}
	
}

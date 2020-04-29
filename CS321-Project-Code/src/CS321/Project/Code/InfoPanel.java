package CS321.Project.Code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import CS321.Project.Code.UIElement.Label;

//This is just a static class to seperate out the code for displaying information
// 	about an item that is being focused on
public class InfoPanel {
	
	GameMenu parent;
	
	public InfoPanel(GameMenu g) {
		parent = g;
	}
	
	public void showInfo(Object o, Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 200, 200);
		
		if(o instanceof Location) {
			displayLocation((Location) o, g);
		}
		
		if(o instanceof Item) {
			displayItem((Item) o, g);
		}
		
		displayTime(g);
	}
	
	public void displayTime(Graphics g) {
		String t = "TIME:";
		long rawHr = parent.getTime() % (24*60)/60, rawMin = parent.getTime() % 60;
		String hr = (rawHr % 12 < 10 ? " " : "") + (rawHr % 12);
		if(rawHr % 12 == 0)
			hr = "12";
		String min = (rawMin < 10 ? "0" : "") + rawMin;
		t += hr + ":" + min + " " + (rawHr < 12 ? "am" : "pm");
		
		
		g.setColor(Color.white);
		g.drawLine(0, 160, 200, 160);
		Font font = new Font("Courier New", Font.PLAIN, 18);
		Label curLabel = new Label(t, true, 100, 180);
		curLabel.setFont(font);
		curLabel.update(g);
	}
	
	public void displayItem(Item item, Graphics g) {
		g.setColor(Color.white);
		Font font = new Font("Courier New", Font.BOLD, 20);
		Label curLabel = new Label(item.getName(), true, 100, 14);
		curLabel.setFont(font);
		curLabel.update(g);
		
		font = new Font("Courier New", Font.PLAIN, 15);
		
		//type
		curLabel = new Label("Type: " + item.getType(), true, 100, 30);
		curLabel.setFont(font);
		curLabel.update(g);
		
		//Weight
		curLabel = new Label(" Wt: " + (item.getWeight()+"000").substring(0, 4), true, 55, 45);
		curLabel.setFont(font);
		curLabel.update(g);
		
		//Volume
		curLabel = new Label("Vol: " + (item.getVolume()+"000").substring(0, 4), true, 145, 45);
		curLabel.setFont(font);
		curLabel.update(g);
		
		//Visible damage
		curLabel = new Label("Dmg: " + (item.getVisDamage()+"000").substring(0, 4), true, 55, 60);
		curLabel.setFont(font);
		curLabel.update(g);
		
		//Full damage (if in debug mode)
		if(parent.debug) {
			curLabel = new Label("F_D: " + (item.getDamage()+"000").substring(0, 4), true, 145, 60);
			curLabel.setFont(font);
			curLabel.setColor(Color.red);
			curLabel.update(g);
		}
		
	}
	
	public void displayLocation(Location loc, Graphics g) {
		g.setColor(Color.white);
		Font font = new Font("Courier New", Font.PLAIN, 20);
		//X coordinate
		Label curLabel = new Label("X:" + (int)loc.xPos, true, 50, 30);
		curLabel.setFont(font);
		curLabel.update(g);
		
		curLabel = new Label("Y:" + (int)loc.yPos, true, 150, 30);
		curLabel.setFont(font);
		curLabel.update(g);
		
		curLabel = new Label("Neighbors:" + loc.links.size(), true, 100, 60);
		curLabel.setFont(font);
		curLabel.update(g);
		
		String distString = "Distance:";
		if(loc == parent.playerLocation)
			distString += "0";
		else if(loc.adjacentTo(parent.playerLocation)) {
			//N.B. later this will be travel time but for now thats the same as distance
			double dist = parent.playerLocation.distanceTo(loc);
			distString += Math.round(dist*100)/100;
		}
		else{
			distString += "-";
		}
		curLabel = new Label(distString, true, 100, 90);
		curLabel.setFont(font);
		curLabel.update(g);
		
	}
}

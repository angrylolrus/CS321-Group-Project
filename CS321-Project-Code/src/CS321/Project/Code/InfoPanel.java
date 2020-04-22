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
		if(o instanceof Location) {
			displayLocation((Location) o, g);
		}
		
		if(o instanceof Item) {
			displayItem((Item) o, g);
		}
		
		if(o == null) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 200, 200);
		}
		
	}
	
	public void displayItem(Item item, Graphics g) {
		
	}
	
	public void displayLocation(Location loc, Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 200, 200);
		
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
		else if(loc.links.contains(parent.playerLocation)) {
			//N.B. later this will be travel time but for now thats the same as distance
			double dist = Math.sqrt(Math.pow(loc.xPos - parent.playerLocation.xPos, 2) + Math.pow(loc.yPos - parent.playerLocation.yPos, 2));
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

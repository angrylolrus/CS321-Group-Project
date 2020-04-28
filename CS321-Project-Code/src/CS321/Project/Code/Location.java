package CS321.Project.Code;

import java.awt.Graphics;
import java.util.ArrayList;
//import java.util.HashMap;

public class Location {
	
	double xPos, yPos;
	public ArrayList<Location> links;
	Inventory contents;

	public Location(double x, double y) {
		xPos = x;
		yPos = y;
		links = new ArrayList<Location>();
		contents = new Inventory(15, 15);
		contents.addItem(JsonFileWorker.getItem("Clothing", 0, true) );
		contents.addItem(JsonFileWorker.getItem("Clothing", 0, true) );
		contents.addItem(JsonFileWorker.getItem("Clothing", 0, true) );
		contents.addItem(JsonFileWorker.getItem("Clothing", 0, true) );
	}
	
	public Inventory getInventory() {
		return contents;
	}

	public String toString() {
		return "[Location: X: " + xPos + " Y: " + yPos+ "]";
	}
	
	public void addLink(Location l) {
		links.add(l);
		l.links.add(this);
	}

	public void generateRegion(ArrayList<Location> curList) {
		curList.add(this);
		for (Location l : links) {
			if (!curList.contains(l))
				l.generateRegion(curList);
		}
	}

	public void drawSelf(Graphics g) {
		g.fillOval((int) (xPos - 3), (int) (yPos - 3), 6, 6);
	}

	public void drawAllLinks(Graphics g) {
		for (Location l : links) {
			g.drawLine((int) xPos, (int) yPos, (int) l.xPos, (int) l.yPos);
		}
	}
	
	//For drawing a section of map. The x & y are the center coordinates, the margin is how wide the square is
	public void drawSelfRelative(double x, double y, double margin, Graphics g) {
		double stretch = 600.0/(margin*2.0);
		g.fillOval((int) (((xPos - 3) - (x - margin))*stretch), (int) (((yPos - 3) - (y - margin))*stretch), (int)(6*stretch), (int)(6*stretch));
	}
	
	public void drawAllLinksRelative(double x, double y, double margin, Graphics g) {
		double stretch = 600.0/(margin*2.0);
		for (Location l : links) {
			g.drawLine((int) ((xPos - (x - margin))*stretch), (int) ((yPos - (y - margin))*stretch), (int) ((l.xPos - (x - margin))*stretch), (int) ((l.yPos - (y - margin))*stretch));
		}
	}
}

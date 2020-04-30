package CS321.Project.Code;

import java.awt.Graphics;
import java.util.ArrayList;
//import java.util.HashMap;

public class Location {
	
	//Size of dots on map
	static final int VIS_SIZE = 8;
	
	double xPos, yPos;
	public ArrayList<Location> links;
	Inventory contents;

	public Location(double x, double y) {
		xPos = x;
		yPos = y;
		links = new ArrayList<Location>();
		contents = new Inventory(15, 15);
		for(int i = 0; i < 3; i++)
			contents.addItem(JsonFileWorker.getRandomNewItem());
	}
	
	public void age(long time) {
		contents.age(time);
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
	
	public boolean adjacentTo(Location l) {
		return links.contains(l);
	}
	
	public double distanceTo(Location l) {
		if(!this.adjacentTo(l))
			return -1;
		else
			return Math.sqrt(Math.pow(this.xPos - l.xPos, 2) + Math.pow(this.yPos - l.yPos, 2));
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
		g.fillOval((int) (((xPos - VIS_SIZE/2) - (x - margin))*stretch), (int) (((yPos - VIS_SIZE/2) - (y - margin))*stretch), (int)(VIS_SIZE*stretch), (int)(VIS_SIZE*stretch));
	}
	
	public void drawAllLinksRelative(double x, double y, double margin, Graphics g) {
		double stretch = 600.0/(margin*2.0);
		for (Location l : links) {
			g.drawLine((int) ((xPos - (x - margin))*stretch), (int) ((yPos - (y - margin))*stretch), (int) ((l.xPos - (x - margin))*stretch), (int) ((l.yPos - (y - margin))*stretch));
		}
	}
}

package CS321.Project.Code;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class Location {
	double xPos, yPos;

	public ArrayList<Location> links;

	public Location(double x, double y) {
		xPos = x;
		yPos = y;
		links = new ArrayList<Location>();
	}

	public void addLink(Location l) {
		links.add(l);
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
}

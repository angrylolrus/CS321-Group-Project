package CS321.Project.Code;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * A class to hold all the locations in the world and to get information about the overmap.
 * @author Jarrod Quinn
 *
 */
public class World {	
	public ArrayList<ArrayList<Location>> locs;
	public ArrayList<Point2D.Double> locuses;
	int xSize, ySize;
	
	GameMenu parent;
	
	public World(GameMenu p, int xS, int yS) {
		parent = p;
		locs = new ArrayList<ArrayList<Location>>();
		locs.add(new ArrayList<Location>());
		xSize = xS; ySize = yS;
		
		ArrayList<Point2D.Double> loci = new ArrayList<Point2D.Double>();
		
		for(int a = 0; a < 16; a++) {
			double x = Controller.random.nextDouble() * xSize;
			double y = Controller.random.nextDouble() * ySize;
			loci.add(new Point2D.Double(x, y));
		}
		
		//Does initial generation of points
		for(int a = 0; a < xSize; a += 25)
			for(int b = 0; b < ySize; b += 25) {
				//So it isn't too even
				if(Controller.random.nextDouble() < .2)
					continue;
				double x = a, y = b;
				//Give it some wiggle
				x += Controller.random.nextDouble() * 30 - 15;
				y += Controller.random.nextDouble() * 30 - 15;
				
				//Moves them towards the closest 3 loci
				double[] dists = new double[loci.size()];
				for(int i = 0; i < loci.size(); i++) {
					Point2D.Double locus = loci.get(i);
					//Gets the distance
					dists[i] = Math.sqrt(Math.pow(x-locus.x, 2) + Math.pow(y - locus.y, 2));
					
				}
				
				for(int i = 0; i < 2; i++) {
					int index = 0;
					for(int j = 1; j < dists.length; j++)
						if(dists[j] < dists[index])
							index = j;
					//Gets the distance the point will move
					double movement = dists[index] * (Controller.random.nextDouble() * .2 + .01);
					//Maxes out the distance to that locus so it won't be reused
					dists[index] = Double.MAX_VALUE;
					//Moves the point 
					Point2D.Double closest = loci.get(index);
					double angleTo = Math.atan2(closest.y- y, closest.x - x);
					x += Math.cos(angleTo)*movement;
					y += Math.sin(angleTo)*movement;
				}//End of random moving //*/
		
				locs.get(0).add(new Location(x, y));
			}//Done generating locations
		
		
		//Creates links between locations
		for(int a = 0; a < locs.get(0).size()-1; a++)
			for(int b = a+1; b < locs.get(0).size(); b++) {
				Location loc1 = locs.get(0).get(a), loc2 = locs.get(0).get(b);
				//Checks the position. If they are more than 25 units in either dimension away, doesn't bother to check distance
				if(Math.abs(loc1.xPos - loc2.xPos) > 50 || Math.abs(loc1.yPos - loc2.yPos) > 50)
					continue;
				//Checks distance and decides if there should be a link (randomly)
				double dist = Math.sqrt(Math.pow(loc1.xPos - loc2.xPos, 2) + Math.pow(loc1.yPos - loc2.yPos, 2));
				
				if(dist < 5) // && random.nextDouble() < .9) 					
					loc1.addLink(loc2);
				else if(dist < 20 && Controller.random.nextDouble() < .9)
					loc1.addLink(loc2);
				else if(dist < 40 && Controller.random.nextDouble() < .6)
					loc1.addLink(loc2);
				else if(dist < 70 && Controller.random.nextDouble() < .1)
					loc1.addLink(loc2);
				
			}
		
		//Removes small regions
		ArrayList<Location> master = locs.remove(0);
		while(master.size() != 0) {
			ArrayList<Location> curList = new ArrayList<Location>();
			master.get(0).generateRegion(curList);
			for(Location l: curList)
				master.remove(l);
			
			if(curList.size() > 30)
				locs.add(curList);
		}
		
		locuses = loci;
		
	}//End of constructor
	
	public Location getRandomLocation() {
		int outer = (int)(Controller.random.nextDouble()*locs.size());
		int inner = (int)(Controller.random.nextDouble()*locs.get(outer).size());
		return locs.get(outer).get(inner);
		
	}
	
	
	public Location getLocationAt(Point p, double zoomLevel) {
		//double stretch = 600.0/((xSize/zoomLevel);
		for(ArrayList<Location> region : locs) {
			for(Location l: region) {
				double xDif = Math.abs(p.x - l.xPos);
				double yDif = Math.abs(p.y - l.yPos);
				if(xDif > 6*zoomLevel  || yDif > 6*zoomLevel)
					continue;
				//Just used the square of teh desired distance
				// theoretically, sqrt function is really slow
				if(xDif*xDif + yDif*yDif < 100)
					return l;
			}
	
		}
		
		return null;
	}
	
	public BufferedImage getSubMap(int x, int y, double zoomLevel) {
		BufferedImage map = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = map.createGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 600);
		
		double margin = (1200 / zoomLevel / 2);
		//Left and right bounds go off theoretical screen to catch things that may be partially in
		double xLeft = x - margin - 20, yTop = y - margin - 20, xRight = x + margin + 20, yBot = y + margin + 20;
		
		
		ArrayList<Location> onScreen = new ArrayList<Location>();
		//Gathers up all the locations that are on screen
		for(ArrayList<Location> region : locs) 
			for(Location loc : region)
				if(loc.xPos > xLeft && loc.xPos < xRight && loc.yPos > yTop && loc.yPos < yBot)
					onScreen.add(loc);
		
		g.setColor(Color.white);
		//Draw links so they're under
		for(Location l: onScreen) 
			l.drawAllLinksRelative(x, y, margin, g);
		
		g.setColor(Color.blue);
		for(Location l: onScreen) {
			l.drawSelfRelative(x, y, margin, g);
		}
		
		g.setColor(Color.red);
		parent.playerLocation.drawSelfRelative(x, y, margin, g);
		
		if(parent.hardFocus instanceof Location) {
			g.setColor(Color.green);
			((Location) parent.hardFocus).drawSelfRelative(x, y, margin, g);
		}
		
		return map;
	}
		
	public BufferedImage drawFullMap() {
		BufferedImage map = new BufferedImage(xSize*2, ySize*2, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = map.createGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, xSize*2, ySize*2);
		
		//Draws all the links first so they're behind the locations
		g.setColor(Color.white);
		for(ArrayList<Location> curList : locs) 
			for(Location l: curList) {
				l.drawAllLinks(g);
			}
		g.drawRect(1, 1, xSize-2, ySize-2);
		//Then draws the locations
		
		for(ArrayList<Location> curList : locs) {
			int r = (int)(Controller.random.nextDouble()*255), gr = (int)(Controller.random.nextDouble()*255), b = (int)(Controller.random.nextDouble()*255);
			g.setColor(new Color(r,gr,b));//
			for(Location l: curList) {
				l.drawSelf(g);
			}
		}
		
		/*
		g.setColor(Color.yellow);
		for(Point2D p : locuses) {
			g.fillOval((int)p.getX()*2-5, (int) p.getY()*2-5, 10, 10);
		}//*/
		
		
		return map;
	}
	/*
	public static void main(String[] args) {
		World temp = new World(1200, 1200);
		BufferedImage map = temp.drawMap();
		Util.writeImageToFolder(map, "c:/321/output/");
		
	}//*/
	
}//End of world (class)

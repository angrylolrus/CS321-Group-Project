package CS321.Project.Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class GameMenu {
	//To receive and pass commands back up
	Controller controller;
	
	//Variables for the UI and broad gameplay elements
	Player player;
	World world;
	int worldSize;
	public ArrayList<UIElement> elements;
	InfoPanel infoPanel;
	double time;
	
	//Variables used to control the map view so the player can zoom in/out & pan
	Point mapCenter;
	double zoomLevel;
	MouseEvent dragStart, dragPos;
	Object softFocus, hardFocus;
	
	
	//Variables about current game events
	Location playerLocation;

	public GameMenu(Controller c) {
		//Basic initializaion
		controller = c;
		player = new Player();
		infoPanel = new InfoPanel(this);
		//World & map initialization 
		worldSize = 1200; //This may be customizable (or just changed) later, but for now its 1200
		world = new World(this, worldSize,worldSize);
		zoomLevel = 2;
		mapCenter = new Point((int)(worldSize/zoomLevel), (int)(worldSize/zoomLevel));
		
		playerLocation = world.getRandomLocation();
		
	}

	public void receiveMouse(MouseEvent e, int type) {
		//For focus on map
		if (e.getX() < 600 && e.getY() < 600) {
			//Margin is how wide the square is
			int margin = (int)(worldSize/zoomLevel/2);
			//Stretch is the amount the coordinate needs to be divided by based on margin
			double stretch = 1/((worldSize/2.0)/(margin*2.0));
			int mapX = (int)((mapCenter.x - margin) + (e.getX() * stretch));
			int mapY = (int)((mapCenter.y - margin) + (e.getY() * stretch));
			
			//Mouse click
			if (type == 2) {
				dragStart = e;
			}
			else if(type == 4 && dragStart != null) {
				if(dragPos != null) {
					int xChange = (int)((dragPos.getX() - e.getX())/Math.pow(zoomLevel/2, .5));
					int yChange = (int)((dragPos.getY() - e.getY())/Math.pow(zoomLevel/2, .5));
					mapCenter.x += xChange;
					mapCenter.y += yChange;
					dragPos = e;
				}
				else if(Math.abs(dragStart.getX() - e.getX()) > 5 || Math.abs(dragStart.getY() - e.getY()) > 5) {
					dragPos = e;
				}
			}
			else if(type == 3) {
				//If the player (presumably) tried to click a single point
				if(dragPos == null) {
					hardFocus = world.getLocationAt(new Point(mapX, mapY), zoomLevel);
				}
				else {
					dragPos = null;
					dragStart = null;
				}
				
			}

			// For mouse scroll
			if (type == 5 || type == 6) {
				//Values for calculating distance
				int xChange = mapX - mapCenter.x;
				int yChange = mapCenter.y - mapY;
				double distance = Math.sqrt(xChange*xChange + yChange*yChange);
				double angleTo = Math.atan2(yChange, xChange);
				//Reuse values to determine where new map center will be
				xChange = (int)(Math.cos(angleTo)*distance*.1);
				yChange = (int)(-Math.sin(angleTo)*distance*.1);
				
				// Scroll down (zoom out)
				if(type == 5) {
					//Use negative change, so its away from the mouse instead of towards
					//mapCenter.x -= xChange;
					//mapCenter.y -= yChange;
					zoomLevel = Math.max(1, zoomLevel - .1);
				}
				if(type == 6) {
					zoomLevel = Math.min(6, zoomLevel + .1);
				}
			}
			//	Ensures it doesn't go off the edge - this code was repeated a lot so I figured it would be
			// better to just run it every time
			mapCenter.x = Math.max((int)(worldSize/zoomLevel/2), Math.min(mapCenter.x, world.xSize - (int)(worldSize/zoomLevel/2)));
			mapCenter.y = Math.max((int)(worldSize/zoomLevel/2), Math.min(mapCenter.y, world.ySize - (int)(worldSize/zoomLevel/2)));
		}
		
		
		
		
		if(type == 3) {
			dragPos = null;
			dragStart = null;
		}
		
	}

	public void receiveKey(KeyEvent e, int type) {
		/*
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			mapCenter.x += 8;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			mapCenter.y += 8;
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			mapCenter.x -= 8;
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			mapCenter.y -= 8;
		
		mapCenter.x = Math.max((int)(worldSize/zoomLevel/2), Math.min(mapCenter.x, world.xSize - (int)(worldSize/zoomLevel/2)));
		mapCenter.y = Math.max((int)(worldSize/zoomLevel/2), Math.min(mapCenter.y, world.ySize - (int)(worldSize/zoomLevel/2)));
		//*/
	}

	public void draw(Graphics g) {
		//BufferedImage map = world.drawFullMap();
		BufferedImage localMap = world.getSubMap(mapCenter.x, mapCenter.y, zoomLevel);
		g.drawImage(localMap, 0, 0, 600, 600, null); 
		
		BufferedImage infPnl = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
		infoPanel.showInfo(hardFocus, infPnl.getGraphics());
		g.drawImage(infPnl, 600, 600, 200, 200, null);
		
		
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 600, 600);
		g.drawRect(600, 600, 200, 200);
	}
}

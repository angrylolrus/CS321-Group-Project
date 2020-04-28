package CS321.Project.Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class GameMenu {
	
	//Placeholders for values that may vary but we haven't implemented yet
	static final double PLAYER_SPEED = 4.7; //player speed in km/h
	
	//To receive and pass commands back up
	Controller controller;
	boolean debug = true;
	
	//Variables for the UI and broad gameplay elements
	Player player;
	World world;
	int worldSize;
	public ArrayList<UIElement> elements;
	InfoPanel infoPanel;
	ActionPanel actionPanel;
	long time;
	
	//Variables used to control the map view so the player can zoom in/out & pan
	Point mapCenter;
	double zoomLevel;
	MouseEvent dragStart, dragPos;
	Object softFocus, hardFocus;
	Inventory openInventory;
	
	
	//Variables about current game events
	Location playerLocation;

	public GameMenu(Controller c) {
		//Basic initializaion
		controller = c;
		player = new Player();
		infoPanel = new InfoPanel(this);
		actionPanel = new ActionPanel(this);
		//World & map initialization 
		worldSize = 1200; //This may be customizable (or just changed) later, but for now its 1200
		world = new World(this, worldSize,worldSize);
		zoomLevel = 2;
		mapCenter = new Point((int)(worldSize/zoomLevel), (int)(worldSize/zoomLevel));
		
		playerLocation = world.getRandomLocation();
		
		//Time is expressed in minutes, this is 8am
		time = 480;
		
		player.getInventory().addItem(JsonFileWorker.getItem("Clothing", 0, true) );
		player.getInventory().addItem(JsonFileWorker.getItem("Clothing", 0, true) );
		player.getInventory().addItem(JsonFileWorker.getItem("Clothing", 0, true) );
		player.getInventory().addItem(JsonFileWorker.getItem("Clothing", 0, true) );

	}
	
	//Methods to adjust time
	
	public void advanceTime(int t) {
		time += t;
	}
	
	public long getTime() {
		return time;
	}
	
	public long timeSince(int t) {
		return time - t;
	}
	
	public void openInventory(Inventory i) {
		//If the player is opening an invalid inventory (shouldn't happen, but)
		//	it'll just close the inventory
		if(i == null) {
			closeInventory();
			return;
		}
		//If the player doesn't have an inventory open, takes a minute
		//	to open the new one
		if(openInventory == null) { 
			advanceTime(1);
			openInventory = i;
		}
		//If the player isn't opening a new inventory
		else if(openInventory != i) {
			advanceTime(2);
			openInventory = i;
		}
		//Final option, but no if else needed, is the player opens an inventory
		//	they already have open
	}
	
	public void closeInventory() {
		if(openInventory != null) {
			openInventory.resetHighlight();
			advanceTime(1);
			openInventory = null;
		}	
	}
	
	public void travelToFocus() {
		//If the player doesn't have a location selected
		if(!(hardFocus instanceof Location))
			return;
		Location dest = (Location)hardFocus;
		//If the selected location isn't adjacent
		if(!playerLocation.adjacentTo(dest))
			return;
		
		
		
		//If the player successfully travels
		double timeTaken = playerLocation.distanceTo(dest); // initial distance
		timeTaken /= PLAYER_SPEED; // Number of hours
		timeTaken *= 60; // Number of minutes
		advanceTime((int)Math.round(timeTaken)); //Finally, advance time
		playerLocation = dest; //And update the location
	}
	
	public void changeFocus(Object o) {
		hardFocus = o;
		
		/* With changes to opening inventories, we don't need to worry about
		   the soft vs hard focus, there's only a hard focus
		//If the player focuses on something that isn't there
		if(o == null) {
			if(softFocus != null) {
				hardFocus = softFocus;
				openInventory.resetHighlight();
				player.getInventory().resetHighlight();
				softFocus = null;
			}
			else {
				hardFocus = null;
			}
			actionPanel.itemFocus(false, null);
		}
		//If the player is actually focusing on something
		else {
			if(hardFocus instanceof Location && o instanceof Item) {
				softFocus = hardFocus;
				actionPanel.itemFocus(true, o);
			}
			hardFocus = o;
		}//*/
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
					changeFocus(world.getLocationAt(new Point(mapX, mapY), zoomLevel));
					player.inventory.resetHighlight();
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
					zoomLevel = Math.max(1, zoomLevel - .25);
				}
				if(type == 6) {
					zoomLevel = Math.min(6, zoomLevel + .25);
				}
			}
			//	Ensures it doesn't go off the edge - this code was repeated a lot so I figured it would be
			// better to just run it every time
			mapCenter.x = Math.max((int)(worldSize/zoomLevel/2), Math.min(mapCenter.x, world.xSize - (int)(worldSize/zoomLevel/2)));
			mapCenter.y = Math.max((int)(worldSize/zoomLevel/2), Math.min(mapCenter.y, world.ySize - (int)(worldSize/zoomLevel/2)));
		}
		
		
		if(e.getX() > 600 && e.getY() < 600) {
			int xRel = e.getX() - 600,  yRel = e.getY();
			if(type == 2){
				Inventory target = player.getInventory();
				// If there's an inventory open, reset its highlighting
				if (openInventory != null) {
					if (yRel > 300) {
						target = openInventory;
						yRel -= 300;
					}
				}
				if (type == 2) {
					changeFocus(target.selectItemAt(xRel, yRel));
				}
				if (type == 5) {
					target.scroll(-1);
				}

				else if (type == 6)
					target.scroll(1);
			}
			
		}

		if(e.getX() < 600 && e.getY() > 600)
		{
			actionPanel.receiveMouse(e, type);
		}
		
		if(type == 3) {
			dragPos = null;
			dragStart = null;
		}
		
	}

	public void receiveKey(KeyEvent e, int type) {
		/*
		//Testing code
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			travelToFocus(); //*/
		
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
		
		BufferedImage playerInv = new BufferedImage(200, 600, BufferedImage.TYPE_INT_RGB);
		player.getInventory().drawSelf(playerInv.getGraphics());
		g.drawImage(playerInv, 600, 0, 200, 600, null);

		BufferedImage actPnl = new BufferedImage(600, 200, BufferedImage.TYPE_INT_RGB);
		actionPanel.draw(actPnl.getGraphics());
		g.drawImage(actPnl, 0, 600, 600, 200, null);
		
		if(openInventory != null) {
			BufferedImage openInv = new BufferedImage(200, 300, BufferedImage.TYPE_INT_RGB);
			openInventory.drawSelf(openInv.getGraphics());
			g.drawImage(openInv, 600, 300, 200, 300, null);
		}
		
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 600, 600);
		g.drawRect(600, 600, 200, 200);
	}
}

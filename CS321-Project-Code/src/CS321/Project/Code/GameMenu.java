package CS321.Project.Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class GameMenu {
	//To receive and pass commands back up
	Controller controller;
	
	//Variables for the UI and broad gameplay elements
	Player player;
	World world;
	public ArrayList<UIElement> elements;
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
		//World & map initialization 
		world = new World(1200,1200);
		mapCenter = new Point(600, 600);
		zoomLevel = 2;
	}

	public void receiveMouse(MouseEvent e, int type) {
		//For focus on map
		if (e.getX() < 600 && e.getY() < 600) {
			
			int mapX = mapCenter.x - 300 + e.getX();
			int mapY = mapCenter.y - 300 + e.getY();
			
			//Mouse click
			if (type == 2) {
				dragStart = e;
			}
			else if(type == 4 && dragStart != null) {
				if(dragPos != null) {
					int xChange = dragPos.getX() - e.getX();
					int yChange = dragPos.getY() - e.getY();
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
			if (type == 4 || type == 5) {

			}
			//	Ensures it doesn't go off the edge - this code was repeated a lot so I figured it would be
			// better to just run it every time
			mapCenter.x = Math.max(300, Math.min(mapCenter.x, world.xSize - 300));
			mapCenter.y = Math.max(300, Math.min(mapCenter.y, world.ySize - 300));
		}
	}

	public void receiveKey(KeyEvent e, int type) {

	}

	public void draw(Graphics g) {
		BufferedImage map = world.drawFullMap();
		BufferedImage localMap = map.getSubimage(mapCenter.x - 300, mapCenter.y - 300, 600, 600);
		g.drawImage(localMap, 0, 0, 600, 600, null);// controller.xSize/10*8, controller.ySize/10*8, null);
		
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 600, 600);
		
		if(dragPos != null)
			g.fillOval(dragPos.getX() - 3, dragPos.getY() - 3, 6, 6);
	}
}

package CS321.Project.Code;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import CS321.Project.Code.UIElement.*;

public class MainMenu {
	public Controller controller;
	public ArrayList<UIElement> elements;
	public Button newGame, loadGame, settings, exit;
	
	public MainMenu(Controller c) {
		controller = c;
		elements = new ArrayList<UIElement>();
		
		elements.add(new UIElement.MouseCoords());
		
		//Title
		UIElement.Label curLabel = new UIElement.Label("CS 321 Apocalyptic Game", false, 50, 100);
		curLabel.setFont("Courier New", Font.PLAIN, 28);
		elements.add(curLabel);
		
		//New game
		newGame = new Button(50, 150, 200, 40);
		elements.add(newGame);
		curLabel = new UIElement.Label("New Game", false, 50, 200);
		curLabel.setFont("Courier New", Font.PLAIN, 28);
		elements.add(curLabel);
		
		
		//Load game
		curLabel = new UIElement.Label("Load Game", false, 50, 300);
		curLabel.setFont("Courier New", Font.PLAIN, 28);
		elements.add(curLabel);
		
		//Settings
		curLabel = new UIElement.Label("Settings", false, 50, 100);
		curLabel.setFont("Courier New", Font.PLAIN, 28);
		elements.add(curLabel);
		
		//Exit
		curLabel = new UIElement.Label("Exit", false, 50, 100);
		curLabel.setFont("Courier New", Font.PLAIN, 28);
		elements.add(curLabel);
		
		
	}
	
	public void draw(Graphics gb) {
		int xSize = controller.xSize, ySize = controller.ySize;
		Graphics2D g = (Graphics2D) gb;
		g.setColor(Color.black);
		g.fillRect(0, 0, xSize, xSize);
		//Makes a quick grid
		g.setColor(Color.blue);
		for(int a = 0; a < xSize; a+= 10) {
			g.drawLine(a, 0, a, ySize);
			g.drawLine(0, a, xSize, a);
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("Courier New", Font.BOLD, 30));
		
		for(UIElement e : elements) {
			e.update(g);
		}
		
		/*
		g.setColor(Color.yellow);
		g.fillOval(xSize/2-3, ySize/2-3, 6, 6);//*/
	}
	
	public void receiveKey(KeyEvent e, int type) {
		
	}

	public void receiveMouse(MouseEvent e, int type) {
		for(UIElement element : elements) {
			element.mouseAction(e, type);
		}
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			System.out.println("Mouse clicked");
		}
		
	}
}

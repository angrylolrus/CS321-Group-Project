package CS321.Project.Code;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainMenu {
	public Controller controller;
	public ArrayList<UIElement> elements;
	
	public MainMenu(Controller c) {
		controller = c;
		elements = new ArrayList<UIElement>();
		
		elements.add(new UIElement.Button(100, 100, 100, 50));
		elements.add(new UIElement.Button(210, 100, 100, 50));
		elements.add(new UIElement.MouseCoords());
		UIElement.Label title = new UIElement.Label("CS 321 Apocalyptic Game", true, controller.xSize/2, controller.ySize/2);
		title.setFont("Courier New", Font.PLAIN, 28);
		elements.add(title);
		
		UIElement.TextBox temp = new UIElement.TextBox(200, 200, 200, 25);
		elements.add(temp);
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
		for(UIElement element: elements)
			if(element.hasFocus)
				if(element.getClass() == UIElement.TextBox.class)
					((UIElement.TextBox) element).keyAction(e, type);
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

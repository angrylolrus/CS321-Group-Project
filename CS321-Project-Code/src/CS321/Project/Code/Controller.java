package CS321.Project.Code;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controller extends JPanel {
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public Insets insets;
	int xSize, ySize;
	int currentState;
	public ArrayList<UIElement> elements;
	
	public MainMenu mainMenu;
	public GameMenu gameMenu;
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.initialize();
	}
	
	public Controller() {
	}
	
	public void updateSize() {
		xSize = frame.getHeight() - (insets.left + insets.right );
		ySize = frame.getHeight() - (insets.top  + insets.bottom);
	}
	
	private void initialize() {
		xSize = 700;
		ySize = 700;
		currentState = 1;
		
		frame = new JFrame("321 Apocalyptic Game");
		/* ******************* N.B Change this later ******************* */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(25, 25);
		frame.setVisible(true);
		insets = frame.getInsets();
		frame.setSize(xSize + insets.left + insets.right, xSize + insets.top + insets.bottom);
		frame.setContentPane(this);
		
		mainMenu = new MainMenu(this);
		updateSize();
		
		//Listeners
		frame.addMouseListener(new MouseInput(this));
		frame.addMouseMotionListener(new MouseInput(this));
		frame.addComponentListener(new WindowChange(this));
		frame.addKeyListener(new KeyInput(this));
	}
	
	public void paintComponent(Graphics g) {
		switch(currentState) {
		case 1: mainMenu.draw(g); break;
		case 2: gameMenu.draw(g); break;
		}
	}
	
	public void launchGame() {
		mainMenu = null;
		gameMenu = new GameMenu(this);
		currentState = 2;
	}
	
	
	private class WindowChange extends ComponentAdapter{
		private Controller controller;
		
		public WindowChange(Controller c) {
			controller = c;
		}
		
		public void componentResized(ComponentEvent e) {
			controller.updateSize();
		}
	}
	
	private class KeyInput extends KeyAdapter{
		private Controller controller;
		public KeyInput(Controller c) {
			controller = c;
		}
		
		public void keyTyped(KeyEvent e) {
			switch(currentState) {
			case 1: mainMenu.receiveKey(e, 0); break;
			case 2: gameMenu.receiveKey(e, 0); break;
			}
			controller.repaint();
	
		}
		
	}
	
	private class MouseInput extends MouseAdapter{
		private Controller controller;
		public MouseInput(Controller c) {
			controller = c;
		}
		
		public void mousePressed(MouseEvent e) {
			e.translatePoint(-insets.left, -insets.top);
			switch(currentState) {
			case 1: mainMenu.receiveMouse(e, 2); break;
			case 2: gameMenu.receiveMouse(e, 2); break;
			}
			controller.repaint();
			
		}
		
		public void mouseMoved(MouseEvent e) {
			e.translatePoint(-insets.left, -insets.top);
			switch(currentState) {
			case 1: mainMenu.receiveMouse(e, 1); break;
			case 2: gameMenu.receiveMouse(e, 1); break;
			}
			controller.repaint();
		}
		
		public void mouseReleased(MouseEvent e) {
			e.translatePoint(-insets.left, -insets.top);
			switch(currentState) {
			case 1: mainMenu.receiveMouse(e, 3); break;
			case 2: gameMenu.receiveMouse(e, 3); break;
			}
			controller.repaint();
		}
	}
	
	
}

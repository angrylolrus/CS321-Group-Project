package CS321.Project.Code;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controller extends JPanel {
	
	public static Random random = new Random(1);
	
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public Insets insets;
	int xSize, ySize;
	int currentState;
	public ArrayList<UIElement> elements;
	public boolean stateChanged;

	public MainMenu mainMenu;
	public GameMenu gameMenu;

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.initialize();
	}

	public Controller() {
	}

	public void updateSize() {
		xSize = frame.getHeight() - (insets.left + insets.right);
		ySize = frame.getHeight() - (insets.top + insets.bottom);
	}

	private void initialize() {
		xSize = 800;
		ySize = 800;
		currentState = 1;
		mainMenu = new MainMenu(this);

		frame = new JFrame("321 Apocalyptic Game");
		/* ******************* N.B Change this later ******************* */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(25, 25);
		frame.setVisible(true);
		insets = frame.getInsets();
		frame.setSize(xSize + insets.left + insets.right, xSize + insets.top + insets.bottom);
		frame.setContentPane(this);

		updateSize();

		// Listeners
		frame.addMouseListener(new MouseInput(this));
		frame.addMouseMotionListener(new MouseInput(this));
		frame.addMouseWheelListener(new MouseInput(this));
		//frame.addComponentListener(new WindowChange(this)); - Not yet implemented
		frame.addKeyListener(new KeyInput(this));
		frame.setVisible(true);
		
	}

	public void paintComponent(Graphics g) {
		if(stateChanged)
			clean();
		
		switch (currentState) {
			case 1:
				mainMenu.draw(g);
				break;
			case 2:
				gameMenu.draw(g);
				break;
		}
	}

	public void launchMenu() {
		mainMenu = new MainMenu(this);
		currentState = 1;
		stateChanged = true;
	}
	
	public void launchGame() {
		gameMenu = new GameMenu(this);
		currentState = 2;
		stateChanged = true;
	}
	
	public void clean() {
		if(currentState == 1)
			gameMenu = null;
		if(currentState == 2)
			mainMenu = null;
		stateChanged = false;
	}

	/* Not yet implemented
	private class WindowChange extends ComponentAdapter {
		private Controller controller;

		public WindowChange(Controller c) {
			controller = c;
		}

		public void componentResized(ComponentEvent e) {
			controller.updateSize();
		}
	}//*/

	private class KeyInput extends KeyAdapter {
		private Controller controller;

		public KeyInput(Controller c) {
			controller = c;
		}
		
		//N.B. I have key movement turned off for now because holding down more than one key is wonky
		// 	and I'm not ready to fix it, but it is an Eventually To Do
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_W && e.isControlDown())
				System.exit(0);
			switch (currentState) {
				case 1:
					mainMenu.receiveKey(e, 0);
					break;
				case 2:
					gameMenu.receiveKey(e, 0);
					break;
			}
			
			controller.repaint();
		}
	}

	private class MouseInput extends MouseAdapter {
		private Controller controller;

		public MouseInput(Controller c) {
			controller = c;
		}

		public void mousePressed(MouseEvent e) {
			e.translatePoint(-insets.left, -insets.top);
			switch (currentState) {
				case 1:
					mainMenu.receiveMouse(e, 2);
					break;
				case 2:
					gameMenu.receiveMouse(e, 2);
					break;
			}
			controller.repaint();

		}

		public void mouseMoved(MouseEvent e) {
			e.translatePoint(-insets.left, -insets.top);
			switch (currentState) {
				case 1:
					mainMenu.receiveMouse(e, 1);
					break;
				case 2:
					gameMenu.receiveMouse(e, 1);
					break;
			}
			controller.repaint();
		}
		
		public void mouseDragged(MouseEvent e) {
			e.translatePoint(-insets.left, -insets.top);
			if(currentState == 2) {
				gameMenu.receiveMouse(e, 4);
				controller.repaint();
			}
		}

		public void mouseReleased(MouseEvent e) {
			e.translatePoint(-insets.left, -insets.top);
			switch (currentState) {
				case 1:
					mainMenu.receiveMouse(e, 3);
					break;
				case 2:
					gameMenu.receiveMouse(e, 3);
					break;
			}
			controller.repaint();
		}
		
		public void mouseWheelMoved(MouseWheelEvent e) {
			//5 for down, 6 for up
			int type = 5;
			if(e.getWheelRotation() < 0)
				type = 6;
				
			e.translatePoint(-insets.left, -insets.top);
			switch (currentState) {
				case 1:
					mainMenu.receiveMouse(e, type);
					break;
				case 2:
					gameMenu.receiveMouse(e, type);
					break;
			}
			controller.repaint();
		}
	}


}

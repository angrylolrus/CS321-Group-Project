package CS321.Project.Code;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class UIElement {
	public int xPos, yPos;
	public boolean hasFocus;
	public int xSize, ySize;
	public Color color;
	public abstract void update(Graphics g);
	public abstract void mouseAction(MouseEvent e, int type);
	public abstract void keyAction(KeyEvent e, int type);
	
	protected UIElement(int x, int y, int w, int h) {
		xPos = x;
		yPos = y;
		xSize = w;
		ySize = h;
		hasFocus = false;
		color = Color.WHITE;
	}
	
	public boolean contains(Point p) {
		if(p.x > xPos && p.x < xPos + xSize && p.y > yPos && p.y < yPos + ySize)
			return true;
		return false;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	/* Taking this out for now, I'm not sure we'll need it
	  Might just use the default textbox class
	public static class TextBox extends UIElement{
		private Label curContents;
		private boolean hasSize;
		
		public TextBox(int x, int y, int w, int h) {
			super(x, y, w, h);
			curContents = new Label("", false, x + 1, y + 2);
			hasSize = false;
			
		}
		
		public void update(Graphics g) {
			//This will only run the first time a box is updated to generate
			// the size its font will be. It isn't efficient, but what in this project is?
			if(!hasSize) {
				g.setFont(curContents.font);
				FontMetrics f = g.getFontMetrics(curContents.font);
				while(f.getHeight() <= ySize) {
					int oldSize = curContents.font.getSize();
					Font newFont = new Font(curContents.font.getName(), curContents.font.getStyle(),oldSize + 1);
					curContents.font = newFont;
					f = g.getFontMetrics(newFont);
					//height = f.getHeight();// + f.getMaxDescent();// + f.getMaxAscent();
					//System.out.println(f.getHeight() + " " + newFont.getSize());
				}
				curContents.yPos += this.ySize - f.getDescent() - 3;
				hasSize = true;
			}
			//Draws the box
			g.setColor(Color.darkGray);
			g.fillRect(xPos, yPos, xSize, ySize);
			g.setColor(color);
			curContents.update(g);
			
		}
		
		public void keyAction(KeyEvent e, int type) {
			String s = curContents.contents;
			if(e.getKeyChar() == '\b') {
				if(s.length() >= 1)
					s = s.substring(0, s.length()-1);
				curContents.rewrite(s);
			}//end of backspace
			else {
				curContents.contents = curContents.contents + e.getKeyChar();
			}
		}

		public void mouseAction(MouseEvent e, int type) {
			if(contains(e.getPoint()))
				hasFocus = true;
			else
				hasFocus = false;
		}
	}//End of TextBox //*/
	
	public static class Label extends UIElement{
		public boolean centered;
		public String contents;
		public Font font;
		
		public Label(String s, boolean c, int x, int y) {
			super(x, y, -1, -1);
			contents = s;
			centered = c;
			font = new Font("Serif", Font.PLAIN, 16);
		}
		
		public void rewrite(String s) {
			contents = s;
		}
		
		public void keyAction(KeyEvent e, int type) {
			
		}
		
		public void setFont(String s, int mod, int size) {
			font = new Font(s, mod, size);
		}
		
		public void update(Graphics g) {
			g.setFont(font);
			if(centered) {
				FontMetrics fm = g.getFontMetrics();
				Rectangle2D boundingBox = fm.getStringBounds(contents, g);
				int x = (xPos - (int)(boundingBox.getWidth()/2));
				int y = (yPos + (int)(boundingBox.getHeight()/2));
				g.drawString(contents, x, y);
				
				
			}
			else {
				g.drawString(contents, xPos, yPos);
			}
		}
		
		public void mouseAction(MouseEvent e, int type) {}
		
		public boolean contains(Point p) {
			return false;
		}
	}//End of text label
	
	public static class MouseCoords extends UIElement{
		public MouseCoords() {
			super(10, 10, -1, -1);
		}
		
		public void mouseAction(MouseEvent e, int type) {
			//Reuses x and y size for mouse coordinates
			xSize = e.getX();
			ySize = e.getY();
		}
		
		public void keyAction(KeyEvent e, int type) {
			
		}
		
		public void update(Graphics g) {
			g.setColor(color);
			g.fillRect(xPos, yPos, 50, 15);
			g.setColor(Color.white);
			g.setFont(new Font("Courier New", Font.PLAIN, 12));
			g.drawString(xSize + "", 10, 22);
			g.drawString(ySize + "", 35, 22);
		}
	}
	
	public static class Button extends UIElement{
		// x,y are position, w,h are width and height
		public Button(int x, int y, int w, int h) {
			super(x, y, w, h);
		}
		
		public void keyAction(KeyEvent e, int type) {
			
		}
		
		public void mouseAction(MouseEvent e, int type) {
			if(this.contains(e.getPoint()))
				hasFocus = true;
			else
				hasFocus = false;
			
			
		}
		
		public void update(Graphics g) {
			g.setColor(color);
			g.fill3DRect(xPos, yPos, xSize, ySize, !hasFocus);
		}
		
	}//End Button
}

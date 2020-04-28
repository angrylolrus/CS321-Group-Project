package CS321.Project.Code;

import java.util.ArrayList;
import CS321.Project.Code.UIElement.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

public class ActionPanel {
    
    public ArrayList<UIElement> elements;
    GameMenu parent;
    public Button equip, eat, travel;
	
	public ActionPanel(GameMenu g) {
        parent = g;
        Label curLabel;
        elements = new ArrayList<UIElement>();
        
        Button[][] blankButtons = new Button[12][6];
        
        for(int a = 0; a < blankButtons.length; a++)
        	for(int b = 0; b < blankButtons[a].length; b++) {
        		blankButtons[a][b] = new Button(5 + (a*60), 5 + (b*40), 50, 30);
        		//elements.add(blankButtons[a][b]);
        	}
        
        equip = blankButtons[0][0];
        equip.updateLabel("Equip");
        elements.add(equip);
        
        /*
        //Equip
        equip = new Button(20, 80, 50, 40);
		elements.add(equip);
		curLabel = new UIElement.Label("Equip", true, 45, 95);
		curLabel.setFont("Courier New", Font.PLAIN, 16);
		curLabel.setColor(Color.black);
        elements.add(curLabel);
        
        //Eat
		eat = new Button(90, 80, 50, 40);
		elements.add(eat);
		curLabel = new UIElement.Label("Eat", true, 115, 95);
		curLabel.setFont("Courier New", Font.PLAIN, 16);
		curLabel.setColor(Color.black);
		elements.add(curLabel);		
		*/
    }

    public void draw(Graphics gb) {
        Graphics2D g = (Graphics2D) gb;

		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 200);
        
        g.setColor(Color.white);
		for(UIElement e : elements) {
			e.update(g);
        }
    }

    public void itemFocus(boolean b, Object o)
    {
        boolean c = false, t = false, f = false;
        if(o != null)
        {
            if(o instanceof Clothing || o instanceof Tool)
            {
                c = true;
                t = true;
                f = false;
            }
            else if(o instanceof Food)
            {
                c = false;
                t = false;
                f = true;
            }
        }
        else
        {
            c = false;
            t = false;
            f = false;
        }
        /*
        Button equip = (UIElement.Button) elements.get(0);
        equip.setClickable(c);

        Button eat = (UIElement.Button) elements.get(2);//*/
        //eat.setClickable(f);
    }
    
    //Sends the button they pressed
    public void buttonPressed(Button b) {
    	if(b == equip) {
    		
    	}
    	else if(b == eat) {
    		
    	}
    	else if(b == travel) {
    		
    	}
    }
    

    public void receiveMouse(MouseEvent e, int type) {
        e.translatePoint(0, -600);

		for(UIElement element : elements) {
            element.mouseAction(e, type);

            //Not the desired button (or not a button)
			if(element.contains(e.getPoint()) == false || !(element instanceof Button))
				continue;
			//Player clicked and button clicked on is valid
			if(type == 3 && ((Button)element).isClickable()) {
				buttonPressed((Button)element);
				break;
			}
        }
	}
}
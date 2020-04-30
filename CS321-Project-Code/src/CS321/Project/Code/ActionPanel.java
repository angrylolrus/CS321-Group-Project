package CS321.Project.Code;

import java.util.ArrayList;

import CS321.Project.Code.UIElement.Button;
import CS321.Project.Code.UIElement.Label;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

public class ActionPanel {
    
    public ArrayList<UIElement> elements;
    GameMenu parent;
    public Button equip, use, inspectShort, inspectMed, inspectLong, travel, transfer, wait, loot;
	
	public ActionPanel(GameMenu g) {
        parent = g;
        elements = new ArrayList<UIElement>();

        
        Button[][] blankButtons = new Button[12][6];
        
        for(int a = 0; a < blankButtons.length; a++)
        	for(int b = 0; b < blankButtons[a].length; b++) {
        		blankButtons[a][b] = new Button(5 + (a*60), 5 + (b*40), 50, 30);
        		//elements.add(blankButtons[a][b]);
        	}
        
        //Equip
        equip = blankButtons[0][0];
        equip.updateLabel("Equip");
        elements.add(equip);
        
        //Use
        use = blankButtons[1][0];
        use.updateLabel("Use");
        elements.add(use);

        //Simple label for inspection column
        Label insp = new Label("Inspect", true, blankButtons[2][0].xPos+30, blankButtons[2][0].yPos+20);
        insp.setFont(Controller.DEFAULT_FONT);
        elements.add(insp);
        
        //Inspect
        inspectShort = blankButtons[2][1];
        inspectShort.updateLabel("Short");
        elements.add(inspectShort);
        
        //Inspect
        inspectMed = blankButtons[2][2];
        inspectMed.updateLabel("Medium");
        elements.add(inspectMed);
        
        //Inspect
        inspectLong = blankButtons[2][3];
        inspectLong.updateLabel("Long");
        elements.add(inspectLong);

        //Travel
        travel = blankButtons[3][0];
        travel.updateLabel("Travel");
        elements.add(travel);

        //Transfer
        transfer = blankButtons[4][0];
        transfer.updateLabel("Transfer");
        elements.add(transfer);

        //Wait
        wait = blankButtons[5][0];
        wait.updateLabel("Wait");
        wait.setClickable(true);
        elements.add(wait);
        
        //Loot
        loot = blankButtons[6][0];
        loot.updateLabel("Loot");
        loot.setClickable(true);
        elements.add(loot);
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

    public void itemFocus(Object o)
    {
        //ct = clothing/tools, f = food, t = travel, ctf = any kind of item
        boolean clthTool = o instanceof Clothing;
        boolean food  = o instanceof Food;
        boolean trvl = o instanceof Location && ((Location)o).adjacentTo(parent.playerLocation);

        equip.setClickable(clthTool);

        use.setClickable(food);

        inspectShort.setClickable(clthTool || food);
        inspectMed.setClickable(clthTool || food);
        inspectLong.setClickable(clthTool || food);

        travel.setClickable(trvl);

        transfer.setClickable((clthTool || food) && parent.openInventory != null); 
        
    }

    //Sends the button they pressed
    public void buttonPressed(Button b) {
    	if(b == equip) {
    		
    	}
    	else if(b == use) {
    		parent.useItem();
    	}
    	else if(b == travel) {
    		parent.travelToFocus();
    	}
    	else if(b == use) {
    		parent.useItem();
    	}
    	else if(b == inspectShort || b == inspectMed || b == inspectMed) {
    		double closeness = 0;
    		if(b == inspectShort)
    			closeness = .3;
    		else if(b == inspectMed)
    			closeness = .5;
    		else
    			closeness = .9;
    		parent.inspectItem(closeness);
    	}
    	else if(b == transfer) {
    		parent.transferItem();
    	}
    	else if(b == wait) {
    		//Waits 4 hours
    		parent.advanceTime(60*4);
    	}
    	else if(b == loot) {
    		if(parent.openInventory != null)
    			parent.closeInventory();
    		//Opens the inventory at the current location
    		else
    			parent.openInventory(parent.playerLocation.getInventory());
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
			}//End of clicked
        }//end of for each element loop
		e.translatePoint(0, 600);
	}
}
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
    public Button equip, use, inspectShort, inspectMed, inspectLong, travel, transfer, wait1, wait4, wait8, search;
	
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
        Label insp = new Label("Inspect", true, blankButtons[2][0].xPos+25, blankButtons[2][0].yPos+15);
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
        
        Label waitLabel = new Label("Wait", true, blankButtons[5][0].xPos+25, blankButtons[5][0].yPos+15);
        waitLabel.setFont(Controller.DEFAULT_FONT);
        elements.add(waitLabel);

        //Wait
        wait1 = blankButtons[5][1];
        wait1.updateLabel("1 hr");
        wait1.setClickable(true);
        elements.add(wait1);

        wait4 = blankButtons[5][2];
        wait4.updateLabel("4 hrs");
        wait4.setClickable(true);
        elements.add(wait4);
        
        wait8 = blankButtons[5][3];
        wait8.updateLabel("8 hrs");
        wait8.setClickable(true);
        elements.add(wait8);
        
        //Loot
        search = blankButtons[6][0];
        search.updateLabel("Loot");
        search.setClickable(true);
        elements.add(search);
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
        boolean clthTool = o instanceof Clothing || o instanceof Tool;
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
    	else if(b == inspectShort || b == inspectMed || b == inspectLong) {
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
    	else if(b == wait1 || b == wait4 || b == wait8) {
    		int hrs = 0;
    		if(b == wait1)
    			hrs = 1;
    		else if(b == wait4)
    			hrs = 4;
    		else if(b == wait8)
    			hrs = 8;
    		//Waits 4 hours
    		parent.advanceTime(60*hrs);
    	}
    	else if(b == search) {
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
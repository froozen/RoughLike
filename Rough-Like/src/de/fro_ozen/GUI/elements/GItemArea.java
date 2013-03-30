package de.fro_ozen.GUI.elements;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;

public class GItemArea extends BaseElement{
	private ArrayList<BaseItem> items; //The items that go into the ItemFields
	private GItemField[][] fields; //The ItemFields displayed
	private int yrow, xrow; //The count of ItemFields in x- and y-direction

	//Refreshes the AreaModel and calls the checkMe() method of the ItemFields
	public void checkMe() {
		refreshAreaModel();
		for(int i = 0; i<xrow;i++){
			for(int i2 = 0; i2<yrow; i2++){
				fields[i][i2].checkMe();
			}
		}
	}

	//Puts the Items into the ItemFields
	private void refreshAreaModel(){
		for(int i = 0; i<xrow;i++){
			for(int i2 = 0; i2<yrow; i2++){
				fields[i][i2].setDisplayItem(null);
			}
		}
		int itemcount = 0;
		for(int yr=0;yr<yrow;yr++){
			for(int xr=0;xr<xrow;xr++){
				if(items!=null && itemcount<items.size()){
					fields[xr][yr].setDisplayItem(items.get(itemcount));
				}
				itemcount++;
			}
		}
	}

	//Changes value of items
	public void setDisplayItems(ArrayList<BaseItem> items){
		this.items = items;
	}

	//Calls the drawMe() method of the ItemFields
	public void drawMe(Graphics g){
		for(int i = 0; i<xrow;i++){
			for(int i2 = 0; i2<yrow; i2++){
				fields[i][i2].drawMe(g);
			}
		}
	}

	//Creates as many ItemFields as possible in the space given
	public void setBounds(int x, int y, int width, int height) {
		box = new Rectangle(x, y, width, height);
		xrow = width/31;
		yrow = height/31;
		fields = new GItemField[xrow][yrow];
		int itemcount = 0;
		for(int yr=0;yr<yrow;yr++){
			for(int xr=0;xr<xrow;xr++){
				fields[xr][yr] = new GItemField();
				if(items!=null && itemcount<items.size()){
					fields[xr][yr].setDisplayItem(items.get(itemcount));
				}
				fields[xr][yr].setBounds(box.x+xr*31, box.y+yr*31, 0, 0);
				itemcount++;
			}
		}
	}

	//Calls the setShift() method of the ItemFields
	public void setShift(int posxshift, int posyshift){
		for(int i = 0; i<xrow;i++){
			for(int i2 = 0; i2<yrow; i2++){
				fields[i][i2].setShift(posxshift, posyshift);
			}
		}
	}

	//Calls the setItemFieldListener() method of the ItemFields
	public void setItemFieldListener(GItemFieldListener listener){
		for(int i = 0; i<xrow;i++){
			for(int i2 = 0; i2<yrow; i2++){
				fields[i][i2].setItemFieldListener(listener);
			}
		}
	}
}

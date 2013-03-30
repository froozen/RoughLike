package de.fro_ozen.GUI.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.fro_ozen.RoughLike.BaseTypes.Items.BaseItem;
import de.fro_ozen.RoughLike.BaseTypes.Items.ItemStack;
import de.fro_ozen.RoughLike.GameMechanics.MouseInput;

public class GItemField extends BaseElement{
	public BaseItem display; //Item displayed in the Field
	private final int fieldSize = 28; //Size of the Field
	private GItemFieldListener listener; //Listener activated when clicked on the Field
	private boolean showItemStacking = true; //Wether ItemsSatcks have their numbers displayed

	public void checkMe() {
		if(box.contains(MouseInput.mousePoint)){
			if(MouseInput.leftClicked){
				if(listener!=null){
					listener.leftClicked(this);
				}
			}
			if(MouseInput.rightClicked){
				if(listener != null){
					listener.rightClicked(this);
				}
			}
		}
	}

	//Creates the ItemField
	public void setBounds(int x, int y, int width, int height) {
		loadUIset();
		img = uiset.getSubimage(0, 30, fieldSize, fieldSize);
		box = new Rectangle(x,y,fieldSize,fieldSize);
	}

	//Draws the Field and the Icon of display
	public void drawMe(Graphics g) {
		g.drawImage(img, box.x, box.y, null);
		if(display!=null){
			g.drawImage(display.icon, box.x+2, box.y+2, null);
			if(display instanceof ItemStack && showItemStacking){
				if(((ItemStack)display).number>1){
					applyTextStandarts(g);
					g.setColor(Color.black);
					g.drawString(((ItemStack)display).number+"", box.x, box.y+box.height);
				}
			}
		}
	}

	//Changes the value of display
	public void setDisplayItem(BaseItem item){
		this.display = item;
	}

	//Changes the value of listener
	public void setItemFieldListener(GItemFieldListener listener){
		this.listener = listener;
	}
	
	//Changes the Value of showItemStacking
	public void showItemStacking(boolean showItemStacking){
		this.showItemStacking = showItemStacking;
	}
}
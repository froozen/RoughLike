package de.fro_ozen.GUI.elements;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.FontSizeAction;

public class GTextArea extends BaseElement{
	private int linecount; //Max. count of lines displayed
	private ArrayList<String> text; //Lines aviaböe for display

	//UNUSED
	public void checkMe() {}

	//Draws the background and the lines
	public void drawMe(Graphics g){
		applyTextStandarts(g);
		g.drawImage(img, box.x, box.y, null);
		if(text != null){
			for(int i = 0; i<linecount; i++){
				if(text.size()>i){
					g.drawString(text.get(i), box.x+4, box.y+fontheight+i*(fontheight+3));
				}
			}
		}
	}

	//Adds a line to text
	public void addText(String text){
		this.text.add(text);
	}

	//Changes value of text
	public void setText(ArrayList<String>text){
		this.text = text;
	}

	//Creates text
	public GTextArea(){
		text = new ArrayList<String>();
	}

	//Creates backgound Image and calculates the linecount
	public void setBounds(int x, int y, int width, int height) {
		box = new Rectangle(x, y, width, height);
		linecount = height/(fontheight+3);
		img = createBaseImage(box.width, box.height, 65, 0, 65, 30, 2);
	}
}
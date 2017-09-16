import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import org.w3c.dom.css.Rect;

public class Block extends Rectangle {
	private Random random = new Random();
	private Color colour;

	public Block(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.colour = Color.BLACK;
	}

	public void draw(Graphics g) {
		g.setColor(colour);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	public void move(){
		int noiseX = random.nextInt(5)-2;
		int noiseY = random.nextInt(5)-2;
		
		this.x += noiseX;
		this.y += noiseY;
		
	}
	
	public void inContact(List<Block> blocks){
		boolean hasContact = false;
		for(Block block : blocks){
			if(this != block && this.intersects(block)){
				hasContact = true;
				this.setColor(Color.BLUE);
//				block.setColor(Color.BLUE);
				
				Rectangle intersectionBlock = (Rectangle) this.intersection(block);
				System.out.println(intersectionBlock);
			}
		}
		if(!hasContact){
			this.setColor(Color.BLACK);
		}
	}
	
	public void setColor(Color colour){
		this.colour = colour;
	}
	
	public String toString(){
		return "x: " + this.x+ " y: " + this.y + " height: "+this.height +" width: " + this.width;
	}
}
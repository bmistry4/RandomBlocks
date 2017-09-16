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

	/**
	 * Simply colours a block
	 * @param g Graphics param passed from the frame
	 */
	public void draw(Graphics g) {
		g.setColor(colour);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	/**
	 * Random movement - for now between -2 to 3
	 * TODO - Add constrainsts so bounces off the frame edges
	 * TODO - Change to Gaussian/ make more interesting
	 */
	public void move(){
		int noiseX = random.nextInt(5)-2;
		int noiseY = random.nextInt(5)-2;
		
		this.x += noiseX;
		this.y += noiseY;
		
	}
	
	/**
	 * Checks if the block is in overlapping any other blocks.
	 * If so the colour will change for the block 
	 * @param blocks List of all blocks 
	 */
	public void inContact(List<Block> blocks){
		boolean hasContact = false;
		for(Block block : blocks){
			if(this != block && this.intersects(block)){
				hasContact = true;
				//Uncomment when not testing
//				this.setColor(Color.BLUE);
//				block.setColor(Color.BLUE);
				
				Rectangle intersectionBlock = (Rectangle) this.intersection(block);
				intersectionCoverage(intersectionBlock);
//				System.out.println(intersectionBlock);
			}
		}
		if(!hasContact){
			//Uncomment when not testing
//			this.setColor(Color.BLACK);
		}
	}
	
	/**
	 * Identifies which part of the block is covered the most
	 * @param intersection
	 */
	public void intersectionCoverage(Rectangle intersection){
		if(Math.abs(this.y-intersection.getY())< Math.abs(this.y+height - intersection.getY())){
			System.out.println(intersection.toString()+ " TOP");
		}else{
			System.out.println(intersection.toString() +" BOTTOM");
		}
		if(Math.abs(this.x-intersection.getX())< Math.abs(this.x+width - intersection.getX())){
			System.out.println(intersection.toString()+ " LEFT");
		}else{
			System.out.println(intersection.toString() +" RIGHT");
		}
	}
	
	public void setColor(Color colour){
		this.colour = colour;
	}
	
	public Color getColor(){
		return this.colour;
	}
	
	@Override
	/**
	 * Overriden to print the blocks dimensions
	 */
	public String toString(){
		return "x: " + this.x+ " y: " + this.y + " height: "+this.height +" width: " + this.width;
	}
}
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
	 * 
	 * @param g
	 *            Graphics param passed from the frame
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
	public void move() {
		int noise = 5;
		int noiseX = random.nextInt(noise) - (noise/2);
		int noiseY = random.nextInt(noise) - (noise/2);

//		this.x += noiseX;
//		this.y += noiseY;
		
		this.y -= 5;
		
		boundaryCheck(this.x, this.y);

	}

	/**
	 * Checks if the block is in overlapping any other blocks. If so the colour
	 * will change for the block
	 * 
	 * @param blocks
	 *            List of all blocks
	 */
	public void inContact(List<Block> blocks) {
		boolean hasContact = false;
		for (Block block : blocks) {
			if (this != block && this.intersects(block)) {
				hasContact = true;
				// Uncomment when not testing
				 this.setColor(Color.BLUE);

				// block.setColor(Color.BLUE);

				Rectangle intersectionBlock = (Rectangle) this.intersection(block);
				intersectionCoverage(intersectionBlock);
				// System.out.println(intersectionBlock);
			}
		}
		if (!hasContact) {
			// Uncomment when not testing
			 this.setColor(Color.BLACK);
		}
	}

	/**
	 * Identifies which part of the block is covered the most
	 * 
	 * @param intersection
	 */
	public void intersectionCoverage(Rectangle intersection) {
		String generalPos = "";
		if (Math.abs(this.y - intersection.getY()) < Math.abs(this.y + height - intersection.getY())) {
//			System.out.println(this + " TOP");
			generalPos += "T";
		} else {
//			System.out.println(this + " BOTTOM");
			generalPos += "B";
		}
		if (Math.abs(this.x - intersection.getX()) < Math.abs(this.x + width - intersection.getX())) {
//			System.out.println(this + " LEFT");
			generalPos += "L";
		} else {
//			System.out.println(this + " RIGHT");
			generalPos += "R";
		}
		moveAway(generalPos);
	}

	public void moveAway(String interPos){
		int xMove = 0;
		int yMove = 0;
		int noise = 5;
		int ranPos = random.nextInt(noise)+1;
		int ranNeg = random.nextInt(noise)-noise;
		switch(interPos){
		// TL = intersection block at top left area of this block so need to move down and right	
		case("TL"):
				xMove = ranPos;
				yMove = ranPos;;
				break;
			case("TR"):
				xMove = ranNeg;
				yMove = ranPos;
				break;
			case("BL"):
				xMove = ranPos;
				yMove = ranNeg;
				break;
			case("BR"):
				xMove = ranNeg;
				yMove = ranNeg;
				break;
			default:
				System.err.println("Invalid position description given");
		}
		this.x += xMove;
		this.y += yMove;
		boundaryCheck(this.x, this.y);
	}
	
	public void boundaryCheck(int x, int y){
		int boundaryOffset = 100;
		if(x<=0){
			this.x = boundaryOffset;
		}else if(x+this.width >= Frame.FRAMESIZE){
			this.x -= boundaryOffset;
		// Need a way to calc the offset from the frame
		}else if(y - 35 <= 0){
			this.y = boundaryOffset;
		}else if(y+ this.height >= Frame.FRAMESIZE){
			this.y -= boundaryOffset;
		}
	}

	public void setColor(Color colour) {
		this.colour = colour;
	}

	public Color getColor() {
		return this.colour;
	}

	@Override
	/**
	 * Overriden to print the blocks dimensions
	 */
	public String toString() {
		return "x: " + this.x + " y: " + this.y + " height: " + this.height + " width: " + this.width;
	}
}
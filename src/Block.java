import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.w3c.dom.css.Rect;

public class Block extends Rectangle {
	private Random random = new Random();
	private Color colour;

	protected Block(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.colour = Color.BLACK;
	}

	/**
	 * Colours a block
	 * 
	 * @param g
	 *            Graphics param passed from the frame
	 */
	public void draw(Graphics g) {
		g.setColor(colour);
		g.fillRect(this.x, this.y, this.width, this.height);
	}

	/**
	 * Random movement via gaussian 
	 */
	protected void move() {
		int noise = 8;
//		int noiseX = random.nextInt(noise) - (noise/2);
//		int noiseY = random.nextInt(noise) - (noise/2);
		
		double noiseX = random.nextGaussian()* noise + 0.1 ;
		double noiseY = random.nextGaussian()* noise + 0.1 ;
		this.x += noiseX;
		this.y += noiseY;
		boundaryCheck(this.x, this.y);

	}

	/**
	 * Checks if the block is in overlapping any other blocks. If so the colour
	 * will change for the block
	 * 
	 * @param blocks
	 *            List of all blocks
	 */
	protected void inContact(HashSet<Block> blocks) {
		boolean hasContact = false;
		for (Block block : blocks) {
			if (this != block && this.intersects(block)) {
				hasContact = true;
				// Uncomment when not testing
				 this.setColor(Color.BLUE);

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
	 * Identifies which part of the block is covered the most and moves the block accordingly
	 * 
	 * @param intersection
	 */
	protected void intersectionCoverage(Rectangle intersection) {
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

	/**
	 * Moves blocks in the opposite position from the given parameter
	 * @param interPos General position (corner) of the intersecting block
	 */
	protected void moveAway(String interPos){
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
	
	/**
	 * Will check if the block is on any of the containers edges, and move the block away if so
	 * @param x position of the block
	 * @param y position of the block
	 */
	protected void boundaryCheck(double x, double y){
		int boundaryOffset = 20;
		if(x<=0){
			this.x = boundaryOffset;
		}else if(x+this.width >= MainPanel.getInstance().getWidth()){
			this.x -= boundaryOffset;
		}else if(y <= 0){
			this.y = boundaryOffset;
		}else if(y+ this.height >= MainPanel.getInstance().getWidth()){
			this.y -= boundaryOffset;
		}
	}

	protected void setColor(Color colour) {
		this.colour = colour;
	}

	protected Color getColor() {
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
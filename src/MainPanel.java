import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static final int BLOCK_QUANTITY = 10;

	private static MainPanel instance = null;
	private HashSet<Block> blockList;
	

	
	private MainPanel(){
		// Setting fixed size of panel 
		setMinimumSize(new Dimension(WIDTH , HEIGHT ));
		setMaximumSize(new Dimension(WIDTH , HEIGHT ));
		setPreferredSize(new Dimension(WIDTH , HEIGHT ));
		
		blockList = generateBlocks(BLOCK_QUANTITY);
		
		CirclePlot circle = CirclePlot.getInstance();
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				for(Block block : blockList){
					block.findClosestPoint(circle);
					block.moveToClosestPoint();
				}
	         } 
		});
	}
	
	public static MainPanel getInstance(){
		if(instance == null){
			return new MainPanel();
		}else{
			return instance;
		}
	}
	
	/**
	 * Creates list of blocks of random sizes and positions with the given quantity
	 * @param quantity Total number of blocks that want to be generated
	 * @return
	 */
	protected HashSet<Block> generateBlocks(int quantity){
		
		Random random = new Random();
		HashSet<Block> blocksList = new HashSet<Block>();
//		Block b1 = new Block(300,300, 50, 50);
//		Block b2 = new Block(100, 100, 30, 30);
//		blocksList.add(b1);
//		blocksList.add(b2);
//		b1.setColor(Color.BLUE);
//		b2.setColor(Color.GREEN);
		
		for(int i=0; i< quantity; i++){
			int width = random.nextInt(15)+10;
			int height = random.nextInt(15)+10;
			int ranX = random.nextInt(320);
			int ranY = random.nextInt(320);
			Block b = new Block(ranX, ranY, width, height);
			blocksList.add(b);
		}
		return blocksList;
	}
	
	/**
	 * Will move all the blocks randomly, sort out intersecting blocks and then paint all the blocks 
	 */
	public void paint(Graphics g){
		super.paint(g);
		for(Block block : this.blockList){
			block.move();
//			block.inContact(blockList);
			block.draw(g);
		}
		try {
			// Slower motions for the viewers eyes
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}
	
	
	
}

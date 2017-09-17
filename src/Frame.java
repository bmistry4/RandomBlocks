import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {
	private List<Block> blockList;
	protected static final int FRAMESIZE = 400;
	
	public Frame(){
		super("Random Blocks Frame");
	}
	
	public void init(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		blockList = generateBlocks(100);
		
		JPanel contentPane = new JPanel();
		this.setContentPane(contentPane);
		this.setSize(FRAMESIZE,FRAMESIZE);
		this.setVisible(true);
		
		while(true){
			repaint();
		}
	}
	
	/**
	 * Creates list of blocks of random sizes and positions with the given quantity
	 * @param quantity Total number of blocks that want to be generated
	 * @return
	 */
	public List<Block> generateBlocks(int quantity){
		
		Random random = new Random();
		List<Block> blocksList = new ArrayList<Block>();
		
		Block b1 = new Block(100, 100, 50, 50);
//		Block b2 = new Block(100, 100, 30, 30);
		blocksList.add(b1);
//		blocksList.add(b2);
		b1.setColor(Color.BLUE);
//		b2.setColor(Color.GREEN);
		
//		for(int i=0; i< quantity; i++){
//			int width = 20;
//			int height = 20;
//			int ranX = random.nextInt(300)+50;
//			int ranY = random.nextInt(300)+50;
//			Block b = new Block(ranX, ranY, width, height);
//			blocksList.add(b);
//		}
		return blocksList;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		
		for(Block block : this.blockList){
			block.move();
//			block.inContact(blockList);
			block.draw(g);
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

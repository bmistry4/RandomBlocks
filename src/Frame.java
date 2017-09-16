import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {
	private List<Block> blockList;
	
	public Frame(){
		super("Random Blocks Frame");
	}
	
	public void init(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		blockList = generateBlocks(50);
		
		JPanel contentPane = new JPanel();
		this.setContentPane(contentPane);
		repaint();
		this.setSize(400,400);
		this.setVisible(true);
		
		while(true){
			repaint();
		}
	}
	
	public List<Block> generateBlocks(int quantity){
		Random random = new Random();
		List<Block> blocksList = new ArrayList<Block>();
		for(int i=0; i< quantity; i++){
			int width = 20;
			int height = 20;
			int ranX = random.nextInt(300)+50;
			int ranY = random.nextInt(300)+50;
			Block b = new Block(ranX, ranY, width, height);
			blocksList.add(b);
		}
		return blocksList;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		for(Block block : this.blockList){
			block.move();
			block.inContact(blockList);
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

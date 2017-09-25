import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Singleton class
 *
 */
public class Frame extends JFrame {

	private static Frame instance = null;
	private JPanel mainPanel;

	private Frame() {
		super("Random Blocks Frame");
		
	}

	public static Frame getInstance() {
		if (instance == null) {
			return new Frame();
		} else {
			return instance;
		}
	}

	protected void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = MainPanel.getInstance();
		this.setContentPane(mainPanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		// Constantly redraw all the blocks
		while (true) {
			mainPanel.repaint();
		}
	}
	
	protected MainPanel getMainPanel(){
		return (MainPanel) mainPanel;
	}

}

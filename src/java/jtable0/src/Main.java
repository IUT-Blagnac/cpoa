import gui.view.SimpleFrame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class Main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SimpleFrame slf = new SimpleFrame();
				slf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				slf.setVisible(true);
			}
		});
	
	}
}

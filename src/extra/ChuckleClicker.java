package extra;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChuckleClicker {

	public static void main(String[] args) {
		makeButtons();
	}

	static void makeButtons() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		JButton button1 = new JButton();
		button1.setText("Joke");
		panel.add(button1);
		
		JButton button2 = new JButton();
		button2.setText("Punchline");
		panel.add(button2);
		
		frame.pack();
	}
}

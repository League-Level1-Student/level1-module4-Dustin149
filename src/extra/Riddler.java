package extra;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Riddler {
	String answer = "";
	
	private JFrame frame;
	private JLabel label;
	private JButton hintBT;
	private JButton submitBT;
	private JTextField textField;
	
	public void createUI() {
		frame = new JFrame();
		label = new JLabel();
		hintBT = new JButton();
		submitBT = new JButton();
		textField = new JTextField();
		
		frame.add(label);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

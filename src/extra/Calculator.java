package extra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Calculator implements ActionListener {

	private JButton addBt;
	private JButton subtractBt;
	private JButton multiplyBt;
	private JButton divideBt;
	private JLabel resultLabel;
	
	public void makeUI() {
		JFrame frame = new JFrame();
		frame.setTitle("Calculator");
		frame.setSize(500, 500);
		
		addBt = new JButton();
		addBt.addActionListener(this);

		subtractBt = new JButton();
		subtractBt.addActionListener(this);

		multiplyBt = new JButton();
		multiplyBt.addActionListener(this);
		
		divideBt = new JButton();
		divideBt.addActionListener(this);
		
		resultLabel = new JLabel();
		
		frame.add(addBt);
		frame.add(subtractBt);
		frame.add(multiplyBt);
		frame.add(divideBt);
		frame.add(resultLabel);
		
		frame.setVisible(true);
		frame.pack();
	}
	
	public static void main(String[] args) {
		new Calculator().makeUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBt) {
			
		}
	}

}

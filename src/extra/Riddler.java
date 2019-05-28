package extra;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Riddler implements MouseListener {
	String riddle = "Can an American speak American, and an Asian speak Asian?";
	String answer = "Yes";
	String hint = "It doesn't always have to refer to langauge. *cough, cough*-accents";
	
	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private JButton hintBT;
	private JButton submitBT;
	private JTextField textField;
	
	public void createUI() {
		frame = new JFrame();
		frame.setVisible(true);
		panel = new JPanel();
		label = new JLabel();
		label.setText(riddle);
		hintBT = new JButton();
		hintBT.setText("Get Hint");
		submitBT = new JButton();
		submitBT.setText("Check Answer");
		textField = new JTextField();
		textField.setText("[Enter Answer Here]");
		
		hintBT.addMouseListener(this);
		submitBT.addMouseListener(this);
		
		frame.add(panel);
		panel.add(label);
		panel.add(hintBT);
		panel.add(submitBT);
		panel.add(textField);
		
		frame.pack();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Riddler riddler = new Riddler();
		riddler.createUI();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == hintBT) {
			JOptionPane.showMessageDialog(null,hint);
		} else if (e.getSource() == submitBT) {
			String UserAnswer = textField.getText();
			if (UserAnswer.equalsIgnoreCase(answer)) {
				JOptionPane.showMessageDialog(null, "Correct!");
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect!");
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

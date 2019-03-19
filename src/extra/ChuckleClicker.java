package extra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChuckleClicker implements ActionListener {
	
	private static JButton jokeBt;
	private static JButton punchlineBt;
	
	public void makeButtons() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		JPanel panel = new JPanel();
		frame.add(panel);
		
		jokeBt = new JButton();
		jokeBt.setText("Joke");
		jokeBt.addActionListener(this);
		panel.add(jokeBt);
		
		punchlineBt = new JButton();
		punchlineBt.setText("Punchline");
		punchlineBt.addActionListener(this);
		panel.add(punchlineBt);
		
		frame.pack();
	}
	
	public static void main(String[] args) {
		new ChuckleClicker().makeButtons();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jokeBt) {
			JOptionPane.showMessageDialog(null, "[Insert Joke Here]");
		} else if(e.getSource() == punchlineBt) {
			JOptionPane.showMessageDialog(null, "[Insert Punchline Here]");
		}
	}
}

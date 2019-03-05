package extra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JButton addBt;
	private JButton subtractBt;
	private JButton multiplyBt;
	private JButton divideBt;
	private JTextField num1Field;
	private JTextField num2Field;
	private JLabel operationLabel;
	private JLabel resultLabel;
	private int SizeX = 40;
	private int SizeY = SizeX/2;
	
	public void makeUI() {
		frame = new JFrame();
		frame.setTitle("Calculator");
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setSize(500, 500);
		panel.setVisible(true);
		frame.add(panel);
		
		addBt = new JButton();
		addBt.setSize(SizeX, SizeY);
		addBt.setText("+");
		addBt.addActionListener(this);
		panel.add(addBt);

		subtractBt = new JButton();
		subtractBt.setSize(SizeX, SizeY);
		subtractBt.setText("-");
		subtractBt.addActionListener(this);
		panel.add(subtractBt);

		multiplyBt = new JButton();
		multiplyBt.setSize(SizeX, SizeY);
		multiplyBt.setText("*");
		multiplyBt.addActionListener(this);
		panel.add(multiplyBt);
		
		divideBt = new JButton();
		divideBt.setSize(SizeX, SizeY);
		divideBt.setText("/");
		divideBt.addActionListener(this);
		panel.add(divideBt);
		
		num1Field = new JTextField();
		num1Field.setSize(SizeX*2, SizeY);
		num1Field.setText("1");
		panel.add(num1Field);
		
		operationLabel = new JLabel();
		operationLabel.setSize(SizeX, SizeY*2);
		operationLabel.setText("");
		panel.add(operationLabel);
		
		num2Field = new JTextField();
		num2Field.setSize(SizeX*2, SizeY);
		num2Field.setText("2");
		panel.add(num2Field);
		
		resultLabel = new JLabel();
		resultLabel.setSize(SizeX, SizeY*2);
		resultLabel.setText("");
		panel.add(resultLabel);
		
		frame.pack();
	}
	
	public static void main(String[] args) {
		new Calculator().makeUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		float num1 = Integer.parseInt(num1Field.getText());
		float num2 = Integer.parseInt(num2Field.getText());
		float answer = 0;
		String operation = "";
		
		if (e.getSource() == addBt) {
			answer = num1+num2;
			operation = " + ";
		} else if (e.getSource() == multiplyBt) {
			answer = num1*num2;
			operation = " * ";
		} else if (e.getSource() == subtractBt) {
			answer = num1-num2;
			operation = " - ";
		} else if (e.getSource() == divideBt) {
			answer = num1/num2;
			operation = " / ";
		}

		operationLabel.setText(operation);
		resultLabel.setText("= "+String.valueOf(answer));
	}

}

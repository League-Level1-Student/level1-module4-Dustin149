import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PigLatinTranslator implements MouseListener, KeyListener {
	
	private JFrame Frame;
	private JPanel Panel;
	private JButton EnterBT;
	private TextField BeforeField;
	private TextField AfterField;

    /*
    * Method to test whether a character is a letter or not.
    * @param c The character to test
    * @return True if it's a letter
    */
	
    private static boolean isLetter(char c) {
         return ( (c >= 'A' && c <= 'Z') || (c >='a' && c <= 'z') );
    }

    /*
    * Method to translate one word into pig latin.
    * @param word The word in english
    * @return The pig latin version
    */
    private static String pigWord(String word) {
         int split = firstVowel(word);
         return word.substring(split)+""+word.substring(0, split)+"ay";
    }

    /*
    * Method to translate a sentence word by word.
    * @param s The sentence in English
    * @return The pig latin version
    */
    public String translate(String s) {
         String latin = "";
         int i = 0;
         while (i < s.length()) {

    // Take care of punctuation and spaces
         while (i < s.length() && !isLetter(s.charAt(i))) {
              latin = latin + s.charAt(i);
              i++;
         }

    // If there aren't any words left, stop.
         if (i>=s.length()) break;

    // Otherwise we're at the beginning of a word.
         int begin = i;
         while (i < s.length() && isLetter(s.charAt(i))) {
              i++;
         }
    // Now we're at the end of a word, so translate it.
         int end = i;
         latin = latin + pigWord(s.substring(begin, end));
         }
         return latin;
    }
    
    public String unTranslate(String s) {
    	return("");
    }

    /*
    * Method to find the index of the first vowel in a word.
    * @param word The word to search
    * @return The index of the first vowel
    */
    
     private static int firstVowel(String word) {
         word = word.toLowerCase();
         for (int i=0; i < word.length(); i++)
              if (word.charAt(i)=='a' || word.charAt(i)=='e' ||
                   word.charAt(i)=='i' || word.charAt(i)=='o' ||
                   word.charAt(i)=='u')
                   return i;
              return 0;
    }
     
    private void createUI() {
    	Frame = new JFrame();
    	Panel = new JPanel();
    	EnterBT = new JButton();
    	EnterBT.addMouseListener(this);
    	EnterBT.setText("Translate");
    	BeforeField = new TextField();
    	BeforeField.setText("[English]");
    	BeforeField.addMouseListener(this);
    	BeforeField.addKeyListener(this);
    	AfterField = new TextField();
    	AfterField.addMouseListener(this);
    	AfterField.setText("[Pig Latin]");
    	
    	Frame.setVisible(true);
    	Frame.add(Panel);
    	Panel.add(EnterBT);
    	Panel.add(BeforeField);
    	Panel.add(AfterField);
    	
    	Frame.pack();
    }
    
    private void showTranslation() {
    	if(meetsRequirements()) {
    		String beforeText = BeforeField.getText().toLowerCase();
        	String afterText = AfterField.getText();
        	
    		if(afterText.equals("") || afterText.equalsIgnoreCase("[Pig Latin]")) {
    			AfterField.setText(translate(beforeText));
    		} else if(beforeText.equals("")) {
    			BeforeField.setText(unTranslate(afterText));
    		} else {
    			AfterField.setText(translate(beforeText));
    		}
    	}
    }
    
    private boolean meetsRequirements() {
    	String beforeText = BeforeField.getText();
    	String afterText = AfterField.getText();
    	
    	if(beforeText.length()<=0 && afterText.length()<=0) {
    		JOptionPane.showMessageDialog(null, "Please input the desired values");
    		return(false);
    	}
    	
    	return(true);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PigLatinTranslator Translator = new PigLatinTranslator();
		Translator.createUI();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==BeforeField) {
			BeforeField.setText("");
		} else if(e.getSource()==AfterField) {
			AfterField.setText("");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==EnterBT) {
			showTranslation();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==10) {
			showTranslation();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

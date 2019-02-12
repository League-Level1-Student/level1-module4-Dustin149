
/*
 *    Copyright (c) The League of Amazing Programmers 2013-2018
 *    Level 1
 */

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javafx.scene.input.KeyCode;

import java.io.IOException;

public class SimonSays extends KeyAdapter {

	// Complete steps 1 - 7 before you test
	// 1. Declare a JFrame variable
	JFrame Frame = new JFrame();

	HashMap<Integer, String> images = new HashMap<Integer, String>();
	private int imageIndex;
	private int tries = 0;
	private boolean simonSays = false;
	private int Points = 0;
	Date timeAtStart;

	private void makeAlbum() {
		// 2. Add the four images that match keyboard keys like this:
		images.put(new Integer(KeyEvent.VK_UP), "up.jpg");
		images.put(new Integer(KeyEvent.VK_DOWN), "down.jpg");
		images.put(new Integer(KeyEvent.VK_LEFT), "left.jpg");
		images.put(new Integer(KeyEvent.VK_RIGHT), "right.jpg");

		// 3. Use a JOptionPane to tell the user the rules: "Press the matching key when
		JOptionPane.showMessageDialog(null,
				"Press the matching key when 'Simon Says.' Otherwise, press a different key.");
		// 'Simon says' otherwise press a different key"

		// 4. Call the showImage method to show an image
		showImage();
	}

	public void keyPressed(KeyEvent e) {
		// 15. Make a points variable to track the score.

		// 16. If the keyCode matches the imageIndex and "Simon says"
		if (e.getKeyCode() == imageIndex && simonSays == true) {

			// 17. Increase the value of score
			Points = Points + 1;
			// 18. Use the speak method to tell the user they were correct
			speak("Correct!");
			// 19. If the keyCode doesn't match the imageIndex and "Simon didn't say..."
		} else if (e.getKeyCode() == imageIndex && simonSays == false) {
			// 20. Increase the value of score
			Points = Points + 1;
			// 21. Use the speak method to tell the user they were correct
		}
		// 22. Increment tries by 1
		tries = tries + 1;
		// 25. If tries is greater than 9 (or however many you want)...
		if (tries > 9) {
			// 26. Tell the user their score
			JOptionPane.showMessageDialog(null, "Your Score: " + Points);
			// 27. Exit the program
		} else {
			// 23. Dispose of the frame
			Frame.dispose();
			// 24. Call the showImage method to show a new image
			showImage();
		}
	}

	private void showImage() {
		// 5. Initialize your frame to a new JFrame()
		Frame = new JFrame();
		// 6. Set the frame to visible
		Frame.setVisible(true);
		// 7. Uncomment the following line to add a random image to your frame
		Frame.add(getNextRandomImage());
		// 8. Set the name of your frame
		Frame.setName("Simon Says");
		// 9. Pack the frame
		Frame.pack();
		// 10. Set the defaultCloseOperation of your from to JFrame.EXIT_ON_CLOSE
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 11. Add a key listener to the frame
		Frame.addKeyListener(this);
		// 12. Create a new instance of Random
		Random ran = new Random();
		// 13. Use the Random and the speak method to either say
		// "Simon says press this key" or "Press this key"
		if (ran.nextInt(2) == 0) {
			JOptionPane.showMessageDialog(null, "Simon says press this key");
			simonSays = true;
		} else {
			JOptionPane.showMessageDialog(null, "Press this key");
			simonSays = true;
			// 14. Above, set the value of simonSays to true/false appropriately
		}
	}

	private Component getNextRandomImage() {
		this.imageIndex = new Random().nextInt(4) + 37;
		return loadImage(images.get(imageIndex));
	}

	private JLabel loadImage(String fileName) {
		URL imageURL = getClass().getResource(fileName);
		Icon icon = new ImageIcon(imageURL);
		return new JLabel(icon);
	}

	void speak(String words) {
		System.out.println(words);
		// try {
//			Runtime.getRuntime().exec("say " + words).waitFor();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public static void main(String[] args) throws Exception {
		new SimonSays().makeAlbum();
	}
}

/*
 * BONUS! Add a timer : ~~~ where the code starts running ~~~ timeAtStart =
 * newDate();
 *
 * ~~~ where the code ends ~~~ Date timeAtEnd = new Date();
 * System.out.println((timeAtEnd.getTime()-timeAtStart.getTime())/1000);
 * System.exit(0);
 */

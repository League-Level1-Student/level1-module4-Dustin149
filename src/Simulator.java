
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Simulator implements MouseListener, KeyListener {
	
	String[] Directions = {"Up","Down","Forward","Backward","Right","Left"};
	String[] Biomes = {"Stone","Sand","Dirt","Water"};
	String[] Statuses = {"On Land","Underwater","In Cave"};
	String[] Events = {"Idle","Drowning","Burning","Falling",};
	String[] Conditions = {"Healthy","Starving","Poisoned","Tired"};
	String[] Actions = {"Idle","Walking","Jumping","Swimming"};
	String[] Sounds = {"/Level1-Module4/src/Walking On Gravel-SoundBible.com-2023303198.wav"};
	
	private String Name = "Steve";
	private int Age = 0; // In years
	private int Height = 0; // From the ground
	private String Direction = Directions[2];
	private String Biome = Biomes[2];
	private String Status = Statuses[0];
	private String Event = Events[0];
	private String Condition = Conditions[0];
	private String Action = Actions[0];
	private boolean Active = true;
	private boolean CanAct = true;
	private JFrame Frame;
	private TextField Panel;
	private boolean KeyDown = false;
	private int KeyPressed = 0;

	public Simulator(String name, int age, String biome){
		this.Name = name;
		this.Age = age;
		this.Biome = biome;
		
		this.Frame = new JFrame();
		this.Frame.setTitle("Simulator");
		this.Frame.setVisible(true);
		this.Frame.setSize(50, 50);
		
		this.Panel = new TextField();
		this.Panel.setSize(50, 50);
		this.Panel.setVisible(true);
		this.Panel.setBackground(Color.black);
		this.Panel.addMouseListener(this);
		this.Panel.addKeyListener(this);
		
		this.Frame.add(this.Panel);
		this.Frame.pack();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Simulator sim = new Simulator("Steve",0,"Dirt");
	}
	
	public boolean ChangedDirection(String NewDirection) {
		if(NewDirection.equalsIgnoreCase(this.Direction)) {
			this.Direction = NewDirection;
			return(false);
		}
		return(true);
	}
	
	public void SetAction() {
		String action = this.Action;
		
		if(action.equalsIgnoreCase(Actions[1])) { // Walking
			if(!IsFalling()) {
				if(IsInWater()) {
					this.Action = Actions[3]; // Swimming
				} else {
					this.Action = Actions[1]; // Walking
				}
			}
		} else if (action.equalsIgnoreCase(Actions[2])) { // Jumping
			if(!IsFalling()) {
				if(IsInWater()) {
					this.Action = Actions[3]; // Swimming
				} else {
					this.Action = Actions[2]; // Jumping
				}
			}
		} else if (IsInWater()) {
			this.Event = Events[3];
			if(this.Height<=-2) {
				this.Event = Events[1];
			}
		}
		System.out.println("|Perform Action: "+this.Action);
	}
	
	public void Wait(int Seconds) throws InterruptedException {
		Thread.sleep(1000*Seconds);
	}
	
	public void PerformAction() throws InterruptedException {
		if(this.Event.equalsIgnoreCase(Events[1])) { // Drowning
			
		} else if(this.Event.equalsIgnoreCase(Events[2])) { // Burning
			
		} else if(IsFalling() && !IsInWater()) { // Falling
			Wait(1);
			Height -= 1;
			System.out.println("|| Falling");
			
			if(Height == 0) {
				if(IsInWater()) {
					Wait(2);
					System.out.println("Float Down");
					Height -= 1;
				} else {
					System.out.println("Fell Back On Land");
					this.Biome = Biomes[0];
					this.Status = Statuses[0];
					this.Event = Events[0];
				}
			}
		} else if(this.Action == Actions[2]) { // Jumping
			Wait(1);
			Height += 1;
			System.out.println("|| Jumping");
			this.Event = Events[3];
		} else if(this.Action == Actions[1]) { // Walking
			Walk();
		}
	}
	
	public boolean IsInWater() {
		if(this.Biome.equalsIgnoreCase(Biomes[3])) {
			return(true);
		}
		return(false);
	}
	
	public boolean IsFalling() {
		if(this.Event.equalsIgnoreCase(Events[3])) {
			return(true);
		}
		return(false);
	}
	
	public void Walk() {
		System.out.println("|| Walking");
		AudioClip sound = playSound("Walking On Gravel-SoundBible.com-2023303198.wav");
		try {
			Wait(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sound.stop();
	}
	
	public void Swim(String direction) {
		
	}
	
	public boolean IsActive() {
		return(this.Active);
	}

	public void EndSimulation() {
		this.Active = false;
	}
	
	private AudioClip playSound(String fileName) {
		AudioClip sound = JApplet.newAudioClip(getClass().getResource(fileName));
		sound.play();
		return(sound);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(this.CanAct) {
			this.CanAct = false;
			System.out.println("__________________________________CAN'T act!");
			this.KeyPressed = arg0.getKeyCode();
			this.KeyDown = true;
			
			// 87-w, 65-a, 83-s, 68-d, 32-" "-space
			
			if(arg0.getKeyCode()==87 || arg0.getKeyCode()==65 || arg0.getKeyCode()==83 || arg0.getKeyCode()==68) {
				this.Action = Actions[1]; // Walking
				System.out.println("Try walking");
			} else if(arg0.getKeyCode()==32) {
				this.Action = Actions[2]; // Jumping
				this.Direction = Directions[0];
				System.out.println("Try jumping");
			} else {
				this.Action = Actions[0];
			}
			SetAction();
			try {
				PerformAction();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("==================================CAN act!");
			this.CanAct = true;
		} else {
			System.out.println("--[NOTIF]: Please wait!");
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

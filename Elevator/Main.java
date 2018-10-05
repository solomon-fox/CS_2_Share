import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial") 
public class Main extends JFrame implements ActionListener
{
	private final int numElevators = 2, numFloors = 10, numBasements = 3; 
	private ArrayList<Elevator> elevators;
	private ArrayList<ElevatorCanvas> canvases;
	private Font bigFont = new Font("Arial", Font.PLAIN, 40);	//adjust this size if you want
	
	//Only very minor changes to the constructor may be necessary.
	public Main()
	{
		super("Elevator Manager");
		elevators = new ArrayList<>();
		canvases = new ArrayList<>();
		
		for(int i=0; i<numElevators; i++)	//create the specified # of elevators and GUI objects
		{
			ElevatorCanvas c = new ElevatorCanvas(numFloors, numBasements);
			Elevator e = new Elevator(c);
			elevators.add(e);
			canvases.add(c);
		}
		
		setSize(1200, 900);
		setLayout(new GridLayout(1, canvases.size()+1));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(numFloors + numBasements, 1));
		for(int i=numFloors-1; i>=0; i--)
			buttonPanel.add(new JButton(""+i));
		for(int i=-1; i>=numBasements * -1; i--)
			buttonPanel.add(new JButton(""+i));
		
		for(int i=0; i<canvases.size()/2; i++)
			add(canvases.get(i));
		
		for(int i=0; i<buttonPanel.getComponentCount(); i++)
		{
			buttonPanel.getComponent(i).setFont(bigFont);
			((JButton)(buttonPanel.getComponent(i))).addActionListener(this);
		}
		
		add(buttonPanel);
		
		for(int i=canvases.size()/2; i<canvases.size(); i++)
			add(canvases.get(i));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	//The elevator management logic goes here!
	//This method runs automatically when a button is clicked.
	//The parameter "e" contains information about which button was clicked;
	//	the int "targetFloor" reads the text on the button to figure out 
	//	which floor the user clicked.
	@Override public void actionPerformed(ActionEvent e)
	{
		int targetFloor = Integer.parseInt(((JButton)(e.getSource())).getText());
		
		//Assign the target floor to an elevator here!
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class MazeNavigator extends JFrame implements ActionListener
{
	private final int SCALE_FACTOR = 2, DEF_WIDTH = 700*SCALE_FACTOR, DEF_HEIGHT = 700*SCALE_FACTOR, 
			DEF_TILESIZE = 10*SCALE_FACTOR, NUM_POINTS = 20;
	private RandomMazeGenerator rmg;
	private final Font DEF_FONT = new Font("Arial", Font.PLAIN, 14*SCALE_FACTOR);
	private NavigatorSolver solver;
	private JButton planButton;
	private JLabel messageLabel;
	private JTextField distanceField;
	private JComboBox sourceBox, destBox;
	
	public MazeNavigator()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEF_WIDTH+400, DEF_HEIGHT + 23);
		setLocationRelativeTo(null);
		rmg = new RandomMazeGenerator(DEF_WIDTH, NUM_POINTS, DEF_TILESIZE);
//***	solver = new ... ; //*** YOUR CLASS NAME HERE ***
		//solver = new SolverAStar();
		solver = new Test();
		
		JPanel uiPanel = new JPanel();
		uiPanel.setLayout(new GridLayout(8, 1));
		setLayout(new BorderLayout());/*
		uiPanel.setPreferredSize(new Dimension(getWidth()-rmg.getWidth()+20, getHeight()+20));
		uiPanel.setMinimumSize(new Dimension(getWidth()-rmg.getWidth()+20, getHeight()+20));*/
		uiPanel.setPreferredSize(new Dimension(400, getHeight()));
		rmg.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
		setupUIPanel(uiPanel);
		
		add(rmg, BorderLayout.CENTER);
		add(uiPanel, BorderLayout.WEST);		
		setVisible(true);
	}
	
	private void setupUIPanel(JPanel panel)
	{
		String[] labels = new String[NUM_POINTS];
		for(int i=0; i<NUM_POINTS; i++)
			labels[i] = ""+(char)(65+i);
		sourceBox = new JComboBox(labels);
		sourceBox.setFont(DEF_FONT);
		destBox = new JComboBox(labels);
		destBox.setFont(DEF_FONT);
		planButton = new JButton("Begin Route");
		planButton.setFont(DEF_FONT);
		planButton.addActionListener(this);
		messageLabel = new JLabel("Status: Initialized", SwingConstants.CENTER);
		messageLabel.setFont(DEF_FONT);
		messageLabel.setForeground(Color.BLUE);
		distanceField = new JTextField();
		distanceField.setFont(DEF_FONT);
		panel.add(messageLabel);
		JLabel temp = new JLabel("Source:");
		temp.setFont(DEF_FONT);
		panel.add(temp);
		panel.add(sourceBox);
		panel.add(temp = new JLabel("Destination:"));
		temp.setFont(DEF_FONT);
		panel.add(temp);
		panel.add(destBox);
		panel.add(temp = new JLabel("Max Distance:"));
		temp.setFont(DEF_FONT);
		panel.add(temp);
		panel.add(distanceField);
		panel.add(planButton);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		try 
		{
		if(e.getSource() == planButton)
		{
			ArrayList<Point> allPoints = rmg.getMajorPoints();
			rmg.applySolution(solver.generateSolution(rmg.getMaze(),
					allPoints.get(sourceBox.getSelectedIndex()), allPoints.get(destBox.getSelectedIndex())));
			messageLabel.setText(solver.getMessage());
			rmg.repaint();
		}
		}
		catch(NullPointerException e2)
		{
			e2.printStackTrace();
			messageLabel.setText("No instance of NavigatorSolver!");
		}
	}
	
	public static void main(String[] args)
	{
		new MazeNavigator();
	}
}

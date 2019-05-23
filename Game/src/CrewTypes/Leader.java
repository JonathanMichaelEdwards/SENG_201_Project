package CrewTypes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import SetUpScreens.CrewSelection;
import WindowSettings.Display;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Leader 
{

	public JFrame frame;

	
	private void btnBack()
	{
		JButton btnBackToCrew = new JButton("Back to Crew Selection");
		btnBackToCrew.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// Setting a new frame
				CrewSelection select = new CrewSelection();
				select.frame.setVisible(true);    // turn on screen
				frame.setVisible(false);          // turn off screen
			}
		});
		btnBackToCrew.setBounds(424, 658, 317, 96);
		frame.getContentPane().add(btnBackToCrew);
	}
	
	
	/*
	 * Initialize the contents of the frame.
	*/
	private void initialize() 
	{
		// Setting Layout dimensions
		frame = new JFrame();
		Display display = new Display();  // Retrieving game window size
		
		// Setting frame of window
		frame.setBounds(display.x, display.y, display.width, display.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
//		frame.setUndecorated(true);  // Frame cannot be adjusted during game
		
		JLabel lblAbility = new JLabel("<html>Leader: \r\n" + 
				"ABILITY: The leader never gets hungry </html>");
		lblAbility.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAbility.setBounds(202, 150, 448, 109);
		frame.getContentPane().add(lblAbility);
		
		JLabel lblDesc = new JLabel("<html>Leader: \r\n" + 
				"Got a problem? These guys will break it down and solve it. Your crew needs someone to lead them when you are at the store. These leaders have been bred to work tirelessly throughout the day and night. They have made small biological advancements so they never need food. That means more food for the rest of the crew.</html>");
		lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDesc.setBounds(69, 248, 711, 195);
		frame.getContentPane().add(lblDesc);
		
		JLabel lblLeader = new JLabel("Leader");
		lblLeader.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblLeader.setBounds(333, 45, 180, 88);
		frame.getContentPane().add(lblLeader);
		
		
		// go back button
		btnBack();
	}
	
	
	/*
	 * Create the application.
	*/
	public Leader() 
	{
		initialize();
	}
	
	
	/*
	 * Launch the application.
	*/
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try {
					Leader window = new Leader();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

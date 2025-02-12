package MainScreen;

// Library imports
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JPanel;

//Self implemented
import WindowSettings.Display;
import SpaceOutpost.SpaceOutpost;
import IOFile.IOFile;
import Inventory.ShipInventory;
import NewPlanet.CrewTravel;
import RepairShields.CrewRepair;
import SearchPlanet.CrewPlanet;
import Sleep.CrewSleep;
import MainScreen.loseGame;
import RandomEvents.asteroids;
import RandomEvents.alienPirates;
import RandomEvents.spacePlague;
import javax.swing.SwingConstants;



public class MainScreen 
{
	public JFrame frmEliteDangerousBeta;
	
	// setting output labels
	private JLabel lblDaysLeft, lblParts;
	private JLabel lblShipType, lblShipName;
	private JLabel lblMember1, lblMember2, lblMember3, lblMember4;
	private JLabel lblCrewType1, lblCrewType2, lblCrewType3, lblCrewType4;

	// Setting action buttons to be scoped globally
	private JButton btnSpaceOutpost;
	private JButton btnNewPlanet;
	private JButton btnRepairShields;

	// Setting progress bars
	private JProgressBar pBarHealth1, pBarHealth2, pBarHealth3, pBarHealth4;
	private JProgressBar pBarTiredness1, pBarTiredness2, pBarTiredness3, pBarTiredness4;
	private JProgressBar pBarHunger1, pBarHunger2, pBarHunger3, pBarHunger4;
	private JProgressBar pBarShipHealth;
	private JLabel player1, player2, player3, player4;
	private JLabel lblCashTotal, setPlanet;
	
	private ArrayList<String> memberOne, memberTwo;
	// Stored information that the user has chosen
	private ArrayList<String> crewType = new ArrayList<String>();
	private ArrayList<String> crewName = new ArrayList<String>();
	private ArrayList<String> changeDays = new ArrayList<String>();
	private ArrayList<String> memberActions1 = new ArrayList<String>();
	private ArrayList<String> memberActions2 = new ArrayList<String>();
	private ArrayList<String> memberActions3 = new ArrayList<String>();
	private ArrayList<String> memberActions4 = new ArrayList<String>();
	
	// Call stored information
	private ArrayList<String> crewInfo, shipInfo, daysInfo, changeShields, totalCash;
	
	private String shipType;
	private String shipName, dayCount;
	private int days;
	private int parts;
	private int repair;
	private JLabel lblptr;
	
	// File locations
	private String writeHealth = "src/StoreGame/CrewSelected/";
	
	// stores the selection type
	private JLabel type[] = new JLabel[4];
	private JLabel member[] = new JLabel[4];
	
	
	private JProgressBar health[] = new JProgressBar[4];
	private JProgressBar tiredness[] = new JProgressBar[4];
	private JProgressBar hunger[] = new JProgressBar [4];
	private JButton btnSleep;
	private JLabel lblPlague;
	

	private String readFile = "StoreGame/CrewSelected/";	
	private JButton btnAbandonShip;
	private JLabel lblHello;

	
	// Store all progress bar so it can be used easily
	// Stores crew details in data arrays so it can be used easily
	private void pBarHealth() 
	{
		health[0] = pBarHealth1;
		health[1] = pBarHealth2;
		health[2] = pBarHealth3;
		health[3] = pBarHealth4;
	}
	
	private void pBarTiredness() 
	{
		tiredness[0] = pBarTiredness1;
		tiredness[1] = pBarTiredness2;
		tiredness[2] = pBarTiredness3;
		tiredness[3] = pBarTiredness4;
	}
	
	private void pBarHunger() 
	{
		hunger[0] = pBarHunger1;
		hunger[1] = pBarHunger2;
		hunger[2] = pBarHunger3;
		hunger[3] = pBarHunger4;
	}
	
	
	// Stores crew details in data arrays so it can be used easily
	private void dispCrewType() 
	{
		type[0] = lblCrewType1;
		type[1] = lblCrewType2;
		type[2] = lblCrewType3;
		type[3] = lblCrewType4;
	}
	
	private void dispCrewMember() 
	{
		member[0] = lblMember1;
		member[1] = lblMember2;
		member[2] = lblMember3;
		member[3] = lblMember4;
	}
	

	// helper function to find the correct member and name
	protected void addCrew(ArrayList<String> crewInfo, int size)
	{	
		for (int index = 0; index < size; index ++) {
			crewType.add(crewInfo.get(index));
			crewName.add(crewInfo.get(index+size));
		}
		disableStatus(size);
	}
		
	
	// items to be disabled
	private void disableInfo(int index) 
	{
		type[index].setEnabled(false);
		member[index].setEnabled(false);
		hunger[index].setEnabled(false);
		health[index].setEnabled(false);
		tiredness[index].setEnabled(false);
	}
	
	
	// helper function to disable/(grey out) unused status information areas
	public void disableStatus(int size)
	{
		int lsSize = 4;
		
		// disable/(grey out) unused status information areas
		for (int index = 0; index < lsSize; index++) {
			if ((lsSize-size) == 2 && (index >= 2)) {
				disableInfo(index);
				player3.setEnabled(false);
				player4.setEnabled(false);
			} else if ((lsSize-size) == 1 && (index == 3)) {
				disableInfo(index);
				player4.setEnabled(false);
			}
		}	
	}

	
	// decode crew information to get correct data
	protected void decodeCrewInfo(ArrayList<String> crewInfo)
	{
		// store crew member and there names at the correct index in separate lists
		// size is -1 because an empty value is added on to the end
		if ((crewInfo.size()) == 4) addCrew(crewInfo, 2);
		else if (crewInfo.size() == 6) addCrew(crewInfo, 3);
		else if (crewInfo.size() == 8) addCrew(crewInfo, 4);
	}
	
	
	// Storing and displaying the characters health
	private void memberOne(ArrayList<String> crewMember1, IOFile ioFile)
	{
		crewMember1 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberOne.txt");

		pBarHealth1.setValue(Integer.valueOf(crewMember1.get(0)));
		pBarTiredness1.setValue(Integer.valueOf(crewMember1.get(1)));
		pBarHunger1.setValue(Integer.valueOf(crewMember1.get(2)));
	}
	
	private void memberTwo(ArrayList<String> crewMember2, IOFile ioFile)
	{
		crewMember2 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberTwo.txt");

<<<<<<< Updated upstream
//		}
=======
>>>>>>> Stashed changes
		pBarHealth2.setValue(Integer.valueOf(crewMember2.get(0)));
		pBarTiredness2.setValue(Integer.valueOf(crewMember2.get(1)));
		pBarHunger2.setValue(Integer.valueOf(crewMember2.get(2)));
	}
	

	private void memberThree(ArrayList<String> crewMember3, IOFile ioFile)
	{
		crewMember3 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberThree.txt");

		pBarHealth3.setValue(Integer.valueOf(crewMember3.get(0)));
		pBarTiredness3.setValue(Integer.valueOf(crewMember3.get(1)));
		pBarHunger3.setValue(Integer.valueOf(crewMember3.get(2)));
	}
	
	private void memberFour(ArrayList<String> crewMember4, IOFile ioFile)
	{
		crewMember4 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberFour.txt");
		
<<<<<<< Updated upstream

		
=======
>>>>>>> Stashed changes
		pBarHealth4.setValue(Integer.valueOf(crewMember4.get(0)));
		pBarTiredness4.setValue(Integer.valueOf(crewMember4.get(1)));
		pBarHunger4.setValue(Integer.valueOf(crewMember4.get(2)));
	}
	
	
	protected void readCrewRatings(IOFile ioFile)
	{ 				
		ArrayList<String> crewMember1 = new ArrayList<String>();
		ArrayList<String> crewMember2 = new ArrayList<String>();
		ArrayList<String> crewMember3 = new ArrayList<String>();
		ArrayList<String> crewMember4 = new ArrayList<String>();
	
		// Reading and storing the crew members health rating
		for (int index = 0; index < crewType.size(); index++) {
			// Storing the character types health rating
			if (index == 0) {
				memberOne(crewMember1, ioFile);
			} else if (index == 1) {
				memberTwo(crewMember2, ioFile);
			} else if (index == 2) {
				memberThree(crewMember3, ioFile);
			} else if (index == 3) {
				memberFour(crewMember4, ioFile);
			}
		}
	}
	
	
	protected void bankStore()
	{
		IOFile ioFile = new IOFile();

		// read how much cash the player has
		totalCash = ioFile.fileRead("src/StoreGame/CashInfo.txt");
		lblCashTotal.setText("Current Cash = $ " + totalCash.get(0).toString());
	}
	
	
	/**
	 * Checking whether one or more people have the plague
	 */
	protected boolean checkPlague()
	{
		boolean state = false;
		
		if (memberOne.get(4).equals("true") && memberTwo.get(4).equals("true")) {
			lblPlague.setText(memberOne.get(5) + " and " + memberTwo.get(5) + " both have the plague");
			state = true;
		} else if (memberOne.get(4).equals("true")) {
			lblPlague.setText(memberOne.get(5) + " has the plague");
			state = true;
		} else if (memberTwo.get(4).equals("true")) {
			lblPlague.setText(memberTwo.get(5) + " has the plague");
			state = true;
		} else {
			lblPlague.setText("No body has the plague");
			state = false;
		}
		
		return state;
	}
	
	
	/**
	 * Organizing storage lists
	 */
	protected void organizeGameInfo()
	{
		// gather information stored in file
		IOFile ioFile = new IOFile();
		
		// Reading files
		crewInfo = ioFile.fileRead("src/StoreGame/CrewInfo.txt");
		shipInfo = ioFile.fileRead("src/StoreGame/ShipInfo.txt");
		daysInfo = ioFile.fileRead("src/StoreGame/DaysInfo.txt");
		changeShields = ioFile.fileRead("src/StoreGame/ShipInfo.txt");
		memberOne = ioFile.fileRead("src/StoreGame/CrewSelected/MemberOne.txt");
		memberTwo = ioFile.fileRead("src/StoreGame/CrewSelected/MemberTwo.txt");

		// unwrap the crew information
		decodeCrewInfo(crewInfo);
		
		shipType = shipInfo.get(0);
		shipName = shipInfo.get(1);
		
		days = Integer.parseInt(daysInfo.get(0));
		parts = Integer.parseInt(daysInfo.get(1));
		
		repair = Integer.parseInt(changeShields.get(2));
		
		// Displaying crew info
		lblShipType.setText(shipType);
		lblShipName.setText(shipName);
		lblDaysLeft.setText("Days Remaining: " + days);
		lblParts.setText("Parts to find: " + parts);
		pBarShipHealth.setValue(repair);
		
		// if true, part is found on this planet
		if (changeShields.get(3).equals("true"))
			lblptr.setText("Parts on this planet: " + 0 );
		else 
			lblptr.setText("Parts on this planet: " + 1 );
		
<<<<<<< Updated upstream
		
		if (memberOne.get(4).equals("true") && memberTwo.get(4).equals("true")) 
			lblPlague.setText("<html>" + memberOne.get(5) + " and " + memberTwo.get(5) + " both have the plague</html>");
		else if (memberOne.get(4).equals("true"))
			lblPlague.setText("<html>" + memberOne.get(5) + " has the plague</html>");
		else if (memberTwo.get(4).equals("true")) 
			lblPlague.setText("<html>" + memberTwo.get(5) + " has the plague</html>");
		else 
			lblPlague.setText("No body has the plague");
=======
		checkPlague();
>>>>>>> Stashed changes
		
		// Output crew
		for (int index = 0; index < crewType.size(); index++) {
			type[index].setText(crewType.get(index));
			member[index].setText(crewName.get(index));
		}
		
		// Read and display the crew ratings
		readCrewRatings(ioFile);
		
		displayCrew(ioFile);
	}


	/** 
	 * Display the crew's action count
	 */
	protected void displayCrew(IOFile ioFile)
	{
		ArrayList<String> membersActions = new ArrayList<String>();

		for (int index = 0; index < crewType.size(); index++) {
			if (index == 0) {
				membersActions = ioFile.fileRead("src/StoreGame/CrewSelected/MemberOne.txt");
				player1.setText(membersActions.get(3));
			} else if (index == 1) {
				membersActions = ioFile.fileRead("src/StoreGame/CrewSelected/MemberTwo.txt");
				player2.setText(membersActions.get(3));
			} else if (index == 2) {
				membersActions = ioFile.fileRead("src/StoreGame/CrewSelected/MemberThree.txt");
				player3.setText(membersActions.get(3));
			} else if (index == 3) {
				membersActions = ioFile.fileRead("src/StoreGame/CrewSelected/MemberFour.txt");
				player4.setText(membersActions.get(3));
			}
		}
	}
	
	
	// Go to the space outpost screen
	protected void spaceOutpost()
	{
		btnSpaceOutpost = new JButton("Space Outpost");
		btnSpaceOutpost.setBounds(773, 132, 300, 50);
		btnSpaceOutpost.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnSpaceOutpost.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				SpaceOutpost outpost = new SpaceOutpost();
				outpost.frmEliteDangerousBeta.setVisible(true);  // turn on screen
				frmEliteDangerousBeta.setVisible(false);         // turn off screen
			}
		});
		frmEliteDangerousBeta.getContentPane().add(btnSpaceOutpost);
	}
	
	
	// Go to the newPlanet screen
	protected void newPlanet()
	{
		btnNewPlanet = new JButton("Travel to a new planet");
		btnNewPlanet.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnNewPlanet.setBounds(1555, 215, 300, 50);
		btnNewPlanet.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				CrewTravel screen = new CrewTravel();
				screen.frmEliteDangerousBeta.setVisible(true);    // turn on screen
				frmEliteDangerousBeta.setVisible(false);          // turn off screen
			}
		});
		frmEliteDangerousBeta.getContentPane().add(btnNewPlanet);
	}
	
	
	// Go to the explore planet screen
	protected void explorePlanet()
	{
		JButton btnExplorePlanet = new JButton("Explore current planet");
		btnExplorePlanet.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnExplorePlanet.setBounds(1555, 325, 300, 50);
		btnExplorePlanet.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				CrewPlanet screen = new CrewPlanet();
				screen.frmEliteDangerousBeta.setVisible(true);    // turn on screen
				frmEliteDangerousBeta.setVisible(false);          // turn off screen
			}
		});
		frmEliteDangerousBeta.getContentPane().add(btnExplorePlanet);
	}
	
	
	// repair shields
	protected void repairShields()
	{
	}
	
	
	// Go to sleep screen
	protected void btnSleep()
	{
		
		lblptr = new JLabel("Parts on this planet: ...");
		lblptr.setOpaque(true);
		lblptr.setHorizontalAlignment(SwingConstants.CENTER);
		lblptr.setFont(new Font("Dialog", Font.BOLD, 12));
		lblptr.setBounds(1555, 387, 300, 40);
		frmEliteDangerousBeta.getContentPane().add(lblptr);
		
		lblPlague = new JLabel("... has the plague");
		lblPlague.setOpaque(true);
		lblPlague.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPlague.setBounds(76, 487, 300, 62);
		frmEliteDangerousBeta.getContentPane().add(lblPlague);
		
		btnAbandonShip = new JButton("Abandon Ship");
		btnAbandonShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("GAME OVER");
				loseGame outpost = new loseGame();
				outpost.frame.setVisible(true);
				frmEliteDangerousBeta.setVisible(false);
			}
		});
		btnAbandonShip.setBounds(1314, 959, 208, 62);
		frmEliteDangerousBeta.getContentPane().add(btnAbandonShip);
		btnSleep = new JButton("Sleep");
		btnSleep.setBounds(76, 346, 300, 50);
		frmEliteDangerousBeta.getContentPane().add(btnSleep);
		btnSleep.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				CrewSleep sleep = new CrewSleep();
				sleep.frmEliteDangerousBeta.setVisible(true);     // turn on screen
				frmEliteDangerousBeta.setVisible(false);          // turn off screen
			}
		});
<<<<<<< Updated upstream
		btnSleep.setFont(new Font("Dialog", Font.PLAIN, 19));
		
		IOFile ioFile = new IOFile();
		ArrayList<String> checkDays = new ArrayList<String>();
		checkDays = ioFile.fileRead("src/StoreGame/ShipInfo.txt");
		System.out.println(checkDays);
		dayCount = checkDays.get(4);
		System.out.println(dayCount);
		lblHello = new JLabel("");
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblHello.setIcon(new ImageIcon(CrewRepair.class.getResource("/gameImages/Planet"+ dayCount +".png")));
		lblHello.setBounds(1601, 0, 193, 204);
		frmEliteDangerousBeta.getContentPane().add(lblHello);
				
//		frame.setUndecorated(true);  // Frame cannot be adjusted during game
				
		JLabel labell = new JLabel("");
		labell.setIcon(new ImageIcon(CrewRepair.class.getResource("/gameImages/galaxy.jpg")));
		labell.setHorizontalAlignment(SwingConstants.CENTER);
		labell.setBounds(0, 0, 1920, 1080);
		frmEliteDangerousBeta.getContentPane().add(labell);
		
		JLabel lblDeleteMe = new JLabel("delete me");
		lblDeleteMe.setBounds(84, 582, 66, 15);
		frmEliteDangerousBeta.getContentPane().add(lblDeleteMe);
		

=======
		btnSleep.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnSleep.setBounds(127, 239, 230, 101);
		frame.getContentPane().add(btnSleep);
		
		lblptr = new JLabel("Parts on this planet: ...");
		lblptr.setFont(new Font("Dialog", Font.BOLD, 16));
		lblptr.setBounds(112, 667, 256, 40);
		frame.getContentPane().add(lblptr);
		
		lblPlague = new JLabel("... has the plague");
		lblPlague.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPlague.setBounds(81, 404, 488, 62);
		frame.getContentPane().add(lblPlague);
>>>>>>> Stashed changes
	}
	

	protected void btnInventory()
	{
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ShipInventory sleep = new ShipInventory();
				sleep.frmEliteDangerousBeta.setVisible(true);    // turn on screen
				frmEliteDangerousBeta.setVisible(false);         // turn off screen
			}
		});
		btnInventory.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnInventory.setBounds(76, 417, 300, 50);
		frmEliteDangerousBeta.getContentPane().add(btnInventory);
	}
	
	


	////////////////////////////////////
	// go to the next day
	protected void nextDay()
	{	
		JButton btnNextDay = new JButton("Next Day");
		btnNextDay.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{

				IOFile ioFile = new IOFile();
				
				// Resetting crew action count
				String boolPlague;
				String check;
<<<<<<< Updated upstream
				
=======
>>>>>>> Stashed changes
				
				changeDays = ioFile.fileRead("src/StoreGame/DaysInfo.txt");
				days = Integer.parseInt(changeDays.get(0)) - 1;
				changeDays.set(0, "" + days);
				ioFile.fileWrite(changeDays, "src/StoreGame/DaysInfo.txt");  // Writing in new days
				if (days == -1)
				{
<<<<<<< Updated upstream
					System.out.println("GAME OVER");
=======
>>>>>>> Stashed changes
					loseGame outpost = new loseGame();
					outpost.frame.setVisible(true);
					frmEliteDangerousBeta.setVisible(false);
				}
				if (days != -1)
				{
				//ioFile.fileWrite(changeDays, "src/StoreGame/DaysInfo.txt");  // Writing in new days
				lblDaysLeft.setText("Days Remaining: "  + days);
				
				// reading from file &Changing file & Writing back to file
				for (int index = 0; index < crewType.size(); index++) {
					if (index == 0) {
						memberActions1 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberOne.txt");
						memberActions1.set(3, "2");
						check = memberActions1.get(7);
						if (check.equals("dead"))
						{
							memberActions1.set(3, "Character is Dead");
						}
						if (check.equals("alive"))
						{
						int tired = Integer.parseInt(memberActions1.get(1)) - 20;
						memberActions1.set(1, "" + tired);
						ioFile.fileWrite(memberActions1, writeHealth + "MemberOne.txt");
						memberActions1 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberOne.txt");
						int tiredHp = Integer.parseInt(memberActions1.get(1));
						if (tiredHp <= 0)
						
						{
							memberActions1.set(1, "" + 0);

							ioFile.fileWrite(memberActions1, "src/StoreGame/CrewSelected/MemberOne.txt");
							memberActions1 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberOne.txt");
							int health = Integer.parseInt(memberActions1.get(0)) - 30;
							memberActions1.set(0, "" + health);
							if (health <= 0)
							{
								memberActions1.set(7, "dead");
								memberActions1.set(0, "" + 0);
								memberActions1.set(1, "" + 0);
								memberActions1.set(2, "" + 0);
								memberActions1.set(3, "" + 0);
								
							}
							ioFile.fileWrite(memberActions1, writeHealth + "MemberOne.txt");
						}
						
						boolPlague = memberActions1.get(4);		
						if (boolPlague.equals("true")) {	
							int health = Integer.parseInt(memberActions1.get(0)) - 30;
							memberActions1.set(0, "" + health);
							ioFile.fileWrite(memberActions1, writeHealth + "MemberOne.txt");
						}
						}
						
					} else if (index == 1) {
						memberActions2 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberTwo.txt");
						memberActions2.set(3, "2");
						check = memberActions2.get(7);
						if (check.equals("dead"))
						{
							memberActions2.set(3, "0");
						}
						if (check.equals("alive"))
						{
						int tired = Integer.parseInt(memberActions2.get(1)) - 20;
						memberActions2.set(1, "" + tired);
						ioFile.fileWrite(memberActions2, writeHealth + "MemberTwo.txt");
						int tiredHp = Integer.parseInt(memberActions2.get(1));
						if (tiredHp <= 0)
						{
							memberActions2.set(1, "0");
							ioFile.fileWrite(memberActions2, "src/StoreGame/CrewSelected/MemberTwo.txt");
							memberActions2 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberTwo.txt");
							int health = Integer.parseInt(memberActions2.get(0)) - 30;
							memberActions2.set(0, "" + health);
							if (health <= 0)
							{
								memberActions2.set(7, "dead");
								memberActions2.set(0, "" + 0);
								memberActions2.set(1, "" + 0);
								memberActions2.set(2, "" + 0);
								memberActions2.set(3, "" + 0); //if dead no more turns
							}
							ioFile.fileWrite(memberActions2, writeHealth + "MemberTwo.txt");
						}
						boolPlague = memberActions2.get(4);
						if (boolPlague.equals("true")) {
							memberActions2 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberTwo.txt");
							int health = Integer.parseInt(memberActions2.get(0)) - 30;
							memberActions2.set(0, "" + health);
							ioFile.fileWrite(memberActions2, writeHealth + "MemberTwo.txt");
						}
						}
					} else if (index == 2) {
						memberActions3 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberThree.txt");
						memberActions3.set(3, "2");
						check = memberActions3.get(7);
						if (check.equals("dead"))
						{
							memberActions3.set(3, "0");
						}
						if (check.equals("alive"))
						{
						int tired = Integer.parseInt(memberActions3.get(1)) - 20;
						memberActions3.set(1, "" + tired);
						ioFile.fileWrite(memberActions3, writeHealth + "MemberThree.txt");
						
						int tiredHp = Integer.parseInt(memberActions3.get(1));
						if (tiredHp <=0)
						{
							memberActions3.set(1, "0");
							ioFile.fileWrite(memberActions3, "src/StoreGame/CrewSelected/MemberThree.txt");
							memberActions3 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberThree.txt");
							int health = Integer.parseInt(memberActions3.get(0)) - 30;
							memberActions3.set(0, "" + health);
							if (health <= 0)
							{
								memberActions3.set(7, "dead");
								memberActions3.set(0, "" + 0);
								memberActions3.set(1, "" + 0);
								memberActions3.set(2, "" + 0);
								memberActions3.set(3, "" + 0);
							}
							ioFile.fileWrite(memberActions3, writeHealth + "MemberThree.txt");
						}
						boolPlague = memberActions3.get(4);
						if (boolPlague.equals("true")) {
							memberActions3 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberThree.txt");
							int health = Integer.parseInt(memberActions3.get(0)) - 30;
							memberActions3.set(0, "" + health);
							ioFile.fileWrite(memberActions3, writeHealth + "MemberThree.txt");
						}
						}
						
					} else if (index == 3) {
						memberActions4 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberFour.txt");
						memberActions4.set(3, "2");
						check = memberActions4.get(7);
						if (check.equals("dead"))
						{
							memberActions4.set(3, "0");
						}
						if (check.equals("alive"))
						{
						int tired = Integer.parseInt(memberActions4.get(1)) - 20;
						memberActions4.set(1, "" + tired);
						ioFile.fileWrite(memberActions4, writeHealth + "MemberFour.txt");
						int tiredHp = Integer.parseInt(memberActions4.get(1));
						if (tiredHp <=0)
						{
							memberActions4.set(1, "0");
							ioFile.fileWrite(memberActions4, "src/StoreGame/CrewSelected/MemberFour.txt");
							memberActions4 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberFour.txt");
							int health = Integer.parseInt(memberActions4.get(0)) - 30;
							memberActions4.set(0, "" + health);
							if (health <= 0)
							{
								memberActions4.set(7, "dead");
								memberActions4.set(0, "" + 0);
								memberActions4.set(1, "" + 0);
								memberActions4.set(2, "" + 0);
								memberActions4.set(3, "" + 0); //if dead no more turns
							}
							ioFile.fileWrite(memberActions4, writeHealth + "MemberFour.txt");
						}
						boolPlague = memberActions4.get(4);
						if (boolPlague.equals("true")) {
							memberActions4 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberFour.txt");
							int health = Integer.parseInt(memberActions4.get(0)) - 30;
							memberActions4.set(0, "" + health);
							ioFile.fileWrite(memberActions4, writeHealth + "MemberFour.txt");
						}
						}
					}
				}
				
			int x = (int)(Math.random()*((3-0)+0))+0;
			if (x == 1)
			{
				// reset Screen
					MainScreen screen = new MainScreen();
					screen.frmEliteDangerousBeta.setVisible(true);    // turn on screen
					frmEliteDangerousBeta.setVisible(false);         // turn off screen
			}
			if (x == 0)
			{ //to make the game a little easier, if anyone is dead, the plague will not happen, asteroids will though

				int makeNormal = 0;
				int curChar = 0;
				memberActions4 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberFour.txt");
				check = memberActions4.get(7);
				System.out.println(check);
				if (check.equals("dead"))
				{
					curChar ++;
				}
				if (check.equals("alive"))
				{
					makeNormal++;
					curChar ++;
				}
				
				memberActions3 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberThree.txt");
				check = memberActions3.get(7);
				if (check.equals("dead"))
				{

					curChar ++;
				}
				if (check.equals("alive"))
				{
					makeNormal++;
					curChar ++;

				}
				memberActions2 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberTwo.txt");
				check = memberActions2.get(7);
				if (check.equals("dead"))
				{

					curChar ++;
				}
				if (check.equals("alive"))
				{
					makeNormal++;
					curChar ++;

				}
				
				memberActions1 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberOne.txt");
				check = memberActions1.get(7);
				if (check.equals("dead"))
				{

					curChar ++;
				}
				if (check.equals("alive"))
				{
					makeNormal++;
					curChar ++;

				}
				
<<<<<<< Updated upstream
				if (curChar == makeNormal )
				{//if the number of characters = number of alive characters, plague is possible
				System.out.println("plague");
				
//					SpaceOutpost outpost = new SpaceOutpost();
//					outpost.frame.setVisible(true);  // turn on screen
//					frame.setVisible(false);         // turn off screen
					
=======
				if (curChar == makeNormal ) { //if the number of characters = number of alive characters, plague is possible
>>>>>>> Stashed changes
					spacePlague outpost = new spacePlague();
					outpost.frmEliteDangerousBeta.setVisible(true);  // turn on screen
					frmEliteDangerousBeta.setVisible(false);         // turn off screen
				}
				if (curChar != makeNormal)  //if the number is different, eg. 1 or more is dead. only asteroids hit
				{
					asteroids outpost2 = new asteroids();
					outpost2.frmEliteDangerousBeta.setVisible(true);
					frmEliteDangerousBeta.setVisible(false);
				}
			}
			if (x == 2)
			{
				alienPirates outpost = new alienPirates();
				outpost.frmEliteDangerousBeta.setVisible(true);
				frmEliteDangerousBeta.setVisible(false);
			}
			
			}
		}
		}
		);
	
		btnNextDay.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnNextDay.setBounds(76, 270, 300, 50);
		frmEliteDangerousBeta.getContentPane().add(btnNextDay);
	}
	
	
	/*
	 * Initialize the contents of the frame.
	*/
	private void initialize() 
	{
		// Setting Layout dimensions
		frmEliteDangerousBeta = new JFrame();
		frmEliteDangerousBeta.setTitle("Elite Dangerous beta");
		Display display = new Display();  // Retrieving game window size
		
		// Setting frame of window
<<<<<<< Updated upstream
		frmEliteDangerousBeta.setBounds(display.x, display.y, display.width, display.height);
		frmEliteDangerousBeta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEliteDangerousBeta.setResizable(false);
		
=======
		frame.setBounds(display.x, display.y, display.width, display.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

>>>>>>> Stashed changes
		
		JPanel panel = new JPanel();
		panel.setBounds(421, 325, 1012, 494);
		panel.setOpaque(false);
		frmEliteDangerousBeta.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panelShip = new JPanel();
		panelShip.setBounds(23, 42, 530, 140);
		panelShip.setOpaque(false);
		panel.add(panelShip);
		panelShip.setLayout(null);
		
		JLabel label = new JLabel("Name:");
		label.setOpaque(true);
		label.setBounds(60, 93, 55, 30);
		panelShip.add(label);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel label_4 = new JLabel("Type:");
		label_4.setOpaque(true);
		label_4.setBounds(60, 51, 55, 30);
		panelShip.add(label_4);
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblShipName = new JLabel("...");
		lblShipName.setOpaque(true);
		lblShipName.setBounds(127, 93, 132, 30);
		panelShip.add(lblShipName);
		lblShipName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblShipType = new JLabel("...");
		lblShipType.setOpaque(true);
		lblShipType.setBounds(127, 51, 132, 30);
		panelShip.add(lblShipType);
		lblShipType.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblParts = new JLabel("Parts: ...");
		lblParts.setOpaque(true);
		lblParts.setHorizontalAlignment(SwingConstants.CENTER);
		lblParts.setFont(new Font("Dialog", Font.BOLD, 18));
		lblParts.setBounds(1555, 439, 300, 33);
		frmEliteDangerousBeta.getContentPane().add(lblParts);
		
		JLabel lblHealth = new JLabel("Shield health");
		lblHealth.setOpaque(true);
		lblHealth.setBounds(268, 51, 250, 30);
		panelShip.add(lblHealth);
		lblHealth.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		pBarShipHealth = new JProgressBar();
		pBarShipHealth.setStringPainted(true);
		pBarShipHealth.setBounds(268, 95, 250, 30);
		panelShip.add(pBarShipHealth);
		
		JLabel lblSp = new JLabel("Ship Info");
		lblSp.setOpaque(true);
		lblSp.setBounds(175, 6, 84, 20);
		panelShip.add(lblSp);
		lblSp.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setOpaque(true);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(450, 12, 112, 33);
		panel.add(lblStatus);
		lblStatus.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JPanel panelCrew = new JPanel();
		panelCrew.setBounds(23, 194, 965, 291);
		panelCrew.setOpaque(false);
		panel.add(panelCrew);
		panelCrew.setLayout(null);
	
		pBarHealth1 = new JProgressBar();
		pBarHealth1.setStringPainted(true);
		pBarHealth1.setBounds(204, 81, 150, 30);
		panelCrew.add(pBarHealth1);
		
		pBarTiredness1 = new JProgressBar();
		pBarTiredness1.setStringPainted(true);
		pBarTiredness1.setBounds(204, 118, 150, 30);
		panelCrew.add(pBarTiredness1);
		
		pBarTiredness3 = new JProgressBar();
		pBarTiredness3.setStringPainted(true);
		pBarTiredness3.setBounds(582, 118, 150, 30);
		panelCrew.add(pBarTiredness3);
		
		pBarTiredness4 = new JProgressBar();
		pBarTiredness4.setStringPainted(true);
		pBarTiredness4.setBounds(780, 118, 150, 30);
		panelCrew.add(pBarTiredness4);
		
		pBarHunger2 = new JProgressBar();
		pBarHunger2.setStringPainted(true);
		pBarHunger2.setBounds(391, 158, 150, 30);
		panelCrew.add(pBarHunger2);
		
		pBarHunger1 = new JProgressBar();
		pBarHunger1.setStringPainted(true);
		pBarHunger1.setBounds(204, 160, 150, 30);
		panelCrew.add(pBarHunger1);
		
		pBarTiredness2 = new JProgressBar();
		pBarTiredness2.setStringPainted(true);
		pBarTiredness2.setBounds(391, 118, 150, 30);
		panelCrew.add(pBarTiredness2);
		
		pBarHealth2 = new JProgressBar();
		pBarHealth2.setStringPainted(true);
		pBarHealth2.setBounds(391, 81, 150, 30);
		panelCrew.add(pBarHealth2);
		
		pBarHunger3 = new JProgressBar();
		pBarHunger3.setStringPainted(true);
		pBarHunger3.setBounds(582, 158, 150, 30);
		panelCrew.add(pBarHunger3);
		
		pBarHunger4 = new JProgressBar();
		pBarHunger4.setStringPainted(true);
		pBarHunger4.setBounds(780, 158, 150, 30);
		panelCrew.add(pBarHunger4);
		
		lblCashTotal = new JLabel("Current Cash = $ <dynamic>");
		lblCashTotal.setOpaque(true);
		lblCashTotal.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCashTotal.setBounds(76, 170, 326, 33);
		frmEliteDangerousBeta.getContentPane().add(lblCashTotal);
		
		lblMember4 = new JLabel("...");
		lblMember4.setOpaque(true);
		lblMember4.setBounds(780, 200, 150, 30);
		panelCrew.add(lblMember4);
		lblMember4.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		lblMember3 = new JLabel("...");
		lblMember3.setOpaque(true);
		lblMember3.setBounds(582, 200, 150, 30);
		panelCrew.add(lblMember3);
		lblMember3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		JLabel lblCrew_1 = new JLabel("Crew Info");
		lblCrew_1.setOpaque(true);
		lblCrew_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrew_1.setBounds(429, 12, 112, 23);
		panelCrew.add(lblCrew_1);
		lblCrew_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblHunger_2 = new JLabel("Hunger:");
		lblHunger_2.setOpaque(true);
		lblHunger_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHunger_2.setBounds(60, 158, 119, 30);
		panelCrew.add(lblHunger_2);
		
		JLabel lblHunger_1 = new JLabel("Tiredness:");
		lblHunger_1.setBounds(60, 116, 119, 30);
		lblHunger_1.setOpaque(true);
		panelCrew.add(lblHunger_1);
		lblHunger_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblHunger = new JLabel("Health:");
		lblHunger.setOpaque(true);
		lblHunger.setBounds(60, 79, 119, 30);
		panelCrew.add(lblHunger);
		lblHunger.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblNames = new JLabel("Name:");
		lblNames.setOpaque(true);
		lblNames.setBounds(60, 200, 119, 30);
		panelCrew.add(lblNames);
		lblNames.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblMember2 = new JLabel("...");
		lblMember2.setOpaque(true);
		lblMember2.setBounds(391, 200, 150, 30);
		panelCrew.add(lblMember2);
		lblMember2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		lblMember1 = new JLabel("...");
		lblMember1.setOpaque(true);
		lblMember1.setBounds(204, 202, 150, 30);
		panelCrew.add(lblMember1);
		lblMember1.setFont(new Font("Lucida Grande", Font.PLAIN, 18)); 
		
		pBarHealth3 = new JProgressBar();
		pBarHealth3.setStringPainted(true);
		pBarHealth3.setBounds(582, 81, 150, 30);
		panelCrew.add(pBarHealth3);
		
		pBarHealth4 = new JProgressBar();
		pBarHealth4.setStringPainted(true);
		pBarHealth4.setBounds(780, 81, 150, 30);
		panelCrew.add(pBarHealth4);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setOpaque(true);
		lblType.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblType.setBounds(60, 39, 119, 30);
		panelCrew.add(lblType);
		
		lblCrewType1 = new JLabel("...");
		lblCrewType1.setOpaque(true);
		lblCrewType1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblCrewType1.setBounds(204, 39, 150, 30);
		panelCrew.add(lblCrewType1);
		
		lblCrewType2 = new JLabel("...");
		lblCrewType2.setOpaque(true);
		lblCrewType2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblCrewType2.setBounds(391, 39, 150, 30);
		panelCrew.add(lblCrewType2);
		
		lblCrewType3 = new JLabel("...");
		lblCrewType3.setOpaque(true);
		lblCrewType3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblCrewType3.setBounds(582, 39, 150, 30);
		panelCrew.add(lblCrewType3);
		
		lblCrewType4 = new JLabel("...");
		lblCrewType4.setOpaque(true);
		lblCrewType4.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblCrewType4.setBounds(780, 39, 150, 30);
		panelCrew.add(lblCrewType4);
		
		player1 = new JLabel("...");
		player1.setOpaque(true);
		player1.setFont(new Font("Dialog", Font.PLAIN, 18));
		player1.setBounds(204, 244, 150, 30);
		panelCrew.add(player1);
		
		player2 = new JLabel("...");
		player2.setOpaque(true);
		player2.setFont(new Font("Dialog", Font.PLAIN, 18));
		player2.setBounds(391, 242, 150, 30);
		panelCrew.add(player2);
		
		player3 = new JLabel("...");
		player3.setOpaque(true);
		player3.setFont(new Font("Dialog", Font.PLAIN, 18));
		player3.setBounds(582, 242, 150, 28);
		panelCrew.add(player3);
		
		player4 = new JLabel("...");
		player4.setOpaque(true);
		player4.setFont(new Font("Dialog", Font.PLAIN, 18));
		player4.setBounds(780, 242, 150, 30);
		panelCrew.add(player4);
		
		JLabel lblactions = new JLabel("Actions Left:");
		lblactions.setOpaque(true);
		lblactions.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblactions.setBounds(60, 242, 119, 30);
		panelCrew.add(lblactions);
		btnRepairShields = new JButton("Repair Shields!");
		btnRepairShields.setBounds(644, 76, 300, 50);
		panel.add(btnRepairShields);
		btnRepairShields.setFont(new Font("Dialog", Font.PLAIN, 19));
		btnRepairShields.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				CrewRepair screen = new CrewRepair();
				screen.frmEliteDangerousBeta.setVisible(true);    // turn on screen
				frmEliteDangerousBeta.setVisible(false);          // turn off screen
			}
		});
		
		
		lblDaysLeft = new JLabel("Day: ...");
		lblDaysLeft.setOpaque(true);
		lblDaysLeft.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDaysLeft.setBounds(76, 225, 230, 33);
		
		frmEliteDangerousBeta.getContentPane().setLayout(null);
		frmEliteDangerousBeta.getContentPane().add(lblDaysLeft);
		
		
		// Initialize storage arrays
		dispCrewType();
		dispCrewMember();
		
		pBarHealth();
		pBarTiredness();
		pBarHunger();
		
		
		// Button Actions
		spaceOutpost();
		newPlanet();
		explorePlanet();
		repairShields();
		btnInventory();
		nextDay();
		btnSleep();

	}
	
	
	/*
	 * Create the application.
	*/
	public MainScreen() 
	{
		initialize();
		bankStore();
		organizeGameInfo();
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
					MainScreen window = new MainScreen();
<<<<<<< Updated upstream
					window.frmEliteDangerousBeta.setVisible(true);
//					asteroids window = new asteroids();
//					window.frame.setVisible(true);
=======
					window.frame.setVisible(true);
>>>>>>> Stashed changes
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
package Inventory;


// Library imports
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
<<<<<<< Updated upstream
import javax.swing.SwingConstants;

=======
>>>>>>> Stashed changes
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import IOFile.IOFile;
import MainScreen.MainScreen;
import WindowSettings.Display;
import javax.swing.ImageIcon;



public class ShipInventory
{
	public JFrame frmEliteDangerousBeta;
	
	private JLabel pBarType1, pBarType2, pBarType3, pBarType4;
	private JLabel siName1, siName2, siName3, siName4;
	
	private JLabel lblCountcookie, lblCountPizza, lblCountFullMeal, lblCountBandages, lblCountMedkit, lblCountSurgical, lblCountPotion;

	private JProgressBar pBarHealth1, pBarHealth2, pBarHealth3, pBarHealth4;
	private JProgressBar pBarTired1, pBarTired2, pBarTired3, pBarTired4;
	private JProgressBar pBarHunger1, pBarHunger2, pBarHunger3, pBarHunger4;
	
	private JRadioButton rBCookie, rBPizza, rBFullMeal, rBPlague, rBSurgical, rBMedkit, rBBandages;
	private JRadioButton rdbtnCrew1, rdbtnCrew2, rdbtnCrew3, rdbtnCrew4;
	private JButton btnConfirmChoice;
	private boolean setectedState, setectedState2, btn3State, btn4State = false;
	private ArrayList<String> crew = new ArrayList<String>();
	
	// stores the selection type
	private ArrayList<String> crewType = new ArrayList<String>();
	private ArrayList<String> crewName = new ArrayList<String>();

	private boolean btnRState, btnCrewState  = false;

	private String actionLeft3, actionLeft4;
	
	private JLabel type[] = new JLabel[4];
	private JLabel member[] = new JLabel[4];
	
	private JProgressBar health[] = new JProgressBar[4];
	private JProgressBar tiredness[] = new JProgressBar[4];
	private JProgressBar hunger[] = new JProgressBar [4];
	
	private int cookies, pizzas, fullMeal, bandage, medkit, surgical, potion;
	private JLabel lblNewLabel;
	private JLabel lblDeleteMeNow;
	

	// Store all progress bar so it can be used easily
	// Stores crew details in data arrays so it can be used easily
	private void pBarHealth() 
	{
		health[0] = pBarHealth1;
		health[1] = pBarHealth2;
		health[2] = pBarHealth3;
		health[3] = pBarHealth4;
	}
	
	private void pBarTired() 
	{
		tiredness[0] = pBarTired1;
		tiredness[1] = pBarTired2;
		tiredness[2] = pBarTired3;
		tiredness[3] = pBarTired4;
	}
	
	private void pBarHunger() 
	{
		hunger[0] = pBarHunger1;
		hunger[1] = pBarHunger2;
		hunger[2] = pBarHunger3;
		hunger[3] = pBarHunger4;
	}
	
	
	// Stores crew details in data arrays so it can be used easily
	private void pBarType() 
	{
		type[0] = pBarType1;
		type[1] = pBarType2;
		type[2] = pBarType3;
		type[3] = pBarType4;
	}
	
	private void siName() 
	{
		member[0] = siName1;
		member[1] = siName2;
		member[2] = siName3;
		member[3] = siName4;
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
	protected void disableInfo(int index) 
	{

		type[index].setEnabled(false);
		member[index].setEnabled(false);
		hunger[index].setEnabled(false);
		health[index].setEnabled(false);
		tiredness[index].setEnabled(false);
	}


	// helper function to disable/(grey out) unused status information areas
	private void disableStatus(int size)
	{
		int lsSize = 4;
		
		// disable/(grey out) unused status information areas
		for (int index = 0; index < lsSize; index++) {
			if ((lsSize-size) == 2 && (index >= 2)) {
				disableInfo(index);
				rdbtnCrew3.setEnabled(false);
				rdbtnCrew4.setEnabled(false);
				siName4.setEnabled(false);
			} else if ((lsSize-size) == 1 && (index == 3)) {
				disableInfo(index);
				rdbtnCrew4.setEnabled(false);
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
		pBarTired1.setValue(Integer.valueOf(crewMember1.get(1)));
		pBarHunger1.setValue(Integer.valueOf(crewMember1.get(2)));
	}
	
	private void memberTwo(ArrayList<String> crewMember2, IOFile ioFile)
	{
		crewMember2 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberTwo.txt");
		
		pBarHealth2.setValue(Integer.valueOf(crewMember2.get(0)));
		pBarTired2.setValue(Integer.valueOf(crewMember2.get(1)));
		pBarHunger2.setValue(Integer.valueOf(crewMember2.get(2)));
	}
	
	private void memberThree(ArrayList<String> crewMember3, IOFile ioFile)
	{
		crewMember3 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberThree.txt");
		
		pBarHealth3.setValue(Integer.valueOf(crewMember3.get(0)));
		pBarTired3.setValue(Integer.valueOf(crewMember3.get(1)));
		pBarHunger3.setValue(Integer.valueOf(crewMember3.get(2)));
	}
	
	private void memberFour(ArrayList<String> crewMember4, IOFile ioFile)
	{
		crewMember4 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberFour.txt");
		
		pBarHealth4.setValue(Integer.valueOf(crewMember4.get(0)));
		pBarTired4.setValue(Integer.valueOf(crewMember4.get(1)));
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
				memberFour(crewMember4, ioFile);;
			}
		}
	}
	
	
	// organizing information from files
	protected void organizeGameInfo()
	{
		// gather information stored in file
		IOFile ioFile = new IOFile();
		
		// Reading files
		ArrayList<String> crewInfo = ioFile.fileRead("src/StoreGame/CrewInfo.txt");
		ArrayList<String> storedItems= ioFile.fileRead("src/StoreGame/Inventory/Storage.txt");
		
		// unwrap information
		decodeCrewInfo(crewInfo);
		readCrewRatings(ioFile);  // Show levels
		
		for (int index = 0; index < crewType.size(); index++) {
			type[index].setText(crewType.get(index));
			member[index].setText(crewName.get(index));
		}
		
		
		// Find out how many of the same items the player has
		for (int index = 0; index < storedItems.size(); index++) {
			if (storedItems.get(index).equals("cookie"))
				cookies++;
			if (storedItems.get(index).equals("pizza"))
				pizzas++;
			if (storedItems.get(index).equals("fullMeal"))
				fullMeal++;
			if (storedItems.get(index).equals("bandage"))
				bandage++;
			if (storedItems.get(index).equals("medkit"))
				medkit++;
			if (storedItems.get(index).equals("surgical"))
				surgical++; 
			if (storedItems.get(index).equals("potion"))
				potion++;	
		}
		
		// Display the item amounts
		lblCountcookie.setText("x" + Integer.toString(cookies));
		lblCountPizza.setText("x" + Integer.toString(pizzas));
		lblCountFullMeal.setText("x" + Integer.toString(fullMeal));
		lblCountBandages.setText("x" + Integer.toString(bandage));
		lblCountMedkit.setText("x" + Integer.toString(medkit));
		lblCountSurgical.setText("x" + Integer.toString(surgical));
		lblCountPotion.setText("x" + Integer.toString(potion));
		
		checkcount(); // check count to see whether to disable button
	}
	
	
	protected void checkcount()
	{
		// determine whether or no the button should be on
		if (cookies == 0)
			rBCookie.setEnabled(false);
		if (pizzas == 0) 
			rBPizza.setEnabled(false);
		if (fullMeal == 0)
			rBFullMeal.setEnabled(false);
		if (potion == 0) 
			rBPlague.setEnabled(false);
		if (bandage == 0)
			rBBandages.setEnabled(false);
		if (medkit == 0) 
			rBMedkit.setEnabled(false);
		if (surgical == 0) 
			rBSurgical.setEnabled(false);
	}
	
	
	// disables the radio buttons
	private void rbEnableFalse(boolean state)
	{
		rBCookie.setEnabled(state);
		rBPizza.setEnabled(state);
		rBFullMeal.setEnabled(state);
		rBPlague.setEnabled(state);
		rBSurgical.setEnabled(state);
		rBMedkit.setEnabled(state);
		rBBandages.setEnabled(state);
	}
	
	
	protected void setradioButtons(JRadioButton button)
	{
		if (button.isSelected()) {
			rbEnableFalse(false);
			button.setEnabled(true);
			btnRState = true;
			if (btnCrewState) btnConfirmChoice.setEnabled(true);
		} else {
			rbEnableFalse(true);
			btnRState = false;
			btnConfirmChoice.setEnabled(false);
		}
		checkcount();
	}
	
	
	protected void cBoxActions()
	{
		rBCookie = new JRadioButton("");
		rBCookie.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				setradioButtons(rBCookie);
			}
		});
		rBCookie.setBounds(853, 199, 38, 28);
		frmEliteDangerousBeta.getContentPane().add(rBCookie);
		
		
		rBPizza = new JRadioButton("");
		rBPizza.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				setradioButtons(rBPizza);
			}
		});
		rBPizza.setBounds(853, 239, 38, 28);
		frmEliteDangerousBeta.getContentPane().add(rBPizza);
		
		
		rBFullMeal = new JRadioButton("");
		rBFullMeal.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				setradioButtons(rBFullMeal);
			}
		});
		rBFullMeal.setBounds(853, 279, 38, 27);
		frmEliteDangerousBeta.getContentPane().add(rBFullMeal);
		
		
		rBPlague = new JRadioButton("");
		rBPlague.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				setradioButtons(rBPlague);
			}
		});
		rBPlague.setBounds(1354, 316, 38, 29);
		frmEliteDangerousBeta.getContentPane().add(rBPlague);
		
		
		rBSurgical = new JRadioButton("");
		rBSurgical.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				setradioButtons(rBSurgical);
			}
		});
		rBSurgical.setBounds(1350, 275, 38, 31);
		frmEliteDangerousBeta.getContentPane().add(rBSurgical);
		
		
		rBMedkit = new JRadioButton("");
		rBMedkit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				setradioButtons(rBMedkit);
			}
		});
		rBMedkit.setBounds(1350, 236, 38, 32);
		frmEliteDangerousBeta.getContentPane().add(rBMedkit);
		
		
		rBBandages = new JRadioButton("");
		rBBandages.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				setradioButtons(rBBandages);
			}
		});
		rBBandages.setBounds(1350, 196, 38, 32);
		frmEliteDangerousBeta.getContentPane().add(rBBandages);
	}

	
	protected void btnBack()
	{
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(940, 700, 250, 100);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				MainScreen screen = new MainScreen();
				screen.frmEliteDangerousBeta.setVisible(true);    // turn on screen
				frmEliteDangerousBeta.setVisible(false);          // turn off screen
			}
		});
		frmEliteDangerousBeta.getContentPane().add(btnBack);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ShipInventory.class.getResource("/gameImages/inventory.PNG")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		frmEliteDangerousBeta.getContentPane().add(lblNewLabel);
		
		lblDeleteMeNow = new JLabel("delete me now");
		lblDeleteMeNow.setBounds(159, 418, 66, 15);
		frmEliteDangerousBeta.getContentPane().add(lblDeleteMeNow);
		
		JLabel lblDeleteMeAsap = new JLabel("delete me asap");
		lblDeleteMeAsap.setBounds(268, 528, 66, 15);
		frmEliteDangerousBeta.getContentPane().add(lblDeleteMeAsap);
	}
	
<<<<<<< Updated upstream
	/**
	 * 
	 * @param crewSelected
	 */
	private void crewWrite(String crewSelected)
=======
	
	protected void crewWrite(String crewSelected)
>>>>>>> Stashed changes
	{
		IOFile ioFile = new IOFile();
		crew = ioFile.fileRead("src/StoreGame/CrewSelected/" + crewSelected + ".txt");
		int valueChange;
		
		if (rBCookie.isSelected()) {
			valueChange = Integer.parseInt(crew.get(2)) + 20;
			if (valueChange > 100)
			{
				valueChange = 100;
			}
			crew.set(2, ""+valueChange);
		} else if (rBPizza.isSelected()) {
			valueChange = Integer.parseInt(crew.get(2)) + 50;
			if (valueChange > 100)
			{
				valueChange = 100;
			}
			crew.set(2, ""+valueChange);
		} else if (rBFullMeal.isSelected()) {
			valueChange = Integer.parseInt(crew.get(2)) + 100;
			if (valueChange > 100)
			{
				valueChange = 100;
			}
			crew.set(2, ""+valueChange);
		} else if (rBPlague.isSelected()) {
			if (crew.get(4).equals("true"))
				crew.set(4, "false");
		} else if (rBSurgical.isSelected()) {
			valueChange = Integer.parseInt(crew.get(0)) + 100;
			if (valueChange > 100)
			{
				valueChange = 100;
			}
			crew.set(0, ""+valueChange);
		} else if (rBMedkit.isSelected()) {
			valueChange = Integer.parseInt(crew.get(0)) + 50;
			if (valueChange > 100)
			{
				valueChange = 100;
			}
			crew.set(0, ""+valueChange);
		} else if (rBBandages.isSelected()) {
			valueChange = Integer.parseInt(crew.get(0)) + 20;
			if (valueChange > 100)
			{
				valueChange = 100;
			}
			crew.set(0, ""+valueChange);
		}
		
		if (setectedState)
			btnConfirmChoice.setEnabled(true); // enable button
		
		ioFile.fileWrite(crew, "src/StoreGame/CrewSelected/" + crewSelected + ".txt");
	}
	
	
	protected void btnConfirm()
	{
		btnConfirmChoice = new JButton("Confirm Choice");
		btnConfirmChoice.setEnabled(false);
		btnConfirmChoice.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				IOFile ioFile = new IOFile();
				ArrayList<String> inventory = new ArrayList<String>();
				
				inventory = ioFile.fileRead("src/StoreGame/Inventory/Storage.txt");
				
		 		if (rdbtnCrew1.isSelected()) {
		 			crewWrite("MemberOne");
		 		} else if (rdbtnCrew2.isSelected()) {
		 			crewWrite("MemberTwo");
		 		} else if (rdbtnCrew3.isSelected()) {
		 			crewWrite("MemberThree");
		 		} else if (rdbtnCrew4.isSelected()) {
		 			crewWrite("MemberFour");
		 		}
		 		
		 		
				if (rBCookie.isSelected()) {
					inventory.remove("cookie");
				} else if (rBPizza.isSelected())
					inventory.remove("pizza");
				else if (rBFullMeal.isSelected())
					inventory.remove("fullMeal");
				else if (rBPlague.isSelected())
					inventory.remove("potion");
				else if (rBSurgical.isSelected())
					inventory.remove("surgical");
				else if (rBMedkit.isSelected())
					inventory.remove("medkit");
				else if (rBBandages.isSelected())
					inventory.remove("bandage");
				
				ioFile.fileWrite(inventory, "src/StoreGame/Inventory/Storage.txt");
				
				ShipInventory ship = new ShipInventory();
				ship.frmEliteDangerousBeta.setVisible(true);    // turn on screen
				frmEliteDangerousBeta.setVisible(false);        // turn off screen
			}
		});
		btnConfirmChoice.setBounds(1200, 700, 250, 100);
		frmEliteDangerousBeta.getContentPane().add(btnConfirmChoice);
	}
	
	
	private void rBClear()
	{
		rdbtnCrew1.setSelected(false);
		rdbtnCrew2.setSelected(false);
		rdbtnCrew3.setSelected(false);
		rdbtnCrew4.setSelected(false);
	}

	
	protected void btnCrew()
	{
		 rdbtnCrew1 = new JRadioButton("Choose");
		 
		 	// this part detects if we have enough turns left, if not the radio button is disabled
			ArrayList<String> member = new ArrayList<String>();
			String actionLeft;
			String readFile = "src/StoreGame/CrewSelected/";
			IOFile ioFile = new IOFile();
			member = ioFile.fileRead(readFile + "MemberOne.txt");
			actionLeft = member.get(7);
			if (actionLeft.equals("dead")) {
				rdbtnCrew1.setEnabled(false);
			}
		 
		 rdbtnCrew1.addActionListener(new ActionListener() 
		 {
		 	public void actionPerformed(ActionEvent arg0) 
		 	{
		 		rBClear();
		 		rdbtnCrew1.setSelected(true);
		 		if (rdbtnCrew1.isSelected()) {
		 			btnCrewState = true;
		 			if (btnRState) btnConfirmChoice.setEnabled(true); // enable button
		 		} else {
		 			btnCrewState = false;
		 			btnConfirmChoice.setEnabled(true);
		 		}
		 	}
		 });
		rdbtnCrew1.setBounds(666, 641, 150, 23);
		frmEliteDangerousBeta.getContentPane().add(rdbtnCrew1);
		
		
		rdbtnCrew2 = new JRadioButton("Choose");
		ArrayList<String> member2 = new ArrayList<String>();
		String actionLeft2;
		String readFile2 = "src/StoreGame/CrewSelected/";
		IOFile ioFile2 = new IOFile();
		member2 = ioFile2.fileRead(readFile2 + "MemberTwo.txt");
		actionLeft2 = member2.get(7);
		if (actionLeft2.equals("dead"))
				{
			rdbtnCrew2.setEnabled(false);
				}
		rdbtnCrew2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				rBClear();
				rdbtnCrew2.setSelected(true);
		 		if (rdbtnCrew2.isSelected()) {
		 			btnCrewState = true;
		 			if (btnRState) btnConfirmChoice.setEnabled(true); // enable button
		 		} else {
		 			btnCrewState = false;
		 			btnConfirmChoice.setEnabled(true);
		 		}
			}
		});
		rdbtnCrew2.setBounds(853, 641, 150, 23);
		frmEliteDangerousBeta.getContentPane().add(rdbtnCrew2);
		
	
		rdbtnCrew3 = new JRadioButton("Choose");
		ArrayList<String> member3 = new ArrayList<String>();
		String readFile3 = "src/StoreGame/CrewSelected/";
		IOFile ioFile3 = new IOFile();
		member3 = ioFile3.fileRead(readFile3 + "MemberThree.txt");
		actionLeft3 = member3.get(7);
		if (actionLeft3.equals("dead"))
				{
			rdbtnCrew3.setEnabled(false);
				}
		rdbtnCrew3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				rBClear();
				rdbtnCrew3.setSelected(true);
		 		if (rdbtnCrew3.isSelected()) {
		 			btnCrewState = true;
		 			if (btnRState) btnConfirmChoice.setEnabled(true); // enable button
		 		} else {
		 			btnCrewState = false;
		 			btnConfirmChoice.setEnabled(true);
		 		}
			}
		});
		rdbtnCrew3.setBounds(1044, 641, 150, 23);
		frmEliteDangerousBeta.getContentPane().add(rdbtnCrew3);
		

		rdbtnCrew4 = new JRadioButton("Choose");
		ArrayList<String> member4 = new ArrayList<String>();
		String readFile4 = "src/StoreGame/CrewSelected/";
		IOFile ioFile4 = new IOFile();
		member4 = ioFile4.fileRead(readFile4 + "MemberFour.txt");
		actionLeft4 = member4.get(7);
		if (actionLeft4.equals("dead"))
				{
			rdbtnCrew4.setEnabled(false);
				}
		rdbtnCrew4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				rBClear();
	 			rdbtnCrew4.setSelected(true);
		 		if (rdbtnCrew4.isSelected()) {
		 			btnCrewState = true;
		 			if (btnRState) btnConfirmChoice.setEnabled(true); // enable button
		 		} else btnCrewState = false;
			}
		});
		rdbtnCrew4.setBounds(1242, 641, 150, 23);
		frmEliteDangerousBeta.getContentPane().add(rdbtnCrew4);
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
		frmEliteDangerousBeta.getContentPane().setLayout(null);
//		frame.setUndecorated(true);  // Frame cannot be adjusted during game
		
=======
		frame.setBounds(display.x, display.y, display.width, display.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

>>>>>>> Stashed changes
		
		// Initialize displays
		JLabel lblShipsInventory = new JLabel("Ships inventory");
		lblShipsInventory.setOpaque(true);
		lblShipsInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblShipsInventory.setFont(new Font("Dialog", Font.BOLD, 24));
		lblShipsInventory.setBounds(650, 50, 600, 50);
		frmEliteDangerousBeta.getContentPane().add(lblShipsInventory);
		
		JLabel lblSelectOneOf = new JLabel("Select an item and a crew member to apply");
		lblSelectOneOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectOneOf.setOpaque(true);
		lblSelectOneOf.setBounds(797, 118, 356, 32);
		frmEliteDangerousBeta.getContentPane().add(lblSelectOneOf);
	
		
		pBarHealth1 = new JProgressBar();
		pBarHealth1.setBounds(666, 466, 150, 30);
		pBarHealth1.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(pBarHealth1);

		pBarTired1 = new JProgressBar();
		pBarTired1.setBounds(666, 503, 150, 30);
		pBarTired1.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(pBarTired1);
	
		pBarHunger1 = new JProgressBar();
		pBarHunger1.setBounds(666, 545, 150, 30);
		pBarHunger1.setStringPainted(true);
<<<<<<< Updated upstream
		frmEliteDangerousBeta.getContentPane().add(pBarHunger1);
=======
		frame.getContentPane().add(pBarHunger1);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
	
=======
		
>>>>>>> Stashed changes
		pBarHealth2 = new JProgressBar();
		pBarHealth2.setBounds(853, 466, 150, 30);
		pBarHealth2.setStringPainted(true);
<<<<<<< Updated upstream
		frmEliteDangerousBeta.getContentPane().add(pBarHealth2);
=======
		frame.getContentPane().add(pBarHealth2);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
		
=======
	
>>>>>>> Stashed changes
		pBarTired2 = new JProgressBar();
		pBarTired2.setBounds(853, 503, 150, 30);
		pBarTired2.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(pBarTired2);
		
		pBarHunger2 = new JProgressBar();
		pBarHunger2.setBounds(853, 543, 150, 30);
		pBarHunger2.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(pBarHunger2);

		pBarHealth3 = new JProgressBar();
		pBarHealth3.setBounds(1044, 466, 150, 30);
		pBarHealth3.setStringPainted(true);
<<<<<<< Updated upstream
		frmEliteDangerousBeta.getContentPane().add(pBarHealth3);
=======
		frame.getContentPane().add(pBarHealth3);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
		
=======
	
>>>>>>> Stashed changes
		pBarTired3 = new JProgressBar();
		pBarTired3.setBounds(1044, 503, 150, 30);
		pBarTired3.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(pBarTired3);

		pBarHunger3 = new JProgressBar();
		pBarHunger3.setBounds(1044, 543, 150, 30);
		pBarHunger3.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(pBarHunger3);

		pBarHealth4 = new JProgressBar();
		pBarHealth4.setBounds(1242, 466, 150, 30);
		pBarHealth4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(pBarHealth4);
		
		pBarTired4 = new JProgressBar();
		pBarTired4.setBounds(1242, 503, 150, 30);
		pBarTired4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(pBarTired4);
		
		pBarHunger4 = new JProgressBar();
		pBarHunger4.setBounds(1242, 543, 150, 30);
		pBarHunger4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(pBarHunger4);

		siName4 = new JLabel("...");
		siName4.setOpaque(true);
		siName4.setFont(new Font("Dialog", Font.PLAIN, 18));
		siName4.setBounds(1242, 585, 150, 30);
		frmEliteDangerousBeta.getContentPane().add(siName4);

		siName3 = new JLabel("...");
		siName3.setOpaque(true);
		siName3.setFont(new Font("Dialog", Font.PLAIN, 18));
		siName3.setBounds(1044, 585, 150, 30);
		frmEliteDangerousBeta.getContentPane().add(siName3);

		siName2 = new JLabel("...");
		siName2.setOpaque(true);
		siName2.setFont(new Font("Dialog", Font.PLAIN, 18));
		siName2.setBounds(853, 585, 150, 30);
		frmEliteDangerousBeta.getContentPane().add(siName2);
		
		siName1 = new JLabel("...");
		siName1.setOpaque(true);
		siName1.setFont(new Font("Dialog", Font.PLAIN, 18));
		siName1.setBounds(666, 587, 150, 30);
		frmEliteDangerousBeta.getContentPane().add(siName1);
		
		JLabel label = new JLabel("Crew Info");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(914, 378, 150, 30);
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label);
	
		JLabel label0 = new JLabel("Hunger:");
		label0.setOpaque(true);
		label0.setBounds(522, 543, 126, 30);
		label0.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label0);

		JLabel label1 = new JLabel("Tiredness:");
		label1.setOpaque(true);
		label1.setBounds(522, 501, 126, 30);
		label1.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label1);
	
		JLabel label2 = new JLabel("Health:");
		label2.setOpaque(true);
		label2.setBounds(522, 464, 126, 30);
		label2.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Name:");
		label3.setOpaque(true);
		label3.setBounds(522, 585, 126, 30);
		label3.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label3);

		JLabel label4 = new JLabel("Type:");
		label4.setBounds(522, 424, 150, 30);
		label4.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label4);

		pBarType1 = new JLabel("...");
		pBarType1.setOpaque(true);
		pBarType1.setBounds(666, 424, 150, 30);
		pBarType1.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(pBarType1);

		pBarType2 = new JLabel("...");
		pBarType2.setOpaque(true);
		pBarType2.setBounds(853, 424, 150, 30);
		pBarType2.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(pBarType2);

		pBarType3 = new JLabel("...");
		pBarType3.setOpaque(true);
		pBarType3.setBounds(1044, 424, 150, 30);
		pBarType3.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(pBarType3);

		pBarType4 = new JLabel("...");
		pBarType4.setOpaque(true);
		pBarType4.setBounds(1242, 424, 150, 30);
		pBarType4.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(pBarType4);
		frmEliteDangerousBeta.getContentPane().setLayout(null);
		
		frmEliteDangerousBeta.getContentPane().setLayout(null);
		lblCountcookie = new JLabel("<dynamic>");
		lblCountcookie.setOpaque(true);
		lblCountcookie.setBounds(755, 199, 84, 28);
		frmEliteDangerousBeta.getContentPane().add(lblCountcookie);
		lblCountcookie.setText(Integer.toString(cookies));
			
		lblCountPizza = new JLabel("New label");
		lblCountPizza.setOpaque(true);
		lblCountPizza.setBounds(755, 239, 84, 28);
		frmEliteDangerousBeta.getContentPane().add(lblCountPizza);
		lblCountPizza.setText(Integer.toString(pizzas));
		
		lblCountFullMeal = new JLabel("New label");
		lblCountFullMeal.setOpaque(true);
		lblCountFullMeal.setBounds(755, 279, 84, 27);
		frmEliteDangerousBeta.getContentPane().add(lblCountFullMeal);
		lblCountFullMeal.setText(Integer.toString(fullMeal));
		
		lblCountBandages = new JLabel("New label");
		lblCountBandages.setOpaque(true);
		lblCountBandages.setBounds(1270, 199, 66, 28);
		frmEliteDangerousBeta.getContentPane().add(lblCountBandages);
		lblCountBandages.setText(Integer.toString(bandage));
		
		lblCountMedkit = new JLabel("New label");
		lblCountMedkit.setOpaque(true);
		lblCountMedkit.setBounds(1270, 239, 66, 28);
		frmEliteDangerousBeta.getContentPane().add(lblCountMedkit);
		lblCountMedkit.setText(Integer.toString(medkit));
		
		lblCountSurgical = new JLabel("New label");
		lblCountSurgical.setOpaque(true);
		lblCountSurgical.setBounds(1270, 278, 66, 28);
		frmEliteDangerousBeta.getContentPane().add(lblCountSurgical);
		lblCountSurgical.setText(Integer.toString(surgical));
		
		lblCountPotion = new JLabel("New label");
		lblCountPotion.setOpaque(true);
		lblCountPotion.setBounds(1270, 316, 66, 29);
		frmEliteDangerousBeta.getContentPane().add(lblCountPotion);
		lblCountPotion.setText(Integer.toString(potion));


		
		
		JLabel lblFood = new JLabel("Food");
		lblFood.setOpaque(true);
		lblFood.setBounds(666, 158, 66, 27);
		lblFood.setFont(new Font("Dialog", Font.BOLD, 16));
		frmEliteDangerousBeta.getContentPane().add(lblFood);
		
		JLabel lblMedical = new JLabel("Medical");
		lblMedical.setOpaque(true);
		lblMedical.setBounds(1084, 162, 110, 23);
		lblMedical.setFont(new Font("Dialog", Font.BOLD, 16));
		frmEliteDangerousBeta.getContentPane().add(lblMedical);
		
		JLabel lblCookieX = new JLabel("Cookie");
		lblCookieX.setOpaque(true);
		lblCookieX.setBounds(666, 199, 77, 28);
		frmEliteDangerousBeta.getContentPane().add(lblCookieX);
		
		JLabel lblPizzaX = new JLabel("Pizza");
		lblPizzaX.setOpaque(true);
		lblPizzaX.setBounds(666, 239, 77, 28);
		frmEliteDangerousBeta.getContentPane().add(lblPizzaX);
		
		JLabel lblFullMealX = new JLabel("Full meal");
		lblFullMealX.setOpaque(true);
		lblFullMealX.setBounds(666, 279, 78, 27);
		frmEliteDangerousBeta.getContentPane().add(lblFullMealX);
		
		JLabel lblBandages = new JLabel("Bandages");
		lblBandages.setOpaque(true);
		lblBandages.setBounds(1084, 199, 126, 28);
		frmEliteDangerousBeta.getContentPane().add(lblBandages);
		
		JLabel lblMedKit = new JLabel("Med Kit");
		lblMedKit.setOpaque(true);
		lblMedKit.setBounds(1084, 241, 126, 25);
		frmEliteDangerousBeta.getContentPane().add(lblMedKit);
		
		JLabel lblSurgialSuite = new JLabel("Surgial Package");
		lblSurgialSuite.setOpaque(true);
		lblSurgialSuite.setBounds(1084, 279, 126, 27);
		frmEliteDangerousBeta.getContentPane().add(lblSurgialSuite);
		
		JLabel lblSpacePlaguePotion = new JLabel("Space Plague Potion");
		lblSpacePlaguePotion.setOpaque(true);
		lblSpacePlaguePotion.setBounds(1084, 316, 176, 29);
		frmEliteDangerousBeta.getContentPane().add(lblSpacePlaguePotion);
	
		


		pBarType();
		siName();
		pBarHealth();
		pBarTired();
		pBarHunger();
		
		// Back Actions
		btnCrew();
		cBoxActions();
		btnConfirm();
		btnBack();
	}
	
	
	/*
	 * Create the application.
	*/
	public ShipInventory()
	{
		initialize();
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
					ShipInventory window = new ShipInventory();
					window.frmEliteDangerousBeta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

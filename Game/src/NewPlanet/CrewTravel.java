package NewPlanet;

//Library imports
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;

//Self implemented
import IOFile.IOFile;
import MainScreen.MainScreen;
import RandomEvents.asteroids;
import SearchPlanet.ExplorePlanet;
import WindowSettings.Display;
<<<<<<< Updated upstream
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
<<<<<<< Updated upstream
import javax.swing.ImageIcon;
import java.awt.Dimension;
=======
=======
>>>>>>> Stashed changes
>>>>>>> Stashed changes



<<<<<<< Updated upstream
	public JFrame frmEliteDangerousBeta;
=======
public class CrewTravel 
{
	public JFrame frame;
>>>>>>> Stashed changes
	
	private JLabel cpType1, cpType2, cpType3, cpType4;
	private JLabel cpName1, cpName2, cpName3, cpName4;
	private JButton btnSearchPlanet;
	

	private JProgressBar cpHealth1, cpHealth2, cpHealth3, cpHealth4;
	private JProgressBar cpTired1, cpTired2, cpTired3, cpTired4;
	private JProgressBar cpHunger1, cpHunger2, cpHunger3, cpHunger4;

	
	// stores the selection type
	private ArrayList<String> crewType = new ArrayList<String>();
	private ArrayList<String> crewName = new ArrayList<String>();

	private JLabel type[] = new JLabel[4];
	private JLabel member[] = new JLabel[4];
	
	private String readFile = "src/StoreGame/CrewSelected/";	
	
	private JProgressBar health[] = new JProgressBar[4];
	private JProgressBar tiredness[] = new JProgressBar[4];
	private JProgressBar hunger[] = new JProgressBar [4];
	private JCheckBox character1, character2, character3, character4;
	private int countCrew = 0;
	private JLabel lblNewLabel;
	

	// Store all progress bar so it can be used easily
	// Stores crew details in data arrays so it can be used easily
	private void cpHealth() 
	{
		health[0] = cpHealth1;
		health[1] = cpHealth2;
		health[2] = cpHealth3;
		health[3] = cpHealth4;
	}
	
	private void cpTired() 
	{
		tiredness[0] = cpTired1;
		tiredness[1] = cpTired2;
		tiredness[2] = cpTired3;
		tiredness[3] = cpTired4;
	}
	
	private void cpHunger() 
	{
		hunger[0] = cpHunger1;
		hunger[1] = cpHunger2;
		hunger[2] = cpHunger3;
		hunger[3] = cpHunger4;
	}
	
	
	// Stores crew details in data arrays so it can be used easily
	private void cpType() 
	{
		
		type[0] = cpType1;
		type[1] = cpType2;
		type[2] = cpType3;
		type[3] = cpType4;
	}
	
	private void cpName() 
	{
		member[0] = cpName1;
		member[1] = cpName2;
		member[2] = cpName3;
		member[3] = cpName4;
	}
	

	// helper function to find the correct member and name
	private void addCrew(ArrayList<String> crewInfo, int size)
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
	private void disableStatus(int size)
	{
		int lsSize = 4;
		
		// disable/(grey out) unused status information areas
		for (int index = 0; index < lsSize; index++) {
			if ((lsSize-size) == 2 && (index >= 2)) {
				disableInfo(index);
			} else if ((lsSize-size) == 1 && (index == 3)) {
				disableInfo(index);
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
	
	
	// organizing information from files
	protected void organizeGameInfo()
	{
		// gather information stored in file
		IOFile ioFile = new IOFile();
		
		// Reading files
		ArrayList<String> crewInfo = ioFile.fileRead("src/StoreGame/CrewInfo.txt");
		
		// unwrap information
		decodeCrewInfo(crewInfo);
		
		readCrewRatings();

		for (int index = 0; index < crewType.size(); index++) {
			type[index].setText(crewType.get(index));
			member[index].setText(crewName.get(index));
		}
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
		lblNewLabel.setIcon(new ImageIcon(CrewTravel.class.getResource("/gameImages/pilot.PNG")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		frmEliteDangerousBeta.getContentPane().add(lblNewLabel);
	}
	
	
	// Enable button if 2 characters have been chosen
	protected void characterChoice()
	{
		character1 = new JCheckBox("Character 1");
<<<<<<< Updated upstream
		character1.setBounds(661, 530, 126, 23);
//		this part detects if we have enough turns left, if not the radio button is disabled
=======
>>>>>>> Stashed changes
		ArrayList<String> member = new ArrayList<String>();
		String actionLeft;
		IOFile ioFile = new IOFile();
		member = ioFile.fileRead(readFile + "MemberOne.txt");
		actionLeft = member.get(3);
		if (actionLeft.equals("0"))
				{
			character1.setEnabled(false);
				}
		
		character1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (character1.isSelected()) {
					countCrew++;
				} else {
					countCrew--;
				}
				
				if (countCrew == 2) {
					btnSearchPlanet.setEnabled(true);
				} else {
					btnSearchPlanet.setEnabled(false);
				}
			}
		});
		frmEliteDangerousBeta.getContentPane().add(character1);
		
		
		character2 = new JCheckBox("Character 2");
		character2.setBounds(848, 530, 126, 23);
		
		ArrayList<String> member2 = new ArrayList<String>();
		String actionLeft2;
		String readFile2 = "src/StoreGame/CrewSelected/";
		IOFile ioFile2 = new IOFile();
		member2 = ioFile2.fileRead(readFile2 + "MemberTwo.txt");
		actionLeft2 = member2.get(3);
		if (actionLeft2.equals("0"))
				{
			character2.setEnabled(false);
				}
		character2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (character2.isSelected()) {
					countCrew++;
				} else {
					countCrew--;
				}

				if (countCrew == 2) {
					btnSearchPlanet.setEnabled(true);
				} else {
					btnSearchPlanet.setEnabled(false);
				}
			}
		});
		frmEliteDangerousBeta.getContentPane().add(character2);
		
		
		character3 = new JCheckBox("Character 3");
		character3.setBounds(1039, 530, 126, 23);
//		this part detects if we have enough turns left, if not the radio button is disabled
		ArrayList<String> member3 = new ArrayList<String>();
		String actionLeft3;
		String readFile3 = "src/StoreGame/CrewSelected/";
		IOFile ioFile3 = new IOFile();
		member3 = ioFile3.fileRead(readFile3 + "MemberThree.txt");
		actionLeft3 = member3.get(3);
		if (actionLeft3.equals("0"))
				{
			character3.setEnabled(false);
				}
		
		character3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (character3.isSelected()) {
					countCrew++;
				} else {
					countCrew--;
				}
				
				if (countCrew == 2) {
					btnSearchPlanet.setEnabled(true);
				} else {
					btnSearchPlanet.setEnabled(false);
				}
			}
		});
		frmEliteDangerousBeta.getContentPane().add(character3);
		
		
		character4 = new JCheckBox("Character 4");
<<<<<<< Updated upstream
		character4.setBounds(1248, 530, 126, 23);
//		this part detects if we have enough turns left, if not the radio button is disabled
=======
>>>>>>> Stashed changes
		ArrayList<String> member4 = new ArrayList<String>();
		String actionLeft4;
		String readFile4 = "src/StoreGame/CrewSelected/";
		IOFile ioFile4 = new IOFile();
		member4 = ioFile4.fileRead(readFile4 + "MemberFour.txt");
		actionLeft4 = member4.get(3);
		if (actionLeft4.equals("0"))
				{
			character4.setEnabled(false);
				}
		character4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (character4.isSelected()) {
					countCrew++;
				} else {
					countCrew--;
				}

				if (countCrew == 2) {
					btnSearchPlanet.setEnabled(true);
				} else {
					btnSearchPlanet.setEnabled(false);
				}
			}
		});
		frmEliteDangerousBeta.getContentPane().add(character4);
	}
		
		
	private void disableMember()
	{
		IOFile ioFile = new IOFile();
		
		// Reading files
		ArrayList<String> crewMembers = ioFile.fileRead("src/StoreGame/CrewInfo.txt");
		
		if ((crewMembers.size()/2) == 2) {
			character3.setEnabled(false);
			character4.setEnabled(false);
		} else if ((crewMembers.size()/2) == 3) {
			character4.setEnabled(false);
		}
	}
	
	protected void tiredRate(ArrayList<String> member, IOFile ioFile, String name)
	{
		member.set(3, ""+(Integer.parseInt(member.get(3)) - 1));
		int tired = Integer.parseInt(member.get(1));
		if (tired <= 0) //are we zero on tired! if so, take hp and make sure tired is set to zero
		{
			int health = Integer.parseInt(member.get(0)) - 30;
			member.set(0, "" + health);
			member.set(1, "" + 0);
			if (health <= 0)
			{
				member.set(7, "dead");
				member.set(0, "" + 0);
				member.set(1, "" + 0);
				member.set(2, "" + 0);
				member.set(3, "" + 0); //if dead no more turns
			}
			ioFile.fileWrite(member, readFile + name + ".txt");
		}
		else 
		{
			int tired1 = Integer.parseInt(member.get(1)) - 10; //above zero tired, so take some tired off
			member.set(1, "" + tired1);
			ioFile.fileWrite(member, readFile + name + ".txt");
		}
	}	


	protected void btnSearch()
	{
		btnSearchPlanet = new JButton("Search for a new Planet");
		btnSearchPlanet.setBounds(1200, 700, 250, 100);
		btnSearchPlanet.setEnabled(false);
		btnSearchPlanet.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				IOFile ioFile = new IOFile();
				ArrayList<String> member = new ArrayList<String>();
				ArrayList<String> updateFalse = new ArrayList<String>();
				
				// changes the member selected file
				if (character1.isSelected()) {
					member = ioFile.fileRead(readFile + "MemberOne.txt");
					tiredRate(member, ioFile, "MemberOne");
				} 
				if (character2.isSelected()) {
					member = ioFile.fileRead(readFile + "MemberTwo.txt");
					tiredRate(member, ioFile, "MemberTwo");
				} 
				if (character3.isSelected()) {
					member = ioFile.fileRead(readFile + "MemberThree.txt");
					tiredRate(member, ioFile, "MemberThree");
				} 
				if (character4.isSelected()) {
					member = ioFile.fileRead(readFile + "MemberFour.txt");
					tiredRate(member, ioFile, "MemberFour");
				} 
				updateFalse = ioFile.fileRead("src/StoreGame/ShipInfo.txt");
				updateFalse.set(3, "false");
				ioFile.fileWrite(updateFalse, "src/StoreGame/ShipInfo.txt");
				int x = (int)(Math.random()*((2-0)+0))+0;
				if (x == 1)
				{
					TravelPlanet screen = new TravelPlanet();
					screen.frmEliteDangerousBeta.setVisible(true);    // turn on screen
					frmEliteDangerousBeta.setVisible(false);          // turn off screen
				}
				if (x == 0)
				{
					asteroids outpost = new asteroids();
					outpost.frmEliteDangerousBeta.setVisible(true);
					frmEliteDangerousBeta.setVisible(false);
				}
			}
		});
		frmEliteDangerousBeta.getContentPane().setLayout(null);
		frmEliteDangerousBeta.getContentPane().add(btnSearchPlanet);
	}
	
	
	// Storing and displaying the characters health
	private void memberOne(ArrayList<String> crewMember1, IOFile ioFile)
	{
		crewMember1 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberOne.txt");
		
		cpHealth1.setValue(Integer.valueOf(crewMember1.get(0)));
		cpTired1.setValue(Integer.valueOf(crewMember1.get(1)));
		cpHunger1.setValue(Integer.valueOf(crewMember1.get(2)));
	}
	
	private void memberTwo(ArrayList<String> crewMember2, IOFile ioFile)
	{
		crewMember2 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberTwo.txt");
		
		cpHealth2.setValue(Integer.valueOf(crewMember2.get(0)));
		cpTired2.setValue(Integer.valueOf(crewMember2.get(1)));
		cpHunger2.setValue(Integer.valueOf(crewMember2.get(2)));
	}
	
	private void memberThree(ArrayList<String> crewMember3, IOFile ioFile)
	{
		crewMember3 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberThree.txt");
		
		cpHealth3.setValue(Integer.valueOf(crewMember3.get(0)));
		cpTired3.setValue(Integer.valueOf(crewMember3.get(1)));
		cpHunger3.setValue(Integer.valueOf(crewMember3.get(2)));
	}
	
	private void memberFour(ArrayList<String> crewMember4, IOFile ioFile)
	{
		crewMember4 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberFour.txt");
		
		cpHealth4.setValue(Integer.valueOf(crewMember4.get(0)));
		cpTired4.setValue(Integer.valueOf(crewMember4.get(1)));
		cpHunger4.setValue(Integer.valueOf(crewMember4.get(2)));
	}
	
	
	protected void readCrewRatings()
	{ 
		ArrayList<String> crewMember1 = new ArrayList<String>();
		ArrayList<String> crewMember2 = new ArrayList<String>();
		ArrayList<String> crewMember3 = new ArrayList<String>();
		ArrayList<String> crewMember4 = new ArrayList<String>();
		IOFile ioFile = new IOFile();
		
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
	
	
	/*
	 * Initialize the contents of the frame.
	*/
	private void initialize() {
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
=======
		frame.setBounds(display.x, display.y, display.width, display.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
<<<<<<< Updated upstream
		frame.getContentPane().setLayout(null);
>>>>>>> Stashed changes
//		frame.setUndecorated(true);  // Frame cannot be adjusted during game
=======
>>>>>>> Stashed changes
		
		
		cpHealth1 = new JProgressBar();
		cpHealth1.setBounds(661, 326, 150, 30);
		cpHealth1.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpHealth1);

		cpTired1 = new JProgressBar();
		cpTired1.setBounds(661, 363, 150, 30);
		cpTired1.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpTired1);
	
		cpHunger1 = new JProgressBar();
		cpHunger1.setBounds(661, 405, 150, 30);
		cpHunger1.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpHunger1);
	
		cpHealth2 = new JProgressBar();
		cpHealth2.setBounds(848, 326, 150, 30);
		cpHealth2.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpHealth2);
		
		cpTired2 = new JProgressBar();
		cpTired2.setBounds(848, 363, 150, 30);
		cpTired2.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpTired2);
		
		cpHunger2 = new JProgressBar();
		cpHunger2.setBounds(848, 403, 150, 30);
		cpHunger2.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpHunger2);

		cpHealth3 = new JProgressBar();
		cpHealth3.setBounds(1039, 326, 150, 30);
		cpHealth3.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpHealth3);
		
		cpTired3 = new JProgressBar();
		cpTired3.setBounds(1039, 363, 150, 30);
		cpTired3.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpTired3);

		cpHunger3 = new JProgressBar();
		cpHunger3.setBounds(1039, 403, 150, 30);
		cpHunger3.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpHunger3);

		cpHealth4 = new JProgressBar();
		cpHealth4.setBounds(1237, 326, 150, 30);
		cpHealth4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpHealth4);
		
		cpTired4 = new JProgressBar();
		cpTired4.setBounds(1237, 363, 150, 30);
		cpTired4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpTired4);
		
		cpHunger4 = new JProgressBar();
		cpHunger4.setBounds(1237, 403, 150, 30);
		cpHunger4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cpHunger4);

		cpName4 = new JLabel("...");
		cpName4.setOpaque(true);
		cpName4.setBounds(1237, 445, 150, 30);
		cpName4.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cpName4);

		cpName3 = new JLabel("...");
		cpName3.setOpaque(true);
		cpName3.setBounds(1039, 445, 150, 30);
		cpName3.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cpName3);

		cpName2 = new JLabel("...");
		cpName2.setOpaque(true);
		cpName2.setBounds(848, 445, 150, 30);
		cpName2.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cpName2);
		
		cpName1 = new JLabel("...");
		cpName1.setOpaque(true);
		cpName1.setBounds(661, 447, 150, 30);
		cpName1.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cpName1);
		
		JLabel label = new JLabel("Crew Info");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(909, 238, 150, 30);
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label);
	
		JLabel label0 = new JLabel("Hunger:");
		label0.setSize(new Dimension(60, 0));
		label0.setOpaque(true);
		label0.setBounds(517, 403, 119, 30);
		label0.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label0);

		JLabel label1 = new JLabel("Tiredness:");
		label1.setSize(new Dimension(60, 0));
		label1.setOpaque(true);
		label1.setBounds(517, 361, 119, 30);
		label1.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label1);
	
		JLabel label2 = new JLabel("Health:");
		label2.setSize(new Dimension(60, 0));
		label2.setOpaque(true);
		label2.setBounds(517, 324, 119, 30);
		label2.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Name:");
		label3.setSize(new Dimension(60, 0));
		label3.setOpaque(true);
		label3.setBounds(517, 445, 119, 30);
		label3.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label3);

		JLabel label4 = new JLabel("Type:");
		label4.setSize(new Dimension(60, 0));
		label4.setOpaque(true);
		label4.setBounds(517, 284, 119, 30);
		label4.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label4);

		cpType1 = new JLabel("...");
		cpType1.setOpaque(true);
		cpType1.setBounds(661, 284, 150, 30);
		cpType1.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cpType1);

		cpType2 = new JLabel("...");
		cpType2.setOpaque(true);
		cpType2.setBounds(848, 284, 150, 30);
		cpType2.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cpType2);

		cpType3 = new JLabel("...");
		cpType3.setOpaque(true);
		cpType3.setBounds(1039, 284, 150, 30);
		cpType3.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cpType3);

		cpType4 = new JLabel("...");
		cpType4.setOpaque(true);
		cpType4.setBounds(1237, 284, 150, 30);
		cpType4.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cpType4);
			

		
		
		
		

		//initialize storage arrays
		cpType();
		cpName();
		
		cpHealth();
		cpTired();
		cpHunger();
		
		// Button actions
		btnSearch();
		characterChoice();
		disableMember();
		btnBack();
	}
	
	
	/*
	 * Create the application.
	*/
	public CrewTravel() {
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
					CrewTravel window = new CrewTravel();
					window.frmEliteDangerousBeta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

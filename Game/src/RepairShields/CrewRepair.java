package RepairShields;

//Library imports
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import IOFile.IOFile;
import MainScreen.MainScreen;
import SearchPlanet.ExplorePlanet;
//Self implemented
import WindowSettings.Display;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;


public class CrewRepair {

	public JFrame frmEliteDangerousBeta;
	
	private JLabel crType1, crType2, crType3, crType4;
	private JLabel crName1, crName2, crName3, crName4;
	private JRadioButton rBChar1, rBChar2, rBChar3, rBChar4;
	JButton btnRepair;
	
	private JProgressBar crHealth1, crHealth2, crHealth3, crHealth4;
	private JProgressBar crTired1, crTired2, crTired3, crTired4;
	private JProgressBar crHunger1, crHunger2, crHunger3, crHunger4;
	
	// File locations
	private String readCrew = "src/StoreGame/CrewRatings/";
	private String readFile = "src/StoreGame/CrewSelected/";	
	
	// stores the selection type
	private ArrayList<String> crewType = new ArrayList<String>();
	private ArrayList<String> crewName = new ArrayList<String>();

	private JLabel type[] = new JLabel[4];
	private JLabel member[] = new JLabel[4];
	
	
	private JProgressBar health[] = new JProgressBar[4];
	private JProgressBar tiredness[] = new JProgressBar[4];
	private JProgressBar hunger[] = new JProgressBar [4];
	

	// Store all progress bar so it can be used easily
	// Stores crew details in data arrays so it can be used easily
	private void crHealth() 
	{
		health[0] = crHealth1;
		health[1] = crHealth2;
		health[2] = crHealth3;
		health[3] = crHealth4;
	}
	
	private void crTired() 
	{
		tiredness[0] = crTired1;
		tiredness[1] = crTired2;
		tiredness[2] = crTired3;
		tiredness[3] = crTired4;
	}
	
	private void crHunger() 
	{
		hunger[0] = crHunger1;
		hunger[1] = crHunger2;
		hunger[2] = crHunger3;
		hunger[3] = crHunger4;
	}
	
	
	// Stores crew details in data arrays so it can be used easily
	private void crType() 
	{
		
		type[0] = crType1;
		type[1] = crType2;
		type[2] = crType3;
		type[3] = crType4;
	}
	
	private void crName() 
	{
		member[0] = crName1;
		member[1] = crName2;
		member[2] = crName3;
		member[3] = crName4;
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
	private void decodeCrewInfo(ArrayList<String> crewInfo)
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
		
		crHealth1.setValue(Integer.valueOf(crewMember1.get(0)));
		crTired1.setValue(Integer.valueOf(crewMember1.get(1)));
		crHunger1.setValue(Integer.valueOf(crewMember1.get(2)));
	}
	
	private void memberTwo(ArrayList<String> crewMember2, IOFile ioFile)
	{
		crewMember2 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberTwo.txt");
		
		crHealth2.setValue(Integer.valueOf(crewMember2.get(0)));
		crTired2.setValue(Integer.valueOf(crewMember2.get(1)));
		crHunger2.setValue(Integer.valueOf(crewMember2.get(2)));
	}
	
	private void memberThree(ArrayList<String> crewMember3, IOFile ioFile)
	{
		crewMember3 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberThree.txt");
		
		crHealth3.setValue(Integer.valueOf(crewMember3.get(0)));
		crTired3.setValue(Integer.valueOf(crewMember3.get(1)));
		crHunger3.setValue(Integer.valueOf(crewMember3.get(2)));
	}
	
	private void memberFour(ArrayList<String> crewMember4, IOFile ioFile)
	{
		crewMember4 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberFour.txt");
		
		crHealth4.setValue(Integer.valueOf(crewMember4.get(0)));
		crTired4.setValue(Integer.valueOf(crewMember4.get(1)));
		crHunger4.setValue(Integer.valueOf(crewMember4.get(2)));
	}
	
	
	private void readCrewRatings()
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
	
	
	// organizing information from files
	private void organizeGameInfo()
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
		
			
	private void clearRepair()
	{
		rBChar1.setSelected(false);
		rBChar2.setSelected(false);
		rBChar3.setSelected(false);
		rBChar4.setSelected(false);
	}
	
	private void tiredRate(ArrayList<String> member, IOFile ioFile, String name)
	{
		member.set(3, ""+(Integer.parseInt(member.get(3)) - 1));
		int tired = Integer.parseInt(member.get(1));
		if (tired <= 0)
		{
			
			int health = Integer.parseInt(member.get(0)) - 30;
			member.set(0, "" + health);
			if (health <= 0)
			{
				member.set(7, "dead");
				member.set(0, "" + 0);
				member.set(1, "" + 0);
				member.set(2, "" + 0);
				member.set(3, "" + 0); //if dead no more turns
			}
			member.set(1, "" + 0);
			ioFile.fileWrite(member, readFile + name + ".txt");
		}
		else 
		{
			int tired1 = Integer.parseInt(member.get(1)) - 10; //this is the value in which a repair heals
			member.set(1, "" + tired1);
			ioFile.fileWrite(member, readFile + name + ".txt");
		}
	}	
	private void btnRepair()
	{
		btnRepair = new JButton("Repair");
		btnRepair.setEnabled(false);
		btnRepair.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				IOFile ioFile = new IOFile();
				
				ArrayList<String> changeShields = ioFile.fileRead("src/StoreGame/ShipInfo.txt");
				ArrayList<String> member = new ArrayList<String>();
				int repair = Integer.parseInt(changeShields.get(2)) + 30; //this is the value in which a repair heals
				
				changeShields.set(2, "" + repair);
				if (repair >= 100)
				{
					changeShields.set(2, "100");
				}
				ioFile.fileWrite(changeShields, "src/StoreGame/ShipInfo.txt");  // Writing in new days
				
				String readFile = "src/StoreGame/CrewSelected/";
				
				// changes the member selected file
				if (rBChar1.isSelected()) {
					member = ioFile.fileRead(readFile + "MemberOne.txt");
					tiredRate(member, ioFile, "MemberOne");
				} 
				if (rBChar2.isSelected()) {
					member = ioFile.fileRead(readFile + "MemberTwo.txt");
					tiredRate(member, ioFile, "MemberTwo");
				} 
				if (rBChar3.isSelected()) {
					member = ioFile.fileRead(readFile + "MemberThree.txt");
					tiredRate(member, ioFile, "MemberThree");
				} 
				if (rBChar4.isSelected()) {
					member = ioFile.fileRead(readFile + "MemberFour.txt");
					tiredRate(member, ioFile, "MemberFour");
				}
			
				MainScreen screen = new MainScreen();
				screen.frmEliteDangerousBeta.setVisible(true);    // turn on screen
				frmEliteDangerousBeta.setVisible(false);          // turn off screen
			}
		});
		frmEliteDangerousBeta.getContentPane().setLayout(null);
		btnRepair.setBounds(1200, 700, 250, 100);
		frmEliteDangerousBeta.getContentPane().add(btnRepair);
	}
	
	
	// back button
	private void btnBack()
	{
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MainScreen screen = new MainScreen();
				screen.frmEliteDangerousBeta.setVisible(true);    // turn on screen
				frmEliteDangerousBeta.setVisible(false);          // turn off screen
			}
		});
		btnBack.setBounds(940, 700, 250, 100);
		frmEliteDangerousBeta.getContentPane().add(btnBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CrewRepair.class.getResource("/gameImages/crew.PNG")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 1920, 1080);
		frmEliteDangerousBeta.getContentPane().add(label);
		
	}
	
	
	private void disableMember()
	{
		IOFile ioFile = new IOFile();
		
		// Reading files
		ArrayList<String> crewMembers = ioFile.fileRead("src/StoreGame/CrewInfo.txt");
		
		if ((crewMembers.size()/2) == 2) {
			rBChar3.setEnabled(false);
			rBChar4.setEnabled(false);
		} else if ((crewMembers.size()/2) == 3) {
			rBChar4.setEnabled(false);
		}
	}
	
	
	// characters that can be chosen
	private void characterChoice()
	{
		rBChar1 = new JRadioButton("character 1");
//		this part detects if we have enough turns left, if not the radio button is disabled
		ArrayList<String> member = new ArrayList<String>();
		String actionLeft;
		String readFile = "src/StoreGame/CrewSelected/";
		IOFile ioFile = new IOFile();
		member = ioFile.fileRead(readFile + "MemberOne.txt");
		actionLeft = member.get(3);
		if (actionLeft.equals("0"))
				{
			rBChar1.setEnabled(false);
				}
		
		rBChar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearRepair();
				rBChar1.setSelected(true);
				btnRepair.setEnabled(true);
			}
		});
		rBChar1.setBounds(661, 530, 126, 23);
		frmEliteDangerousBeta.getContentPane().add(rBChar1);
		
		
		rBChar2 = new JRadioButton("character 2");
		
//		this part detects if we have enough turns left, if not the radio button is disabled
		ArrayList<String> member2 = new ArrayList<String>();
		String actionLeft2;
		String readFile2 = "src/StoreGame/CrewSelected/";
		IOFile ioFile2 = new IOFile();
		member2 = ioFile2.fileRead(readFile2 + "MemberTwo.txt");
		actionLeft2 = member2.get(3);
		if (actionLeft2.equals("0"))
				{
			rBChar2.setEnabled(false);
				}
		rBChar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearRepair();
				rBChar2.setSelected(true);
				btnRepair.setEnabled(true);
			}
		});
		rBChar2.setBounds(848, 530, 126, 23);
		frmEliteDangerousBeta.getContentPane().add(rBChar2);
		
		
		rBChar3 = new JRadioButton("character 3");
//		this part detects if we have enough turns left, if not the radio button is disabled
		ArrayList<String> member3 = new ArrayList<String>();
		String actionLeft3;
		String readFile3 = "src/StoreGame/CrewSelected/";
		IOFile ioFile3 = new IOFile();
		member3 = ioFile3.fileRead(readFile3 + "MemberThree.txt");
		actionLeft3 = member3.get(3);
		if (actionLeft3.equals("0"))
				{
			rBChar3.setEnabled(false);
				}
		
		rBChar3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearRepair();
				rBChar3.setSelected(true);
				btnRepair.setEnabled(true);
			}
		});
		rBChar3.setBounds(1039, 530, 126, 23);
		frmEliteDangerousBeta.getContentPane().add(rBChar3);
		
		
		rBChar4 = new JRadioButton("character 4");
//		this part detects if we have enough turns left, if not the radio button is disabled
		ArrayList<String> member4 = new ArrayList<String>();
		String actionLeft4;
		String readFile4 = "src/StoreGame/CrewSelected/";
		IOFile ioFile4 = new IOFile();
		member4 = ioFile4.fileRead(readFile4 + "MemberFour.txt");
		actionLeft4 = member4.get(3);
		if (actionLeft4.equals("0"))
				{
			rBChar4.setEnabled(false);
				}
		rBChar4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearRepair();
				rBChar4.setSelected(true);
				btnRepair.setEnabled(true);
			}
		});
		rBChar4.setBounds(1248, 530, 126, 23);
		frmEliteDangerousBeta.getContentPane().add(rBChar4);
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
		frmEliteDangerousBeta.setBounds(display.x, display.y, display.width, display.height);
		frmEliteDangerousBeta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEliteDangerousBeta.setResizable(false);
//		frame.setUndecorated(true);  // Frame cannot be adjusted during game
		
		crHealth1 = new JProgressBar();
		crHealth1.setBounds(661, 326, 150, 30);
		crHealth1.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crHealth1);

		crTired1 = new JProgressBar();
		crTired1.setBounds(661, 363, 150, 30);
		crTired1.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crTired1);
	
		crHunger1 = new JProgressBar();
		crHunger1.setBounds(661, 405, 150, 30);
		crHunger1.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crHunger1);
	
		crHealth2 = new JProgressBar();
		crHealth2.setBounds(848, 326, 150, 30);
		crHealth2.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crHealth2);
		
		crTired2 = new JProgressBar();
		crTired2.setBounds(848, 363, 150, 30);
		crTired2.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crTired2);
		
		crHunger2 = new JProgressBar();
		crHunger2.setBounds(848, 403, 150, 30);
		crHunger2.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crHunger2);

		crHealth3 = new JProgressBar();
		crHealth3.setBounds(1039, 326, 150, 30);
		crHealth3.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crHealth3);
		
		crTired3 = new JProgressBar();
		crTired3.setBounds(1039, 363, 150, 30);
		crTired3.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crTired3);

		crHunger3 = new JProgressBar();
		crHunger3.setBounds(1039, 403, 150, 30);
		crHunger3.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crHunger3);

		crHealth4 = new JProgressBar();
		crHealth4.setBounds(1237, 326, 150, 30);
		crHealth4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crHealth4);
		
		crTired4 = new JProgressBar();
		crTired4.setBounds(1237, 363, 150, 30);
		crTired4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crTired4);
		
		crHunger4 = new JProgressBar();
		crHunger4.setBounds(1237, 403, 150, 30);
		crHunger4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(crHunger4);

		crName4 = new JLabel("...");
		crName4.setOpaque(true);
		crName4.setBounds(1237, 445, 150, 30);
		crName4.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(crName4);

		crName3 = new JLabel("...");
		crName3.setOpaque(true);
		crName3.setBounds(1039, 445, 150, 30);
		crName3.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(crName3);

		crName2 = new JLabel("...");
		crName2.setOpaque(true);
		crName2.setBounds(848, 445, 150, 30);
		crName2.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(crName2);
		
		crName1 = new JLabel("...");
		crName1.setOpaque(true);
		crName1.setBounds(661, 447, 150, 30);
		crName1.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(crName1);
		
		JLabel label = new JLabel("Crew Info");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(909, 238, 150, 30);
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label);
	
		JLabel label0 = new JLabel("Hunger:");
		label0.setOpaque(true);
		label0.setBounds(517, 403, 118, 30);
		label0.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label0);

		JLabel label1 = new JLabel("Tiredness:");
		label1.setOpaque(true);
		label1.setBounds(517, 361, 118, 30);
		label1.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label1);
	
		JLabel label2 = new JLabel("Health:");
		label2.setOpaque(true);
		label2.setBounds(517, 324, 118, 30);
		label2.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Name:");
		label3.setOpaque(true);
		label3.setBounds(517, 445, 118, 30);
		label3.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label3);

		JLabel label4 = new JLabel("Type:");
		label4.setOpaque(true);
		label4.setBounds(517, 284, 118, 30);
		label4.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label4);

		crType1 = new JLabel("...");
		crType1.setOpaque(true);
		crType1.setBounds(661, 284, 150, 30);
		crType1.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(crType1);

		crType2 = new JLabel("...");
		crType2.setOpaque(true);
		crType2.setBounds(848, 284, 150, 30);
		crType2.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(crType2);

		crType3 = new JLabel("...");
		crType3.setOpaque(true);
		crType3.setBounds(1039, 284, 150, 30);
		crType3.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(crType3);

		crType4 = new JLabel("...");
		crType4.setOpaque(true);
		crType4.setBounds(1237, 284, 150, 30);
		crType4.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(crType4);		
	
		
		

		//initialize storage arrays
		crType();
		crName();
		
		crHealth();
		crTired();
		crHunger();
		
		// Button actions
		characterChoice();
		disableMember();
		btnRepair();
		btnBack();
	}
	
	
	/*
	 * Create the application.
	*/
	public CrewRepair() {
		initialize();
		organizeGameInfo();  // displaying info
	}
	
	
	/*
	 * Launch the application.
	*/
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					CrewRepair window = new CrewRepair();
					window.frmEliteDangerousBeta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

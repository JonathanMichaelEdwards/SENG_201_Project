package SpaceOutpost;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

//Self implemented
import WindowSettings.Display;
import IOFile.IOFile;
import SetUpScreens.ChooseDays;


public class MedicalStore 
{
	public JFrame frmEliteDangerousBeta;

	private JLabel lblCurrentCash;
	private JComboBox cBox1, cBox2, cBox3, cBox4;
	private JLabel lbl1, lbl2, lbl3, lbl4;
	private JLabel lblAmount;
	
	private int cashSpent, totalAmount, cash1, cash2, cash3, cash4 = 0;
	private JLabel cBType1, cBType2, cBType3, cBType4;
	private JLabel msName1, msName2, msName3, msName4;
	private JButton btnBuy;
	
	private JProgressBar cBHealth1, cBHealth2, cBHealth3, cBHealth4;
	private JProgressBar cBTired1, cBTired2, cBTired3, cBTired4;
	private JProgressBar cBHunger1, cBHunger2, cBHunger3, cBHunger4;
	private int bandage, medkit, surgical, potion;
	private JLabel lblCountBandages, lblCountMedkit, lblCountSurgical, lblCountPotion;
	
	// stores the selection type
	private ArrayList<String> crewType = new ArrayList<String>();
	private ArrayList<String> crewName = new ArrayList<String>();
	private ArrayList<String> broughtItems1 = new ArrayList<String>();
	private ArrayList<String> broughtItems2 = new ArrayList<String>();
	private ArrayList<String> broughtItems3 = new ArrayList<String>();
	private ArrayList<String> broughtItems4 = new ArrayList<String>();

	private JLabel type[] = new JLabel[4];
	private JLabel member[] = new JLabel[4];
	
	
	private JProgressBar health[] = new JProgressBar[4];
	private JProgressBar tiredness[] = new JProgressBar[4];
	private JProgressBar hunger[] = new JProgressBar [4];
	private JLabel lblNewLabel;
	

	// Store all progress bar so it can be used easily
	// Stores crew details in data arrays so it can be used easily
	private void cBHealth() 
	{
		health[0] = cBHealth1;
		health[1] = cBHealth2;
		health[2] = cBHealth3;
		health[3] = cBHealth4;
	}
	
	private void cBTired() 
	{
		tiredness[0] = cBTired1;
		tiredness[1] = cBTired2;
		tiredness[2] = cBTired3;
		tiredness[3] = cBTired4;
	}
	
	private void cBHunger() 
	{
		hunger[0] = cBHunger1;
		hunger[1] = cBHunger2;
		hunger[2] = cBHunger3;
		hunger[3] = cBHunger4;
	}
	
	
	// Stores crew details in data arrays so it can be used easily
	private void cBType() 
	{
		
		type[0] = cBType1;
		type[1] = cBType2;
		type[2] = cBType3;
		type[3] = cBType4;
	}
	
	private void msName() 
	{
		member[0] = msName1;
		member[1] = msName2;
		member[2] = msName3;
		member[3] = msName4;
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
		
		cBHealth1.setValue(Integer.valueOf(crewMember1.get(0)));
		cBTired1.setValue(Integer.valueOf(crewMember1.get(1)));
		cBHunger1.setValue(Integer.valueOf(crewMember1.get(2)));
	}
	
	private void memberTwo(ArrayList<String> crewMember2, IOFile ioFile)
	{
		crewMember2 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberTwo.txt");
		
		cBHealth2.setValue(Integer.valueOf(crewMember2.get(0)));
		cBTired2.setValue(Integer.valueOf(crewMember2.get(1)));
		cBHunger2.setValue(Integer.valueOf(crewMember2.get(2)));
	}
	
	private void memberThree(ArrayList<String> crewMember3, IOFile ioFile)
	{
		crewMember3 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberThree.txt");
		
		cBHealth3.setValue(Integer.valueOf(crewMember3.get(0)));
		cBTired3.setValue(Integer.valueOf(crewMember3.get(1)));
		cBHunger3.setValue(Integer.valueOf(crewMember3.get(2)));
	}
	
	private void memberFour(ArrayList<String> crewMember4, IOFile ioFile)
	{
		crewMember4 = ioFile.fileRead("src/StoreGame/CrewSelected/MemberFour.txt");
		
		cBHealth4.setValue(Integer.valueOf(crewMember4.get(0)));
		cBTired4.setValue(Integer.valueOf(crewMember4.get(1)));
		cBHunger4.setValue(Integer.valueOf(crewMember4.get(2)));
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
		ArrayList<String> storedItems= ioFile.fileRead("src/StoreGame/Inventory/Storage.txt");
		ArrayList<String> crewInfo = ioFile.fileRead("src/StoreGame/CrewInfo.txt");
		
		// unwrap information
		decodeCrewInfo(crewInfo);
		readCrewRatings();
		
		for (int index = 0; index < crewType.size(); index++) {
			type[index].setText(crewType.get(index));
			member[index].setText(crewName.get(index));
		}
		
		
		// Find out how many of the same items the player has
		for (int index = 0; index < storedItems.size(); index++) {
			if (storedItems.get(index).equals("bandage"))
				bandage++;
			if (storedItems.get(index).equals("medkit"))
				medkit++;
			if (storedItems.get(index).equals("surgical"))
				surgical++; 
			if (storedItems.get(index).equals("potion"))
				potion++;	
		}
		lblCountBandages.setText("x" + Integer.toString(bandage));
		lblCountMedkit.setText("x" + Integer.toString(medkit));
		lblCountSurgical.setText("x" + Integer.toString(surgical));
		lblCountPotion.setText("x" + Integer.toString(potion));
	}
	
	// get the amount of cash the player has in his bank
	private void totalCash()
	{
		ArrayList<String> bank = new ArrayList<String>();
		IOFile ioFile = new IOFile();
		
		bank = ioFile.fileRead("src/StoreGame/CashInfo.txt");
		lblCurrentCash.setText("Current Cash = $ " + bank.get(0).toString());
	}
	
	
	// Go back to the space outpost
	private void backToOutpost()
	{
		JButton btnBackToOutpost = new JButton("Back to Outpost");
		btnBackToOutpost.setBounds(940, 700, 250, 100);
		btnBackToOutpost.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				SpaceOutpost spaceOutpost = new SpaceOutpost();
				spaceOutpost.frmEliteDangerousBeta.setVisible(true);  // turn on screen
				frmEliteDangerousBeta.setVisible(false);              // turn off screen
			}
		});
		frmEliteDangerousBeta.getContentPane().add(btnBackToOutpost);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		lblNewLabel.setIcon(new ImageIcon(ChooseDays.class.getResource("/gameImages/hospital.png")));
		frmEliteDangerousBeta.getContentPane().add(lblNewLabel);
	}
	
	
	private void btnBuy()
	{
		btnBuy = new JButton("Buy");
		btnBuy.setBounds(1200, 700, 250, 100);
		btnBuy.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{		
				ArrayList<String> totalCash = new ArrayList<String>();
				ArrayList<String> inventory = new ArrayList<String>();
				IOFile ioFile = new IOFile();
				
				cashSpent += cash1 + cash2 + cash3 + cash4;
				totalCash = ioFile.fileRead("src/StoreGame/CashInfo.txt");
				inventory = ioFile.fileRead("src/StoreGame/Inventory/Storage.txt");
				
				// Storing information
				int bank = Integer.parseInt(totalCash.get(0)) - cashSpent;
				totalCash.set(0, "" + bank);
				inventory.addAll(broughtItems1);
				inventory.addAll(broughtItems2);
				inventory.addAll(broughtItems3);
				inventory.addAll(broughtItems4);
				
				// store the new cash amount
				ioFile.fileWrite(totalCash, "src/StoreGame/CashInfo.txt");  // Writing in new days
				ioFile.fileWrite(inventory, "src/StoreGame/Inventory/Storage.txt");  // Writing new items in inventory
				
				// Refresh screen
				MedicalStore screen = new MedicalStore();
				screen.frmEliteDangerousBeta.setVisible(true);  // turn on screen
				frmEliteDangerousBeta.setVisible(false);        // turn off screen
			}
		});
		frmEliteDangerousBeta.getContentPane().add(btnBuy);
	}
	
	
	
	// Add and remove previous items to the inventory store
	private void storeItems(String item, int amount, int factor, ArrayList<String> listClear)
	{	
		listClear.clear();
		btnBuy.setEnabled(false);
		
		for (int index = 0; index < (amount/factor); index++) {
			if (item == "bandage") {
				broughtItems1.add(item);
				btnBuy.setEnabled(true);
			} else if (item == "medkit") {
				broughtItems2.add(item);
				btnBuy.setEnabled(true);
			} else if (item == "surgical") {
				broughtItems3.add(item);
				btnBuy.setEnabled(true);
			} else if (item == "potion") {
				broughtItems4.add(item);
				btnBuy.setEnabled(true);
			}
		}
	}
	
	
	private void cBoxActions()
	{
		cBox1 = new JComboBox();
		cBox1.setBounds(867, 197, 90, 30);
		cBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cash1 = Integer.valueOf(((String)cBox1.getSelectedItem()).replace("x", "")) * 5;
				lbl1.setText("= $" + cash1);
				totalAmount = cash1 + cash2 + cash3 + cash4;
				lblAmount.setText("Selected Amount = $ " + totalAmount);
				 storeItems("bandage", cash1, 5, broughtItems1);
			}
		});
		cBox1.setModel(new DefaultComboBoxModel(new String[] {"0", "x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8", "x9"}));
		cBox1.setMaximumRowCount(9);
		frmEliteDangerousBeta.getContentPane().add(cBox1);
		
		
		
		cBox2 = new JComboBox();
		cBox2.setBounds(867, 244, 90, 30);
		cBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cash2 = Integer.valueOf(((String)cBox2.getSelectedItem()).replace("x", "")) * 8;
				lbl2.setText("= $" + cash2);
				totalAmount = cash1 + cash2 + cash3 + cash4;
				lblAmount.setText("Selected Amount = $ " + totalAmount);
				storeItems("medkit", cash2, 8, broughtItems2);
			}
		});
		cBox2.setModel(new DefaultComboBoxModel(new String[] {"0", "x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8", "x9"}));
		cBox2.setSelectedIndex(0);
		cBox2.setMaximumRowCount(9);
		frmEliteDangerousBeta.getContentPane().add(cBox2);
		
		cBox3 = new JComboBox();
		cBox3.setBounds(867, 286, 90, 30);
		cBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cash3 = Integer.valueOf(((String)cBox3.getSelectedItem()).replace("x", "")) * 14;
				lbl3.setText("= $" + cash3);
				totalAmount = cash1 + cash2 + cash3 + cash4;
				lblAmount.setText("Selected Amount = $ " + totalAmount);
				storeItems("surgical", cash3, 14, broughtItems3);
			}
		});
		cBox3.setModel(new DefaultComboBoxModel(new String[] {"0", "x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8", "x9"}));
		cBox3.setSelectedIndex(0);
		cBox3.setMaximumRowCount(9);
		frmEliteDangerousBeta.getContentPane().add(cBox3);
		
		cBox4 = new JComboBox();
		cBox4.setBounds(867, 328, 94, 30);
		cBox4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cash4 = Integer.valueOf(((String)cBox4.getSelectedItem()).replace("x", "")) * 50;
				lbl4.setText("= $" + cash4);
				totalAmount = cash1 + cash2 + cash3 + cash4;
				lblAmount.setText("Selected Amount = $ " + totalAmount);
				storeItems("potion", cash4, 50, broughtItems4);
			}
		});
		cBox4.setModel(new DefaultComboBoxModel(new String[] {"0", "x1"}));
		cBox4.setSelectedIndex(0);
		cBox4.setMaximumRowCount(2);
		frmEliteDangerousBeta.getContentPane().add(cBox4);
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
		frmEliteDangerousBeta.setBounds(display.x, display.y, display.width, display.height);
		frmEliteDangerousBeta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEliteDangerousBeta.setUndecorated(false);  // Frame cannot be adjusted during game
		frmEliteDangerousBeta.setResizable(false);
		frmEliteDangerousBeta.getContentPane().setLayout(null);
		
		
		// Initializing displays
		JLabel lblMedicalStore = new JLabel("Medical Store");
		lblMedicalStore.setOpaque(true);
		lblMedicalStore.setFont(new Font("Dialog", Font.BOLD, 24));
		lblMedicalStore.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicalStore.setBounds(662, 49, 600, 50);
		frmEliteDangerousBeta.getContentPane().add(lblMedicalStore);
		
		JLabel lblBandages = new JLabel("Bandages - 2 health restored");
		lblBandages.setOpaque(true);
		lblBandages.setBounds(576, 197, 205, 30);
		frmEliteDangerousBeta.getContentPane().add(lblBandages);
		
		JLabel lblMedKit = new JLabel("Med Kit - 5 health restored");
		lblMedKit.setOpaque(true);
		lblMedKit.setBounds(576, 244, 205, 30);
		frmEliteDangerousBeta.getContentPane().add(lblMedKit);
		
		JLabel lblSurgicalPackage = new JLabel("Surgical Package - Max health restored");
		lblSurgicalPackage.setOpaque(true);
		lblSurgicalPackage.setBounds(576, 286, 205, 30);
		frmEliteDangerousBeta.getContentPane().add(lblSurgicalPackage);
		
		JLabel lblPlaguePotion = new JLabel("Space Plague Potion");
		lblPlaguePotion.setOpaque(true);
		lblPlaguePotion.setBounds(576, 328, 218, 30);
		frmEliteDangerousBeta.getContentPane().add(lblPlaguePotion);
		
		JLabel label = new JLabel("$5");
		label.setOpaque(true);
		label.setBounds(806, 197, 46, 30);
		frmEliteDangerousBeta.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("$8");
		label_1.setOpaque(true);
		label_1.setBounds(806, 244, 46, 30);
		frmEliteDangerousBeta.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("$14");
		label_2.setOpaque(true);
		label_2.setBounds(804, 286, 46, 30);
		frmEliteDangerousBeta.getContentPane().add(label_2);
		
		JLabel label_6 = new JLabel("$50");
		label_6.setOpaque(true);
		label_6.setBounds(806, 328, 46, 30);
		frmEliteDangerousBeta.getContentPane().add(label_6);
		
		lblCurrentCash = new JLabel("Current Cash = $");
		lblCurrentCash.setOpaque(true);
		lblCurrentCash.setBounds(1306, 328, 220, 30);
		frmEliteDangerousBeta.getContentPane().add(lblCurrentCash);
		
		lbl1 = new JLabel("= $ 0");
		lbl1.setOpaque(true);
		lbl1.setBounds(980, 197, 104, 30);
		frmEliteDangerousBeta.getContentPane().add(lbl1);
		
		lbl2 = new JLabel("= $ 0");
		lbl2.setOpaque(true);
		lbl2.setBounds(980, 245, 104, 30);
		frmEliteDangerousBeta.getContentPane().add(lbl2);
		
		lbl3 = new JLabel("= $ 0");
		lbl3.setOpaque(true);
		lbl3.setBounds(979, 286, 105, 30);
		frmEliteDangerousBeta.getContentPane().add(lbl3);
		
		lbl4 = new JLabel("= $ 0");
		lbl4.setOpaque(true);
		lbl4.setBounds(979, 328, 105, 30);
		frmEliteDangerousBeta.getContentPane().add(lbl4);
		
		lblAmount = new JLabel("Selected Amount = $ 0");
		lblAmount.setOpaque(true);
		lblAmount.setBounds(1306, 370, 220, 30);
		frmEliteDangerousBeta.getContentPane().add(lblAmount);

		
		lblCountBandages = new JLabel("New label");
		lblCountBandages.setOpaque(true);
		lblCountBandages.setBounds(1177, 197, 84, 30);
		frmEliteDangerousBeta.getContentPane().add(lblCountBandages);
		lblCountBandages.setText(Integer.toString(bandage));
		
		lblCountMedkit = new JLabel("New label");
		lblCountMedkit.setOpaque(true);
		lblCountMedkit.setBounds(1177, 244, 84, 30);
		frmEliteDangerousBeta.getContentPane().add(lblCountMedkit);
		lblCountMedkit.setText(Integer.toString(medkit));
		
		lblCountSurgical = new JLabel("New label");
		lblCountSurgical.setOpaque(true);
		lblCountSurgical.setBounds(1177, 286, 84, 30);
		frmEliteDangerousBeta.getContentPane().add(lblCountSurgical);
		lblCountSurgical.setText(Integer.toString(surgical));
		
		lblCountPotion = new JLabel("New label");
		lblCountPotion.setOpaque(true);
		lblCountPotion.setBounds(1177, 328, 112, 30);
		frmEliteDangerousBeta.getContentPane().add(lblCountPotion);
		lblCountPotion.setText(Integer.toString(potion));
		cBHealth1 = new JProgressBar();
		cBHealth1.setBounds(662, 459, 150, 30);
		cBHealth1.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBHealth1);

		cBTired1 = new JProgressBar();
		cBTired1.setBounds(662, 496, 150, 30);
		cBTired1.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBTired1);
	
		cBHunger1 = new JProgressBar();
		cBHunger1.setBounds(662, 538, 150, 30);
		cBHunger1.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBHunger1);
	
		cBHealth2 = new JProgressBar();
		cBHealth2.setBounds(849, 459, 150, 30);
		cBHealth2.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBHealth2);
		
		cBTired2 = new JProgressBar();
		cBTired2.setBounds(849, 496, 150, 30);
		cBTired2.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBTired2);
		
		cBHunger2 = new JProgressBar();
		cBHunger2.setBounds(849, 536, 150, 30);
		cBHunger2.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBHunger2);

		cBHealth3 = new JProgressBar();
		cBHealth3.setBounds(1040, 459, 150, 30);
		cBHealth3.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBHealth3);
		
		cBTired3 = new JProgressBar();
		cBTired3.setBounds(1040, 496, 150, 30);
		cBTired3.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBTired3);

		cBHunger3 = new JProgressBar();
		cBHunger3.setBounds(1040, 536, 150, 30);
		cBHunger3.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBHunger3);

		cBHealth4 = new JProgressBar();
		cBHealth4.setBounds(1238, 459, 150, 30);
		cBHealth4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBHealth4);
		
		cBTired4 = new JProgressBar();
		cBTired4.setBounds(1238, 496, 150, 30);
		cBTired4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBTired4);
		
		cBHunger4 = new JProgressBar();
		cBHunger4.setBounds(1238, 536, 150, 30);
		cBHunger4.setStringPainted(true);
		frmEliteDangerousBeta.getContentPane().add(cBHunger4);

		msName4 = new JLabel("...");
		msName4.setOpaque(true);
		msName4.setFont(new Font("Dialog", Font.PLAIN, 18));
		msName4.setBounds(1238, 578, 150, 30);
		frmEliteDangerousBeta.getContentPane().add(msName4);

		msName3 = new JLabel("...");
		msName3.setOpaque(true);
		msName3.setFont(new Font("Dialog", Font.PLAIN, 18));
		msName3.setBounds(1040, 578, 150, 30);
		frmEliteDangerousBeta.getContentPane().add(msName3);

		msName2 = new JLabel("...");
		msName2.setOpaque(true);
		msName2.setFont(new Font("Dialog", Font.PLAIN, 18));
		msName2.setBounds(849, 578, 150, 30);
		frmEliteDangerousBeta.getContentPane().add(msName2);
		
		msName1 = new JLabel("...");
		msName1.setOpaque(true);
		msName1.setFont(new Font("Dialog", Font.PLAIN, 18));
		msName1.setBounds(662, 580, 150, 30);
		frmEliteDangerousBeta.getContentPane().add(msName1);
		
		JLabel labell = new JLabel("Crew Info");
		labell.setOpaque(true);
		labell.setHorizontalAlignment(SwingConstants.CENTER);
		labell.setBounds(910, 371, 150, 30);
		labell.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(labell);
	
		JLabel label0 = new JLabel("Hunger:");
		label0.setOpaque(true);
		label0.setBounds(518, 536, 124, 30);
		label0.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label0);

		JLabel label1 = new JLabel("Tiredness:");
		label1.setOpaque(true);
		label1.setBounds(518, 494, 124, 30);
		label1.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label1);
	
		JLabel label2 = new JLabel("Health:");
		label2.setOpaque(true);
		label2.setBounds(518, 457, 124, 30);
		label2.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Name:");
		label3.setOpaque(true);
		label3.setBounds(518, 578, 124, 30);
		label3.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label3);

		JLabel label4 = new JLabel("Type:");
		label4.setOpaque(true);
		label4.setBounds(518, 417, 124, 30);
		label4.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmEliteDangerousBeta.getContentPane().add(label4);

		cBType1 = new JLabel("...");
		cBType1.setOpaque(true);
		cBType1.setBounds(662, 417, 150, 30);
		cBType1.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cBType1);

		cBType2 = new JLabel("...");
		cBType2.setOpaque(true);
		cBType2.setBounds(849, 417, 150, 30);
		cBType2.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cBType2);

		cBType3 = new JLabel("...");
		cBType3.setOpaque(true);
		cBType3.setBounds(1040, 417, 150, 30);
		cBType3.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cBType3);

		cBType4 = new JLabel("...");
		cBType4.setOpaque(true);
		cBType4.setBounds(1238, 417, 150, 30);
		cBType4.setFont(new Font("Dialog", Font.PLAIN, 18));
		frmEliteDangerousBeta.getContentPane().add(cBType4);
		frmEliteDangerousBeta.getContentPane().setLayout(null);


		//initialize storage arrays
		cBType();
		msName();
		
		cBHealth();
		cBTired();
		cBHunger();
		
		// Button Actions
		btnBuy();
		cBoxActions();
		backToOutpost();
	}
	
	
	/*
	 * Create the application.
	*/
	public MedicalStore() 
	{
		initialize();
		totalCash();
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
					MedicalStore window = new MedicalStore();
					window.frmEliteDangerousBeta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

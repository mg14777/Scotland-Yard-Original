package solution;
import java.util.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;

public class LoadMenu extends JPanel{
	JLabel selectFile = new JLabel();
	JLabel rounds, players, currentPlayer, playerInfo;
	JLabel ok,back;
	
	JComboBox fileBox = new JComboBox();
	ImageIcon select = new ImageIcon("./resources/selectFile.gif");
	ImageIcon backpic = new ImageIcon("./resources/back.gif");
	ImageIcon okpic = new ImageIcon("./resources/ok.gif");
	/**
	 * Displays the Load Menu allowing player to load any saved game
	 */
	LoadMenu() {
		ok = new JLabel();
		back = new JLabel();
		Border border = BorderFactory.createEmptyBorder(50, 10, 50, 10);
		Border border1 = BorderFactory.createEmptyBorder(30, 10, 30, 10);
		Border border2 = BorderFactory.createEmptyBorder(10, 0, 10, 300);
		Box box0 = Box.createVerticalBox();
		Box box1 = Box.createHorizontalBox();
		Box boxr = Box.createHorizontalBox();
		Box boxp = Box.createHorizontalBox();
		Box boxc = Box.createHorizontalBox();
		Box combox = Box.createHorizontalBox();
		Box box2 = Box.createHorizontalBox();
		selectFile.setIcon(select);
		box1.add(selectFile);
		rounds = new JLabel("Rounds: - ",SwingConstants.RIGHT);
		players = new JLabel("Players: - ");
		currentPlayer = new JLabel("CurrentPlayer: - ");
		rounds.setAlignmentX(Component.RIGHT_ALIGNMENT);
		players.setAlignmentX(Component.RIGHT_ALIGNMENT);
		currentPlayer.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		rounds.setFont(new Font("Georgia",Font.PLAIN,30));
		players.setFont(new Font("Georgia",Font.PLAIN,30));
		currentPlayer.setFont(new Font("Georgia",Font.PLAIN,30));
		rounds.setForeground(Color.YELLOW);
		players.setForeground(Color.RED);
		currentPlayer.setForeground(Color.BLUE);
		combox.add(fileBox);
		boxr.add(rounds);
		boxp.add(players);
		boxc.add(currentPlayer);
	    back.setIcon(backpic);
	    back.setText("                                                                                 ");
	    ok.setIcon(okpic);
		box2.add(back);
		box2.add(ok);
		box1.setBorder(border);
		boxr.setBorder(border1);
		boxp.setBorder(border1);
		boxc.setBorder(border1);
		box2.setBorder(border);
		box0.add(box1);
		box0.add(combox);
		box0.add(boxr);
		box0.add(boxp);
		box0.add(boxc);
		box0.add(box2);
		this.add(box0);
		this.setBackground(Color.BLACK);
	}
	/**
	 * 
	 * @param directory List of all Text Files in the working directory
	 * @return Text Files that have the keyword "save" in the end
	 */
	List<String> textFiles(String directory) {
		  List<String> textFiles = new ArrayList<String>();
		  File dir = new File(directory);
		  for (File file : dir.listFiles()) {
		    if (file.getName().endsWith(("save.txt"))) {
		      textFiles.add(file.getName());
		    }
		  }
		  return textFiles;
		}
	
	void createFile(List<String> files) {
		fileBox.addItem("---");
		for(String file: files)
			fileBox.addItem(file);
	}
	public void preLoadPlayers(Scanner in) {

	}
	/**
	 * Displays details of the Game that the player wants to load
	 * @param fileToLoad The Game chosen by player to load
	 */
	public void preLoadFile(String fileToLoad) {
		try {
			File file = new File(fileToLoad);	
			Scanner in = new Scanner(file);
			players.setText("Players: " + in.nextLine());
			currentPlayer.setText("Current player: " + in.nextLine());
			in.nextLine();
			rounds.setText("Rounds: " + in.nextLine().split(" ").length);
		} catch(Exception e) { System.out.println("Inexistent load file");}
	}
	

	public void addOKListener(MouseAdapter listenExitGame) {
		ok.addMouseListener(listenExitGame);
	}
	public void addBackListener(MouseAdapter listenExitGame) {
		back.addMouseListener(listenExitGame);
	}
}
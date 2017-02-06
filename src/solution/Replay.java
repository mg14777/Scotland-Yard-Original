package solution;
import java.io.*;
import java.util.Timer;
import java.util.*;

import javax.swing.*;
import javax.swing.border.Border;

import scotlandyard.Colour;

import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;
/** 
 * reads the replay file and "Plays"the game
 */
public class Replay extends JPanel {
	private GameView game;
	private int t,tw;
	public Timer timer;
	private int numberOfNull = 0, linesInLabel = 0;
	private int interval,intervalw;
	public int noPlayers, currentLineNumber = 0;
	public Boolean playOn = false;
	ScotlandYardViewer view;
	JButton play, forward, back;
	List<String[]> gameInfo = new ArrayList<String[]>();
	Box boxMain = Box.createHorizontalBox();
	JPanel sidePanel = new JPanel();
	Box box = Box.createVerticalBox();
	Box box1 = Box.createHorizontalBox();
	Border border = BorderFactory.createEmptyBorder(10, 10, 10, 60);
	Border border1 = BorderFactory.createEmptyBorder(0, 30, 10, 50);
	JLabel replay = new JLabel("Replay");
	JLabel moveInfo = new JLabel("<html>",SwingConstants.LEFT);
	JPanel p = new JPanel();
	/**
	 * Initialises the replay and creates the game view, same as a new game
	 * @param view
	 */
	Replay(ScotlandYardViewer view) {
		this.view = view;
		this.game = new GameView();
		JScrollPane scrollPane = new JScrollPane(game);
		JPanel buttons = new JPanel();
		replay.setFont(new Font("Serif",Font.PLAIN,80));
		replay.setForeground(Color.RED);
		moveInfo.setForeground(Color.RED);
		replay.setBorder(border1);
		box1.add(replay);
		box.add(box1);
		p.add(moveInfo);
		box.add(p);
		sidePanel.add(box);
		sidePanel.setBackground(Color.BLACK);
		play = new JButton("Play");
		forward = new JButton("Forward");
		back = new JButton("Back");
		buttons.add(play);
		buttons.add(forward);
		buttons.add(back);
		boxMain.add(scrollPane);
		boxMain.add(sidePanel);
		buttons.setBackground(Color.BLACK);
		buttons.setBorder( BorderFactory.createLineBorder(Color.YELLOW,5));
		sidePanel.setBorder( BorderFactory.createLineBorder(Color.YELLOW,5));
		view.getContentPane().remove(view.box);
		view.getContentPane().remove(view.xlog);
		view.getContentPane().remove(view.bar);
		view.getContentPane().add(boxMain);
		view.add(buttons,BorderLayout.SOUTH);
		view.revalidate();
		view.repaint();
		t = tw = 0;
		interval = intervalw = 2;
	}
	
	/**
	 * For each move in the file, draw all players's positions
	 */
	public void displayOnMap() {
        this.currentLineNumber++;
		String[] currentLine = gameInfo.get(this.currentLineNumber);
		List<Point> positions = new ArrayList<Point>();
		for(int i = 0; i<noPlayers*2; i = i + 2)
			positions.add(new Point((int)Double.parseDouble(currentLine[i]), (int)Double.parseDouble(currentLine[i+1])));
		game.paintPlayers(positions);
		if(this.currentLineNumber<gameInfo.size()-1&&playOn) {
			timer = new Timer();
			int delay = 1000, period = 1000; 
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					int count = setInterval();   
				}
			}, delay, period);
		}
		else {
			this.play.setText("Play");
			this.playOn = false;
		}
		if(linesInLabel%30==0)
			this.moveInfo.setText("<html> ");
		//null initialises location, there is no move
		if(!currentLine[noPlayers*2].equals("null")) {
			linesInLabel++;
			this.moveInfo.setText(this.moveInfo.getText() + (currentLineNumber- numberOfNull)  + ". ");
			if(currentLine.length>noPlayers*2 + 2) {
				for(int i = noPlayers*2; i<currentLine.length; i ++)
					this.moveInfo.setText(this.moveInfo.getText() + currentLine[i] + " ");
				this.moveInfo.setText(this.moveInfo.getText() + "                      <br>");
			}
		}
		else 
			numberOfNull ++;
		if(currentLineNumber == gameInfo.size()-1) {
			play.setEnabled(false);
			forward.setEnabled(false);
			back.setEnabled(true);
		}
		else 
			if(currentLineNumber == numberOfNull) {
				play.setEnabled(true);
				forward.setEnabled(true);
				back.setEnabled(false);
			}
			else if(playOn) {
				play.setEnabled(true);
				forward.setEnabled(false);
				back.setEnabled(false);
			}
			else {
				play.setEnabled(true);
				forward.setEnabled(true);
				back.setEnabled(true);
			}
	}
	
	int setInterval() {
		if(currentLineNumber<gameInfo.size()-1)
		 if (interval == 1 && t==0) {
		        timer.cancel();
		        t = tw;
		        interval = intervalw;
		        displayOnMap();
		    }
		 return  --interval;
	}
	
	/**
	 * reads player information from file
	 * @param in
	 */
	void intitalisePlayersfromFile(Scanner in) {
		String[] topLine = in.nextLine().split(" ");
		List<Colour> players = new ArrayList<Colour>();
		for(int i = 0; i<topLine.length; i++)
			players.add(Colour.valueOf(topLine[i]));
		game.initialisePlayers(players);
		this.noPlayers = players.size();
	}
	
	/**
	 * Reads the information from file
	 * @param in
	 */
	void readFile(Scanner in) {
		while(in.hasNextLine())
			gameInfo.add(in.nextLine().split(" "));
	}
	
	/**
	 * Initialises the file to read from
	 */
	void loadReplay() {
		try {
			File file = new File("./resources/replay.txt");
			Scanner in = new Scanner(file);
			intitalisePlayersfromFile(in);
			readFile(in);
			displayOnMap();
		} catch(Exception e) { System.out.println("Error loading replay"); }
	}
	
	public void addPlayListener(ActionListener listenPlay) {
		play.addActionListener(listenPlay);
	}
	
	public void addForwardListener(ActionListener listenPlay) {
		forward.addActionListener(listenPlay);
	}
	
	public void addBackPlayListener(ActionListener listenBackPlay) {
		back.addActionListener(listenBackPlay);
	}	
}
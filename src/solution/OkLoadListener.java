package solution;
import scotlandyard.*;

import java.awt.event.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


import java.io.*;

class OkLoadListener extends MouseAdapter {
	ScotlandYardApplication c;
	OkLoadListener(ScotlandYardApplication c) {
		this.c = c;
		
	}
	/**
	 * Loads the number of rounds for a Load Game
	 * @param in Scanned input file to load
	 * @return Rounds to be loaded for a Load Game
	 */
	public List<Boolean> loadRounds(Scanner in) {				//gets the rounds from file
		List<Boolean> rounds = new ArrayList<Boolean>();
		String topLine = in.nextLine();
		String[] parts = topLine.split(" ");
		for(int i=0; i<parts.length; i++)
			rounds.add(Boolean.parseBoolean(parts[i]));
		return rounds;
	}
	/**
	 * Loads number of Tickets of each player for a load game
	 * @param parts
	 * @return Tickets to be loaded for a Load Game
	 */
	public Map<Ticket, Integer> loadTickets(String[] parts) {			//Loads tickets from file
		Map<Ticket, Integer> tickets = new HashMap<Ticket, Integer>();
		tickets.put(Ticket.Bus, Integer.parseInt(parts[2]));
		tickets.put(Ticket.Taxi, Integer.parseInt(parts[3]));
		tickets.put(Ticket.Underground, Integer.parseInt(parts[4]));
		tickets.put(Ticket.DoubleMove, Integer.parseInt(parts[5]));
		tickets.put(Ticket.SecretMove, Integer.parseInt(parts[6]));
		return tickets;
	}
	/**
	 * Loads the participating players in a Load Game
	 * @param in Scanned input file to load
	 * @return List of players participating in the game
	 */
	public List<Colour> loadColours(Scanner in) {
		List<Colour> colours = new ArrayList<Colour>();
		String topLine = in.nextLine();
		String[] parts = topLine.split(" ");
		for(int i = 0; i<parts.length; i++)
			colours.add(Colour.valueOf(parts[i]));
		return colours;
	}
	
		public void mouseClicked(MouseEvent e) {
			c.playSound();
			File file = new File((String) c.menu.fileBox.getItemAt(c.menu.fileBox.getSelectedIndex()));	
			Scanner in = null;
	        try {
				in = new Scanner(file);
				for(Colour colour: loadColours(in)) {
					switch(colour) {
						case Green : c.toBeEnabled.add(1);
									 break;
						case Blue : c.toBeEnabled.add(2);
									break;
						case White: c.toBeEnabled.add(3);
									break;
						case Red :	c.toBeEnabled.add(4);
									break;
						case Yellow: c.toBeEnabled.add(5);
									break;	
					}
				}
				Colour currentPlayer = Colour.valueOf(in.nextLine());
				int numberOfDetectives = Integer.parseInt(in.nextLine());
				List<Boolean> rounds = loadRounds(in);
	       
				
				try {
					c.model = new ScotlandYardModel(numberOfDetectives, rounds, "graph.txt");
					c.join = new Join();
				} catch(Exception i) {
					System.out.println("Graph file not found");
				}
			loadPlayers(in, numberOfDetectives);
			c.model.setCurrentPlayer(currentPlayer);
	        } catch (FileNotFoundException i) {
				System.out.println(e);
				}
			
		}
		/**
		 * Loads the participating players who have to Join the Game in a Load Game
		 * @param in Scanned input file to Load
		 * @param numberOfDetectives Number of Detectives in the game
		 */
		public void loadPlayers(Scanner in, int numberOfDetectives) {
			
			while(in.hasNextLine()) {
				String topLine = in.nextLine(); System.out.println(topLine + "HH");
				String[] parts = topLine.split(" ");
				Colour colour = Colour.valueOf(parts[0]);
				int i;
				switch(colour) {
					case Black : {
						 i = 0;
						 c.join.join.addActionListener(c.new JoinListener(Colour.Black,i,loadTickets(parts),Integer.parseInt(parts[1])));
						 break;
					}
					case Green : {
						 i = 1;
						 c.join.join1.addActionListener(c.new JoinListener(Colour.Green,i,loadTickets(parts),Integer.parseInt(parts[1])));
						 c.join.njoin1.addActionListener(c.new RemoveListener(c.new JoinListener(Colour.Green,i,loadTickets(parts),Integer.parseInt(parts[1]))) );
						 break;
					}
					case Blue : {
						 i = 2;
						 c.join.join2.addActionListener(c.new JoinListener(Colour.Blue,i,loadTickets(parts),Integer.parseInt(parts[1])));
						 c.join.njoin2.addActionListener(c.new RemoveListener(c.new JoinListener(Colour.Blue,i,loadTickets(parts),Integer.parseInt(parts[1]))) );
						 break;
					}
					case White : {
						 i = 3;
						 c.join.join3.addActionListener(c.new JoinListener(Colour.White,i,loadTickets(parts),Integer.parseInt(parts[1])));
						 c.join.njoin3.addActionListener(c.new RemoveListener(c.new JoinListener(Colour.White,i,loadTickets(parts),Integer.parseInt(parts[1]))) );
						 break;
					}
					case Red : {
						 i = 4;
						 c.join.join4.addActionListener(c.new JoinListener(Colour.Red,i,loadTickets(parts),Integer.parseInt(parts[1])));
						 c.join.njoin4.addActionListener(c.new RemoveListener(c.new JoinListener(Colour.Red,i,loadTickets(parts),Integer.parseInt(parts[1]))) );
						 break;
					}
					case Yellow : {
						 i = 5;
						 c.join.join5.addActionListener(c.new JoinListener(Colour.Yellow,i,loadTickets(parts),Integer.parseInt(parts[1])));
						 c.join.njoin5.addActionListener(c.new RemoveListener(c.new JoinListener(Colour.Yellow,i,loadTickets(parts),Integer.parseInt(parts[1]))) );
						 break;
					}
					
				}	
			}
		}
	}



































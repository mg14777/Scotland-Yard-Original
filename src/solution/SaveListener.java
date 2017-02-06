package solution;
import scotlandyard.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import java.awt.*;

	/**
	 * Used for saving games to file in the form of:
	 * Players -> currentPlayer -> numberOfDetectives -> rounds -> player location and tickets
	 */
		class SaveListener implements ActionListener {
			ScotlandYardModel model;
			int numberOfDetectives;
			SaveListener(ScotlandYardModel model,int numberOfDetectives) {
				this.model = model;
				this.numberOfDetectives = numberOfDetectives;	
		}
		/**
		 * Saves the rounds to the file	
		 * @param printer the file where the round should be saved
		 */
		private void saveRounds(PrintWriter printer) {			
			List<Boolean> rounds = model.getRounds();
			for(int i = model.getRound(); i<rounds.size();i++)
				printer.print(rounds.get(i) + " ");
			printer.println();
		}
		/**
		 * saves the tickets a player has
		 * @param printer	
		 * @param player	the player whose thickets will be saved
		 */
		private void savePlayerTickets(PrintWriter printer, Colour player) {		
			for(Ticket ticket: Ticket.values())
				printer.print(" " + model.getPlayerTickets(player, ticket));
			printer.println();
		}
		
		/**
		 * saves the players(colours + location) to the file
		 * @param printer
		 */
		private void savePlayers(PrintWriter printer) {			
			for(Colour player: model.getPlayers()) {
				if(player.equals(Colour.Black))
					printer.print(player.toString() + " " + model.mrxActual());
				else
					printer.print(player.toString() + " " + model.getPlayerLocation(player));
				savePlayerTickets(printer, player);
			}
		}
		
		/**
		 * saves current player-> numberOfDetectives -> rounds -> player, location, tickets
		 */
		public void actionPerformed(ActionEvent actionEvent) {	
			int i = -1;
			File file;
			do {
				i++;
				file = new File(i+"save.txt");
			}
			while(file.exists());
			FileWriter writer;
			//Opens file for saving
			try {
				writer = new FileWriter(i+"save.txt");
				PrintWriter printer = new PrintWriter(writer);
				for(Colour colour: model.getPlayers())
					printer.print(colour.toString() + " ");
				printer.println();
				printer.println(model.getCurrentPlayer());
				printer.println(numberOfDetectives);
				saveRounds(printer);
				savePlayers(printer);
				printer.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
}
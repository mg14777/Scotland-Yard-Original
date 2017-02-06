package solution;

import scotlandyard.*;

import java.util.*;

class RealPlayer {
	public Colour colour;
	public Player player;
	public int location;
	public Map<Ticket, Integer> tickets;
	RealPlayer(Player player,Colour colour, int location, Map<Ticket, Integer> tickets) {
		this.colour = colour;
		this.location = location;
		this.tickets = tickets;
		this.player = player;
		 
	}
}
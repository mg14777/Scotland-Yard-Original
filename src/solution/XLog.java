package solution;
import scotlandyard.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.*;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;
/**
 * 
 * Displays the Mr.X log containing tickets used by him in each round
 *
 */
public class XLog extends JPanel{
	List<JLabel> roundx = new ArrayList<JLabel>();
	JLabel log = new JLabel("Mr. X Log        ");
	int numRounds;
	ImageIcon taxi = new ImageIcon("./resources/TAXI.png");
	ImageIcon bus = new ImageIcon("./resources/BUS.png");
	ImageIcon train = new ImageIcon("./resources/TRAIN.png");
	ImageIcon secret = new ImageIcon("./resources/SECRET.jpg");
	JButton possible = new JButton("Possible Moves");
	XLog(int numRounds) {
		
		this.numRounds = numRounds;
		log.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
		log.setForeground(Color.GREEN);
		this.add(log);
		for(int i =0;i<=numRounds;i++) {
			JLabel label = new JLabel();
			roundx.add(label);
			if(i!=0)
				roundx.get(i).setText(Integer.toString(i));
			roundx.get(i).setFont(new Font("Comic Sans MS",Font.PLAIN,20));
			roundx.get(i).setForeground(Color.CYAN);
			this.add(roundx.get(i));
			this.setBackground(Color.BLACK);
		}
		this.add(possible);
			
	}
	/**
	 * Updates the Log with the Tickets used by Mr.X in each round
	 * @return Tickets used by Mr.X after each round
	 */
	public void update(Move move,int round) {
		
		if(move instanceof MoveDouble) {
			set(((MoveDouble) move).moves.get(0),round+1);
			set(((MoveDouble) move).moves.get(1),round+2);
		}
		if(move instanceof MoveTicket) 
			set(move,round+1);                                                        //System.out.println("Yes");
			
		
	}
	/**
	 * Sets the round number in the log to the Ticket Icon used by Mr.X
	 * @param move Type of move made by Mr.X
	 * @param round Current Round Number
	 */
	public void set(Move move,int round) {
		roundx.get(round).setText("");
		if(((MoveTicket) move).ticket.equals(Ticket.Bus))
			roundx.get(round).setIcon(bus);
		else if(((MoveTicket) move).ticket.equals(Ticket.Taxi))
			roundx.get(round).setIcon(taxi);
		else if(((MoveTicket) move).ticket.equals(Ticket.Underground))
			roundx.get(round).setIcon(train);
		else
			roundx.get(round).setIcon(secret);;
		
	}
		
	public void addPossibleListener(ActionListener listenPossible) {	
		possible.addActionListener(listenPossible);
	}
	
}

package solution;
import scotlandyard.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;


/**
 * 
 * Displays the Side Panel containing information about Tickets of each player and the round number
 *
 */
public class SidePanel extends JPanel{
	ScotlandYardApplication controller;
	List<Colour> players;
	BufferedImage img, imgBlock, background;
	JLabel round = new JLabel();
	JPanel roundp = new JPanel();
	JLabel x = new JLabel();
	List<JLabel> det = new ArrayList<JLabel>();
	List<JLabel> player = new ArrayList<JLabel>();
	int taxiTicket;
	int busTicket;
	int trainTicket;
	JLabel taxi = new JLabel();
	JLabel bus = new JLabel();
	JLabel train = new JLabel();
	Box box = Box.createVerticalBox();
	Box box0 = Box.createHorizontalBox();
	Box box1 = Box.createHorizontalBox();
	Box box2 = Box.createHorizontalBox();
	Box box3 = Box.createHorizontalBox();
	List<Box> boxv = new ArrayList<Box>();
	ImageIcon circle = new ImageIcon("./resources/circle0.png");
	ImageIcon taxiIcon = new ImageIcon("./resources/TAXI.png");
	ImageIcon busIcon = new ImageIcon("./resources/BUS.png");
	ImageIcon trainIcon = new ImageIcon("./resources/TRAIN.png");
	ImageIcon doubleIcon = new ImageIcon("./resources/x2.png");
	ImageIcon secretIcon = new ImageIcon("./resources/SECRET.jpg");
	
	SidePanel(ScotlandYardApplication controller) {
		this.controller = controller;
		try {
			img = ImageIO.read(new File("./resources/side2.jpeg"));
		}
		catch(IOException e) {
			e.printStackTrace();			
		}
		int k = 0;
		for(Colour colour : controller.model.getPlayers()) {
			JLabel label = new JLabel();
			JLabel label1 = new JLabel();
			JLabel label2 = new JLabel();
			JLabel label3 = new JLabel();
			JLabel label4 = new JLabel();
			det.add(label);
			det.add(label1);
			det.add(label2);
			if(k == 0) {
				det.add(label3);
				det.add(label4);
			}
			k++;
			Box boxvt = Box.createVerticalBox();
			boxv.add(boxvt);
			JLabel player1 = new JLabel();
			player.add(player1);
		}
		taxi.setIcon(taxiIcon);
		bus.setIcon(busIcon);
		train.setIcon(trainIcon);
		
		
	}
	/**
	 * Updates the Round Number on the Side Panel to the Current Round
	 * @param flag Integer signal to specify the occurrence of Single or Double Move
	 * @param roundn roundCounter from the Model class
	 */
	public void updateRound(int flag,int roundn) {
			if(flag == -1)
				round.setText(Integer.toString(roundn));
			else if(flag == 1)
				round.setText(Integer.toString(roundn+2));
			else
				round.setText(Integer.toString(roundn+1));
				
		round.setFont(new Font("Serif",Font.PLAIN,80));
		round.setForeground(Color.WHITE);
		
	}
	/**
	 * Updates the Current Number of Tickets each Player has for each transport medium
	 */
	public void updateTicket() {
		Border border = BorderFactory.createEmptyBorder(13, 0, 10, 10);
		Border border1 = BorderFactory.createEmptyBorder(0, 30, 10, 50);
		Border border2 = BorderFactory.createEmptyBorder(0, 30, 80, 50);
		box0.add(round);
		int i=0;
		int j = 0;
		round.setBorder(border);
		int secrett = controller.model.getPlayerTickets(Colour.Black, Ticket.SecretMove);
		int doublet = controller.model.getPlayerTickets(Colour.Black, Ticket.DoubleMove);

		for(Colour colour : controller.model.getPlayers()) {
			int flag = 0;
			int taxit, bust, traint;
			taxit = controller.model.getPlayerTickets(colour, Ticket.Taxi);
			bust = controller.model.getPlayerTickets(colour, Ticket.Bus);
			traint = controller.model.getPlayerTickets(colour, Ticket.Underground);
			if(colour.equals(Colour.Black))
				flag = 1;
			player.get(j).setText("<html>"+colour.toString()+"<br></html>");
			player.get(j).setForeground(Color.GREEN);
			player.get(j).setFont(new Font("Algerian",Font.PLAIN,30));
			det.get(i).setIcon(taxiIcon);
			det.get(i).setText(Integer.toString(taxit));
			det.get(i).setHorizontalTextPosition(SwingConstants.RIGHT);
			det.get(i+1).setIcon(busIcon);
			det.get(i+1).setText(Integer.toString(bust));
			det.get(i+1).setHorizontalTextPosition(SwingConstants.RIGHT);
			det.get(i+2).setIcon(trainIcon);
			det.get(i+2).setText(Integer.toString(traint));
			det.get(i+2).setHorizontalTextPosition(SwingConstants.RIGHT);
			if(flag==1) {
				det.get(i+3).setIcon(secretIcon);
				det.get(i+3).setText(Integer.toString(secrett));
				det.get(i+3).setHorizontalTextPosition(SwingConstants.RIGHT);
				det.get(i+4).setIcon(doubleIcon);
				det.get(i+4).setText(Integer.toString(doublet));
				det.get(i+4).setHorizontalTextPosition(SwingConstants.RIGHT);
			}
			for(int k=0;k<3;k++) {
				det.get(i+k).setFont(new Font("Comic Sans MS",Font.PLAIN,20));
				det.get(i+k).setForeground(Color.GREEN);
			}
			if(j==1)
				boxv.get(j).setBorder(border2);
			else
				boxv.get(j).setBorder(border1);
			boxv.get(j).add(player.get(j));
			boxv.get(j).add(det.get(i));
			boxv.get(j).add(det.get(i+1));
			boxv.get(j).add(det.get(i+2));
			if(j<2)
				box1.add(boxv.get(j));
			else if(j<4)
				box2.add(boxv.get(j));
			else if(j<6)
				box3.add(boxv.get(j));
			else
				;
			if(flag==1) {
				det.get(i+3).setFont(new Font("Comic Sans MS",Font.PLAIN,20));
				det.get(i+3).setForeground(Color.GREEN);
				det.get(i+4).setFont(new Font("Comic Sans MS",Font.PLAIN,20));
				det.get(i+4).setForeground(Color.GREEN);
				boxv.get(j).add(det.get(i+3));
				boxv.get(j).add(det.get(i+4));
				i = i + 5;
			}
			else
				i = i+3;
			j++;

		}
		box.add(box0);
		box.add(box1);
		box.add(box2);
		box.add(box3);
		this.add(box,BorderLayout.NORTH);
		this.setBackground(Color.BLACK);
		//load images
		try {
			background = ImageIO.read(new File("./resources/side2.jpeg"));
			img = ImageIO.read(new File("./resources/dsa.png"));
			imgBlock = ImageIO.read(new File("./resources/block.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // paint the background image and scale it to fill the entire space
        int i = controller.model.getPlayers().size();
		g.drawImage(background, 0, 0,  null);
		g.drawImage(img, this.getWidth()/2 - 150/2, this.getHeight()/2*1/5 - 150/2, 150, 150,  null);
	}	
}
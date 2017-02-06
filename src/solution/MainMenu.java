package solution;

import scotlandyard.*;

import javax.swing.*;

import java.io.*;
import java.text.*;
import java.util.*;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.imageio.*;

import java.awt.image.*;
import java.awt.event.*;

public class MainMenu extends JPanel{
	//JFrame frame = new JFrame();
	JButton newGame = new JButton("New Game");
	JButton loadGame = new JButton("Load Game");
	JButton saveGame = new JButton("Save Game");
	JButton exitGame = new JButton("Exit Game");
	JLabel panelimg = new JLabel();
	JLabel panel0 = new JLabel();
	JLabel panel1 = new JLabel();
	JLabel panel2 = new JLabel();
	JLabel panel3 = new JLabel();
	JLabel panel4 = new JLabel("A CALMUD PRODUCTION");
	JLabel panel5 = new JLabel("MISTER X © 2015    ");
	BufferedImage img = null;
	/**
	 * Displays and sets the contents of the Main Menu
	 */
	MainMenu() {
		try
		{   
			img = ImageIO.read(new File("./resources/black1.png"));
		}
		catch( IOException e )
		{
			System.out.println(e);
		}
		exitGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }});
		Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		
		Box box = Box.createVerticalBox();
		box.setBorder(border);
		ImageIcon iconimg = new ImageIcon("./resources/sy.jpg");
		panelimg.setIcon(iconimg);
		ImageIcon icon0 = new ImageIcon("./resources/newgame.png");
		panel0.setIcon(icon0);
		ImageIcon icon1 = new ImageIcon("./resources/loadgame.png");
		panel1.setIcon(icon1);
		ImageIcon icon2 = new ImageIcon("./resources/savegame.png");
		panel2.setIcon(icon2);
		ImageIcon icon3 = new ImageIcon("./resources/exitgame.png");
		panel3.setIcon(icon3);
		Box boximg = Box.createHorizontalBox();
		boximg.add(panelimg);
		Box box0 = Box.createHorizontalBox();
		box0.add(panel0);
	
		Box box1 = Box.createHorizontalBox();
		box1.add(panel1);
		
		Box box2 = Box.createHorizontalBox();
		box2.add(panel2);
		
		Box box3 = Box.createHorizontalBox();
		box3.add(panel3);
		panel4.setForeground(Color.WHITE);
		panel5.setForeground(Color.WHITE);
		Box box4 = Box.createHorizontalBox();
		box4.add(panel4);
		Box box5 = Box.createHorizontalBox();
		box5.add(panel5);
		/*panel0.setBorder(border);
		panel1.setBorder(border);
		panel2.setBorder(border);
		panel3.setBorder(border);
		*/
		boximg.setBorder(border);
		box0.setBorder(border);
		box1.setBorder(border);
		//box2.setBorder(border);
		box3.setBorder(border);
		
		box.add(boximg);
		box.add(box0);
		box.add(box1);
		box.add(box2);
		box.add(box3);
		box.add(box4);
		box.add(box5);
		this.add(box);
	}
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(img, 0, 0, null);
	}
	public void addNewGameListener(MouseAdapter listenNewGame) {
		panel0.addMouseListener(listenNewGame);
	}
	
	
	public void addLoadGameListener(MouseAdapter listenLoadGame) {
		panel1.addMouseListener(listenLoadGame);
	}
	public void addOptionListener(MouseAdapter listenOption) {
		panel2.addMouseListener(listenOption);
	}
	public void addExitGameListener(MouseAdapter listenExitGame) {
		panel3.addMouseListener(listenExitGame);
	}
}

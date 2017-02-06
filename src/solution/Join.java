package solution;
import scotlandyard.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
public class Join {
	JFrame frame = new JFrame("Join Players");
	JButton join = new JButton("Join");
	JButton njoin = new JButton("Remove");
	JButton join1 = new JButton("Join");
	JButton njoin1 = new JButton("Remove");
	JButton join2 = new JButton("Join");
	JButton njoin2 = new JButton("Remove");
	JButton join3 = new JButton("Join");
	JButton njoin3 = new JButton("Remove");
	JButton join4 = new JButton("Join");
	JButton njoin4 = new JButton("Remove");
	JButton join5 = new JButton("Join");
	JButton njoin5 = new JButton("Remove");
	JLabel panel0 = new JLabel();
	JLabel panel1 = new JLabel();
	JLabel panel2 = new JLabel();
	JLabel panel3 = new JLabel();
	JLabel panel4 = new JLabel();
	JLabel panel5 = new JLabel();
	
	BufferedImage img = null;
	/**
	 * Initialises the Join Frame with all the Players and their respective join and remove buttons
	 */
	Join() {
		try
		{   
			img = ImageIO.read(new File("./resources/black.png"));
		}
		catch( IOException e )
		{
			System.out.println(e);
		}
		Border borderb = BorderFactory.createEmptyBorder(60, 10, 60, 10);
		Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border border1 = BorderFactory.createEmptyBorder(40, 30, 40, 10);
		ImageIcon icon1 = new ImageIcon("./resources/Black.gif");
		JLabel label = new JLabel(icon1);
		panel0.setIcon(icon1);
		ImageIcon icon2 = new ImageIcon("./resources/Green.gif");
		JLabel label1 = new JLabel(icon2);
		panel1.setIcon(icon2);
		ImageIcon icon3 = new ImageIcon("./resources/Blue.gif");
		JLabel label2 = new JLabel(icon3);
		panel2.setIcon(icon3);
		ImageIcon icon4 = new ImageIcon("./resources/White.gif");
		JLabel label3 = new JLabel(icon4);
		panel3.setIcon(icon4);
		ImageIcon icon5 = new ImageIcon("./resources/Red.gif");
		JLabel label4 = new JLabel(icon5);
		panel4.setIcon(icon5);
		ImageIcon icon6 = new ImageIcon("./resources/Yellow.gif");
		JLabel label5 = new JLabel(icon6);
		panel5.setIcon(icon6);
		panel0.setBorder(border);
		panel1.setBorder(border);
		panel2.setBorder(border);
		panel3.setBorder(border);
		panel4.setBorder(border);
		panel5.setBorder(border);
		GridLayout gridb  = new GridLayout(1,1,10,10);
		GridLayout grid  = new GridLayout(2,1,10,10);
		JPanel vbox0  = new JPanel();
		JPanel vbox1  = new JPanel();
		JPanel vbox2  = new JPanel();
		JPanel vbox3  = new JPanel();
		JPanel vbox4  = new JPanel();
		JPanel vbox5  = new JPanel();
		vbox0.setLayout(gridb);
		vbox0.setBorder(borderb);
		vbox0.setBackground(Color.BLACK);
		vbox0.add(join);
		//vbox0.add(njoin);
		vbox1.setLayout(grid);
		vbox1.setBorder(border1);
		vbox1.setBackground(Color.BLACK);
		vbox1.add(join1);
		vbox1.add(njoin1);
		vbox2.setLayout(grid);
		vbox2.setBorder(border);
		vbox2.setBackground(Color.BLACK);
		vbox2.add(join2);
		vbox2.add(njoin2);
		
		vbox3.setLayout(grid);
		vbox3.setBorder(border);
		vbox3.setBackground(Color.BLACK);
		vbox3.add(join3);
		vbox3.add(njoin3);
		
		vbox4.setLayout(grid);
		vbox4.setBorder(border);
		vbox4.setBackground(Color.BLACK);
		vbox4.add(join4);
		vbox4.add(njoin4);
		
		vbox5.setLayout(grid);
		vbox5.setBorder(border);
		vbox5.setBackground(Color.BLACK);
		vbox5.add(join5);
		vbox5.add(njoin5);
		Box box = Box.createVerticalBox();
		box.setBorder(border);
		Box box0 = Box.createHorizontalBox();
		box0.add(panel0);
		box0.add(vbox0);
		box0.add(panel1);
		box0.add(vbox1);
		Box box1 = Box.createHorizontalBox();
		box1.add(panel2);
		box1.add(vbox2);
		box1.add(panel3);
		box1.add(vbox3);
		Box box2 = Box.createHorizontalBox();
		box2.add(panel4);
		box2.add(vbox4);
		box2.add(panel5);
		box2.add(vbox5);
		join1.setEnabled(false);
		join2.setEnabled(false);
		join3.setEnabled(false);
		join4.setEnabled(false);
		join5.setEnabled(false);
		njoin1.setEnabled(false);
		njoin2.setEnabled(false);
		njoin3.setEnabled(false);
		njoin4.setEnabled(false);
		njoin5.setEnabled(false);
		box0.setBorder(border);
		box1.setBorder(border);
		box2.setBorder(border);
		box.add(box0);
		box.add(box1);
		box.add(box2);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.add(box);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);	
		
	}
}

package solution;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class NewGame {
	JFrame frame = new JFrame("Initialise");
	JButton ok = new JButton("OK");
	JButton cancel = new JButton("Cancel");
	JLabel label = new JLabel();
	JTextField field = new JTextField();
	/**
	 * Displays the Initialise frame asking for the number of detectives joining the game
	 */
	NewGame() {
		
		
		
		cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }});
		
		Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Box box = Box.createVerticalBox();
		box.setBorder(border);
		ImageIcon iconimg = new ImageIcon("./resources/nod.gif");
		label.setIcon(iconimg);
		label.setBorder(border);
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		box.add(label);
		box.add(field);
		JPanel buttonBox = new JPanel();
		buttonBox.setBackground(Color.BLACK);
		GridLayout grid = new GridLayout(1, 2, 10, 10);
        buttonBox.setLayout(grid);
        buttonBox.setBorder(border);
        //ok.setBorder(border);
        //cancel.setBorder(border);
		buttonBox.add(ok);
		buttonBox.add(cancel);
		box.add(buttonBox);
		frame.add(box);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
	
	public void addOKListener(ActionListener listenOK) {
		ok.addActionListener(listenOK);
	}
	

}

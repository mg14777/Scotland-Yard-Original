package solution;
import scotlandyard.*;

import javax.swing.*;

import java.util.*;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.border.Border;
import java.awt.event.*;
public class GameOver {
	JFrame frame = new JFrame("Game Over");
	JLabel gameover = new JLabel();
	JLabel mrx = new JLabel();
	JLabel detective = new JLabel();
	JButton ok = new JButton("Exit");
	JButton replay = new JButton("Replay");
	/**
	 * Displays and sets the contents of the Game Over window
	 * @param winners Winners of the Game (Mr.X or Detectives)
	 */
	GameOver(Set<Colour> winners) {
		ImageIcon over = new ImageIcon("./resources/gameover.gif");
		gameover.setIcon(over);
		Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Box box = Box.createVerticalBox();
		box.setBorder(border);
		Box boximg = Box.createHorizontalBox();
		boximg.add(gameover);
		box.add(boximg);
		if(winners.contains(Colour.Black)) {
			ImageIcon x = new ImageIcon("./resources/mrx.gif");
			mrx.setIcon(x);
			Box box1 = Box.createHorizontalBox();
			box1.add(mrx);
			box.add(box1);
		}
		else {
			ImageIcon d = new ImageIcon("./resources/detective.gif");
			detective.setIcon(d);
			Box box2 = Box.createHorizontalBox();
			box2.add(detective);
			box.add(box2);
		}
		JPanel buttonBox = new JPanel();
		buttonBox.setBackground(Color.BLACK);
		GridLayout grid = new GridLayout(1, 2, 10, 10);
        buttonBox.setLayout(grid);
        buttonBox.setBorder(border);
       
		buttonBox.add(ok);
		buttonBox.add(replay);
		box.add(buttonBox);
		frame.add(box);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	public void addReplayListener(ActionListener listenReplay) {
		replay.addActionListener(listenReplay);
	}
	
	public void addOkOverListener(ActionListener listenReplay) {
		ok.addActionListener(listenReplay);
	}
	
	
}

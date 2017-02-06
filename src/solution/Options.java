package solution;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Options extends JPanel{
	JLabel sound = new JLabel();
	JLabel on = new JLabel();
	JLabel off = new JLabel();
	JLabel back = new JLabel();
	JLabel panelimg = new JLabel();
	JLabel label = new JLabel();
	
	/**
	 * Initialises the layout and images for the options menu
	 */
	Options() {
		ImageIcon iconimg = new ImageIcon("./resources/sy.jpg");
		panelimg.setIcon(iconimg);
		Box box1 = Box.createHorizontalBox();
		box1.add(panelimg);
		ImageIcon icon0 = new ImageIcon("./resources/sound.gif");
		sound.setIcon(icon0);
		ImageIcon icon1 = new ImageIcon("./resources/on.gif");
		on.setIcon(icon1);
		ImageIcon icon2 = new ImageIcon("./resources/off.gif");
		off.setIcon(icon2);
		ImageIcon icon3 = new ImageIcon("./resources/back.gif");
		back.setIcon(icon3);
		ImageIcon icon4 = new ImageIcon("./resources/newgame.png");
		label.setIcon(icon4);
		Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border border1 = BorderFactory.createEmptyBorder(150, 10, 10, 500);
		Box box = Box.createVerticalBox();
		box.setBorder(border);
		Box box2 = Box.createHorizontalBox();
		Box box3 = Box.createHorizontalBox();
		Box box4 = Box.createHorizontalBox();
		box2.add(sound);
		box2.add(on);
		box2.add(off);
		box3.add(label);
		box4.add(back);
		box1.setBorder(border);
		box2.setBorder(border);
		box4.setBorder(border1);
		box.add(box1);
		box.add(box2);
		box.add(box4);
		this.add(box);
		this.setBackground(Color.BLACK);	
	}
	
	public void addOnListener(MouseAdapter listenExitGame) {
		on.addMouseListener(listenExitGame);
	}
	
	public void addOffListener(MouseAdapter listenExitGame) {
		off.addMouseListener(listenExitGame);
	}
	
	public void addBackListener(MouseAdapter listenExitGame) {
		back.addMouseListener(listenExitGame);
	}
}

package solution;
import scotlandyard.*;

import javax.swing.*;
import javax.swing.Timer;
import java.io.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.*;

public class GameView extends JLabel {					//contains the map where players are drawn 
	private ImageIcon icon;
	private List<Point> playersToPaint;					//list of coordinates for the players
	private Map<Colour, BufferedImage> playerImages = new HashMap<Colour, BufferedImage>(); 
	private List<Colour> playerOrder;
	public List<Point> validLocatonsToDraw;
	private List<BufferedImage> circles = new ArrayList<BufferedImage>();
	private int drawClick = 0, clickX, clickY;		//0 for not drawing, 1 for valid click, 2 for invalid click
	GameView() {								
		try {
			File file = new File("./resources/map.jpg"); 
		    FileInputStream fis = new FileInputStream(file);  
		    BufferedImage image = ImageIO.read(fis);	
			icon = new ImageIcon(image);
			setIcon(icon);
			for(int i = 0; i<4; i++) {
				file = new File("./resources/circle" + i + ".png");
				fis = new FileInputStream(file);
				image = ImageIO.read(fis); 
				circles.add(image);
			}
			fis.close();	
		}
		catch( IOException e ) {
			System.out.println(e);
		}
	
	}
	
	/**
	 * 
	 * @param players List of Players to be initialised for drawing on the map
	 */
	public void initialisePlayers(List<Colour> players) { 
		this.playerOrder = players; 
		for(Colour player: players) {
			try {
				File file = new File("./resources/"+player.toString() + ".png");  
			    FileInputStream fis = new FileInputStream(file);  
			    BufferedImage image = ImageIO.read(fis); 
				playerImages.put(player, image);
				fis.close();
			}
			catch( IOException e ) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * Repaints the map with the players and map clicks
	 */
	public void paint(Graphics g) {		
		super.paint(g);
		if(drawClick == 1)
			g.drawImage(circles.get(0), clickX - 15, clickY - 15, 30, 30, null);
			else if(drawClick == 2)
				g.drawImage(circles.get(1), clickX - 15, clickY - 15, 30, 30, null);
				else if(drawClick == 3)
					for(Point l: validLocatonsToDraw)
						g.drawImage(circles.get(2), (int)l.getX() - 15, (int)l.getY() - 15, 30, 30, null);
					else if(drawClick == 4)
						for(Point l: validLocatonsToDraw)
							g.drawImage(circles.get(3), (int)l.getX() - 20, (int)l.getY() - 20, 40, 40, null);
		if(playersToPaint!=null)
			for(int i = 0; i < playersToPaint.size(); i++) {
				Point aux = playersToPaint.get(i);
				if(aux!=null) 
					g.drawImage(playerImages.get(playerOrder.get(i)), (int) aux.getX(), (int) aux.getY() - 55, 55, 55, null);
			}
	}
	
	
	/**
	 *  Sets a list of point which represent the locations that have to be drawn on the map
	 * @param option Location to be drawn on map
	 * @param locations List of all possible locations
	 */
	public void setValidLocations(int option, List<Point> locations) {
		this.validLocatonsToDraw = locations;
		this.drawClick = option;
		this.repaint();
	}
	/**
	 * 
	 * @param playersToPaint List of points to be finally painted
	 */
	public void paintPlayers(List<Point> playersToPaint) {
			this.playersToPaint = playersToPaint;
			repaint();
	}
	
	public void drawMapClick(int drawClick, int clickX, int clickY) {
		this.drawClick = drawClick;
		this.clickX = clickX;
		this.clickY = clickY;
		repaint();
	}
	
	public void resetDrawClick() {
		drawClick = 0;
		repaint();
	}
	
	public void addMapClickedListener(MouseAdapter listenLoadGame) {
		this.addMouseListener(listenLoadGame);
	}
}
























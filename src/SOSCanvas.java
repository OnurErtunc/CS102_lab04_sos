import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * SOSCanvas class of the Lab04_sosgame
 * @author Onur Ertunc
 * @version 27.11.2020
 */
public class SOSCanvas extends JPanel{
	
	// Instances of the SOSCanvas
	private static final int CANVAS_SIZE = 600;
	private SOS game;
	private Graphics page;
	private int boxesPerLine; // Grid size
	private int boxesPerLinePlace; // box edge 
	
	/*
	 * Constructor
	 */
	public SOSCanvas( SOS game ) {
		
		this.game = game;
		this.boxesPerLine = game.getDimension();
		this.boxesPerLinePlace = CANVAS_SIZE / boxesPerLine; 
		this.setBackground( Color.CYAN );
		this.setPreferredSize( new Dimension ( CANVAS_SIZE, CANVAS_SIZE ) );
		repaint();
		
	}
	
	/*
	 * the get method of the boxesPerLinePlace to use it in SOSGUIPanel
	 */
	public int getBoxesPerLinePlace() {
		return (int)(this.boxesPerLinePlace);
	}
	
	/**
	 * paint component 
	 */
	public void paintComponent( Graphics g ) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g.setFont(  new Font( "", Font.PLAIN, 20));
       
		double xPosition = 0;
		double yPosition = 0;
		
		for ( int i = 0; i < (int)( boxesPerLine ); i++ ) {
			xPosition = 0;
			for ( int j = 0; j < (int)( boxesPerLine ); j++ ) {
				g2.drawRect((int) ( xPosition ), (int) ( yPosition ), getBoxesPerLinePlace() , getBoxesPerLinePlace() );
				xPosition = xPosition + getBoxesPerLinePlace();
			}
			yPosition = yPosition + getBoxesPerLinePlace();
		}
		
		xPosition = getBoxesPerLinePlace() / 2;
		yPosition = getBoxesPerLinePlace() / 2;
		
		for ( int m = 0;  m < (int)( boxesPerLine ); m++ ) {
			xPosition = getBoxesPerLinePlace() / 2;
			for ( int n = 0; n < (int)( boxesPerLine ); n++ ) {
				g.drawString(game.getCellContents(n, m) + "", (int) ( xPosition ), (int) ( yPosition ) );
				xPosition = xPosition + getBoxesPerLinePlace();
			}
			yPosition = yPosition + getBoxesPerLinePlace();
		}
		
	}
}

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Test class of the SOS game
 * @author Onur Ertunc
 * @version 27.11.2020
 *
 */
public class TestSOS extends JFrame {
	public static void main( String[] args ) {
		
		// Input dialogs (Opens a pop-up screen and gets the user info, game info
		String grid = JOptionPane.showInputDialog( "Enter the grid: " );
		String player1 = JOptionPane.showInputDialog( "Name of player1: " );
		String player2 = JOptionPane.showInputDialog( "Name of player2: " );
		
		// converts the number in the string grid into its integer value to use it
		int gridd = Integer.parseInt(grid);
		
		// SOS object
		SOS game = new SOS(gridd);
		
		// SOSGUIPanel Object
		SOSGUIPanel canvas = new SOSGUIPanel( game, player1, player2 );
		
		// Frame
		JFrame frame = new JFrame( "It is not a game!!!" );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		(frame.getContentPane()).add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}

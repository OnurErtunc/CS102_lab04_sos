import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * SOSGUIPanel of the Lab04_sos
 * @author Onur Ertunc
 * @version 27.11.2020
 *
 */
public class SOSGUIPanel extends JPanel{

     // Instances of the SOSGUIPanel class 
     private SOSCanvas canvas;
     private JPanel scorePanel;
     private JLabel p1;
     private JLabel p2;
     private JRadioButton s;
     private JRadioButton o;
     private SOS game;
     private String player1;
     private String player2;

     
     /**
      * Constructor of the SOSGUIPanel
      * @param game
      * @param player1
      * @param player2
      */
     public SOSGUIPanel(SOS game, String player1, String player2){
           
    	 // assigns parameters of the object from TestSOS class to the instances of this class
         this.game = game;
         this.player1 = player1;
         this.player2 = player2;
         
         // scorePanel
         scorePanel();
         
         // playerLabel
         playerLabel();
         
         // calls the radioGaGa
         radioGaGa();
         
         // calls the additionToScorePanel method
         additionToScorePanel();
         
         // calls the addCanvas method
         addCanvas();
     }
     
     /**
      * scorePanel method that draws the scorePanel.
      * added into the constructor
      */
     private void scorePanel() {
    	 scorePanel = new JPanel();
         scorePanel.setPreferredSize(new Dimension(600, 100)); 
         this.setBackground(Color.CYAN);
         scorePanel.setBackground(Color.CYAN);
     }
     
     /**
      * buttons are added into the score panel. this method is
      * used in the constructor.
      */
     private void additionToScorePanel() {
    	 scorePanel.add(p1);
         scorePanel.add(s);
         scorePanel.add(p2);
         scorePanel.add(o);
     }
     
     /**
      * Initializes the player labels and specializes their colors.
      * the method is called in constructor
      */
     private void playerLabel() {
    	 p1 = new JLabel(player1 + ":  " + game.getPlayerScore1());
         p2 = new JLabel(player2 + ":  " + game.getPlayerScore2());
         p1.setBackground(Color.green);
         p2.setBackground(Color.CYAN); 
         p1.setForeground(Color.BLACK);
         p2.setForeground(Color.BLACK);
     }
     
     /**
      * The method radioGaGa initializes radioButtons in the game
      * the method is called in constructor
      */
     private void radioGaGa() {
    	 s = new JRadioButton("S");
         o = new JRadioButton("O");
         s.addActionListener(new RadioHandler());
         o.addActionListener(new RadioHandler());
         s.setBackground(Color.CYAN);
         o.setBackground(Color.CYAN);
     }
     
     /**
      * the method initializes the canvas, called in constructor 
      */
     public void addCanvas() {
    	 canvas = new SOSCanvas(game);
         canvas.addMouseListener(new TheHandler());
         canvas.setBackground(Color.GREEN);
         
         this.add(canvas, BorderLayout.CENTER);
         this.add(scorePanel, BorderLayout.SOUTH);
         
         this.setPreferredSize(new Dimension(800, 800));
     }
     
     /**
      * TheHandler innerclass written to follow the mouse adapter events
      * @author Onur Ertunc
      * @version 27.11.2020
      *
      */
     private class TheHandler extends MouseAdapter{
           
    	 /**
    	  * mousePressed
    	  */
           public void mousePressed(MouseEvent e) {
                 
                 Point p = e.getPoint();
                 double xPosi = p.getX();
                 double yPosi = p.getY();
                 
                 double boxEdge = canvas.getBoxesPerLinePlace();
                 
                 int row = (int)yPosi / (int)boxEdge +1;
                 int column = (int)xPosi / (int)boxEdge +1;
                 
                 if(s.isSelected()){
                       game.play('s', column, row);
                       p1.setText(player1 + ":  " + game.getPlayerScore1());
                       p2.setText(player2 + ":  " + game.getPlayerScore2());
                       
                 }
                 else if(o.isSelected()){
                       game.play('o', column, row);
                       p1.setText(player1 + ":  " + game.getPlayerScore1());
                       p2.setText(player2 + ":  " + game.getPlayerScore2());
                 }
                 
                 int turn = game.getTurn();
                 
                 if(turn == 1){
                       p1.setBackground(Color.GREEN);
                       p2.setBackground(Color.CYAN);
                 }
                 else if(turn == 2){
                       p2.setBackground(Color.GREEN);
                       p1.setBackground(Color.CYAN);
                 }
                 
                 repaint();
                 
                 if(game.isGameOver()){   
                       String endMes = "";
                       if(game.getPlayerScore1() > game.getPlayerScore2()){
                             endMes = player1 + " You know what? You won the game." ; 
                       }
                       else if(game.getPlayerScore1() < game.getPlayerScore2()){
                             endMes = player2 + " You know what? You won the game."; 
                       }
                       else if(game.getPlayerScore1() == game.getPlayerScore2()){
                             endMes = "Well, the result is draw.";
                       }   
                       int again = JOptionPane.showConfirmDialog(canvas, endMes , "Game Over", JOptionPane.DEFAULT_OPTION);
                       if(again == 0){
                             System.exit(0);
                       }
                 }
           }
           
     } 
     
     /**
      * RadioHandler class that is written to follow the events in the radio buttons 
      * @author Onur Ertunc
      * @version 27.11.2020
      *
      */
     private class RadioHandler implements ActionListener{
     
           public void actionPerformed( ActionEvent e ){
                 if((e.getSource()).equals(s)){
                       o.setSelected(false);
                       
                 }
                 else if((e.getSource()).equals(o)){
                       s.setSelected(false);     
                 }
           
           }
     }
}
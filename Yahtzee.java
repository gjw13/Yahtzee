package gjw13_a4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class Yahtzee extends JFrame implements ActionListener
{
	JLabel scoreBoxes[];   		  // where score is stored next to score buttons
	Die dice [] = new Die[5];     // array of dice 
	Score scoreCard = new Score();// score object to store scores
	JButton diceButtons[];        // actual buttons to click on to manipulate the die
	JButton scoreButtons[];	      // buttons like one twos aces etc.
	public int SIDES = 6; 
	
	JPanel rollPanel; 		  // roll panel 
	JPanel leftPanel;
	JPanel rightPanel;
	JButton rollButton;
	
	int rollCount;
	
	
	
	public static void main( String[] args )
	{
	   new Yahtzee();
	}
	
	// Constructor
	public Yahtzee()
	{
		// each dice has 6 sides
		for (int i = 0; i < 5; i++)
			dice[i] = new Die(SIDES);
		rollCount = 0;
		diceButtons = new JButton[5];
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Yahtzee");
		setLayout( new GridLayout( 1,3 )); // whole window
		//setBackground(Color.green);
		
		setupLeftPanel(); // set up upper section of scorePanel on left of dice
		setupRollPanel(); // set up dice
		setupRightPanel(); // set up lower section of scorePanel on right of dice

		setSize( new Dimension( 1000,1000 ) );
		setVisible(true);
	}
	
	public void setupLeftPanel()
	{
		leftPanel = new JPanel();
	    leftPanel.setBackground( new Color( 50, 100, 0 ));
	    leftPanel.setLayout(new GridLayout(10,2));
	    scoreBoxes = new JLabel[18];
	    scoreButtons = new JButton[13];
	    
	    // setting up boxes
	    JLabel l1 = new JLabel("            Score");
		l1.setFont(new Font("Helvetica", Font.BOLD, 14));
		l1.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		leftPanel.add(l1);
		
		JLabel l2 = new JLabel("       Upper Section");
		l2.setFont(new Font("Helvetica", Font.BOLD, 14));
		l2.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		leftPanel.add(l2);
		
		// set scoreButtons up for left panel
		scoreButtons[0] = new JButton("Ones   \t [1]");
		scoreButtons[1] = new JButton("Twos   \t [2]");
		scoreButtons[2] = new JButton("Threes \t[3]");
		scoreButtons[3] = new JButton("Fours  \t [4]");
		scoreButtons[4] = new JButton("Fives  \t [5]");
		scoreButtons[5] = new JButton("Sixes  \t [6]");
	    
		// set up 
	    for (int g=0;g < 6; g++)
	    {
	    	scoreBoxes[g] = new JLabel("               0");
	    	leftPanel.add(scoreBoxes[g]);
	    	leftPanel.add(scoreButtons[g]);
	    	scoreButtons[g].addActionListener(this);
	    	scoreButtons[g].setEnabled(false);
	    }
	    scoreBoxes[6] = new JLabel("               0");
	    leftPanel.add(scoreBoxes[6]);
	    JLabel l3 = new JLabel("        Sum");
	    l3.setFont(new Font("Helvetica", Font.BOLD, 14));
		l3.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		leftPanel.add(l3);
	    
		scoreBoxes[7] = new JLabel("               0");
		leftPanel.add(scoreBoxes[7]);
	    JLabel l4 = new JLabel("        Bonus");
	    l4.setFont(new Font("Helvetica", Font.BOLD, 14));
		l4.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		leftPanel.add(l4);
	    
		scoreBoxes[8] = new JLabel("               0");
		leftPanel.add(scoreBoxes[8]);
	    JLabel l5 = new JLabel("        Total");
	    l5.setFont(new Font("Helvetica", Font.BOLD, 14));
		l5.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		leftPanel.add(l5);
	    
	    
	    add(leftPanel);
	}
	
	public void setupRollPanel()
	{
		rollPanel = new JPanel();
		rollPanel.setBackground( new Color( 50, 100, 0 ) );
		rollPanel.setLayout(new GridLayout(6,1));
	    
	    // set each die to random number
	    rollButton = new JButton("Roll");
	    rollPanel.add(rollButton);
	    rollButton.addActionListener(this);
	    
	    for (int j=0; j< 5; j++)
	    {
	    	diceButtons[j] = new JButton();
			diceButtons[j].setFont(new Font("Helvetica", Font.PLAIN, 50));
			diceButtons[j].addActionListener(this);
			rollPanel.add(diceButtons[j]);
	    }
	    
	    questionMarks(); // set dice with question marks at start
	    add(rollPanel); // put rollPanel in middle
	}
	
	public void setupRightPanel()
	{
		 rightPanel = new JPanel();
		 rightPanel.setBackground( new Color( 50, 100, 0 ));
		 rightPanel.setLayout(new GridLayout(10,2));
		 
		 JLabel l1 = new JLabel("       Lower Section");
		 l1.setFont(new Font("Helvetica", Font.BOLD, 14));
		 l1.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		 rightPanel.add(l1);
		
		 JLabel l2 = new JLabel("            Score");
		 l2.setFont(new Font("Helvetica", Font.BOLD, 14));
		 l2.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		 rightPanel.add(l2);
		
		 scoreButtons[6] = new JButton("3 of a kind");
		 scoreButtons[7] = new JButton("4 of a kind");
		 scoreButtons[8] = new JButton("Full House");
		 scoreButtons[9] = new JButton("Sm. Straight");
		 scoreButtons[10] = new JButton("Large Straight");
		 scoreButtons[11] = new JButton("Chance");
		 scoreButtons[12] = new JButton("Yahtzee");
	    
	     for (int g=6;g < 13; g++)
	     { 
	    	 rightPanel.add(scoreButtons[g]);
	    	 scoreBoxes[g+3] = new JLabel("              0");
	    	 rightPanel.add(scoreBoxes[g+3]);
	    	 scoreButtons[g].addActionListener(this);
	    	 scoreButtons[g].setEnabled(false);
	     }
	     
	     JLabel l3 = new JLabel("        Total");
	     l3.setFont(new Font("Helvetica", Font.BOLD, 14));
		 l3.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		 rightPanel.add(l3);
		 scoreBoxes[16] = new JLabel("              0");
		 rightPanel.add(scoreBoxes[16]);
		 
		 
	     JLabel l4 = new JLabel("      Grand Total");
	     l4.setFont(new Font("Helvetica", Font.BOLD, 14));
		 l4.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		 rightPanel.add(l4);
		 scoreBoxes[17] = new JLabel("              0");
		 rightPanel.add(scoreBoxes[17]);
		 
		 add(rightPanel); // add the right panel to the frame
	}
	
	public void questionMarks()
	{
		for (int i = 0; i < 5; i++)
			diceButtons[i].setText("?");
	}
	
	
	// function to enable buttons if necessary depending on die
	public void enableButtons()
	{
		
		for (int i = 0; i < 6; i++)
		{
			scoreButtons[i].setEnabled(scoreCard.num(i+1, dice));
		}
		scoreButtons[6].setEnabled(scoreCard.isThreeOfAKind(dice));
		scoreButtons[7].setEnabled(scoreCard.isFourOfAKind(dice));
		scoreButtons[8].setEnabled(scoreCard.fullHouse(dice));
		scoreButtons[9].setEnabled(scoreCard.smStraight(dice)); // need function for these two
		scoreButtons[10].setEnabled(scoreCard.largeStraight(dice));
		scoreButtons[11].setEnabled(true);
		scoreButtons[12].setEnabled(scoreCard.yahtzee(dice));
		
		// if score has already been selected, disable button
		int counter = 0;
		for (int j=0; j<13;j++)
		{
			if (scoreCard.getScore(j) > -1)
			{
				scoreButtons[j].setEnabled(false);
				counter++;
			}
			if (counter == 12)
				new Yahtzee();
		}
	}
	
	public void score(int n, int score)
	{
		scoreCard.setScore(n, score);
		if (n < 6)
			scoreBoxes[n].setText("              " + Integer.toString(score));
		else
			scoreBoxes[n+3].setText("              " + Integer.toString(score));
		
		scoreBoxes[6].setText("              " + 
				Integer.toString(scoreCard.upperScore()));
		scoreBoxes[7].setText("              " + 
				Integer.toString(scoreCard.bonusScore()));
		scoreBoxes[8].setText("              " + 
				Integer.toString(scoreCard.totalUpperScore()));
		scoreBoxes[16].setText("              " + 
				Integer.toString(scoreCard.lowerScore()));
		scoreBoxes[17].setText("              " + 
				Integer.toString(scoreCard.grandTotal()));
		
		// update the rest of the scores and reset dice
		for (int i=0; i<dice.length; i++)
		{
			diceButtons[i].setFont(new Font("Helvetica", Font.PLAIN, 50));
			questionMarks();
			rollButton.setEnabled(true);
			if (dice[i].selected() == true)
				dice[i].toggleSelected();
		}
		
		// disable all buttons after scoreButton is selected
		int counter = 0;
		for(int j =0; j< 13; j++)
		{
			scoreButtons[j].setEnabled(false);
		}
		
	}
	
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource()==rollButton && rollCount < 3) 
		{
			System.out.println("You rolled the die");
			for(int i=0; i<5; i++)
			{
				if( dice[i].selected() == false)
				{
					dice[i].roll();
					diceButtons[i].setText(Integer.toString(dice[i].value()));
				}
			}
			rollCount ++;
			if (rollCount == 3)
			{
				rollButton.setEnabled(false);
				enableButtons();
			}
		}
		else if ( e.getSource()==diceButtons[0] ) 
		{
			System.out.println("you selected die1");
			dice[0].toggleSelected();
			if (dice[0].selected() == true)
			{
				diceButtons[0].setFont(new Font("Helvetica", Font.BOLD, 100));
			}
			else
			{
				diceButtons[0].setFont(new Font("Helvetica", Font.PLAIN, 50));
				diceButtons[0].setOpaque(false);
			}
		}
		else if ( e.getSource()==diceButtons[1] ) 
		{
			System.out.println("you selected die2"); 
			dice[1].toggleSelected();
			if (dice[1].selected() == true)
			{
				diceButtons[1].setFont(new Font("Helvetica", Font.BOLD, 100));
			}
			else
			{
				diceButtons[1].setFont(new Font("Helvetica", Font.PLAIN, 50));
				diceButtons[1].setOpaque(false);
			}
		}
		else if ( e.getSource()==diceButtons[2] ) 
		{
			System.out.println("you selected die3"); 
			dice[2].toggleSelected();
			if (dice[2].selected() == true)
			{
				diceButtons[2].setFont(new Font("Helvetica", Font.BOLD, 100));
			}
			else
			{
				diceButtons[2].setFont(new Font("Helvetica", Font.PLAIN, 50));
				diceButtons[2].setOpaque(false);
			}
		}
		else if ( e.getSource()==diceButtons[3] ) 
		{
			System.out.println("you selected die4"); 
			dice[3].toggleSelected();
			if (dice[3].selected() == true)
			{
				diceButtons[3].setFont(new Font("Helvetica", Font.BOLD, 100));
			}
			else
			{
				diceButtons[3].setFont(new Font("Helvetica", Font.PLAIN, 50));
				diceButtons[3].setOpaque(false);
			}
		}
		else if (e.getSource() == diceButtons[4])
		{
			System.out.println("you selected die5");
			dice[4].toggleSelected();
			if (dice[4].selected() == true)
			{
				diceButtons[4].setFont(new Font("Helvetica", Font.BOLD, 100));
			}
			else
			{
				diceButtons[4].setFont(new Font("Helvetica", Font.PLAIN, 50));
				diceButtons[4].setOpaque(false);
			}
		}
		else if (rollCount == 3)
		{
			int answer = 0;
			for (int i = 0; i < 6; i++)	
			{
				if (e.getSource() == scoreButtons[i])
				{
					System.out.println("You selected scoreButton " + i);
					for (int j=0; j<dice.length; j++)
						if (dice[j].value() == i+1)
							answer = answer + (i+1);
							
					score(i, answer); // send score to scoreBox
					rollCount = 0;
				}
			} // END loop 1-6
			
			if (e.getSource() == scoreButtons[6])
			{
				for (int i=0; i<dice.length; i++)
					answer += dice[i].value();
				score(6, answer);
				rollCount = 0;
			}
			
			if (e.getSource() == scoreButtons[7])
			{
				for (int i=0; i<dice.length; i++)
					answer += dice[i].value();
				score(7, answer);
				rollCount = 0;
			}
			
			if (e.getSource() == scoreButtons[8])
			{
				score(8, 25);
				rollCount = 0;
			}
			
			if (e.getSource() == scoreButtons[9])
			{
				score(9, 30);
				rollCount = 0;
			}
			
			if (e.getSource() == scoreButtons[10])
			{
				score(10, 40);
				rollCount = 0;
			}
			
			if (e.getSource() == scoreButtons[11])
			{
				for (int i=0; i<dice.length; i++)
					answer += dice[i].value();
				score(11, answer);
				rollCount =0;
			}
			
			if (e.getSource() == scoreButtons[12])
			{
				score(12, 50);
				rollCount = 0;
			}
			
		} // END ELSE IF rollCount = 3
		
	}
	
}

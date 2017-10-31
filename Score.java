package gjw13_a4;

public class Score {

	private int[] scores;
	
	public Score()
	{
		scores = new int[14];
		for (int i = 0; i < 14; i++)
		{
			scores[i] = -1;
		}
	}
	
	public void setScore(int n, int score)
	{
		scores[n] = score;
	}
	
	public int getScore(int n)
	{
		return scores[n];
	}
	
	public int upperScore()
	{
		int upperScore = 0;
		for (int i = 0; i < 6; i++)
			if (scores[i] != -1)
				upperScore += scores[i];
		return upperScore;
	}
	
	// adds bonus if upperScore exceeds 62
	public int bonusScore()
	{
		if (upperScore() >= 63)
			return 35;
		return 0;
	}
	
	public int totalUpperScore()
	{
		return upperScore() + bonusScore();
	}
	
	// calculates the lower scores aka straights and full house
	public int lowerScore()
	{
		int lowerScore = 0;
		for (int i = 6; i < scores.length; i++)
			if (scores[i] != -1)
				lowerScore += scores[i];
		return lowerScore;
 	}

	public int grandTotal()
	{
		return upperScore() + lowerScore();
	}
	
	// function used for allowing button to be clicked to save score
	public boolean filled()
	{
		boolean ans = true;
		for (int i = 0; i < 13; i++)
			if (scores[i] == -1)
				ans = false;
		return ans;
	}
	
	// used for 1-6 calculations
	public boolean num(int n, Die [] dice)
	{
		boolean ans = false;
		for (int i = 0; i < dice.length; i++)
			if (dice[i].value() == n)
				ans = true;
				
		return ans;
	}	
	
	// boolean to test if chance has been taken
	public boolean chance()
	{
		return scores[11] == -1;
	}
	
	/**** functions to calculate right side scores ***/
	
	// method to check if dice has 3 of a kind
	public boolean isThreeOfAKind(Die[] dice)
	{
		int count = 0;
		//Loop through the values and compare each value to all values
	    //If 3 matches are made - return true
	    for(int x = 1; x < dice.length; x++){
	        for(int y = 0; y < x; y++){
	            if(dice[x].value() == dice[y].value()) {
	            	count++;
	            }
	        }
	        if (count == 2)  
	        	return true;
	        count = 0;
	    }
	    return false;
	}
	
	// method to test rule 2 --> 4 of a kind
	boolean isFourOfAKind(Die[] dice)
	{
		int count = 0;
		//Loop through the values and compare each value to all values
	    //If 4 matches are made - return true
	    for(int x = 1; x < dice.length; x++){
	        for(int y = 0; y < x; y++){
	            if(dice[x].value() == dice[y].value()) 
	            	count++;
	        }
	        if (count == 3)  
	        	return true;
	        count = 0;
	    }
	    return false;
	}
	
	// method to determine if dice has full house (3 of kind and 2 of kind)
	public boolean fullHouse(Die[] dice)
	{
		int[] fh = new int[7]; // new array to count numbers in m
		boolean is3 = false;
		boolean is2 = false;
		for(int i=0; i<dice.length;i++){ // for loop to put numbers in fh
			int num = dice[i].value();
			fh[num] ++;
		}
		
		for(int j=0;j<fh.length;j++){ // traverse fh to check for 3 and 2
			if(fh[j] ==3)
				is3 = true;
			if(fh[j] ==2)
				is2 = true;
		}
		
		// if both 3 of a kind and 2 of a kind are true
		if(is3 == true && is2==true)
			return true;
		else // not a full house
			return false;
	}
	
	// method to test if large straight 
	public boolean smStraight(Die[] dice)
	{
		int[] str = new int[7];
		for(int i=0; i<dice.length;i++){ // for loop to put numbers in fh
			int num = dice[i].value();
			str[num] ++;
		}
		
		for(int j=0;j<str.length;j++){
			
			
		}
		
		return true;
		
	}
	
	// method to test if large straight 
	public boolean largeStraight(Die[] dice)
	{
		int[] str = new int[7];
		for(int i=0; i<dice.length;i++){ // for loop to put numbers in fh
			int num = dice[i].value();
			str[num] ++;
		}
		
		for(int j=1;j<str.length;j++)
		{
			//if(str[j] == 1)
				
			
		}
		
		return true;
		
	}
	
	
	// method to test if Yahtzee aka 5 of a kind
	public boolean yahtzee(Die[] dice)
	{
		for (int i=1; i < dice.length; i++)
		{
			if(dice[0].value() != dice[i].value()){
                return false;
			}
		}
		return true;
	}
	
	// returns sum of all values held in array of dice
	public int chance(Die[] dice)
	{
		int count = 0;
		for(int i = 1; i< dice.length; i++)
		{
			count += dice[i].value();
		}
		return count;
	}
}

package gjw13_a4;

import java.util.Random;

public class Die
{
	private int sides, value;
	private Random rng;
	private boolean selected;
	
	public Die()
	{
		sides = 6;
		rng = new Random();
		value = roll();
		selected = false;
	}
		
	public Die(int num)
	{
		sides = num;
		rng = new Random();
		value = roll();
		selected = false;
	}
	
	public int roll()
	{
		value = rng.nextInt(sides) + 1;
		return value;
	}
	
	public int value()
	{
		return value;
	}
	
	public boolean selected()
	{
		return selected;
	}
	
	public void toggleSelected()
	{
		selected = !selected;
	}
}
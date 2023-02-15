package tetris_pattern;

import java.awt.Color;
import java.util.Random;

public class Utils {
	
	private static Random rand = new Random();
	
	public static Color rndColor()
	{
		float red = rand.nextFloat();
		float green = rand.nextFloat();
		float blue = rand.nextFloat();
		return new Color(red, green, blue);
	}
}

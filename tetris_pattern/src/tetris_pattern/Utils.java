package tetris_pattern;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {
	
	private static Random rand = new Random();
	
	public static Color rndColor()
	{
		float red = rand.nextFloat();
		float green = rand.nextFloat();
		float blue = rand.nextFloat();
		return new Color(red, green, blue);
	}
	
	/**
	 * Generic way of filtering values from ArrayList
	 * @param <T>
	 * @param criteria
	 * @param list
	 * @return
	 */
	 public static<T> List<T> filter(Predicate<T> criteria, List<T> list) {
	        return list.stream().filter(criteria).collect(Collectors.<T>toList());
	 }
}

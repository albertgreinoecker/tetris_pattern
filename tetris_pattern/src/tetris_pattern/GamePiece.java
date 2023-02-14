package tetris_pattern;

import java.awt.Color;
import java.util.ArrayList;

public class GamePiece {
	// first dim: which orientation, second dim: the positions
	private ArrayList<ArrayList<Position>> orientations = new ArrayList<>();
	private Color color;
	
	public GamePiece(Color color)
	{
		this.color = color;
	}
	public void add(int orientation, ArrayList<Position> pos) {
		orientations.add(orientation, pos);
	}

	public ArrayList<Position> getOrientation(int orientation)
	{
		return orientations.get(orientation);
	}
	
	public Color getColor()
	{
		return color;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (ArrayList<Position> orientation : orientations) {
			for (Position pos : orientation) {
				s.append(pos + ", ");
			}
			s.append("\n\n");
		}
		return s.toString();
	}
}

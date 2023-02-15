package tetris_pattern;

import java.awt.Color;

public class Position {
	private int x, y;
	private Color color;

	public Position(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * 
	 * @param absX x value to be added to the current x-value
	 * @param absY y value to be added to the current y-value
	 * @return a new Position instance
	 */
	public Position add(int absX, int absY) {
		return new Position(x + absX, y + absY, color);
	}

	/**
	 * @return A new Position object located directly under the current position
	 */
	public Position onePosDown() {
		return add(0, 1);
	}

	public void down()
	{
		y++;
	}
	
	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return String.format("(%d %d)", x, y);
	}

	/**
	 * Ignore Color when comparing
	 */
	@Override
	public boolean equals(Object obj) {
		Position p = (Position) obj;
		return x == p.x && y == p.y;
	}
}

package tetris_pattern;

import java.util.ArrayList;

public class GamePiece {
	// first dim: which orientation, second dim: the positions
	private ArrayList<ArrayList<Position>> orientations = new ArrayList<>();
	private int yAbs = 0;
	private int xAbs = 5;
	private int orientation;

	public GamePiece() {
	}

	public GamePiece(GamePiece p) {
		yAbs = 0;
		xAbs = (int) (Math.random() * TetrisModel.BOARD_WIDTH - 3) + 2;
		this.orientation = p.orientation;
		this.orientations = p.orientations;
	}

	public void add(int orientation, ArrayList<Position> pos) {
		orientations.add(orientation, pos);
	}

	public ArrayList<Position> getPositions() {
		ArrayList<Position> absOrientations = new ArrayList<Position>();
		ArrayList<Position> relOrientations = orientations.get(orientation);
		for (int i = 0; i < relOrientations.size(); i++) {
			Position relOrientation = relOrientations.get(i);
			absOrientations.add(relOrientation.add(xAbs, yAbs));
		}
		return absOrientations;
	}

	public void down() {
		if (yAbs < TetrisModel.BOARD_HEIGHT - 2) // if collides with down from timer
			yAbs++;
	}

	public void left() {
		if (xAbs > 0)
			xAbs--;
	}

	public void right() {
		if (xAbs < TetrisModel.BOARD_WIDTH - 2)
			xAbs++;
	}

	public void changeOrientation() {
		int newOrientation = (orientation + 1) % 4;
		for (Position p : orientations.get(newOrientation))
		{
			if (xAbs + p.getX() < 0 || xAbs + p.getX() > TetrisModel.BOARD_WIDTH -1) return; //outside of board
		}
		
		orientation = newOrientation;
	}

	public ArrayList<ArrayList<Position>> getOrientations() {
		return orientations;
	}

	public void setOrientations(ArrayList<ArrayList<Position>> orientations) {
		this.orientations = orientations;
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

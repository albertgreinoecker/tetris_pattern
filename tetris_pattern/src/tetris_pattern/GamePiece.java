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
		xAbs = (int) (Math.random() * TetrisModel.BOARD_WIDTH);
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
		if (yAbs < TetrisModel.BOARD_HEIGHT - 1)
			yAbs++;
	}

	public void left() {
		xAbs--;
	}

	public void right() {
		xAbs++;
	}

	public void changeOrientation() {
		orientation = (orientation + 1) % 4;
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

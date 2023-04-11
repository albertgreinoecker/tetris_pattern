package tetris_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author albert
 *
 */
public class GamePiece {
	// first dim: which orientation, second dim: the positions
	private ArrayList<ArrayList<Position>> orientations = new ArrayList<>();
	private int yAbs = 0;
	private int xAbs = 5;
	private int orientation;

	public GamePiece() {
	}

	/**
	 * Also set random start x-position
	 * @param p copy constructor
	 */
	public GamePiece(GamePiece p) {
		yAbs = 0;
		xAbs = (int) (Math.random() * TetrisModel.BOARD_WIDTH - 3) + 2;
		this.orientation = p.orientation;
		this.orientations = p.orientations;
	}

	public void add(int orientation, ArrayList<Position> pos) {
		orientations.add(orientation, pos);
	}

	/**
	 * 
	 * @return All positions of the actual orientated game piece
	 */
	public ArrayList<Position> getPositions() {
		ArrayList<Position> absOrientations = new ArrayList<Position>();
		ArrayList<Position> relOrientations = orientations.get(orientation);
		for (int i = 0; i < relOrientations.size(); i++) {
			Position relOrientation = relOrientations.get(i);
			absOrientations.add(relOrientation.add(xAbs, yAbs));
		}
		return absOrientations;
	}

	/**
	 * @return true if piece has not moved yet
	 */
	public boolean isOnTop()
	{
		return (yAbs == 0);
	}

	/**
	 * Move current piece down if possible
	 * @return true if piece is in start position and cannot move down
	 * @param lyingCells
	 * @return true if moving down was possible
	 */
	public boolean down(List<Position> lyingCells) {
		System.out.println(yAbs);
		if (!isOnFloor(lyingCells)) // if collides with down from timer
		{
			yAbs++;
			return true;
		}
		return false;
	}

	
	public boolean isOnFloor(List<Position> lyingCells) {
		for (Position p : getPositions()) {
			if (p.getY() == TetrisModel.BOARD_HEIGHT - 1 || lyingCells.contains(p.onePosDown()))
				return true;
		}
		return false;
	}
	
	/**
	 * Move current piece left if possible
	 */
	public void left() {
		if (xAbs > 0)
			xAbs--;
	}

	/**
	 * Move current piece right if possible
	 */
	public void right() {
		if (xAbs < TetrisModel.BOARD_WIDTH - 2)
			xAbs++;
	}

	/**
	 * change orientation if possible
	 */
	public void changeOrientation() {
		int newOrientation = (orientation + 1) % 4;
		for (Position p : orientations.get(newOrientation))
		{
			if (xAbs + p.getX() < 0 || xAbs + p.getX() > TetrisModel.BOARD_WIDTH -1) return; //outside of board
		}
		
		orientation = newOrientation;
	}

	/**
	 * 
	 * @return all orienations of the current game piece
	 */
	public ArrayList<ArrayList<Position>> getOrientations() {
		return orientations;
	}

	/**
	 * @param orientations Orienations to be set as actual piece
	 */
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

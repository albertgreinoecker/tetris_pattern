package tetris_pattern;

import java.util.ArrayList;

public class TetrisMessage {
	enum ACTION {
		REDRAW
	};

	private ACTION action;
	private GamePiece piece;
	private ArrayList<Position> lyingCells;

	public TetrisMessage(ACTION action, GamePiece piece, ArrayList<Position> lyingCells) {
		this.action = action;
		this.piece = piece;
		this.lyingCells = lyingCells;
	}

	public ACTION getAction() {
		return action;
	}

	public GamePiece getPiece() {
		return piece;
	}

	public ArrayList<Position> getLyingCells() {
		return lyingCells;
	}
}

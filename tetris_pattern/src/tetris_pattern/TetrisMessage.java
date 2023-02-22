package tetris_pattern;

import java.util.List;

public class TetrisMessage {
	enum ACTION {
		REDRAW
	};

	private ACTION action;
	private GamePiece piece;
	private List<Position> lyingCells;

	public TetrisMessage(ACTION action, GamePiece piece, List<Position> lyingCells) {
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

	public List<Position> getLyingCells() {
		return lyingCells;
	}
}

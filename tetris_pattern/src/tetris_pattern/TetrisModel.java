package tetris_pattern;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class TetrisModel extends Observable {
	public static final int BOARD_WIDTH = 10;
	public static final int BOARD_HEIGHT = 22;
	private final long PERIOD_INTERVAL = 400;
	private final int NO_PIECES = 3;
	private GamePiece piece = null; // the actual game piece
	private List<Position> lyingCells = new ArrayList<>();
	private Timer timer; // used for periodically moving down
	// all available pieces
	private ArrayList<GamePiece> pieces = new ArrayList<>();
	private boolean running = true;

	public TetrisModel() {
		init();
	}

	private void init() {
		try {
			for (int i = 0; i < NO_PIECES; i++)
				pieces.add(GamePieceFactory.generate(i));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		piece = rndPiece();
	}

	public void start() {
		timer = new Timer();
		TimerTask t = new TimerTask() {

			@Override
			public void run() {
				gameStep(false);
			}
		};

		timer.schedule(t, 10, PERIOD_INTERVAL);
	}

	public void changeOrientation() {
		piece.changeOrientation();
	}

	public void left() {
		piece.left();
	}

	public void right() {
		piece.right();
	}

	public void fullDown() {
		gameStep(true);
	}

	/**
	 * @param full <b>false</b> one step down, <b>true</b>: move to the floor
	 */
	private synchronized void gameStep(boolean full) {
		if (!running)
			return;

		if (full)
			while (piece.down(lyingCells))
				;
		else
			piece.down(lyingCells);

		if (piece.isOnTop()) {
			gameOver();
		} else if (piece.isOnFloor(lyingCells)) {
			// put to lyingCells
			lyingCells.addAll(piece.getPositions());
			// check and remove filled lines
			removeFullLines();
			// new piece
			piece = rndPiece();
		}
		notify(TetrisMessage.ACTION.REDRAW);
	}

	/**
	 * remove all existing full lines
	 */
	private void removeFullLines() {
		int curr = BOARD_HEIGHT;
		while (curr > 0) {
			if (isFilledLine(curr)) {
				removeLine(curr);
				curr = BOARD_HEIGHT; // start again at the bottom

			} else {
				curr--;
			}
		}
	}

	/**
	 * Remove line at a certain position
	 * 
	 * @param y the pos of the line to be removed
	 */
	private void removeLine(int y) {
		lyingCells = Utils.filter(p -> p.getY() != y, lyingCells);
		for (Position p : lyingCells) {
			if (p.getY() < y) {
				p.down();
			}
		}
	}

	private boolean isFilledLine(int y) {
		int cnt = 0;
		for (Position p : lyingCells) {
			if (y == p.getY())
				cnt++;
		}
		return cnt == BOARD_WIDTH;
	}

	private GamePiece rndPiece() {
		int type = (int) (Math.random() * pieces.size());
		return new GamePiece(pieces.get(type));
	}

	private void gameOver() {
		timer.cancel();
		timer.purge();
		running = false;
		notify(TetrisMessage.ACTION.GAMEOVER);

	}

	private void notify(TetrisMessage.ACTION action) {
		System.out.println(this); //call toString every time
		setChanged();
		notifyObservers(new TetrisMessage(action, piece, lyingCells));
	}

	@Override
	public String toString() {
		ArrayList<Position> poss = piece.getPositions();
		poss.addAll(lyingCells);
		StringBuilder str = new StringBuilder();

		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = 0; x < BOARD_WIDTH; x++) {
				if (poss.contains(new Position(x, y))) {
					str.append("X");
				} else {
					str.append("_");
				}
			}
			str.append("\n");
		}
		return str.toString();
	}

}

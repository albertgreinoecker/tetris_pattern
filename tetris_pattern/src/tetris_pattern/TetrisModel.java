package tetris_pattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.Timer;

public class TetrisModel extends Observable implements ActionListener {
	public static final int BOARD_WIDTH = 10;
	public static final int BOARD_HEIGHT = 22;
	private final int PERIOD_INTERVAL = 500;
	private GamePiece piece = null; // the actual game piece
	private ArrayList<Position> lyingCells = new ArrayList<>();

	// all available pieces
	private ArrayList<GamePiece> pieces = new ArrayList<>();

	public TetrisModel() {
		init();
	}

	private void init() {
		try {
			for (int i = 0; i < 2; i++)
				pieces.add(GamePieceFactory.generate(i));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		piece = rndPiece();
	}

	public void start() {
		System.out.println("START");
		Timer timer = new Timer(PERIOD_INTERVAL, this);
		timer.setInitialDelay(10);
		timer.start();
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

	public void down() {
		piece.down();
	}

	/**
	 * is used by the timer
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		down();
		if (isOnFloor()) {
			// put to lyingCells
			lyingCells.addAll(piece.getPositions());
			// new piece
			piece = rndPiece();
		}
		notify("REDRAW");
	}

	private GamePiece rndPiece()
	{
		int type = (int) (Math.random() * pieces.size()-2) + 1; //be aware of borders
		return new GamePiece(pieces.get(type));
	}
	
	private boolean isOnFloor() {
		for (Position p : piece.getPositions()) {
			if (p.getY() == BOARD_HEIGHT-1 || lyingCells.contains(p.onePosDown()))
				return true;
		}
		return false;
	}

	private void notify(String action) {
		setChanged();
		notifyObservers(new TetrisMessage(TetrisMessage.ACTION.REDRAW, piece, lyingCells));
	}

}

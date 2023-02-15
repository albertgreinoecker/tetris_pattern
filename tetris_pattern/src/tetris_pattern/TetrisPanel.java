package tetris_pattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TetrisPanel extends JPanel implements Observer {

	private final int CELL_SIZE = 40;
	private final int PIXEL_WIDTH = CELL_SIZE * TetrisModel.BOARD_WIDTH;
	private final int PIXEL_HEIGHT = CELL_SIZE * TetrisModel.BOARD_HEIGHT;


	public TetrisPanel(TetrisController controller, TetrisModel model) throws FileNotFoundException {
		model.addObserver(this); //no need to store the model here
		
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		setPreferredSize(new Dimension(PIXEL_WIDTH, PIXEL_HEIGHT));
		setMaximumSize(new Dimension(PIXEL_WIDTH, PIXEL_HEIGHT));
		setBackground(Color.RED);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		addKeyListener(controller);
	}

	@Override
	public void update(Observable o, Object arg) {
		setFocusable(true); //otherwise key listener will not work
		requestFocusInWindow();
		
		TetrisMessage msg = (TetrisMessage) arg;
		
		GamePiece piece = msg.getPiece();
		Graphics g = this.getGraphics();
		g.clearRect(0, 0, getWidth(), getHeight());

		ArrayList<Position> poss = piece.getPositions();
		poss.addAll(msg.getLyingCells());
		
		for (Position pos : poss) {
			g.setColor(pos.getColor());
			g.fillRect(CELL_SIZE * pos.getX(), CELL_SIZE * pos.getY(), CELL_SIZE, CELL_SIZE);
		}
	}
}

package tetris_pattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisPanel extends JPanel implements ActionListener {
	private final int BOARD_WIDTH = 10;
	private final int BOARD_HEIGHT = 22;
	private final int CELL_SIZE = 40;
	private final int PIXEL_WIDTH = CELL_SIZE * BOARD_WIDTH;
	private final int PIXEL_HEIGHT = CELL_SIZE * BOARD_HEIGHT;
	private final int PERIOD_INTERVAL = 1000;

	int yTot = 0;
	int xTot = 5;
	GamePiece piece = null;
	int orientation = 0;

	ArrayList<GamePiece> pieces = new ArrayList<>();

	public TetrisPanel() throws FileNotFoundException {
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		setPreferredSize(new Dimension(PIXEL_WIDTH, PIXEL_HEIGHT));
		setMaximumSize(new Dimension(PIXEL_WIDTH, PIXEL_HEIGHT));
		setBackground(Color.RED);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		//pieces.add(GamePieceFactory.generate(0)); //TODO loop
		pieces.add(GamePieceFactory.generate(1));
	}

	public void start() {
		System.out.println("START");
		Timer timer = new Timer(PERIOD_INTERVAL, this);
		timer.setInitialDelay(10);
		timer.start();
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("KEY");
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					orientation = (orientation + 1) % 4;
					break;
				case KeyEvent.VK_DOWN:
					if (yTot < BOARD_HEIGHT)
						yTot--;
					break;
				case KeyEvent.VK_LEFT:
					if (xTot > 0)
						xTot--;
					break;
				case KeyEvent.VK_RIGHT:
					if (xTot < BOARD_WIDTH)
						xTot++;
					break;
				default:
					return;
				}
				draw();
				System.out.printf("(%d %d)\n", xTot, yTot);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		draw();
		yTot++;
	}

	private void draw() {
		Graphics g = this.getGraphics();
		g.clearRect(0, 0, getWidth(), getHeight());
		GamePiece piece = pieces.get(0);
		System.out.println(piece);

		g.setColor(piece.getColor());
		for (Position pos : piece.getOrientation(orientation)) {
			g.fillRect(CELL_SIZE * (xTot + pos.getX()), CELL_SIZE * (yTot + pos.getY()), CELL_SIZE, CELL_SIZE);
		}
		
	}

}

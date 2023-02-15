package tetris_pattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

public class TetrisController extends KeyAdapter implements ActionListener {

	TetrisModel model;

	public TetrisController(TetrisModel model) {
		this.model = model;
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			model.changeOrientation();
			break;
		case KeyEvent.VK_DOWN:
			model.down();
			break;
		case KeyEvent.VK_LEFT:
			model.left();
			break;
		case KeyEvent.VK_RIGHT:
			model.right();
			break;
		default:
			return;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getName().equals("START"))
			model.start();

	}

}

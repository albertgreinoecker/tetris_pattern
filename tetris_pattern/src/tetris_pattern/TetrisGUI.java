package tetris_pattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TetrisGUI extends JFrame {

	private TetrisPanel game;

	public TetrisGUI(String title, TetrisController control, TetrisModel model) throws FileNotFoundException {
		super(title);
		setLayout(new BorderLayout());

		game = new TetrisPanel(control, model);
		// use this to get
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.add(game);
		centerPanel.setBackground(Color.BLACK);
		add(centerPanel, BorderLayout.CENTER);

		JButton start = new JButton("Start");
		start.setName("START");
		start.addActionListener(control);
		add(start, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public static void main(String[] args) throws FileNotFoundException {
		TetrisModel model = new TetrisModel();
		TetrisController control = new TetrisController(model);
		TetrisGUI gui = new TetrisGUI("Tetris", control, model);
		gui.setSize(1024, 1024);
		gui.setVisible(true);
	}
}

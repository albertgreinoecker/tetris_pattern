package tetris_pattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TetrisGUI extends JFrame implements ActionListener {

	TetrisPanel game;

	public TetrisGUI(String title) throws FileNotFoundException {
		super(title);
		setLayout(new BorderLayout());

		game = new TetrisPanel();
		//use this to get 
		JPanel centerPanel = new JPanel(new GridBagLayout()); 
		centerPanel.add(game);
		centerPanel.setBackground(Color.BLACK);
		add(centerPanel, BorderLayout.CENTER);

		JButton start = new JButton("Start");
		start.addActionListener(this);
		add(start, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}


	
	public static void main(String[] args) throws FileNotFoundException {
		TetrisGUI gui = new TetrisGUI("Tetris");
		gui.setSize(1024, 1024);
		gui.setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		game.start();
		
	}

}

package tetris_pattern;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GamePieceFactory {
	
	public static GamePiece generate(int which) throws FileNotFoundException {
		Color color = Utils.rndColor();
		GamePiece piece = new GamePiece();
		Scanner s = new Scanner(new File("resources/" + which + ".txt"));
		int pos = 0;
		String line = "";
		do // all pieces
		{
			int x = 0;
			ArrayList<Position> part = new ArrayList<>();
			do // all lines of one piece
			{
				line = s.nextLine();
				int y = 0;
				for (char c : line.toCharArray()) // all parts of a line
				{
					if (c == '#') {
						part.add(new Position(x, y, color));
					}
					y++;
				}
				x++;
			} while (s.hasNextLine() && line.length() > 0);
			piece.add(pos++, part);
		} while (pos <= 3);
		s.close();
		return piece;
	}
}

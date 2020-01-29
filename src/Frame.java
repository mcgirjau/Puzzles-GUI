import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

	private Puzzle puzzle;
	private SpotRenderer[][] grid;

	// frame is puzzle-dependent
	public Frame(Puzzle puzzle) {
		this.puzzle = puzzle;
		int rows = puzzle.getRows();
		int columns = puzzle.getColumns();
		grid = new SpotRenderer[rows][columns];
	}

	public void initialize() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800, 700));

		Container pane = this.getContentPane();
		pane.setLayout(new BorderLayout());

		// top pane holds navigation between puzzles
		JPanel topPane = new JPanel(new FlowLayout());
		pane.add(topPane, BorderLayout.NORTH);

		JPanel puzzleNavigation = new JPanel();
		JComboBox comboBox = new JComboBox();

		puzzleNavigation.add(new JLabel("Puzzle: "));
		puzzleNavigation.add((comboBox));

		String[] choices = Puzzle.getAvailablePuzzles();

		for (int i = 0; i < choices.length; ++i) {
			comboBox.addItem(choices[i]);
		}

		comboBox.addActionListener(new SwitchListener());

		topPane.add(puzzleNavigation);

		// bottom pane holds the game controls
		JPanel bottomPane = new JPanel(new FlowLayout());
		pane.add(bottomPane, BorderLayout.SOUTH);

		Container buttons = new JPanel();
		bottomPane.add(buttons, BorderLayout.SOUTH);
		buttons.setLayout(new GridLayout(1, 3));

		JButton rules = new JButton("How to Play");
		JButton restart = new JButton("Clear Board");
		JButton solve = new JButton("Solve Puzzle");

		buttons.add(rules);
		rules.addActionListener(new RulesListener(puzzle));

		// space between buttons
		buttons.add(Box.createRigidArea(new Dimension(1, 0)));

		buttons.add(restart);
		restart.addActionListener(new RestartListener(puzzle, this));

		// space between buttons
		buttons.add(Box.createRigidArea(new Dimension(1, 0)));

		buttons.add(solve);
		solve.addActionListener(new SolveListener(puzzle, this));

		// the middle holds the puzzle board
		JPanel board = new JPanel();
		board.setLayout(new GridLayout(puzzle.getRows() + 2, puzzle.getColumns() + 2));
		board.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		// counter to keep track of the spot we are to render next
		int n = 0;

		for (int i = 0; i < puzzle.getRows() + 2; ++i) {
			for (int j = 0; j < puzzle.getColumns() + 2; ++j) {
				// the corners are empty
				if ((i == 0 && j == 0) ||
						(i == 0 && j == puzzle.getColumns() + 1) ||
						(i == puzzle.getRows() + 1 && j == 0) ||
						(i == puzzle.getRows() + 1 && j == puzzle.getColumns() + 1)) {
					board.add(new HintPanel(" "));
				} else if (i == 0) {
					// the top holds the top hints
					board.add(new HintPanel(puzzle.top[j - 1]));
				} else if (j == 0) {
					// the left holds the left hints
					board.add(new HintPanel(puzzle.left[i - 1]));
				} else if (i == puzzle.getRows() + 1) {
					// the bottom holds the bottom hints
					board.add(new HintPanel(puzzle.bottom[j - 1]));
				} else if (j == puzzle.getColumns() + 1) {
					// the right holds the right hints
					board.add(new HintPanel(puzzle.right[i - 1]));
				} else {
					// the middle holds actual puzzle spots
					SpotRenderer spot;
					if (puzzle.isBlackAndWhite()) {
						spot = new SpotToggler(puzzle.getSpotList()[n++], puzzle);
					} else {
						spot = new SpotRenderer(puzzle.getSpotList()[n++], puzzle.getPossibleValues());
					}
					board.add(spot);
					grid[j - 1][i - 1] = spot;
				}
			}
		}

		pane.add(board, BorderLayout.CENTER);
	}

	public void update() {
		for (int i = 0; i < puzzle.getColumns(); ++i) {
			for (int j = 0; j < puzzle.getRows(); ++j) {
				grid[i][j].setValue();
			}
		}
		this.repaint();
	}
}

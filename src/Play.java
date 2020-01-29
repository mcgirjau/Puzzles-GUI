/*
    NAME: COSC 112 Project
    AUTHOR: Maria-Cristiana Girjau
    DATE: 11 December 2019
    DESCRIPTION: In this assignment, you are going to write a program to setup one of several puzzles and display it
                 for the user in a GUI interface. The interface should at least allow the user to:
                    * try to solve the puzzle
                    * change to a different kind of puzzle
                    * display a solution which has been found by the program

                 The design of the GUI is largely up to you. Your program will work for a number of different kinds of
                 puzzle, and you are going to have a number of classes.

                 Each of these puzzles consists of:
                    * A (usually rectangular) grid of spots to fill in. The spots are either pre-filled, or can be
                      filled in by the player.
                    * A set of possible values that can be used to fill in a spot
                    * A set of constraints on how the spots are filled in. The goal is to fill in all the spots so that
                      no constraint is violated. Each constraint will involve some subset of the spots.

                 For each different puzzle type, you will want a different class to set up that type of puzzle. You
                 will usually want this class to read the details of a particular instance of a puzzle from a file, but
                 you may, especially when prototyping, want to hand code the construction into the class. In the final
                 version, at least one type of puzzle must be read from a file.

                 All of these classes should be related - they should either be in a class hierarchy, or they should all
                 implement the same interface. You should decide which is most appropriate for your design. The puzzle
                 creation class should be able to hand various pieces of information to the using program.

                 Your search method should be exactly the same for all the puzzles – it should be the same code in the
                 same place. This is the organizational key to this program. Each sort of constraint should be a class,
                 and each constraint an instance of that class. These classes should be related, either in a hierarchy,
                 or via an interface.

                 The same is true of varieties of spots. The order of the labeling matters to the length of time it
                 takes to solve the puzzle. You should keep track of this time, in a simple way (you can just count the
                 number of times you execute the loop in the labeling method. For an extra-special program, think about
                 choosing the spots in some useful order.

                 You will need something to give you user options for filling in the squares (do not use TextFields
                 and free-form text – too messy).
 */

import javax.swing.*;

public class Play {

	public static void main(String[] args) throws ClassNotFoundException,
												  UnsupportedLookAndFeelException,
			                                      InstantiationException,
			                                      IllegalAccessException {

		// set cross-platform look and feel for consistency
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

		FrameManager.run();
	}
}

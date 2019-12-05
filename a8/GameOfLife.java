package a8;

import javax.swing.JFrame;

public class GameOfLife {

	public static void main(String[] args) {
		
		// The dimensions of the square can be adjusted below.
		
		int dimensions = 80;
		
		A8view view = new A8view(dimensions);
		A8model model = new A8model(dimensions);
		A8controller controller = new A8controller(view, model);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Game of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		main_frame.setContentPane(view);

		main_frame.pack();
		main_frame.setVisible(true);
		view.addA8viewlistener(controller);
		model.addObserver(controller);
	}

}
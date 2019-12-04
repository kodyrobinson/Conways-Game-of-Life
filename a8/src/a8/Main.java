package a8;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//Thresholds for survival and birth requirements:
		int lowBirth = 3;
		int highBirth = 3;
		int lowSurvival = 2;
		int highSurvival = 3;
		
		int dimensions = 30;
		
		A8view view = new A8view(dimensions);
		A8model model = new A8model(dimensions, lowBirth, highBirth, lowSurvival, highSurvival);
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

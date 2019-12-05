package a8;

import java.awt.Color;

public class A8controller implements A8observer, A8viewlistener {
	
	private A8view _view;
	private A8model _model;

	public A8controller(A8view view, A8model model) {
		_model = model;
		_view = view;
	}
	
	public void update() {
		for (int x = 0; x < _model.getUniverse().length; x++) {
			for (int y = 0; y < _model.getUniverse()[0].length; y++) {
				if (_model.getUniverse()[x][y]) {
					_view._universe.getSpotAt(x, y).setBackground(Color.BLACK);
				} else {
					_view._universe.getSpotAt(x, y).setBackground(Color.WHITE);
				}
			}
		}
		
	}
	public void handleViewEvent(ViewEvent e) {
		if (e.isNextEvent()) {
			_model.nextGen();
		}
		if (e.isRandomEvent()) {
			_model.random();
		}
		if (e.isSpotEvent()) {
			SpotEvent spotEvent = (SpotEvent) e;
			_model.updateSpot(spotEvent.getSpotX(), spotEvent.getSpotY());
		}
		if (e.isWipeEvent()) {
			_model.wipe();
		}
		if (e.isTorusEvent()) {
			_model.torus();
		}
		if (e.isAdvanceEvent()) {
			_model.advance(((AdvanceEvent) e).getTime());
		}
		if (e.isThresholdEvent()) {
			_model.setThreshold(((ThresholdEvent)e).getLb(),((ThresholdEvent)e).getHb(), ((ThresholdEvent)e).getLs(), ((ThresholdEvent)e).getHs());
		}
	}



}

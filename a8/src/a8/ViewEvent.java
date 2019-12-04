package a8;

abstract public class ViewEvent {

	public boolean isWipeEvent() {
		return false;
	}
	public boolean isRandomEvent() {
		return false;
	}
	public boolean isSpotEvent() {
		return false;
	}
	public boolean isNextEvent() {
		return false;
	}
	public boolean isTorusEvent() {
		return false;
	}
	public boolean isAdvanceEvent() {
		return false;
	}
}

class WipeEvent extends ViewEvent {
	
	public boolean isWipeEvent() {
		return true;
	}
}

class RandomEvent extends ViewEvent {
	public boolean isRandomEvent() {
		return true;
	}
}

class NextEvent extends ViewEvent {
	public boolean isNextEvent() {
		return true;
	}
}

class SpotEvent extends ViewEvent {
	private Spot _spot;
	
	public SpotEvent(Spot s) {
		_spot = s;
	}
	
	public boolean isSpotEvent() {
		return true;
	}
	public int getSpotX() {
		return _spot.getSpotX();
	}
	public int getSpotY() {
		return _spot.getSpotY();
	}
}
class TorusEvent extends ViewEvent {
	public boolean isTorusEvent() {
		return true;
	}
}
class AdvanceEvent extends ViewEvent {
	private int _time;
	
	public AdvanceEvent(int time) {
		_time = time;
	}
	
	public boolean isAdvanceEvent() {
		return true;
	}
	public int getTime() {
		return _time;
	}
}
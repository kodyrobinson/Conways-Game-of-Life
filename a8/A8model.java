package a8;

import java.util.List;
import java.util.ArrayList;

public class A8model implements Runnable {

	private List<A8observer> _observers;
	private boolean[][] _bools;
	private int _lb;
	private int _hb;
	private int _ls;
	private int _hs;
	private boolean _torus;
	private boolean _running;
	private int _time;
	
	public A8model(int dimensions) {
		_bools = new boolean[dimensions][dimensions];
		_observers = new ArrayList<A8observer>();
		_lb = 3;
		_hb = 3;
		_ls = 2;
		_hs = 3;
		_torus = false;
		_running = false;
		_time = 10;
	}

	public void wipe() {
		for (int x = 0; x < _bools.length; x++) {
			for (int y = 0; y < _bools.length; y++) {
				_bools[x][y] = false;
			}
		}
		notifyObservers();
	}
	
	public void random() {
		for (int x = 0; x < _bools.length; x++) {
			for (int y = 0; y < _bools.length; y++) {
				double rand = Math.random();
				boolean spotVal = rand < 0.5;
				_bools[x][y] = spotVal;
			}
		}
		notifyObservers();
	}
	public void nextGen() {
		int[][] neighbors = getNeighbors();
		for (int x = 0; x < _bools.length; x++) {
			for (int y = 0; y < _bools.length; y++) {
				if (_bools[x][y] && neighbors[x][y] <= _hs && neighbors[x][y] >= _ls) {
					_bools[x][y] = true;
				} else if (_bools[x][y]) {
					_bools[x][y] = false;
				} else if (!_bools[x][y] && neighbors[x][y] <= _hb && neighbors[x][y] >= _lb) {
					_bools[x][y] = true;
				} 
			}
		}
		notifyObservers();
	}
	
	public int[][] getNeighbors() {
		int[][] neighborArray = new int[_bools.length][_bools.length];
	
		if (!_torus) {
			for (int x = 0; x < _bools.length; x++) {
				for (int y = 0; y < _bools.length; y++) {
					int count = 0;
					try {
						if (_bools[x-1][y+1]) {
							count++;
						}
						if (_bools[x][y+1]) {
							count++;
						}
						if (_bools[x+1][y+1]) {
							count++;
						}
						if (_bools[x-1][y]) {
							count++;
						}
						if (_bools[x+1][y]) {
							count++;
						}
						if (_bools[x-1][y-1]) {
							count++;
						}
						if (_bools[x][y-1]) {
							count++;
						}
						if (_bools[x+1][y-1]) {
							count++;
						}
					}
					catch(Exception e) {
						
					}
					neighborArray[x][y] = count;
				}
			}
		} else {
			for (int x = 1; x < _bools.length-1; x++) {
				for (int y = 1; y < _bools.length-1; y++) {
					int count = 0;
					
					if (_bools[x-1][y+1]) {
						count++;
					}
					if (_bools[x][y+1]) {
						count++;
					}
					if (_bools[x+1][y+1]) {
						count++;
					}
					if (_bools[x-1][y]) {
						count++;
					}
					if (_bools[x+1][y]) {
						count++;
					}
					if (_bools[x-1][y-1]) {
						count++;
					}
					if (_bools[x][y-1]) {
						count++;
					}
					if (_bools[x+1][y-1]) {
						count++;
					}
					
					neighborArray[x][y] = count;
				}
			}
			for (int y = 1; y < _bools.length-1; y++){
				int count = 0;
				if (_bools[_bools.length-1][y+1]) {
					count++;
				}
				if (_bools[_bools.length-1][y]) {
					count++;
				}
				if (_bools[_bools.length-1][y-1]) {
					count++;
				}
				if (_bools[0][y+1]) {
					count++;
				}
				if (_bools[1][y+1]) {
					count++;
				}
				if (_bools[1][y]) {
					count++;
				}
				if (_bools[1][y-1]) {
					count++;
				}
				if (_bools[0][y-1]) {
					count++;
				}
				neighborArray[0][y] = count;
			}
			for (int y = 1; y < _bools.length-1; y++){
				int count = 0;
				if (_bools[_bools.length-2][y+1]) {
					count++;
				}
				if (_bools[_bools.length-2][y]) {
					count++;
				}
				if (_bools[_bools.length-2][y-1]) {
					count++;
				}
				if (_bools[_bools.length-1][y+1]) {
					count++;
				}
				if (_bools[0][y+1]) {
					count++;
				}
				if (_bools[0][y]) {
					count++;
				}
				if (_bools[0][y-1]) {
					count++;
				}
				if (_bools[_bools.length-1][y-1]) {
					count++;
				}
				neighborArray[_bools.length-1][y] = count;
			}
			for (int x = 1; x < _bools.length-1; x++){
				int count = 0;
				if (_bools[x-1][0]) {
					count++;
				}
				if (_bools[x+1][0]) {
					count++;
				}
				if (_bools[x-1][1]) {
					count++;
				}
				if (_bools[x][1]) {
					count++;
				}
				if (_bools[x+1][1]) {
					count++;
				}
				if (_bools[x+1][_bools.length-1]) {
					count++;
				}
				if (_bools[x][_bools.length-1]) {
					count++;
				}
				if (_bools[x-1][_bools.length-1]) {
					count++;
				}
				neighborArray[x][0] = count;
			}
			for (int x = 1; x < _bools.length-1; x++){
				int count = 0;
				if (_bools[x-1][0]) {
					count++;
				}
				if (_bools[x+1][0]) {
					count++;
				}
				if (_bools[x][0]) {
					count++;
				}
				if (_bools[x-1][_bools.length-1]) {
					count++;
				}
				if (_bools[x+1][_bools.length-1]) {
					count++;
				}
				if (_bools[x+1][_bools.length-2]) {
					count++;
				}
				if (_bools[x][_bools.length-2]) {
					count++;
				}
				if (_bools[x-1][_bools.length-2]) {
					count++;
				}
				neighborArray[x][_bools.length-1] = count;
			}
			int count = 0;
			if (_bools[1][1]) {
				count++;
			}
			if (_bools[0][1]) {
				count++;
			}
			if (_bools[1][0]) {
				count++;
			}
			if (_bools[_bools.length-1][1]) {
				count++;
			}
			if (_bools[_bools.length-1][0]) {
				count++;
			}
			if (_bools[1][_bools.length-1]) {
				count++;
			}
			if (_bools[0][_bools.length-1]) {
				count++;
			}
			if (_bools[_bools.length-1][_bools.length-1]) {
				count++;
			}
			neighborArray[0][0] = count;
			count = 0;
			if (_bools[_bools.length-1][0]) {
				count++;
			}
			if (_bools[0][_bools.length-1]) {
				count++;
			}
			if (_bools[0][_bools.length-2]) {
				count++;
			}
			if (_bools[_bools.length-1][1]) {
				count++;
			}
			if (_bools[_bools.length-1][_bools.length-2]) {
				count++;
			}
			if (_bools[_bools.length-2][_bools.length-1]) {
				count++;
			}
			if (_bools[_bools.length-2][_bools.length-2]) {
				count++;
			}
			if (_bools[0][0]) {
				count++;
			}
			neighborArray[_bools.length-1][_bools.length-1] = count;
			count = 0;
			if (_bools[1][_bools.length-1]) {
				count++;
			}
			if (_bools[1][_bools.length-2]) {
				count++;
			}
			if (_bools[0][_bools.length-2]) {
				count++;
			}
			if (_bools[1][0]) {
				count++;
			}
			if (_bools[0][0]) {
				count++;
			}
			if (_bools[_bools.length-1][_bools.length-1]) {
				count++;
			}
			if (_bools[_bools.length-1][_bools.length-2]) {
				count++;
			}
			if (_bools[_bools.length-1][0]) {
				count++;
			}
			neighborArray[0][_bools.length-1] = count;
			count = 0;
			if (_bools[_bools.length-2][0]) {
				count++;
			}
			if (_bools[_bools.length-2][1]) {
				count++;
			}
			if (_bools[_bools.length-1][1]) {
				count++;
			}
			if (_bools[0][1]) {
				count++;
			}
			if (_bools[0][0]) {
				count++;
			}
			if (_bools[_bools.length-1][_bools.length-1]) {
				count++;
			}
			if (_bools[_bools.length-2][_bools.length-1]) {
				count++;
			}
			if (_bools[0][_bools.length-1]) {
				count++;
			}
			neighborArray[_bools.length-1][0] = count;
		}
		return neighborArray.clone();
	}
	public void addObserver(A8observer o) {
		_observers.add(o);
	}
	public void removeObserver(A8observer o) {
		_observers.remove(o);
	}
	public void notifyObservers() {
		for (A8observer o : _observers) {
			o.update();
		}
	}
	public void updateSpot(int x, int y) {
		if (_bools[x][y]) {
			_bools[x][y] = false;
		} else {
			_bools[x][y] = true;
		}
	}
	public boolean[][] getUniverse(){
		return _bools.clone();
	}
	public void torus() {
		if (_torus) {
			_torus = false;
		} else {
			_torus = true;
		}
	}
	public void advance(int t) {
		if (_running) {
			_running = false;
		} else {
			_running = true;
		}
		_time = t;
		(new Thread(this)).start();
	}

	@Override
	public void run() {
		while (_running) {
			nextGen();
			notifyObservers();
			try {
				Thread.sleep(_time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
	}
	public void setThreshold(int lb, int hb, int ls, int hs) {
		_lb = lb;
		_hb = hb;
		_ls = ls;
		_hs = hs;
	}
}
	

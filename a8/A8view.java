package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class A8view extends JPanel implements ActionListener, SpotListener {

	public JSpotBoard _universe;
	private JPanel _buttons;
	private List<A8viewlistener> listeners;
	private JSlider _slider;
	private JPanel _textBoxes;
	private JFormattedTextField lb;
	private JFormattedTextField hb;
	private JFormattedTextField ls;
	private JFormattedTextField hs;
	
	
	public A8view(int dimensions) {
		_universe = new JSpotBoard(dimensions, dimensions, 1);
		setLayout(new BorderLayout());
		add(_universe, BorderLayout.CENTER);
		
		_universe.addSpotListener(this);
		
		_buttons = new JPanel();
		_buttons.setLayout(new GridLayout(1,0));
		_textBoxes = new JPanel();
		_textBoxes.setLayout(new GridLayout(2,4));
		
		_buttons.add(new JButton("Set Thresholds"));
		_buttons.add(new JButton("Wipe"));
		_buttons.add(new JButton("Random"));
		_buttons.add(new JButton("Next Gen"));
		_buttons.add(new JButton("Torus"));
		_buttons.add(new JButton("Advance"));
		
		_textBoxes.add(new JLabel("Low Birth"));
		_textBoxes.add(new JLabel("High Birth"));
		_textBoxes.add(new JLabel("Low Survival"));
		_textBoxes.add(new JLabel("High Survival"));
		
		
		lb = new JFormattedTextField(NumberFormat.getNumberInstance());
		lb.setValue(3);
		_textBoxes.add(lb);
		hb = new JFormattedTextField(NumberFormat.getNumberInstance());
		hb.setValue(3);
		_textBoxes.add(hb);
		ls = new JFormattedTextField(NumberFormat.getNumberInstance());
		ls.setValue(2);
		_textBoxes.add(ls);
		hs = new JFormattedTextField(NumberFormat.getNumberInstance());
		hs.setValue(3);
		_textBoxes.add(hs);
		
		add(_buttons, BorderLayout.SOUTH);
		add(_textBoxes, BorderLayout.NORTH);
		
		for(Component c: _buttons.getComponents()) {
			JButton b = (JButton) c;
			b.addActionListener(this);
		}
		JSlider s = new JSlider(10, 1000);
		_buttons.add(s);
		_slider = s;
		
		listeners = new ArrayList<A8viewlistener>();
		
		this.setFocusable(true);
		this.grabFocus();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		
		String buttonPressed = button.getText();
		char startingLetter = buttonPressed.charAt(0);
		ViewEvent viewEvent = null;
		switch(startingLetter) {
		case 'W':
			 viewEvent = (WipeEvent) new WipeEvent();
			break;
		case 'R':
			viewEvent = (RandomEvent) new RandomEvent();
			break;
		case 'N':
			viewEvent = (NextEvent) new NextEvent();
			break;
		case 'T':
			viewEvent = (TorusEvent) new TorusEvent();
			break;
		case 'A':
			viewEvent = (AdvanceEvent) new AdvanceEvent(_slider.getValue());
			break;
		case 'S':
			viewEvent = (ThresholdEvent) new ThresholdEvent(((Number)lb.getValue()).intValue(), ((Number)hb.getValue()).intValue(), ((Number)ls.getValue()).intValue(), ((Number)hs.getValue()).intValue());
			break;
		}
		for (A8viewlistener l : listeners) {
			l.handleViewEvent(viewEvent);
		}
	}

	@Override
	public void spotClicked(Spot spot) {
		if (spot.getBackground()==Color.WHITE) {
			spot.setBackground(Color.BLACK);
		} else {
			spot.setBackground(Color.WHITE);
		}
		ViewEvent viewEvent = (SpotEvent) new SpotEvent(spot);
		for (A8viewlistener l : listeners) {
			l.handleViewEvent(viewEvent);
		}
	}



	@Override
	public void spotEntered(Spot spot) {
		spot.highlightSpot();
	}



	@Override
	public void spotExited(Spot spot) {
		spot.unhighlightSpot();
	}
	
	public void addA8viewlistener(A8viewlistener l) {
		listeners.add(l);
	}
}

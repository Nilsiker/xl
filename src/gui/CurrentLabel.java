package gui;

import model.CurrentSlot;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class CurrentLabel extends ColoredLabel implements Observer {
    public CurrentLabel() {
        super("A1", Color.WHITE);

    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
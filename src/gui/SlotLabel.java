package gui;

import model.Sheet;
import util.XLException;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class SlotLabel extends ColoredLabel implements Observer {
	private String address;
	private Sheet sheet;

    public SlotLabel(String address, Sheet sheet){
		super("                    ", Color.WHITE, RIGHT);
		this.sheet = sheet;
		this.address = address;
		sheet.addObserver(this);
	}

	public String getAddress() {
		return address;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(sheet.contains(address)){
			try{
				setText(sheet.get(address).getValueString(sheet));
			}catch (XLException e){
				sheet.setStatus(e.getMessage());
				setText("ERROR");
			}
		}else{
			setText("");
		}
	}

	@Override
	public String toString() {
		return address;
	}
}
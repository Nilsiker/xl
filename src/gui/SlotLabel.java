package gui;

import model.Sheet;
import util.XLException;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class SlotLabel extends ColoredLabel implements MouseListener, Observer {
	private String address;
	private Sheet sheet;
	private CurrentSlot current;

    public SlotLabel(String address, Sheet sheet, CurrentSlot current){
		super("                    ", Color.WHITE, RIGHT);
		this.sheet = sheet;
		this.address = address;
		this.current = current;
		addMouseListener(this);
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
	public void mouseClicked(MouseEvent e) {
    	current.unmark();
		current.setLabel(this);
		setBackground(Color.YELLOW);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public String toString() {
		return address;
	}
}
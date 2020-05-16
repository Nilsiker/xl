package model;

import expr.Environment;
import util.XLException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class Sheet extends Observable implements Environment {
	private Map<String, Slot> slots;
	private String status;

	public Sheet() {
		slots = new HashMap<>();
	}

	public void addSlot(String address, String data) {
		try {
			Slot toAdd = SlotFactory.build(data);
			if (validate(toAdd, address)) {
				slots.put(address, SlotFactory.build(data));
			}
		} catch (XLException e) {
			status = e.getMessage();
		}

		setChanged();
		notifyObservers();
	}

	public void removeSlot(String address) {
		slots.remove(address);

		setChanged();
		notifyObservers();
	}

	public Slot get(String address) {
		return slots.get(address);
	}

	public boolean contains(String address) {
		return slots.containsKey(address);
	}

	public void clear() {
		slots.clear();
		setChanged();
		notifyObservers();
	}

	private boolean validate(Slot slot, String address) {
		Slot previous = get(address);
		slots.put(address, new ErrorSlot());
		try {
			slot.getValue(this);
			status = "Slots updated!";
		} catch (XLException | NullPointerException e) { // den här notationen är skitball!
			status = e.getMessage();
			if (previous == null)
				slots.remove(address);
			else
				slots.put(address, previous);
			return false;
		}
		return true;
	}

	@Override
	public double value(String slotName) {
		if (slots.containsKey(slotName)) {
			return slots.get(slotName).getValue(this);
		} else {
			return 0;
		}
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void save(String path) {

		XLPrintStream stream;
		try {
			stream = new XLPrintStream(path);
			Set<Entry<String, Slot>> set = slots.entrySet();
			stream.save(set);
			status = "Saved file!";
		} catch (FileNotFoundException e) {
			status = e.getMessage();
			e.printStackTrace();
		}
		setChanged();
		notifyObservers();
	}

	public void load(String path) {
		Map<String, Slot> temp = new HashMap<>();
		try {
			XLBufferedReader bufferedReader = new XLBufferedReader(path);
			bufferedReader.load(temp);
			for(Entry<String, Slot> row: temp.entrySet()) {
				addSlot(row.getKey(), row.getValue().toString());
			}
		} catch (Exception e) {
			status = e.getMessage();
			e.printStackTrace();
		}
		setChanged();
		notifyObservers();
	}
}

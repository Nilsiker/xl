package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import gui.CurrentSlot;
import model.Sheet;

class ClearMenuItem extends JMenuItem implements ActionListener {
	Sheet sheet;
	CurrentSlot currentSlot;
    public ClearMenuItem(Sheet sheet, CurrentSlot currentSlot) {
        super("Clear");
        addActionListener(this);
        this.sheet = sheet;
        this.currentSlot = currentSlot;
    }

    public void actionPerformed(ActionEvent e) {
        sheet.removeSlot(currentSlot.getAddress());
    }
}
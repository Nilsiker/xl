package gui;
import java.awt.*;
import java.util.Observable;

public class CurrentSlot extends Observable {
    private SlotLabel label;

    public void setLabel(SlotLabel label) {
        this.label = label;
        this.label.getText();
        setChanged();
        notifyObservers();
    }

    public String getAddress() {
        return label.getAddress();
    }

    public String toString(){
        return label.toString();
    }

    public void unmark(){
        if(label != null) label.setBackground(Color.WHITE);
    }
}

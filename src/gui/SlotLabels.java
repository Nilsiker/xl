package gui;

import model.Sheet;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;

public class SlotLabels extends GridPanel {
	public List<SlotLabel> labelList;

	public SlotLabels(int rows, int cols, Sheet sheet, CurrentSlot current) {
		super(rows + 1, cols);
		labelList = new ArrayList<SlotLabel>(rows * cols);
		for (char ch = 'A'; ch < 'A' + cols; ch++) {
			add(new ColoredLabel(Character.toString(ch), Color.LIGHT_GRAY, SwingConstants.CENTER));
		}
		for (int row = 1; row <= rows; row++) {
			for (char ch = 'A'; ch < 'A' + cols; ch++) {
				SlotLabel label = new SlotLabel(ch+Integer.toString(row), sheet);
				add(label);
				labelList.add(label);
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						current.unmark();
						current.setLabel(label);
						label.setBackground(Color.YELLOW);
					}
				});
			}
		}

		SlotLabel first = labelList.get(0);
		first.setBackground(Color.YELLOW);
		current.setLabel(first);
	}
}

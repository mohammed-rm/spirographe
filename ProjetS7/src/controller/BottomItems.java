package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

public class BottomItems {

	/**
	 * Method to add a name to the application
	 *
	 * @return labName
	 */
	public JLabel createLabName() {
		JLabel labName = new JLabel("\u00A9 2021 Spirograph Application.");
		labName.setForeground(UIManager.getColor("Button.background"));
		labName.setBackground(UIManager.getColor("Button.focus"));
		labName.setBounds(10, 15, 260, 15);

		return labName;
	}

	/**
	 * Method to display date and time
	 *
	 * @return labTime
	 */
	public JLabel createTime() {
		JLabel labTime = new JLabel();
		labTime.setForeground(UIManager.getColor("Button.background"));
		labTime.setHorizontalAlignment(SwingConstants.RIGHT);
		labTime.setText(new Date().toString());
		ActionListener updateClockAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labTime.setText(new Date().toString());
			}
		};
		Timer tm = new Timer(1000, updateClockAction);
		tm.start();
		labTime.setBounds(860, 15, 330, 15);

		return labTime;
	}
}

package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Panel builder
	 */
	public Panel() {
		setOpaque(false);
		new JPanel();
		setLayout(null);
	}

	/**
	 * Method to change the shape and the color of the main panel
	 */
	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setColor(getBackground());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint gradPaint = new GradientPaint(0, 0, Color.decode("#3a4a4c"), 0, getHeight(),
				Color.decode("#fdbb2d"));
		g2.setPaint(gradPaint);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		super.paintComponent(grphcs);

	}
}

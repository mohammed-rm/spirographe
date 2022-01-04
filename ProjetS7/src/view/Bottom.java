package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Bottom extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public Bottom() {
		setOpaque(false);
		new JPanel();
		setLayout(null);
		setSize(WindowBuilder.getFrameWidth(), 40);
		setLocation(0, 760);
	}

	/**
	 *
	 */
	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setColor(getBackground());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint gradPaint = new GradientPaint(0, 0, Color.decode("#111111"), 0, getHeight(),
				Color.decode("#302b63"));
		g2.setPaint(gradPaint);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		g2.fillRect(0, 0, getWidth(), 20);
		super.paintComponent(grphcs);
	}
}

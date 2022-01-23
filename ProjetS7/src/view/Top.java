package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Top extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Top panel builder
	 */
	public Top() {
		setOpaque(false);
		new JPanel();
		setLayout(null);
		setSize(WindowBuilder.getFrameWidth(), 40);
	}

	/**
	 * Method to change the shape and the color of the top panel
	 */
	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D graph2D = (Graphics2D) grphcs;
		graph2D.setColor(getBackground());
		graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint gradPaint = new GradientPaint(0, 0, Color.decode("#111111"), 0, getHeight(),
				Color.decode("#302b63"));
		graph2D.setPaint(gradPaint);
		graph2D.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		graph2D.fillRect(0, getHeight() - 25, getWidth(), getHeight());
		super.paintComponent(grphcs);
	}

}

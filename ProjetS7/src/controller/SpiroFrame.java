package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class SpiroFrame {

	public static final Color BLACK = Color.BLACK;
	public static final Color WHITE = Color.WHITE;
	private static final Color DEFAULT_CLEAR_COLOR = WHITE;
	// Boundary of drawing
	private static final double BORDER = 0.0;
	// Default canvas size
	private static final int DEFAULT_SIZE = 800;
	private static final double PEN_RADIUS = 0.002;
	private Color colorOfPen;
	// Canvas size
	private int width = 1050;
	private int height = 720;
	private double radiusOfPen;
	// To show or not the window
	private boolean defer = true;
	private double xmin, ymin, xmax, ymax;
	// JLabel for drawing
	private JLabel spiro;
	private BufferedImage offScreen, onScreen;
	private Graphics2D offScreenG2D, onScreenG2D;

	/**
	 * Class builder
	 */
	public SpiroFrame() {
		initSpiro();
	}

	/**
	 * Method to initialize
	 */
	private void initSpiro() {
		offScreen = new BufferedImage(2 * width, 2 * height, BufferedImage.TYPE_INT_ARGB);
		onScreen = new BufferedImage(2 * width, 2 * height, BufferedImage.TYPE_INT_ARGB);
		offScreenG2D = offScreen.createGraphics();
		onScreenG2D = onScreen.createGraphics();
		offScreenG2D.scale(2.0, 2.0);
		offScreenG2D.setColor(DEFAULT_CLEAR_COLOR);
		offScreenG2D.fillRect(0, 0, width, height);
		definePenRadius(PEN_RADIUS);
		RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		offScreenG2D.addRenderingHints(hints);
		ImageDraw icon = new ImageDraw(onScreen);
		spiro = new JLabel(icon);

	}

	/**
	 * Modify the scale of the x
	 *
	 * @param min the minimum the x
	 * @param max the maximum scale
	 */
	public void modifyXscale(double min, double max) {
		double size = max - min;
		if (size == 0.0)
			throw new IllegalArgumentException("Min and Max can't be equal!");
		xmin = min - BORDER * size;
		xmax = max + BORDER * size;
	}

	/**
	 * Modify the scale of the y
	 *
	 * @param min the minimum of the y
	 * @param max the maximum of the y
	 */
	public void modifyYscale(double min, double max) {
		double size = max - min;
		if (size == 0.0)
			throw new IllegalArgumentException("Min and Max can't be equal!");
		ymin = min - BORDER * size;
		ymax = max + BORDER * size;
	}

	/**
	 * Method to scale from user coordinates to screen coordinates
	 * 
	 * @param x
	 * @return width * (x - xmin) / (xmax - xmin);
	 */
	private double scaleX(double x) {
		return width * (x - xmin) / (xmax - xmin);
	}

	/**
	 * Method to scale from user coordinates to screen coordinates
	 * 
	 * @param y
	 * @return return height * (ymax - y) / (ymax - ymin);
	 */
	private double scaleY(double y) {
		return height * (ymax - y) / (ymax - ymin);
	}

	/**
	 * Method to clear the screen to the new color
	 *
	 * @param background color
	 */
	public void remove(Color color) {
		offScreenG2D.setColor(color);
		offScreenG2D.fillRect(0, 0, width, height);
		offScreenG2D.setColor(colorOfPen);
		drawSpiro();
	}

	/**
	 * Method for pen radius
	 *
	 * @param radius the radius of the pen
	 */
	public void definePenRadius(double radius) {
		radiusOfPen = radius * DEFAULT_SIZE;
		BasicStroke stroke = new BasicStroke((float) radiusOfPen, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		offScreenG2D.setStroke(stroke);
	}

	/**
	 * Method for pen color
	 *
	 * @param color the color to make the pen
	 */
	public void definePenColor(Color color) {
		colorOfPen = color;
		offScreenG2D.setColor(colorOfPen);
	}

	/**
	 * Method to allow us use the drawing in the WindowBuilder class
	 *
	 * @return spiro
	 */
	public JLabel getSpiroLabel() {
		return spiro;
	}

	/**
	 * Method to draw a point at coordinates x, y
	 *
	 * @param x the x-coordinate of the point
	 * @param y the y-coordinate of the point
	 */
	public void drawPoint(double x, double y) {
		double xs = scaleX(x);
		double ys = scaleY(y);
		double r = radiusOfPen;
		if (r <= 1)
			offScreenG2D.fillRect((int) Math.round(scaleX(x)), (int) Math.round(scaleY(y)), 1, 1);
		else
			offScreenG2D.fill(new Ellipse2D.Double(xs - r / 2, ys - r / 2, r, r));
		drawSpiro();
	}

	/**
	 * Method to copy offScreen to onSrceen and show it
	 * 
	 */
	public void showDrawing() {
		onScreenG2D.drawImage(offScreen, 0, 0, null);
	}

	/**
	 * Method to draw onscreen
	 */
	private void drawSpiro() {
		if (!defer)
			showDrawing();
	}

	/****************************************************************************************************************/
	/****************************************************************************************************************/
	@SuppressWarnings("serial")
	/**
	 * Internal class for displays
	 *
	 */
	private static class ImageDraw extends ImageIcon {

		public ImageDraw(Image image) {
			super(image);
		}

		public int getIconWidth() {
			return super.getIconWidth() / 2;
		}

		/**
		 * Gets the height of the icon.
		 *
		 * @return the height in pixels
		 */
		public int getIconHeight() {
			return super.getIconHeight() / 2;
		}

		public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.scale(0.5, 0.5);
			super.paintIcon(c, g2, x * 2, y * 2);
			g2.dispose();
		}
	}
	/****************************************************************************************************************/
}

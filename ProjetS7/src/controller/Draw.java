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

public final class Draw {

	public static final Color BLACK = Color.BLACK;
	public static final Color WHITE = Color.WHITE;
	// default colors
	private static final Color DEFAULT_CLEAR_COLOR = WHITE;
	// boundary of drawing canvas, 0% border
	private static final double BORDER = 0.0;
	// default canvas size is SIZE-by-SIZE
	private static final int DEFAULT_SIZE = 800;
	// default pen radius
	private static final double DEFAULT_PEN_RADIUS = 0.002;
	// current pen color
	private Color penColor;
	// canvas size
	private int width = 1050;
	private int height = 720;
	// current pen radius
	private double penRadius;
	// show we draw immediately or wait until next show
	private boolean defer = false;
	private double xmin, ymin, xmax, ymax;
	// the JLabel for drawing
	private JLabel draw;
	// double buffered graphics
	private BufferedImage offscreenImage, onscreenImage;
	private Graphics2D offscreen, onscreen;

	/**
	 * Initializes an empty drawing object.
	 */
	public Draw() {
		init();
	}

	/***************************************************************************
	 * Drawing images.
	 ***************************************************************************/
	//
	private void init() {
		offscreenImage = new BufferedImage(2 * width, 2 * height, BufferedImage.TYPE_INT_ARGB);
		onscreenImage = new BufferedImage(2 * width, 2 * height, BufferedImage.TYPE_INT_ARGB);
		offscreen = offscreenImage.createGraphics();
		onscreen = onscreenImage.createGraphics();
		offscreen.scale(2.0, 2.0); // since we made it 2x as big
		offscreen.setColor(DEFAULT_CLEAR_COLOR);
		offscreen.fillRect(0, 0, width, height);
		setPenRadius(DEFAULT_PEN_RADIUS);
		// add antialiasing
		RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		offscreen.addRenderingHints(hints);
		// frame stuff
		RetinaImageIcon icon = new RetinaImageIcon(onscreenImage);
		draw = new JLabel(icon);

	}

	/****************************************************************************************************************/
	/**
	 * Sets the x-scale.
	 *
	 * @param min the minimum value of the x-scale
	 * @param max the maximum value of the x-scale
	 * @throws IllegalArgumentException if {@code (max == min)}
	 * @throws IllegalArgumentException if either {@code min} or {@code max} is
	 *                                  either NaN or infinite
	 */
	public void setXscale(double min, double max) {
		double size = max - min;
		if (size == 0.0)
			throw new IllegalArgumentException("the min and max are the same");
		xmin = min - BORDER * size;
		xmax = max + BORDER * size;
	}

	/****************************************************************************************************************/

	/****************************************************************************************************************/
	/**
	 * Sets the y-scale.
	 *
	 * @param min the minimum value of the y-scale
	 * @param max the maximum value of the y-scale
	 * @throws IllegalArgumentException if {@code (max == min)}
	 * @throws IllegalArgumentException if either {@code min} or {@code max} is
	 *                                  either NaN or infinite
	 */
	public void setYscale(double min, double max) {
		double size = max - min;
		if (size == 0.0)
			throw new IllegalArgumentException("the min and max are the same");
		ymin = min - BORDER * size;
		ymax = max + BORDER * size;
	}

	/****************************************************************************************************************/
	/****************************************************************************************************************/
	// helper functions that scale from user coordinates to screen coordinates and
	// back
	private double scaleX(double x) {
		return width * (x - xmin) / (xmax - xmin);
	}

	/****************************************************************************************************************/
	/****************************************************************************************************************/
	private double scaleY(double y) {
		return height * (ymax - y) / (ymax - ymin);
	}

	/****************************************************************************************************************/
	/****************************************************************************************************************/
	/**
	 * Clears the screen to the given color.
	 *
	 * @param color the color to make the background
	 * @throws IllegalArgumentException if {@code color} is {@code null}
	 */
	public void clear(Color color) {
		offscreen.setColor(color);
		offscreen.fillRect(0, 0, width, height);
		offscreen.setColor(penColor);
		draw();
	}

	/****************************************************************************************************************/

	/****************************************************************************************************************/
	/**
	 * Sets the radius of the pen to the given size.
	 *
	 * @param radius the radius of the pen
	 * @throws IllegalArgumentException if {@code radius} is negative, NaN, or
	 *                                  infinite
	 */
	public void setPenRadius(double radius) {
		penRadius = radius * DEFAULT_SIZE;
		BasicStroke stroke = new BasicStroke((float) penRadius, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		offscreen.setStroke(stroke);
	}

	/****************************************************************************************************************/
	/****************************************************************************************************************/
	/**
	 * Sets the pen color to the given color.
	 *
	 * @param color the color to make the pen
	 * @throws IllegalArgumentException if {@code color} is {@code null}
	 */
	public void setPenColor(Color color) {
		penColor = color;
		offscreen.setColor(penColor);
	}

	/****************************************************************************************************************/
	/****************************************************************************************************************/
	/**
	 * Gets the current {@code JLabel} for use in some other GUI.
	 *
	 * @return the current {@code JLabel}
	 */
	public JLabel getJLabel() {
		return draw;
	}

	/****************************************************************************************************************/	
	/****************************************************************************************************************/
	/**
	 * Draws one pixel at (x, y).
	 *
	 * @param x the x-coordinate of the pixel
	 * @param y the y-coordinate of the pixel
	 * @throws IllegalArgumentException if {@code x} or {@code y} is either NaN or
	 *                                  infinite
	 */
	private void pixel(double x, double y) {
		offscreen.fillRect((int) Math.round(scaleX(x)), (int) Math.round(scaleY(y)), 1, 1);
	}

	/****************************************************************************************************************/
	/****************************************************************************************************************/
	/**
	 * Draws a point at (x, y).
	 *
	 * @param x the x-coordinate of the point
	 * @param y the y-coordinate of the point
	 * @throws IllegalArgumentException if either {@code x} or {@code y} is either
	 *                                  NaN or infinite
	 */
	public void point(double x, double y) {
		double xs = scaleX(x);
		double ys = scaleY(y);
		double r = penRadius;
		if (r <= 1)
			pixel(x, y);
		else
			offscreen.fill(new Ellipse2D.Double(xs - r / 2, ys - r / 2, r, r));
		draw();
	}

	/****************************************************************************************************************/	
	/****************************************************************************************************************/
	/**
	 * Copies offscreen buffer to onscreen buffer. There is no reason to call this
	 * method unless double buffering is enabled.
	 */
	public void show() {
		onscreen.drawImage(offscreenImage, 0, 0, null);
	}

	/****************************************************************************************************************/
	/****************************************************************************************************************/
	// draw onscreen if defer is false
	private void draw() {
		if (!defer)
			show();
	}

	/****************************************************************************************************************/
	/****************************************************************************************************************/
	/**
	 * Enable double buffering. All subsequent calls to drawing methods such as
	 * {@code line()}, {@code circle()}, and {@code square()} will be deferred until
	 * the next call to show(). Useful for animations.
	 */
	public void enableDoubleBuffering() {
		defer = true;
	}

	/****************************************************************************************************************/
	/****************************************************************************************************************/
	/***************************************************************************
	 * For improved resolution displays.
	 ***************************************************************************/

	@SuppressWarnings("serial")
	private static class RetinaImageIcon extends ImageIcon {

		public RetinaImageIcon(Image image) {
			super(image);
		}

		public int getIconWidth() {
			return super.getIconWidth() / 2;
		}

		/**
		 * Gets the height of the icon.
		 *
		 * @return the height in pixels of this icon
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

package controller;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class IconsResize {

	/**
	 * Method to resize an icon for the application window
	 *
	 * @param srcImg
	 * @param width
	 * @param height
	 * @return res
	 */
	public static ImageIcon getScaledImage(ImageIcon srcImg, int width, int height) {
		BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		// g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		// RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.drawImage(srcImg.getImage(), 0, 0, width, height, null);
		g2.dispose();

		ImageIcon res = new ImageIcon(resizedImg);
		return res;
	}
}

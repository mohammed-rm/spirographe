package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class DataComponents {

	private JSlider sliderFixedRadius;
	private JSlider sliderMovingRadius;
	private JSlider sliderPenLength;
	private JLabel fixedValue;
	private JLabel movingValue;
	private JLabel penValue;

	/**
	 * Sliders builder
	 */
	public DataComponents() {

		sliderFixedRadius = new JSlider(SwingConstants.VERTICAL, 0, 100, 60);
		sliderMovingRadius = new JSlider(SwingConstants.VERTICAL, 0, 100, 20);
		sliderPenLength = new JSlider(SwingConstants.VERTICAL, 0, 100, 40);

		fixedValue = new JLabel("Value : ");
		movingValue = new JLabel("Value : ");
		penValue = new JLabel("Value : ");
		fixedValue.setBounds(1113, 230, 77, 14);
		fixedValue.setBackground(Color.GREEN);
		movingValue.setBounds(1113, 480, 77, 14);
		penValue.setBounds(1113, 730, 77, 14);

	}

	/**
	 * Method to create fixed circle slider
	 * 
	 * @return sliderFixedRadius
	 */
	public JSlider fixedSl() {
		sliderFixedRadius.setPaintTicks(true);
		sliderFixedRadius.setPaintLabels(true);
		sliderFixedRadius.setMinorTickSpacing(5);
		sliderFixedRadius.setMajorTickSpacing(20);
		sliderFixedRadius.setForeground(Color.WHITE);
		sliderFixedRadius.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		sliderFixedRadius.setBackground(new Color(128, 0, 0));
		sliderFixedRadius.setBounds(1053, 60, 47, 184);

		return sliderFixedRadius;

	}

	/**
	 * Method to create moving circle slider
	 * 
	 * @return sliderMovingRadius
	 */
	public JSlider movingSl() {
		sliderMovingRadius.setPaintTicks(true);
		sliderMovingRadius.setPaintLabels(true);
		sliderMovingRadius.setMinorTickSpacing(5);
		sliderMovingRadius.setMajorTickSpacing(20);
		sliderMovingRadius.setForeground(Color.WHITE);
		sliderMovingRadius.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		sliderMovingRadius.setBackground(new Color(128, 0, 0));
		sliderMovingRadius.setBounds(1053, 310, 47, 184);

		return sliderMovingRadius;
	}

	/**
	 * Method to create pen slider
	 * 
	 * @return sliderPenLength
	 */
	public JSlider penSl() {
		sliderPenLength.setPaintTicks(true);
		sliderPenLength.setPaintLabels(true);
		sliderPenLength.setMinorTickSpacing(5);
		sliderPenLength.setMajorTickSpacing(20);
		sliderPenLength.setForeground(Color.WHITE);
		sliderPenLength.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		sliderPenLength.setBackground(new Color(128, 0, 0));
		sliderPenLength.setBounds(1053, 560, 47, 184);

		return sliderPenLength;
	}

	/**
	 * @return the sliderFixedRadius
	 */
	public JSlider getSliderFixedRadius() {
		return sliderFixedRadius;
	}

	/**
	 * @return the sliderMovingRadius
	 */
	public JSlider getSliderMovingRadius() {
		return sliderMovingRadius;
	}

	/**
	 * @return the sliderPenLength
	 */
	public JSlider getSliderPenLength() {
		return sliderPenLength;
	}

	/**
	 * @return the fixedValue
	 */
	public JLabel getFixedValue() {
		return fixedValue;
	}

	/**
	 * @return the movingValue
	 */
	public JLabel getMovingValue() {
		return movingValue;
	}

	/**
	 * @return the penValue
	 */
	public JLabel getPenValue() {
		return penValue;
	}

	/**
	 * @param fixedValue the fixedValue to set
	 */
	public void setFixedValue(JLabel fixedValue) {
		this.fixedValue = fixedValue;
	}

	/**
	 * @param movingValue the movingValue to set
	 */
	public void setMovingValue(JLabel movingValue) {
		this.movingValue = movingValue;
	}

	/**
	 * @param penValue the penValue to set
	 */
	public void setPenValue(JLabel penValue) {
		this.penValue = penValue;
	}
}

package model;

public class Circles {

	private double fixedCirleRadius;
	private double movingCircleRadius;
	private double penLength;

	/**
	 * @param fixedCirleRadius
	 * @param movingCircleRadius
	 * @param penLength
	 */
	public Circles(double fixedCirleRadius, double movingCircleRadius, double penLength) {
		this.fixedCirleRadius = fixedCirleRadius;
		this.movingCircleRadius = movingCircleRadius;
		this.penLength = penLength;
	}

	/**
	 * @return the fixedCirleRadius
	 */
	public double getFixedCirleRadius() {
		return fixedCirleRadius;
	}

	/**
	 * @return the movingCircleRadius
	 */
	public double getMovingCircleRadius() {
		return movingCircleRadius;
	}

	/**
	 * @return the penLength
	 */
	public double getPenLength() {
		return penLength;
	}

	/**
	 * @param fixedCirleRadius the fixedCirleRadius to set
	 */
	public void setFixedCirleRadius(double fixedCirleRadius) {
		this.fixedCirleRadius = fixedCirleRadius;
	}

	/**
	 * @param movingCircleRadius the movingCircleRadius to set
	 */
	public void setMovingCircleRadius(double movingCircleRadius) {
		this.movingCircleRadius = movingCircleRadius;
	}

	/**
	 * @param penLength the penLength to set
	 */
	public void setPenLength(double penLength) {
		this.penLength = penLength;
	}

}

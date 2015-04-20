package interfaces;

import java.awt.Color;

import animatables.Position;


public abstract class Ball implements Animatable {
	/**
	 * The color of the Ball
	 */
	protected Color ballColor;
	
	/**
	 * The 2-D position of the Ball
	 */
	protected Position ballCenter;
	
	/**
	 * The radius of the Ball
	 */
	protected int ballRadius;
	
	public Ball(){
		this(Color.BLACK, new Position(0, 0), 10);
	}
	
	/**
	 * Constructs a Ball object, giving it a Color, a Position, and a radius
	 * 
	 * @param initialColor
	 * The Ball's initial Color
	 * @param initialPosition
	 * The Ball's initial Position in 2-D space
	 * @param initialRadius
	 * The Ball's initial Radius
	 */
	public Ball(Color initialColor, Position initialPosition, int initialRadius){
		ballColor = initialColor;
		ballCenter = initialPosition;
		ballRadius = initialRadius;
	}

	/**
	 * Gets the Ball's Color property
	 * @return
	 * The Color of the Ball
	 */
	public Color getColor(){
		return ballColor;
	}
	
	/**
	 * Gets the center of the Ball
	 * @return
	 * A Position object representing the Ball's center
	 */
	public Position getCenter(){
		return ballCenter;
	}
	
	/**
	 * Gets the radius of the Ball
	 * @return
	 * The radius of the Ball
	 */
	public double getRadius(){
		return ballRadius;
	}
	
	/**
	 * Sets the Color of the ball to the Color specified
	 * @param newColor
	 * The new Color of the Ball
	 */
	public Ball setColor(Color newColor){
		ballColor = newColor;
		return this;
	}
	
	/**
	 * Sets the center of the ball to the Position specified
	 * @param newCenter
	 * The new center of the Ball
	 */
	public Ball setCenter(Position newCenter){
		ballCenter = newCenter;
		return this;
	}
	
	/**
	 * Sets the radius of the ball to the value specified
	 * @param newRadius
	 * The new radius of the ball
	 */
	public Ball setRadius(int newRadius){
		ballRadius = newRadius;
		return this;
	}
	
	public String toString(){
		String tempString = ballColor.getRed() + " " + ballColor.getGreen() + " " + ballColor.getBlue();
		tempString += " " + ballCenter.toString() + " " + ballRadius;
		
		return tempString;
	}
	
	public abstract Ball clone();
}

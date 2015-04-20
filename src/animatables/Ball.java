package animatables;

import interfaces.Animatable;

import java.awt.Color;
import java.awt.Graphics;


import utilities.GeneralUtilities;

/**
 * A class representing a Ball in 2-D space, to be used with the Animator class
 * @author Adam
 */
public class Ball extends interfaces.Ball implements Animatable {
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
	
	public Ball(Color color, int x, int y, int radius){
		this(color, new Position(x, y), radius);
	}

	public void paint(Graphics g){
		g.setColor(Color.PINK);
		g.fillOval((int)(ballCenter.getX() - ballRadius),
					(int)(ballCenter.getY() - ballRadius), 
					(int)ballRadius*2, (int)ballRadius*2);
	}

	@Override
	public Animatable interpolate(TimeFrame timeA, TimeFrame timeZ, double timeM) {
		Ball ballA = (Ball) timeA.getItem();
		Ball ballZ = (Ball) timeZ.getItem();
		
		Color color = GeneralUtilities.interpolate(timeA.getTime(), ballA.getColor(),
									timeZ.getTime(), ballZ.getColor(), timeM);
		double radius = GeneralUtilities.interpolate(timeA.getTime(), ballA.getRadius(),
									timeZ.getTime(), ballZ.getRadius(), timeM);
		Position position = GeneralUtilities.interpolate(timeA.getTime(), ballA.getCenter(),
									timeZ.getTime(), ballZ.getCenter(), timeM);

		return this.setColor(color).setCenter(position).setRadius((int) radius);
	}
	
	public Ball clone(){
		return new Ball(ballColor, ballCenter, ballRadius);
	}
}
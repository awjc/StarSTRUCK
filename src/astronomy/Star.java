package astronomy;

import interfaces.Animatable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import animatables.Position;
import animatables.TimeFrame;

import utilities.AnimationUtilities;
import utilities.GeneralUtilities;

public class Star implements Animatable{
	private Color color;
	private int centerRadius;
	private int nPoints;
	private int pointLength;
	
	private Position center;
	
	public Star(){
		this(Color.WHITE, new Position(0,0), 5, 4, 5);
	}
	
	public Star(Color color, Position center, int centerRadius, int nPoints, int pointLength){
		this.color = color;
		this.center = center;
		this.centerRadius = centerRadius;
		this.nPoints = nPoints;
		this.pointLength = pointLength;
	}
	
	public Star setColor(Color color){
		this.color = color;
		return this;
	}
	
	public Star setRadius(int radius){
		this.centerRadius = radius;
		return this;
	}
	
	
	public Star setCenter(Position center){
		this.center = center;
		return this;
	}
	
	public Star setNPoints(int nPoints){
		this.nPoints = nPoints;
		return this;
	}
	
	public Star setPointLength(int pointLength){
		this.pointLength = pointLength;
		return this;
	}
	
	@Override
	public Star interpolate(TimeFrame timeA, TimeFrame timeZ, double timeM) {
		Star starA = (Star) timeA.getItem();
		Star starZ = (Star) timeZ.getItem();
		
		Color color = GeneralUtilities.interpolate(timeA, starA.color, timeZ, starZ.color, timeM);
		Position center = GeneralUtilities.interpolate(timeA, starA.center, timeZ, starZ.center, timeM);
		double centerRadius = GeneralUtilities.interpolate(timeA, starA.centerRadius, timeZ, starZ.centerRadius, timeM);
		double pointLength = GeneralUtilities.interpolate(timeA, starA.pointLength, timeZ, starZ.pointLength, timeM);
		
		return this.setColor(color).setCenter(center).setRadius((int) centerRadius)
						.setPointLength((int) pointLength);
	}

	private static int[] xpoints = new int[3];
	private static int[] ypoints = new int[3];

	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(center.getX() - centerRadius, center.getY() - centerRadius, 
						centerRadius*2, centerRadius*2);
		
		xpoints[0] = (int) (center.getX() + (pointLength / 2.0) / 2);
		xpoints[1] = center.getX();
		xpoints[2] = (int) (center.getX() - (pointLength / 2.0) / 2);
		
		ypoints[0] = ypoints[2] = center.getY() - centerRadius / 2;
		ypoints[1] = center.getY() - centerRadius - pointLength;
		
		Polygon starPoint = new Polygon(xpoints, ypoints, 3);
		
		Graphics2D g2d = (Graphics2D) g.create();
		AnimationUtilities.setAntiAlias(g2d, true);
		AffineTransform at = new AffineTransform();
		g2d.setTransform(at);
		
		for(int i=0; i < 360; i += 360 / nPoints){
			g2d.rotate(Math.toRadians(360 / nPoints), center.getX(), center.getY());
			g2d.fill(at.createTransformedShape(starPoint));
		}
	}
	
	public Star clone(){
		return new Star(color, center, centerRadius, nPoints, pointLength);
	}
}

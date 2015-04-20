package astronomy;

import images.Images;
import interfaces.Animatable;

import java.awt.Graphics;
import java.awt.Image;

import animatables.Position;
import animatables.TimeFrame;

import utilities.GeneralUtilities;

public class Planet implements Animatable {
	public static final int MERCURY = 0;
	public static final int VENUS = 1;
	public static final int EARTH = 2;
	public static final int MARS = 3;
	public static final int JUPTIER = 4;
	public static final int SATURN = 5;
	public static final int URANUS = 6;
	public static final int NEPTUNE = 7;
	public static final int PLUTO = 8;
	
	private Image planetImg;
	public Position center;
	private int radius;
	private int planetNumber;
	
	public Planet(int planetNumber, Position center, int radius){
		this(Images.getImage("planet" + planetNumber), center, radius);
		this.planetNumber = planetNumber;
	}

	public Planet(Image planetImg, Position center, int radius){
		this.center = center;
		this.radius = radius;
		this.planetImg = planetImg;
	}
	
	public Planet setRadius(int newRadius){
		this.radius = newRadius;
		return this;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public Planet setCenter(Position newCenter){
		this.center = newCenter;
		return this;
	}
	
	public Position getCenter(){
		return center;
	}
	
	public Planet setPlanetNumber(int number){
		this.planetNumber = number;
		return this;
	}
	
	public int getPlanetNumber(){
		return planetNumber;
	}
	
	@Override
	public Animatable interpolate(TimeFrame timeA, TimeFrame timeZ,	double timeM) {
		Planet planetA = (Planet) timeA.getItem();
		Planet planetZ = (Planet) timeZ.getItem();
		
		double radius = GeneralUtilities.interpolate(timeA, planetA.getRadius(),
											timeZ, planetZ.getRadius(), timeM);
		Position position = GeneralUtilities.interpolate(timeA, planetA.getCenter(),
											timeZ, planetZ.getCenter(), timeM);
		
		return this.setCenter(position).setRadius((int) radius);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(planetImg, center.getX() - radius, center.getY() - radius,
					radius*2, radius*2, null);
	}
	
	public Planet clone(){
		return new Planet(planetImg, center, radius).setPlanetNumber(planetNumber);
	}
}
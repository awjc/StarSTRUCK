package astronomy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import utilities.AnimationUtilities;
import utilities.GeneralUtilities;
import animatables.Position;
import animatables.TimeFrame;


public class Sun extends interfaces.Ball {
	private int beamHeight = 5;
	private int nBeams = 12;
	private boolean isAntiAlias = false;
	private int beamSpace = 3;
	
	public Sun(Color initialColor, Position initialPosition, int initialRadius){
		super(initialColor, initialPosition, initialRadius);
	}
	
	public Sun(Color color, int x, int y, int initialRadius){
		super(color, new Position(x, y), initialRadius);
	}
	
	public Sun(Position initialPosition, int initialRadius) {
		super(Color.YELLOW, initialPosition, initialRadius);
	}
	
	public Sun setBeamHeight(int beamHeight){
		this.beamHeight = beamHeight;
		return this;
	}
	
	public Sun setNBeams(int nBeams){
		this.nBeams = nBeams;
		return this;
	}
	
	public Sun setAntiAlias(boolean isAntiAlias){
		this.isAntiAlias = isAntiAlias;
		return this;
	}
	
	public Sun setBeamSpace(int beamSpace){
		this.beamSpace = beamSpace;
		return this;
	}
	
	public int getBeamHeight(){
		return this.beamHeight;
	}
	
	public int getNBeams(){
		return this.nBeams;
	}
	
	public boolean isAntiAliased(){
		return this.isAntiAlias;
	}
	
	public int getBeamSpace(){
		return this.beamSpace;
	}

	private static final int[] xpoints = new int[4];
	private static final int[] ypoints = new int[4];
	
	@Override
	public void paint(Graphics g){
		g.setColor(ballColor);
		g.fillOval((int)(ballCenter.getX() - ballRadius),
					(int)(ballCenter.getY() - ballRadius), 
					(int)(ballRadius * 2), (int)(ballRadius * 2));
		
		int beamWidth = 2;

		if(nBeams <= 0){
			return;
		}
		
		xpoints[0] = xpoints[1] = ballCenter.getX() + beamWidth / 2;
		xpoints[2] = xpoints[3] = ballCenter.getX() - beamWidth / 2;
		
		ypoints[0] = ypoints[3] = ballCenter.getY() - ballRadius - beamSpace;
		ypoints[1] = ypoints[2] = ballCenter.getY() - ballRadius - beamHeight - beamSpace;
		
		Polygon sunBeam = new Polygon(xpoints, ypoints, 4);

		Graphics2D g2d = (Graphics2D) g.create();
		AnimationUtilities.setAntiAlias(g2d, isAntiAlias);
		AffineTransform at = new AffineTransform();
		g2d.setTransform(at);
		
		double degStep = 360.0 / nBeams;
		for(double i=0; i < 360; i+=degStep){
			g2d.fill(at.createTransformedShape(sunBeam));
			g2d.rotate(Math.toRadians(degStep), ballCenter.getX(), ballCenter.getY());
		}
	}
	
	@Override
	public Sun interpolate(TimeFrame timeA, TimeFrame timeZ, double timeM) {
		Sun ballA = (Sun) timeA.getItem();
		Sun ballZ = (Sun) timeZ.getItem();
		
		Color color = GeneralUtilities.interpolate(timeA, ballA.getColor(),
									timeZ, ballZ.getColor(), timeM);
		double radius = GeneralUtilities.interpolate(timeA, ballA.getRadius(),
									timeZ, ballZ.getRadius(), timeM);
		Position position = GeneralUtilities.interpolate(timeA, ballA.getCenter(),
									timeZ, ballZ.getCenter(), timeM);
		double beamHeight = GeneralUtilities.interpolate(timeA, ballA.getBeamHeight(),
									timeZ, ballZ.getBeamHeight(), timeM);
		
		double nBeams = GeneralUtilities.interpolate(timeA, ballA.getNBeams(),
									timeZ, ballZ.getNBeams(), timeM);
		
		double beamSpace = GeneralUtilities.interpolate(timeA, ballA.getBeamSpace(),
									timeZ, ballZ.getBeamSpace(), timeM);
		
		this.setColor(color);
		this.setCenter(position);
		this.setRadius((int)radius);
		return this.setBeamHeight((int) beamHeight).setNBeams((int) nBeams)
					.setAntiAlias(ballA.isAntiAliased()).setBeamSpace((int) beamSpace);
	}
	
	@Override
	public Sun clone(){
		return new Sun(ballColor, ballCenter, ballRadius).setBeamHeight(beamHeight).setNBeams(nBeams)
				.setAntiAlias(isAntiAlias).setBeamSpace(beamSpace);
	}
}

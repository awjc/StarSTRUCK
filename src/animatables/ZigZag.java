package animatables;

import interfaces.Animatable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;

import utilities.GeneralUtilities;

public class ZigZag implements Animatable {
	private int[] x;
	private int[] y;
	private Color c;
	private int t;
	private int nPoints;
	private BasicStroke stroke;
	
	public static class G extends ZigZag {
		public G(){
			this(2);
		}
		
		public G(int thickness){
			super(Color.GREEN, thickness);
		}
	}
	
	public ZigZag(){
		this(Color.WHITE, 2);
	}
	
	public ZigZag(int thickness){
		this(Color.WHITE, thickness);
	}
	
	public ZigZag(Color color){
		this(color, 2);
	}
	
	public ZigZag(Color color, int thickness){
		x = new int[5];
		y = new int[5];
		c = color;
		t = thickness;
		stroke = new BasicStroke(t);
		
		nPoints = 0;
	}
	
	public ZigZag addPoint(int xp, int yp){
		if(nPoints >= x.length){
			x = Arrays.copyOf(x, x.length * 2);
			y = Arrays.copyOf(y, y.length * 2);
		}
		
		x[nPoints] = xp;
		y[nPoints] = yp;
		nPoints++;
		return this;
	}
	

	public ZigZag addPointsCentered(int cx, int cy, int... ns) {
		if(ns.length % 2 != 0){
			return this;
		}
		
		for(int i=0; i < ns.length; i += 2){
			addPoint(ns[i] + cx, ns[i + 1] + cy);
		}
		
		return this;
	}
	
	public ZigZag addPoints(int... ns){
		if(ns.length % 2 != 0){
			return this;
		}
		
		for(int i=0; i < ns.length; i += 2){
			addPoint(ns[i], ns[i + 1]);
		}
		
		return this;
	}
	
	public int[] getXs(){
		return x;
	}
	
	public int[] getYs(){
		return y;
	}
	
	public int getNPoints(){
		return nPoints;
	}
	
	public ZigZag setXs(int[] xs){
		x = xs;
		return this;
	}
	
	public ZigZag setYs(int[] ys){
		y = ys;
		return this;
	}
	
	public ZigZag setNPoints(int np){
		this.nPoints = np;
		return this;
	}
	
	public ZigZag copyXs(int[] xs){
		if(xs.length != x.length){
			return this;
		}
		
		for(int i=0; i < xs.length; i++){
			x[i] = xs[i];
		}
		
		return this;
	}
	
	public ZigZag copyYs(int[] ys){
		if(ys.length != y.length){
			return this;
		}
		
		for(int i=0; i < ys.length; i++){
			y[i] = ys[i];
		}
		
		return this;
	}
	
	public Color getColor(){
		return c;
	}
	
	public ZigZag setColor(Color col){
		this.c = col;
		return this;
	}
	
	@Override
	public ZigZag interpolate(TimeFrame timeA, TimeFrame timeZ, double timeM) {
		ZigZag zigA = (ZigZag) timeA.getItem();
		ZigZag zigZ = (ZigZag) timeZ.getItem();
		
		int[] xA = zigA.getXs();
		int[] yA = zigA.getYs();
		
		int[] xZ = zigZ.getXs();
		int[] yZ = zigZ.getYs();
		
		if(xA.length != x.length){
			x = new int[xA.length];
		} if(yA.length != y.length){
			y = new int[yA.length];
		}
		
		for(int i=0; i < xA.length; i++){
			x[i] = (int) GeneralUtilities.interpolate(timeA, xA[i], timeZ, xZ[i], timeM);
			y[i] = (int) GeneralUtilities.interpolate(timeA, yA[i], timeZ, yZ[i], timeM);
		}
		
		Color color = GeneralUtilities.interpolate(timeA, zigA.getColor(), timeZ, zigZ.getColor(), timeM);
		
		return this.setColor(color);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setStroke(stroke);
		g2d.setColor(c);
		for(int i=0; i < nPoints - 1; i++){
			g.drawLine(x[i], y[i], x[i+1], y[i+1]);
		}
//		for(int i=0; i < x.length - 1; i++){
//			Polygon p = new Polygon(x, y, nPoints);
//			g.fillPolygon(p);
//		}
	}
	
	@Override
	public Animatable clone(){
		return new ZigZag(c, t).copyXs(x).copyYs(y).setNPoints(nPoints);
	}
}

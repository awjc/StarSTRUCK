package interfaces;

import java.awt.Graphics;

import animatables.TimeFrame;


public interface Animatable {
	public Animatable interpolate(TimeFrame timeA, TimeFrame timeZ, double timeM);
	public void paint(Graphics g);
	public Animatable clone();
}

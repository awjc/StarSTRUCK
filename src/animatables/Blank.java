package animatables;

import java.awt.Graphics;

import interfaces.Animatable;

public class Blank implements Animatable {
	@Override
	public Animatable interpolate(TimeFrame timeA, TimeFrame timeZ, double timeM) {
		return this;
	}

	@Override
	public void paint(Graphics g) {
		return;
	}
	
	@Override
	public Animatable clone(){
		return new Blank();
	}
}

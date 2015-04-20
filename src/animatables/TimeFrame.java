package animatables;

import interfaces.Animatable;

public class TimeFrame {
	private Animatable item;
	private double time;
	
	public TimeFrame(Animatable item, double time){
		this.item = item;
		this.time = time;
	}
		public Animatable getItem(){
		return this.item;
	}
	
	public double getTime(){
		return this.time;
	}
}

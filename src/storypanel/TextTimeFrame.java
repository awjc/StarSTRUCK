package storypanel;

public class TextTimeFrame {
	private char c;
	private double time;
	
	public TextTimeFrame(char c, double time){
		this.c = c;
		this.time = time;
	}
	
	public char getChar(){
		return c;
	}
	
	public double getTime(){
		return time;
	}
}

package animation;

import interfaces.Animatable;

import java.util.ArrayList;

import animatables.TimeFrame;


public class SingleAnimator {
	private AnimationComponent comp;
	private ArrayList<TimeFrame> items;
	private int number;
	private boolean repeat = false;
	private boolean isRunning = false;
	private boolean removeAfterDone = false;
	
	private String name = "default";
	
	private Animatable interpolator;
	
	private static int currentNumber = 0;
	public void assignNumber(){
		this.number = currentNumber;
		currentNumber++;
	}
	
	public static void resetNumbers(){
		currentNumber = 0;
	}
	
	public SingleAnimator(AnimationComponent comp){
		this(comp, false);
	}
	
	public SingleAnimator(AnimationComponent comp, boolean repeat){
		items = new ArrayList<TimeFrame>();
		this.comp = (AnimationComponent) comp;
		this.repeat = repeat;
		
		this.isRunning = true;
	}
	
	public String getName(){
		return name;
	}
	
	public SingleAnimator setName(String name){
		this.name = name;
		return this;
	}
	
	public ArrayList<TimeFrame> getItems(){
		return items;
	}
	
	public Animatable getInterpolator(){
		return interpolator;
	}
	
	public SingleAnimator setRepeat(boolean repeat){
		this.repeat = repeat;
		return this;
	}
	
	public SingleAnimator addItem(Animatable item, double t){
		items.add(new TimeFrame(item, t));
		if(items.size() == 1){
			interpolator = items.get(0).getItem().clone();
		}
		return this;
	}
	
	public SingleAnimator setRemoveAfterDone(boolean removeAfterDone){
		this.removeAfterDone = removeAfterDone;
		return this;
	}
	
	public SingleAnimator resetStartTime(){
		startTime = System.currentTimeMillis();
		bias = 0;
		return this;
	}
	
	public void stop(){
		isRunning = false;
		if(removeAfterDone){
			comp.clear(number);
		}
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
	private int i=0;
	private long startTime;
	private long bias;
	private long pauseTime;
	private boolean isPaused = false;
	private boolean unpausedRecently = false;

	public void update(long nMillis){
		if(isPaused){
			return;
		}
		
		if(unpausedRecently){
			bias = nMillis - pauseTime;
			unpausedRecently = false;
		}
		
		long elapsed = nMillis - startTime - bias;
		
		if(elapsed / 1000.0  < items.get(0).getTime() || items.size() < 2){
			return;
		}
	
		if(elapsed / 1000.0 > items.get(i + 1).getTime()){
			do{
				i++;
			} while(i < items.size() - 1 && elapsed / 1000.0 > items.get(i + 1).getTime());
			
			if(i >= items.size() - 1){
				if(repeat){
					i=0;
					resetStartTime();
					return;
				}
				stop();
				if(removeAfterDone){
					comp.clear(number);
				}
				return;
			}
		}
		
		comp.update(number, interpolator.interpolate(items.get(i),
				items.get(i + 1), elapsed / 1000.0));
	}

	public void pause() {
		isPaused = true;
		pauseTime = System.currentTimeMillis();
	}
	
	public void resume(){
		if(isPaused) {
			isPaused = false;
			unpausedRecently = true;
		}
	}

	public void clear() {
		items.clear();
	}
}

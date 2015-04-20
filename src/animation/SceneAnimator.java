package animation;

import graphics.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SceneAnimator {
	private ArrayList<SingleAnimator> animators;
	private AnimationComponent comp;
	private CutsceneAnimator anim;
	private Graphics g;
	private Timer updateTimer;
	private boolean isDone = false;
	
	private int runTime = -1;
	
	private Image bgImg = null;
	private Color bgColor = Color.BLACK;
	
	private static final int FPS = 30;
	private static final int updateDelay = 1000 / FPS;
	
	public SceneAnimator(CutsceneAnimator anim, AnimationComponent comp){
		this.anim = anim;
		this.comp = comp;
		animators = new ArrayList<SingleAnimator>();
		updateTimer = new Timer();
	}
	
	public SceneAnimator addAnimator(SingleAnimator animator){
		animators.add(animator);
		return this;
	}
	
	public ArrayList<SingleAnimator> getAnimators(){
		return animators;
	}
	
	public void setRunTime(int runTime){
		this.runTime = runTime;
	}
	
	public void setBackground(Color bgColor){
		this.bgColor = bgColor;
	}
	
	public void setBackground(Image img){
		this.bgImg = img;
	}
	
	public Image getBGImg(){
		return bgImg;
	}
	
	public Color getBackground(){
		return bgColor;
	}
	
	public boolean isDone(){
		return isDone;
	}
	
	public SingleAnimator getAnimator(String name){
		for(int i=0; i < animators.size(); i++){
			if(animators.get(i).getName().equalsIgnoreCase(name)){
				return animators.get(i);
			}
		}
		
		return null;
	}
	
	public ArrayList<SingleAnimator> getAnimatorList(String tag){
		ArrayList<SingleAnimator> list = new ArrayList<SingleAnimator>();
		for(int i=0; i < animators.size(); i++){
			if(animators.get(i).getName().equalsIgnoreCase(tag)){
				list.add(animators.get(i));
			}
		}
		
		return list;
	}
	
	public void run(){
		if(runTime != -1){
			updateTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					stop();
				}
			}, runTime * 1000);
		} else{
			g = GUI.getContentPaneGraphics();
			
			for(int i=0; i < animators.size(); i++){
				animators.get(i).assignNumber();
				animators.get(i).resetStartTime();
			}
	
			updateTimer.schedule(new TimerTask() {
				private long currentTime;
				
				@Override
				public void run() {
					currentTime = System.currentTimeMillis();
					
					boolean isDone = true;
					for(int i=0; i < animators.size(); i++){
						if(animators.get(i).isRunning()){
							isDone = false;
							animators.get(i).update(currentTime);
						}
					}
					
					if(isDone){
						stop();
					}
					
					comp.paint(SceneAnimator.this, g);
				}
			}, 0, updateDelay);
		}
	}
	
	public void stop(){
		comp.erase(this, GUI.getContentPaneGraphics());
		
		for(int i=0; i < animators.size(); i++){
			animators.get(i).stop();
		}
		
		animators.clear();
		
		SingleAnimator.resetNumbers();
		comp.clearAll();
		updateTimer.cancel();
		
		isDone = true;
		anim.next();
	}
	
	public void runFor(int nSeconds){
		run();
		
		Timer stopTimer = new Timer();
		stopTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				SceneAnimator.this.stop();
				updateTimer.cancel();
			}
		}, nSeconds * 1000);
	}
	
	public void $$(){
		/*		new Thread(new Runnable() {
//		@Override
//		public void run() {
//			long start=System.currentTimeMillis();
//			while(true){
//				long t = System.currentTimeMillis();
//				if(t - start >= repaintDelay){
//					//comp.paintImmediately(0,0,comp.getWidth(), comp.getHeight());
//					start = System.currentTimeMillis();
////					comp.repaint();
//					Graphics g = GUI.getContentPaneGraphics();
////					if(g != null){
//						comp.paint(g);
////					}
//				}
//			}
//		}
		}).start();
*/
	}
}

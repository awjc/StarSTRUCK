package storypanel;

import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import utilities.Holder;

public class StoryAnimator {
	private ArrayList<Holder<Character, Double>> story;
	private StoryPanel panel;
	private JTextArea textArea;
	private Timer updateTimer;
	private Timer cursorTimer;
	private boolean cursorOn;
	private boolean isAppending = true;
	
	private static final String cursor = "\u2589";
	private static final int updateDelay = 50;
	private static final int cursorDelay = 300;
	private int startDelay = 0;
	
	public StoryAnimator(JTextArea textArea, StoryPanel panel){
		this.panel = panel;
		this.textArea = textArea;
		
		story = new ArrayList<Holder<Character,Double>>();
	}
	
	public void setStartDelay(int startDelay){
		this.startDelay = startDelay;
	}
	
	private double timeBase = 0;
	public StoryAnimator addText(String text, double delay, int charPerMin){
		double time = timeBase + delay;
		
		for(int i=0; i < text.length(); i++){
			story.add(new Holder<Character, Double>(text.charAt(i), time));
			if(text.charAt(i) != '\n'){
				time += 60.0 / charPerMin;
			}
		}
		
		timeBase = time;

		return this;
	}
		
	public void run(){
		run(startDelay);
	}
	
	public void run(int delay){
		panel.setSize(panel.getStoryWidth(), panel.getStoryHeight());
		
		Insets insets = panel.getInsets();
		panel.setTextAreaSize(new Dimension(panel.getWidth() - insets.left - insets.right - 5,
											panel.getHeight() - insets.top - insets.bottom));
		
		panel.repaint();
		
		updateTimer = new Timer();
		
		startTime = System.currentTimeMillis() + delay * 1000;
		updateTimer.schedule(new TimerTask() {
			private long currentTime;
			Runnable r = new Runnable() {
				@Override
				public void run() {
					currentTime = System.currentTimeMillis();
					update(currentTime);
				}
			};
			
			@Override
			public void run() {
				SwingUtilities.invokeLater(r);
			}
		}, delay * 1000, updateDelay);
		
		cursorTimer =  new Timer();
		cursorTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						if(isAppending){
							return;
						}
						if(!cursorOn){
							textArea.append(cursor);
							cursorOn = true;
						}
						else{
							textArea.setText(textArea.getText().replace(cursor, ""));
							cursorOn = false;
						}
					}
				});
			}
		}, delay * 1000, cursorDelay);
	}
	
	public void stop(){
		if(cursorOn){
			String s = textArea.getText();
			textArea.setText(s.replace(cursor, ""));
			cursorOn = false;
		}
		updateTimer.cancel();
		
//		Audio.playSound("hey_listen", 2);
	}
	
	private int i=0;
	private long startTime = 0;
	double delay = 0;
	
	private synchronized void update(long nSeconds){
		nSeconds -= startTime;
		if(nSeconds / 1000.0 < story.get(0).getB()){
			isAppending = false;
		}
		
		if(nSeconds / 1000.0 > story.get(i).getB()){
			char c;
			do{
				if(i >= story.size() - 1){
					break;
				}
				c = story.get(i).getA();
				double delay2 = story.get(i+1).getB() - story.get(i).getB();

				if(Math.abs(delay2 - delay) > 0.1 && delay2 != 0 && delay != 0){
					isAppending = false;
				}
				else{
					isAppending = true;
				}
				delay = delay2;

				textArea.setText(textArea.getText().replace(cursor, ""));
				textArea.append(c + cursor);
				cursorOn = true;
				i++;
			} while(c == '\n');

			i--;
			panel.repaint();
			do{
				i++;
			} while(i < story.size() - 1 && nSeconds / 1000.0 > story.get(i + 1).getB());
			
			if(i >= story.size() - 1){
				stop();
				return;
			}
		}
	}
}

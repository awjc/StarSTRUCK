package storypanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class StoryPanel extends JPanel {
	private StoryAnimator anim;
	private JTextArea textArea;
	
	private int storyWidth;
	private int storyHeight;
	
	private Runnable r;
	
	public StoryPanel(Color fontColor, Font font){
		super();
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(null);
		textArea.setForeground(fontColor);
		textArea.setFont(font);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		anim = new StoryAnimator(textArea, this);
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		layout.putConstraint("West", textArea, 5, "West", this);
		layout.putConstraint("North", textArea, 5, "North", this);
		
		add(textArea);
	}
	
	public void setTextAreaSize(Dimension size){
		textArea.setSize(size);
	}
	
	public void run(){
		if(r != null){
			r.run();
		}
		anim.run();
	}
	
	public void run(int delay){
		anim.run(delay);
	}

	public StoryPanel addText(String text, double time, int charPerMin){
		anim.addText(text, time, charPerMin);
		return this;
	}
	
	public void setStartDelay(int startDelay) {
		anim.setStartDelay(startDelay);
	}
	
	public int getStoryWidth(){
		return storyWidth;
	}
	
	public int getStoryHeight(){
		return storyHeight;
	}
	
	public void setStoryWidth(int swidth){
		storyWidth = swidth;
	}
	
	public void setStoryHeight(int sheight){
		storyHeight = sheight;
	}
	
	public synchronized void paint(Graphics g){
		g.setColor(this.getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		super.paint(g);
	}

	public void setRunAction(Runnable runnable) {
		r = runnable;
	}
}

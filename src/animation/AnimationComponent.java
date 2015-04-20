package animation;

import interfaces.Animatable;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class AnimationComponent extends JComponent {
	private ArrayList<Animatable> anim;
	
	public AnimationComponent(){
		anim = new ArrayList<Animatable>();
	}
	
	public synchronized void update(int i, Animatable anim){
		if(this.anim.size() <= i){
			this.anim.add(anim);
		}
		else{
			this.anim.set(i, anim);
		}
	}
	
	public synchronized void clear(int i){
		anim.set(i, null);
		repaint();
	}
	
	public synchronized void clearAll(){
		anim.clear();
		repaint();
	}
	
	private Image offScrImg = null;
	private Graphics offG = null;
	
	public synchronized void paint(SceneAnimator sanim, Graphics g){
		int w = getWidth();
		int h = getHeight();
		
	    if (offScrImg == null || offScrImg.getWidth(null) != w
	    			|| offScrImg.getHeight(null) != h) {
	    	offScrImg = createImage(w, h);
	    }
	    
	    if(offScrImg == null){
	    	return;
	    }
	    
	    if(offG == null){
	    	offG = offScrImg.getGraphics();
	    }
	    
		if(sanim.getBGImg() == null){
			offG.setColor(sanim.getBackground());
			offG.fillRect(0, 0, getWidth(), getHeight());
		}
		else{
			offG.drawImage(sanim.getBGImg(), 0, 0, null);
		}
		
		if(anim != null){
			for(int i=0; i < anim.size(); i++){
				if(anim.get(i) != null){
					anim.get(i).paint(offG);
				}
			}
		}
		
		g.drawImage(offScrImg, getX(), getY(), null);
	}

	public void erase(SceneAnimator sanim, Graphics g) {
		int w = getWidth();
		int h = getHeight();
		
	    if (offScrImg == null || offScrImg.getWidth(null) != w
	    			|| offScrImg.getHeight(null) != h) {
	    	offScrImg = createImage(w, h);
	    }
	    
	    if(offScrImg == null){
	    	return;
	    }
	    
	    if(offG == null){
	    	offG = offScrImg.getGraphics();
	    }
	    
		if(sanim.getBGImg() == null){
			offG.setColor(sanim.getBackground());
			offG.fillRect(0, 0, getWidth(), getHeight());
		}
		else{
			offG.drawImage(sanim.getBGImg(), 0, 0, null);
		}
		
		if(g != null){
			g.drawImage(offScrImg, getX(), getY(), null);
		}
	}
}

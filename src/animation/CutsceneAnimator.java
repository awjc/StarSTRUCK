package animation;

import java.util.ArrayList;

import storypanel.StoryPanel;
import utilities.Holder;

public class CutsceneAnimator {
	private ArrayList<SceneAnimator> animators;
	private ArrayList<Holder<StoryPanel, Integer>> stories;
	private int currentScene = 0;
	
	public CutsceneAnimator(){
		animators = new ArrayList<SceneAnimator>();
		stories = new ArrayList<Holder<StoryPanel, Integer>>();
	}
	
	public CutsceneAnimator addScene(SceneAnimator anim){
		animators.add(anim);
		return this;
	}
	
	public CutsceneAnimator addStory(StoryPanel anim, int animNumber){
		stories.add(new Holder<StoryPanel, Integer>(anim, animNumber));
		return this;
	}
	
	public void run(){
		next();
	}
	
	public void next(){
		if(currentScene >= animators.size()){
			return;
		}
		
		for(int i=0; i < stories.size(); i++){
			if(stories.get(i).getB() == currentScene){
				stories.get(i).getA().run();
			}
		}
		
		animators.get(currentScene++).run();
	}
}

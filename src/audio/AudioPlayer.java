package audio;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
	private Clip clip;
	
	public AudioPlayer(){
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void playSound(String sound, int nTimes){
		if(clip.isOpen()){
			clip.close();
		}
		
		URL url;
		if(!sound.contains(".")){
			url = Class.class.getResource("/sounds/" + sound + ".wav");
		} else{
			url = Class.class.getResource("/sounds/" + sound);
		}
		
		AudioInputStream audioIn;
		try {
			audioIn = AudioSystem.getAudioInputStream(url);
			clip.open(audioIn);
			clip.loop(nTimes - 1);
			audioIn.close();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} 
	}
	
	public void playSound(String sound){
		playSound(sound, 1);
	}
	
	public void loopSound(String sound){
		playSound(sound, Clip.LOOP_CONTINUOUSLY);
	}
}

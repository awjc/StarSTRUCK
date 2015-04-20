package audio;

import java.net.URL;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Audio {
	private static int MAX_VOLUME = 100;
	private static MP3 backgroundSound;
	
	public static void playBackgroundMP3(String sound, boolean repeat){
		if(repeat){
			backgroundSound = new MP3(sound, MP3.INFINITE);
		} else{
			backgroundSound = new MP3(sound, 1);
		}
		backgroundSound.play();
	}
	
	public static void playBackgroundMP3(String sound){
		playBackgroundMP3(sound, false);
	}
	
	public static void setBackgroundVolume(int newVolume){
		if(newVolume < 0 || newVolume > MAX_VOLUME){
			return;
		}
		
//		FloatControl gain = backgroundSound.getFloatControl();
//		float newGain = gain.getMinimum() + newVolume * (gain.getMaximum() - gain.getMinimum()) / MAX_VOLUME;
//		gain.setValue(newGain);
	}
	
	public static void playMidi(String sound){
		URL url = Class.class.getResource("/sounds/" + sound + ".mid");
		if(url == null){
			return;
		}
		
		try {
			// From URL
			Sequence sequence = MidiSystem.getSequence(url);
			
			// Create a sequencer for the sequence
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.setSequence(sequence);
			
			// Start playing
			sequencer.start();
		} catch (MidiUnavailableException e) {
			System.out.println("Oh dear! Your system appears to be unable to play MIDI files.");
		} catch (Exception e){}
	}
	
	public static void playSound(String sound, int nTimes){
		URL url = null;
		if(!sound.contains(".")){
			url = Class.class.getResource("/sounds/" + sound + ".wav");
		} else{
			url = Class.class.getResource("/sounds/" + sound);
		}	
		
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		
		AudioInputStream audioIn = null;
		try {
			if(clip != null && url != null){
				audioIn = AudioSystem.getAudioInputStream(url);
				if(clip.isOpen()){
					clip.stop();
					clip.close();
				}
				clip.open(audioIn);
				clip.loop(nTimes - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void playSound(String sound){
		playSound(sound, 1);
	}
	
	public static void loopSound(String sound){
		playSound(sound, Clip.LOOP_CONTINUOUSLY + 1);
	}
}

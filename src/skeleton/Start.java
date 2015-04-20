package skeleton;

import graphics.GUI;
import images.Images;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

import cutscenes.Cutscene1;

@SuppressWarnings("serial")
public class Start extends JApplet {
	public static void main(String[] args){
		new Start().start();
	}
	
	public void start(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				startForReal();
			}
		});
	}
	
	private void startForReal(){
		GUI.initialize();
		
		Images.loadImages();
		
//		Audio.setBackgroundVolume(80);
//		Audio.playBackgroundMP3("sonic_city_escape", true);
//		Audio.playBackgroundMP3("horse_race_goal", true);
//		Audio.playMidi("RussianDance");
		
//		Audio.playSound("test", 10);
//		Audio.playSound("hey_listen", 5);
//		Audio.playSound("horse_race_goal", 5);
		
		Cutscene1.setup();
		Cutscene1.run();
	}
}

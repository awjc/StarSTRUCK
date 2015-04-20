package audio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URL;

import javazoom.jl.player.advanced.AdvancedPlayer;

public class MP3 {
	public static final int INFINITE = -1;
	
    private String filename;
    private AdvancedPlayer player; 
    
    private int nTimes = 0;
    private int time = 0;
    private boolean infinite = false;

    public MP3(String filename){
    	this(filename, 1);
    }
    
	// constructor that takes the name of an MP3 file
	public MP3(String filename, int nTimes) {
	    this.filename = filename;
	    this.nTimes = nTimes;
	    
	    if(nTimes == INFINITE){
	    	infinite = true;
	    }
	}

	public void close(){ 
		if (player != null){
			player.close(); 
		}
	}

	// play the MP3 file to the sound card
	public void play() {
		// run in new thread to play in background
		final Thread t = new Thread() {
			public void run() {
				try {
					while(infinite || time++ < nTimes){
						URL url = Class.class.getResource("/sounds/" + filename + ".mp3");
						URI uri = url.toURI();
				    	if(url != null){
					        FileInputStream fis     = new FileInputStream(new File(uri));
					        BufferedInputStream bis = new BufferedInputStream(fis);
					        player = new AdvancedPlayer(bis);
				    	}
				    	if(player != null){
				    		player.play();
				    	}
					}
				} catch (Exception e) {
					e.printStackTrace();
				    System.out.println("Problem playing file " + filename);
				}
			}
		};
	    t.start();
	}
}
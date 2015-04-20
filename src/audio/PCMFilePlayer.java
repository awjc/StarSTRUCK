package audio;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PCMFilePlayer implements Runnable { 
	File file;
	URL url;
	AudioInputStream in;
	SourceDataLine line;
	int frameSize;
	byte[] buffer = new byte [32 * 1024]; // 32k is arbitrary
	Thread playThread;
	boolean playing;
	boolean notYetEOF;
	
	boolean repeat;

	
	public PCMFilePlayer(File f) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
		file = f;
		in = AudioSystem.getAudioInputStream (f);
		
		begin();
	}
	
	public PCMFilePlayer(URL url) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		this.url = url;
		in = AudioSystem.getAudioInputStream(url);
		
		begin();
	}
	
	public PCMFilePlayer setRepeat(boolean repeat){
		this.repeat = repeat;
		return this;
	}
	
	private void begin() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		AudioFormat format = in.getFormat();
		AudioFormat.Encoding formatEncoding = format.getEncoding();
		if (! (formatEncoding.equals (AudioFormat.Encoding.PCM_SIGNED) ||
			   formatEncoding.equals (AudioFormat.Encoding.PCM_UNSIGNED))) 
		   throw new UnsupportedAudioFileException (
                              file == null ? url.getFile() : file.getName() + " is not PCM audio");
	   frameSize = format.getFrameSize(); 
	   DataLine.Info info = new DataLine.Info (SourceDataLine.class, format); 
	   line = (SourceDataLine) AudioSystem.getLine (info); 
	   line.open(); 
	   playThread = new Thread (this); 
	   playing = false; 
	   notYetEOF = true;        
	   playThread.start();
	}
	
	public void run() {
		int readPoint = 0;
		int bytesRead = 0;

		try {
			while (notYetEOF) {
				if (playing) {
					bytesRead = in.read (buffer, 
							 readPoint, 
							 buffer.length - readPoint);
					
					if (bytesRead == -1) { 
						if(repeat){
							readPoint = 0;
							in.close();
							
							if(url == null){
								in = AudioSystem.getAudioInputStream(file);
							}
							else{
								in = AudioSystem.getAudioInputStream(url);
							}
						}
						
						else{
							notYetEOF = false; 
							break;
						}
					}
					
					else{
						int leftover = bytesRead % frameSize;
						
		                // send to line
						line.write (buffer, readPoint, bytesRead - leftover);
						
						// save the leftover bytes
						System.arraycopy (buffer, bytesRead,
								  buffer, 0, 
								  leftover); 
		                
						readPoint = leftover;
					}
				} else { 
					try { 
						Thread.sleep (10);
					} catch (InterruptedException ie) {}
				}
			} // while notYetEOF
			
			line.drain();
			line.stop();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// line.close();
		}
	} // run

	public void start() {
		playing = true;
		if (! playThread.isAlive())
			playThread.start();
		line.start();
	}

	public void stop() {
		playing = false;
		line.stop();
	}
   
	public SourceDataLine getLine() {
		return line;
	}

	public File getFile() {		
		return file; 
	} 
	
	public FloatControl getFloatControl(){
		return (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
	}
}
package graphics;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class Fonts {
	public static String[] systemFonts;
	public static Font monospaced;
	
	public static void initialize(){
		systemFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	
		monospaced = new Font(Font.MONOSPACED, Font.PLAIN, 16);
	}
	
	public static boolean isSupported(String fontName){
		if(systemFonts != null){
			for(int i=0; i < systemFonts.length; i++){
				if(systemFonts[i] != null && systemFonts[i].equals(fontName)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static Font getFont(String name){
		if(isSupported(name)){
			return new Font(name, Font.PLAIN, 16);
		}
		
		return null;
	}
	
	public static Font getMonospaced(){
		return monospaced;
	}
	
	public static Font getMonospaced(int size){
		return monospaced.deriveFont(size);
	}
}

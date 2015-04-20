package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class GUI {
	private static JFrame frame;
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 700;
	
	public static void initialize(){
		//****** Change the "look and feel" of the program to the default Swing Java. It will look the same across all platforms. ******//
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e){
			e.printStackTrace();
		}
		
		setupFrame();
		
		Fonts.initialize();
	}
	
	private static void setupFrame(){
		frame = new JFrame("StarSTRUCK!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setPreferredSize(frame.getSize());
		frame.setMaximumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		frame.setMinimumSize(frame.getMaximumSize());
		frame.setResizable(false);
	
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());
		frame.setLocation((screenDim.width - insets.left - insets.right - frame.getWidth()) / 2,
						(screenDim.height - insets.top - insets.bottom - frame.getHeight()) / 2);
	
		frame.setVisible(true);
	}
	
	public static int getWindowWidth(){
		return frame.getContentPane().getWidth();
		//return WINDOW_WIDTH;
	}
	
	public static int getWindowHeight(){
		return frame.getContentPane().getHeight();
	//	return WINDOW_HEIGHT;
	}
	
	public static Dimension getWindowSize(){
		return new Dimension(getWindowWidth(), getWindowHeight());
	}
	
	public static Insets getFrameInsets(){
		return frame.getInsets();
	}
	
	public static JFrame getFrame(){
		return frame;
	}
	
	public static Graphics getContentPaneGraphics(){
		return frame.getContentPane().getGraphics();
	}
}

package utilities;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class AnimationUtilities {
	public static void setAntiAlias(Graphics2D g2d, boolean isAntiAliased){
		RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
					isAntiAliased ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
		renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		g2d.setRenderingHints(renderHints);
	}
}

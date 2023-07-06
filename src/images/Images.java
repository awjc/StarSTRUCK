package images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Images {
	private static BufferedImage[] planets;

	public static void loadImages(){
		planets = new BufferedImage[9];

		for(int i=0; i < 9; i++){
			try {
				planets[i] = ImageIO.read(new File("src/images/planets/planet" + i + ".gif"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static BufferedImage getImage(String imgName){
		Scanner scan = new Scanner(imgName);
		if(scan.findInLine("^planet([0-8])$") != null){
			int pNum = Integer.parseInt(scan.match().group(1));
			scan.close();
			return planets[pNum];
		}
		scan.close();
		return null;
	}
}

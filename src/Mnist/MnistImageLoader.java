package Mnist;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MnistImageLoader {
	
	
	public static BufferedImage loadImage(String path) throws IOException {
		return resize(ImageIO.read(new File(path)),28,28);
	}
	
	public static BufferedImage resize(BufferedImage img, int newWidth, int newHeight) {
		Image tmp = img.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = newImage.createGraphics();
		g2d.drawImage(tmp,  0,  0,  null);
		g2d.dispose();
		
		return newImage;
	}

}

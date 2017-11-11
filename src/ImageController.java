import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageController{

	private static int maxWidth = 600;
	private static int maxHeight = 600;
	
	public ImageController(){ //Constructor
		
	}
	
	public BufferedImage getImage(String path) throws IOException{
		BufferedImage img = ImageIO.read(new File(path));
		return resizeImage(img, path);
	}
	
	public BufferedImage resizeImage(BufferedImage resizeMe, String path) throws IOException {
		
		int type = resizeMe.getType() ==0? BufferedImage.TYPE_INT_ARGB : resizeMe.getType();
		
		int fHeight = maxHeight;
		int fWidth = maxWidth;
		if(resizeMe.getHeight() > maxHeight || resizeMe.getWidth() > maxWidth){
			fHeight = maxHeight;
			int wid = maxWidth;
			float sum = (float)resizeMe.getWidth() / (float)resizeMe.getHeight();
			fWidth = Math.round(fHeight* sum);
			if(fWidth > wid){
				fHeight = Math.round(wid/sum);
				fWidth = wid;
			}
		}
		BufferedImage resizedImage = new BufferedImage(fWidth, fHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(resizeMe, 0, 0, fWidth, fHeight, null);
		g.dispose();

		ImageIO.write(resizedImage, "png", new File("test.png"));
		return resizedImage;
	}
	
}

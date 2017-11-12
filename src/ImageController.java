/*
 * Class : Main
 * Description : Driver class to instantiate and instance of memegrame applicaton
 * Revision Date : 11/11/2017
 * Revision Number: 1
 * Authors : Team Foxtrot 
 */

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageController{

    private static int maxWidth = 600;
    private static int maxHeight = 400;

    public ImageController(){ //Constructor

    }
    
    
    public BufferedImage getImage(File f) throws IOException{

    	String path = f.getAbsolutePath();
    	String[] s = path.split(".", 2);  //split string on the period to find file type
        BufferedImage img = ImageIO.read(f);
        return resizeImage(img, path, s[1]);
 
    }
    
    //Resizes the image and saves the image to the same name
    public BufferedImage resizeImage(BufferedImage resizeMe, String path, String fileType) throws IOException {

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

        ImageIO.write(resizedImage, fileType, new File("path"));
       
        return resizedImage;
    }

}

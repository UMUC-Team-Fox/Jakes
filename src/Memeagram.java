import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;  

public class Memeagram{

    JFrame f;
    JPanel mainPanel,createPanel,browsePanel;
    JTabbedPane tp;
    BufferedImage image;
    JLabel pic;
    ImageController im;

    public Memeagram() throws IOException{  //Constructor
        f = new JFrame();
        mainPanel = new JPanel();
        createPanel = new JPanel();
        im = new ImageController();
        image = im.getImage("assets/terminator.jpg");
        pic = new JLabel(new ImageIcon(image));
        pic.setSize(600, 600);
        createPanel.add(pic);
        browsePanel = new JPanel();
        tp = new JTabbedPane();
        tp.setBounds(50, 50, 600, 600);
        tp.add(" Home ", mainPanel);
        tp.add(" Create ", createPanel);
        tp.add(" Browse ", browsePanel);
        f.add(tp);
        f.setSize(700,700);
        f.setLayout(null);
        f.setVisible(true);
    }

}

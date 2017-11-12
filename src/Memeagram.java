/*
 * Class : Main
 * Description : Driver class to instantiate and instance of memegrame applicaton
 * Revision Date : 11/11/2017
 * Revision Number: 1
 * Authors : Team Foxtrot 
 */

import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;  

public class Memeagram{

    JFrame f;
    JPanel mainPanel,createPanel,browsePanel;
    JButton btnFileChooser;
    JTabbedPane tp;
    BufferedImage image;
    JLabel pic;
    ImageController ic;
    JFileChooser jfc;

    public Memeagram() throws IOException{  //Constructor
        f = new JFrame();
        ic = new ImageController();
        jfc = new JFileChooser(FileSystemView.getFileSystemView());
        File wkDir = new File(System.getProperty("user.dir"));
        jfc.setCurrentDirectory(wkDir);
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //Panels and layouts
        mainPanel = new JPanel();
        createPanel = new JPanel();
        createPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        browsePanel = new JPanel();
        
        //Objects and configurations
        //image = ic.getImage("assets/javaone-rockstar.gif");

        
        
        //Interactive objects and configurations
        btnFileChooser = new JButton("Browse");
        
        //Adding objects to panels
        createPanel.add(btnFileChooser);
      
        //Listeners
        //Button listener for file chooser button
        btnFileChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnValue = jfc.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
						
					    File f = jfc.getSelectedFile();
					    try{
					    image = ic.getImage(f);
						pic = new JLabel(new ImageIcon(image));
				        pic.setSize(600, 400);
				        createPanel.add(pic);
						createPanel.validate();
						createPanel.repaint();
					    }catch(IOException ex) {}
				}
			}
        	
        });
        
        //Tabbed Pane setup
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

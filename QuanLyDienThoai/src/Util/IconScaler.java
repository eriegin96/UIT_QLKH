package Util;

import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Utility class for scaling icons to specified dimensions
 */
public class IconScaler {
    
    /**
     * Scales an icon to the specified width and height
     * @param path Path to the icon resource (e.g., "/UI/Icons/icon.png")
     * @param width Target width in pixels
     * @param height Target height in pixels
     * @return Scaled ImageIcon
     */
    public static ImageIcon scaleIcon(String path, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(IconScaler.class.getResource(path));
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            System.err.println("Error loading icon: " + path);
            e.printStackTrace();
            return new ImageIcon(); // Return empty icon on error
        }
    }
    
    /**
     * Scales an icon to 20x20 pixels (default size for dashboard icons)
     * @param path Path to the icon resource
     * @return Scaled ImageIcon at 20x20
     */
    public static ImageIcon scaleIcon20(String path) {
        return scaleIcon(path, 20, 20);
    }
    
    /**
     * Scales an icon to 16x16 pixels (default size for small icons)
     * @param path Path to the icon resource
     * @return Scaled ImageIcon at 16x16
     */
    public static ImageIcon scaleIcon16(String path) {
        return scaleIcon(path, 16, 16);
    }
}

package org.example;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import java.awt.*;

public class Stamp {
    private final String fileLocation = "src/main/resources/StampImage/AcademyStamp.jpeg";
    private final ImagePlus image = IJ.openImage(fileLocation);

    public void showImage() {
        image.show();
    }

    public void putSignature(String signature) {
        ImageProcessor ip = image.getProcessor();
        Font font = new Font("Arial", Font.PLAIN, 60);
        ip.setFont(font);
        ip.setColor(Color.BLACK);
        FontMetrics metrics = ip.getFontMetrics();
        int xCoordinate = (image.getWidth() - metrics.stringWidth(signature)) / 2;
        int yCoordinate = (image.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        ip.drawString(signature, xCoordinate, yCoordinate);
    }

    public ImagePlus getImage() {
        return image;
    }
}

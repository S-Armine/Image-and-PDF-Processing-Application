package org.example;

import ij.ImagePlus;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import ij.ImagePlus;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDF {
    private String inputFilePath;
    private String outputFilePath;
    private ImagePlus stamp;

    public PDF(String inputFilePath, String outputFilePath, ImagePlus stamp) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.stamp = stamp;
    }

    public PDF(String inputFilePath, String outputFilePath, Stamp stamp) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.stamp = stamp.getImage();
    }

    public void getStampedFile() {
        try {
            PDDocument document = Loader.loadPDF(new File(inputFilePath));
            int lastPageIndex = document.getNumberOfPages() - 1;
            PDPage page = document.getPage(lastPageIndex);
            BufferedImage bufferedImage = stamp.getBufferedImage();
            PDImageXObject image = LosslessFactory.createFromImage(document, bufferedImage);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
                float x = 20;
                float y = 20;
                float minimize = 0.5F;
                float width = image.getWidth() * minimize;
                float height = image.getHeight() * minimize;
                contentStream.drawImage(image, x, y, width, height);
            }
            document.removePage(document.getNumberOfPages() - 1);
            document.addPage(page);
            document.save(new File(outputFilePath));
            document.close();
            System.out.println("Stamp added to PDF successfully.");
        } catch (IOException e) {
            System.out.println("Stamp weren't added to PDF due to file system error.");
        }
    }
}

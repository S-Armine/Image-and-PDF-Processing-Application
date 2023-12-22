package org.example;

import ij.ImagePlus;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Stamp stamp = new Stamp();
        stamp.putSignature(inputSignature());
        PDF pdf = new PDF(inputInputFilePath(), inputOutputFilePath(), stamp.getImage());
        pdf.getStampedFile();
    }

    private static String inputSignature() {
        Scanner signatureScn = new Scanner(System.in);
        String signature;
        do {
            System.out.println("Please input your signature:");
            signature = signatureScn.nextLine();
        } while (signature.isEmpty() || signature.length() > 3);
        return signature;
    }

    private static String inputInputFilePath() {
        Scanner fileLocationScn = new Scanner(System.in);
        String inputFile;
        System.out.println("Input location of the file that should be stamped: ");
        inputFile = fileLocationScn.nextLine();
        return inputFile;
    }

    private static String inputOutputFilePath() {
        Scanner fileLocationScn = new Scanner(System.in);
        String outputFile;
        System.out.println("Input location where you want to store the stamped file: ");
        outputFile = fileLocationScn.nextLine();
        return outputFile;
    }
}
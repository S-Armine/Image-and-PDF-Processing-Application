package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Stamp stamp = new Stamp();
        stamp.putSignature(inputSignature());
        stamp.showImage();
    }

    public static String inputSignature() {
        Scanner signatureScn = new Scanner(System.in);
        String signature;
        do {
            System.out.println("Please enter your signature:");
            signature = signatureScn.nextLine();
        } while (signature.isEmpty() || signature.length() > 3);
        return signature;
    }
}
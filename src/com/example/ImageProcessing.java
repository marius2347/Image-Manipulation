package com.example;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import javax.imageio.ImageIO;
public class ImageProcessing {
    public static void main(String[] args) {
        // The provided image is car.jpg
        int[][] imageData = imgToTwoD("./src/com/example/car.jpg");
        viewImageData(imageData);


        // negative version of the image (negative pixels)
        String pathToNegativeCar = "./images/negative_car.jpg";
        int[][] negative = negativeColor(imageData, pathToNegativeCar);
        twoDToImage(negative, pathToNegativeCar);

        // stretch the image horizontally
        String pathToStretchH = "./images/stretchHorizontally_car.jpg";
        int[][] stretchH = stretchHorizontally(imageData, pathToStretchH);
        twoDToImage(stretchH, pathToStretchH);
    }
    // Image Processing Methods

    // Negative Color Method
    public static int[][] negativeColor(int[][] imageTwoD, String path) {
        int[][] negativeArray = new int[imageTwoD.length][imageTwoD[0].length];
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]); // rgba values
                // negatives values
                rgba[0] = 255 - rgba[0];
                rgba[1] = 255 - rgba[1];
                rgba[2] = 255 - rgba[2];
                negativeArray[i][j] = getColorIntValFromRGBA(rgba); // get the int hexadecimal pixel from rgba array
            }
        }
        System.out.println(path.substring(path.lastIndexOf("/") + 1) + " has been created!");
        return negativeArray;
    }
    public static int[][] stretchHorizontally(int[][] imageTwoD, String path) {
        int[][] array = new int[imageTwoD.length][imageTwoD[0].length * 2];
        int pos = 0; // keep track of position to modify
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                pos = j * 2; // double the column index
                array[i][pos] = imageTwoD[i][j]; // copy the current pixel
                array[i][pos + 1] = imageTwoD[i][j];
            }
        }
        System.out.println(path.substring(path.lastIndexOf("/") + 1) + " has been created!");
        return array;
    }
    public static int[][] shrinkVertically(int[][] imageTwoD) {
        // TODO: Fill in the code for this method
        return null;
    }
    public static int[][] invertImage(int[][] imageTwoD) {
        // TODO: Fill in the code for this method
        return null;
    }
    public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
        // TODO: Fill in the code for this method
        return null;
    }
    // Painting Methods
    public static int[][] paintRandomImage(int[][] canvas) {
        // TODO: Fill in the code for this method
        return null;
    }
    public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {
        // TODO: Fill in the code for this method
        return null;
    }
    public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
        // TODO: Fill in the code for this method
        return null;
    }
    // Utility Methods
    public static int[][] imgToTwoD(String inputFileOrLink) {
        try {
            BufferedImage image = null;
            if (inputFileOrLink.substring(0, 4).equalsIgnoreCase("http")) {
                URL imageUrl = new URL(inputFileOrLink);
                image = ImageIO.read(imageUrl);
                if (image == null) {
                    System.out.println("Failed to get image from provided URL.");
                }
            } else {
                image = ImageIO.read(new File(inputFileOrLink));
            }
            int imgRows = image.getHeight();
            int imgCols = image.getWidth();
            int[][] pixelData = new int[imgRows][imgCols];
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    pixelData[i][j] = image.getRGB(j, i);
                }
            }
            return pixelData;
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getLocalizedMessage());
            return null;
        }
    }
    public static void twoDToImage(int[][] imgData, String fileName) {
        try {
            int imgRows = imgData.length;
            int imgCols = imgData[0].length;
            BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    result.setRGB(j, i, imgData[i][j]);
                }
            }
            File output = new File(fileName);
            ImageIO.write(result, "jpg", output);
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e.getLocalizedMessage());
        }
    }
    public static int[] getRGBAFromPixel(int pixelColorValue) {
        Color pixelColor = new Color(pixelColorValue);
        return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
    }
    public static int getColorIntValFromRGBA(int[] colorData) {
        if (colorData.length == 4) {
            Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
            return color.getRGB();
        } else {
            System.out.println("Incorrect number of elements in RGBA array.");
            return -1;
        }
    }
    public static void viewImageData(int[][] imageTwoD) {
        if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
            int[][] rawPixels = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rawPixels[i][j] = imageTwoD[i][j];
                }
            }
            System.out.println("Raw pixel data from the top left corner.");
            System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
            int[][][] rgbPixels = new int[3][3][4];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
                }
            }
            System.out.println();
            System.out.println("Extracted RGBA pixel data from top the left corner.");
            for (int[][] row : rgbPixels) {
                System.out.print(Arrays.deepToString(row) + System.lineSeparator());
            }
        } else {
            System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
        }
    }
}
package com.example;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ImageProcessing {
    // Functions

    // negative version of the image (negative pixels)
    public static void NegativeCar(int[][] imageData) {
        String pathToNegativeCar = "./images/negative_car.jpg";
        int[][] imageOne = imageData.clone();
        int[][] negative = negativeColor(imageOne, pathToNegativeCar);
        twoDToImage(negative, pathToNegativeCar);
    }

    // stretch the image horizontally
    public static void stretchHorizontally(int[][] imageData) {
        String pathToStretchH = "./images/stretchHorizontally_car.jpg";
        int[][] imageTwo = imageData.clone();
        int[][] stretchH = stretchHorizontally(imageTwo, pathToStretchH);
        twoDToImage(stretchH, pathToStretchH);
    }

    // shrink the image vertically
    public static void shrinkVertically(int[][] imageData) {
        String pathToStretchV = "./images/stretchVertically_car.jpg";
        int[][] imageThree = imageData.clone();
        int[][] stretchV = stretchHorizontally(imageThree, pathToStretchV);
        twoDToImage(stretchV, pathToStretchV);
    }

    // invert the image
    public static void invertImage(int[][] imageData) {
        String pathToInvert = "./images/invert_car.jpg";
        int[][] imageFour = imageData.clone();
        int[][] invert = invertImage(imageFour, pathToInvert);
        twoDToImage(invert, pathToInvert);
    }

    // color filter
    public static void colorFilter(int[][] imageData) {
        String pathToColorFilter = "./images/colorFilter_car.jpg";
        int redValue = -75;
        int greenValue = 30;
        int blueValue = -30;
        int[][] imageFive = imageData.clone();
        int[][] colorFilter = colorFilter(imageFive, redValue, greenValue, blueValue, pathToColorFilter);
        twoDToImage(colorFilter, pathToColorFilter);
    }

    // drawing a rectangle on an image
   public static void drawRectangle(int[][] imageData) {
       String pathToDrawRectangle = "./images/drawRectangle_car.jpg";
       int w = 200;
       int h = 200;
       int rowPos = 100;
       int colPos = 100;
       int[] rgbaToPaint = {255, 255, 0, 255};
       int colorToPaint = getColorIntValFromRGBA(rgbaToPaint);
       int[][] imageSeven = imageData.clone();
       int[][] drawRectangle = paintRectangle(imageSeven, w, h, rowPos, colPos, colorToPaint);
       twoDToImage(drawRectangle, pathToDrawRectangle);
       System.out.println(pathToDrawRectangle.substring(pathToDrawRectangle.lastIndexOf("/") + 1) + " has been created!");
   }
    // painting an image of random colors
public static void randomColors(int[][] imageData) {
    String pathToRandomColors = "./images/randomColors_car.jpg";
    int[][] imageSix = imageData.clone();
    int[][] randomColors = paintRandomImage(imageSix, pathToRandomColors);
    twoDToImage(randomColors, pathToRandomColors);
}


    // create abstract geometric art
public static void geometricArt(int[][] imageData) {
    String pathToPaintRandomGeometricArt = "./images/paintRandomGeometricArt_car.jpg";
    int[][] imageEight = imageData.clone();
    int numberOfRectangle = 3;
    int[][] paintRandomGeometricArt = generateRectangles(imageEight, numberOfRectangle);
    twoDToImage(paintRandomGeometricArt, pathToPaintRandomGeometricArt);
    System.out.println(pathToPaintRandomGeometricArt.substring(pathToPaintRandomGeometricArt.lastIndexOf("/") + 1) + " has been created!");
}

    public static void main(String[] args) {
        // The provided image is car.jpg
        int[][] imageData = imgToTwoD("./src/com/example/car.jpg");
        viewImageData(imageData);

        // Run the program
        while(true) {

            // Delay time
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            // List of options to choose
            System.out.println("1 - Negative Version of the Image");
            System.out.println("2 - Stretch the Image Horizontally");
            System.out.println("3 - Shrink the Image Vertically");
            System.out.println("4 - Invert the Image");
            System.out.println("5 - Applying a Color Filter");
            System.out.println("6 - Painting an Image of Random Colors");
            System.out.println("7 - Drawing a Rectangle on an Image");
            System.out.println("8 - Create Abstract Geometric Art");
            System.out.println("9 - All of above");
            System.out.println("0 - Exit");
            System.out.println("Please type an option to do so: ");

            // Take the input from the user
            Scanner input = new Scanner(System.in);
            int choiceNumber = input.nextInt();

            // Conditions
            if (choiceNumber == 1) {
                NegativeCar(imageData.clone());
            }
            if (choiceNumber == 2) {
                stretchHorizontally(imageData.clone());
            }
            if (choiceNumber == 3) {
                shrinkVertically(imageData.clone());
            }
            if (choiceNumber == 4) {
                invertImage(imageData.clone());
            }
            if (choiceNumber == 5) {
                colorFilter(imageData.clone());
            }
            if (choiceNumber == 6) {
                randomColors(imageData.clone());
            }
            if (choiceNumber == 7) {
                drawRectangle(imageData.clone());
            }
            if (choiceNumber == 8) {
                geometricArt(imageData.clone());
            }
            if (choiceNumber == 9) {
                NegativeCar(imageData.clone());
                stretchHorizontally(imageData.clone());
                shrinkVertically(imageData.clone());
                invertImage(imageData.clone());
                colorFilter(imageData.clone());
                drawRectangle(imageData.clone());
                geometricArt(imageData.clone());
                randomColors(imageData.clone());
            }
            if (choiceNumber == 0) {
                System.exit(0);
            }
        }
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
    // Stretch Horizontally
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
    // Stretch Vertically
    public static int[][] shrinkVertically(int[][] imageTwoD, String path) {
        int[][] array = new int[imageTwoD.length / 2][imageTwoD[0].length];
        for (int i = 0; i < imageTwoD[0].length; i++) {
            for (int j = 0; j < imageTwoD.length; j += 2) {
                array[j / 2][i] = imageTwoD[j][i];
            }
        }
        System.out.println(path.substring(path.lastIndexOf("/") + 1) + " has been created!");
        return array;
    }
    // Invert
    public static int[][] invertImage(int[][] imageTwoD, String path) {
        int[][] array = new int[imageTwoD.length][imageTwoD[0].length];
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                array[i][j] = imageTwoD[(imageTwoD.length - 1) - i][(imageTwoD[i].length - 1) - j];
            }
        }
        System.out.println(path.substring(path.lastIndexOf("/") + 1) + " has been created!");
        return array;
    }
    // Color filter
    public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue, String path) {
       int[][] array = new int[imageTwoD.length][imageTwoD[0].length];
       for (int i = 0; i < imageTwoD.length; i++) {
           for (int j = 0; j < imageTwoD[i].length; j++) {
               int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
               int newRed = rgba[0] + redChangeValue;
               int newGreen = rgba[1] + greenChangeValue;
               int newBlue = rgba[2] + blueChangeValue;

               if (newRed > 255) {
                   newRed = 255;
               } else if (newRed < 0) {
                   newRed = 0;
               }

               if (newGreen > 255) {
                   newGreen = 255;
               } else if (newGreen < 0) {
                   newGreen = 0;
               }

               if (newBlue > 255) {
                   newBlue = 255;
               } else if (newBlue < 0) {
                   newBlue = 0;
               }

               rgba[0] = newRed;
               rgba[1] = newGreen;
               rgba[2] = newBlue;

               array[i][j] = getColorIntValFromRGBA(rgba);
           }
       }
       System.out.println(path.substring(path.lastIndexOf("/") + 1) + " has been created!");
       return array;
    }
    // Painting Methods

    // Painting an image of random color
    public static int[][] paintRandomImage(int[][] canvas, String path) {
        Random rand = new Random();
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                int[] rgba = {rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), 255};
                canvas[i][j] = getColorIntValFromRGBA(rgba);
            }
        }
        System.out.println(path.substring(path.lastIndexOf("/") + 1) + " has been created!");
        return canvas;
    }

    // Drawing a Rectangle of an image
    public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                if (i >= rowPosition && i <= rowPosition + width) {
                    if (j >= colPosition && j <= colPosition + height) {
                        canvas[i][j] = color;
                    }
                }
            }
        }
        return canvas;
    }
    // Create abstract geometric art
    public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
        Random rand = new Random();
        for (int i = 0; i < numRectangles; i++) {
            int randomWidth = rand.nextInt(canvas[0].length);
            int randomHeight = rand.nextInt(canvas.length);
            int randomRowPos = rand.nextInt(canvas.length - randomHeight);
            int randomColPos = rand.nextInt(canvas[0].length - randomWidth);
            int[] rgba = { rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), 255 }; // random color
            int randomColor = getColorIntValFromRGBA(rgba);
            canvas = paintRectangle(canvas, randomWidth, randomHeight, randomRowPos, randomColPos, randomColor);
        }
        return canvas;
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
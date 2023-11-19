package com.github.romankh3.usage.imagecomparison;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Main class for showing the usage.
 */
public class Main {

    public static void main(String[] args) {
        // load the images to be compared
        BufferedImage bufferedImage1 = ImageComparisonUtil.readImageFromResources("actualGoogle.png");
        BufferedImage bufferedImage2 = ImageComparisonUtil.readImageFromResources("img.png");

        // where to save the result (leave null if you want to see the result in the UI)
        File resultDestination = new File("result.png");

        //Create ImageComparison object for it.
        ImageComparison imageComparison = new ImageComparison(bufferedImage1, bufferedImage2, resultDestination);

        //Can be used another constructor for it, without destination.
        new ImageComparison("image1.png", "image2.png");
        //or
        new ImageComparison(bufferedImage1, bufferedImage2);

        //Also can be configured BEFORE comparing next properties:

        //Threshold - it's the max distance between non-equal pixels. By default it's 5.
        imageComparison.setThreshold(10);
        imageComparison.getThreshold();

        //RectangleListWidth - Width of the line that is drawn in the rectangle. By default it's 1.
        imageComparison.setRectangleLineWidth(5);
        imageComparison.getRectangleLineWidth();

        //Destination. Before comparing also can be added destination file for result image.
        imageComparison.setDestination(resultDestination);
        imageComparison.getDestination();

        //MaximalRectangleCount - It means that would get first x biggest rectangles for drawing.
        // by default all the rectangles would be drawn.
        imageComparison.setMaximalRectangleCount(10);
        imageComparison.getMaximalRectangleCount();

        //MinimalRectangleSize - The number of the minimal rectangle size. Count as (width x height).
        // by default it's 1.
        imageComparison.setMinimalRectangleSize(100);
        imageComparison.getMinimalRectangleSize();

        //After configuring the ImageComparison object, can be executed compare() method:
        ImageComparisonResult comparisonResult = imageComparison.compareImages();

        //Can be found ComparisonState.
        ImageComparisonState comparisonState = comparisonResult.getImageComparisonState();

        //And Result Image
        BufferedImage resultImage = comparisonResult.getResult();

        //Image can be saved after comparison, using ImageComparisonUtil.
        ImageComparisonUtil.saveImage(resultDestination, resultImage);
    }
}

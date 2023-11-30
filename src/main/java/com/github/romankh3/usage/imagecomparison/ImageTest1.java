package com.github.romankh3.usage.imagecomparison;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.github.romankh3.usage.utils.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class ImageTest1 {

    int i;

    @Test
    public void testImage() throws IOException {

        String url1 = "https://www.zoetisequine.com/products/dewormers/quest";
        String url2 = "https://www.zoetisequine.com/products/dewormers/quest-plus";


        // load the images to be compared
        BufferedImage bufferedImage1 = ImageComparisonUtil.readImageFromResources(url1 +".png");
        BufferedImage bufferedImage2 = ImageComparisonUtil.readImageFromResources(url2 +".png");



        //Create ImageComparison object and compare the images.
        ImageComparisonResult imageComparisonResult = new ImageComparison(bufferedImage1, bufferedImage2).compareImages();

        File resultDestination = new File( "result.png" );

        //Check the result
        BufferedImage resultImage = imageComparisonResult.getResult();

        //Image can be saved after comparison, using ImageComparisonUtil.
        ImageComparisonUtil.saveImage(resultDestination, resultImage);

        BufferedImage bufferedImage3 = ImageIO.read(new File("C:\\Users\\huseyin.turksoy\\IdeaProjects\\usage-image-comparison\\result.png"));




        //---------------------------------------------------------------

        // Assuming images are of the same size
        int width = Math.max(Math.max(bufferedImage1.getWidth(), bufferedImage2.getWidth()), bufferedImage3.getWidth());
        int height = bufferedImage1.getHeight() + bufferedImage2.getHeight() + bufferedImage3.getHeight();

        // Create a new combined image with the calculated width and height
        BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combinedImage.createGraphics();

        // Draw the images onto the combined image
        g2d.drawImage(bufferedImage1, 0, 0, null);
        g2d.drawImage(bufferedImage2, 0, bufferedImage1.getHeight(), null);
        g2d.drawImage(bufferedImage3, 0, bufferedImage1.getHeight() + bufferedImage2.getHeight(), null);

        // Dispose the Graphics2D object
        g2d.dispose();

        // Save the combined image to a file
        File output = new File("combinedImage.png");
        ImageIO.write(combinedImage, "png", output);



    }



    @Test
    public void test() throws InterruptedException {

        String url1 = "https://www.zoetisequine.com/products/dewormers/quest";
        String url2 = "https://www.zoetisequine.com/products/dewormers/quest-plus";


        i = BrowserUtils.FullPageImage(url1);
        //BrowserUtils.FullPageImage(url2);
       //

    }

    @Test
    public void test2(){
        String url1 = "https://www.zoetisequine.com/products/dewormers/quest";
        BrowserUtils.CombineAllİmages(8, url1);
    }


    @Test
    public void jframeSample(){
        JFrame jFrame = new JFrame("GUI Test");
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(500,500);
        jFrame.add(new JLabel("Hello World"));
        jFrame.add(new JButton("Click Button"));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);


    }


}

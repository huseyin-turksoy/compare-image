package com.github.romankh3.usage.imagecomparison;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class ImageTest1 {

    @Test
    public void testImage() throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.zoetisequine.com/products/dewormers/quest");
        byte[] screenshot = ( (TakesScreenshot) driver ).getScreenshotAs(OutputType.BYTES);

        // Get the resources directory path
        String resourcesDirectory = "src/main/resources/";

        // Define the directory path to save the screenshot within resources
        //String screenshotsDirectory = resourcesDirectory + "/screenshots/";

        // Create the screenshots directory if it doesn't exist
        File directory = new File(resourcesDirectory);


        // Create a file object with the directory path and file name
        File screenshotFile = new File(resourcesDirectory+"google.png");

        try {
            // Write the byte array to the file
            FileOutputStream fileOutputStream = new FileOutputStream(screenshotFile);
            fileOutputStream.write(screenshot);
            fileOutputStream.close();

            System.out.println("Screenshot saved to: " + screenshotFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.get("https://www.zoetisequine.com/products/dewormers/quest-plus");

        byte[] screenshot2 = ( (TakesScreenshot) driver ).getScreenshotAs(OutputType.BYTES);

        File screenshotFile2 = new File(resourcesDirectory+"gmail.png");

        try {
            // Write the byte array to the file
            FileOutputStream fileOutputStream = new FileOutputStream(screenshotFile2);
            fileOutputStream.write(screenshot2);
            fileOutputStream.close();

            System.out.println("Screenshot saved to: " + screenshotFile2.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }


        // load the images to be compared
        BufferedImage bufferedImage1 = ImageComparisonUtil.readImageFromResources("gmail.png");
        BufferedImage bufferedImage2 = ImageComparisonUtil.readImageFromResources("google.png");



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


}

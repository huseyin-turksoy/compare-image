package com.github.romankh3.usage.utils;

import com.github.romankh3.image.comparison.ImageComparisonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {

    private static JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

    public static Long getTotalHeight(){



        Long documentWidth = (Long) js.executeScript("return Math.max("
                + "document.body.scrollWidth, document.documentElement.scrollWidth,"
                + "document.body.offsetWidth, document.documentElement.offsetWidth,"
                + "document.body.clientWidth, document.documentElement.clientWidth);");

        Long documentHeight = (Long) js.executeScript("return Math.max("
                + "document.body.scrollHeight, document.documentElement.scrollHeight,"
                + "document.body.offsetHeight, document.documentElement.offsetHeight,"
                + "document.body.clientHeight, document.documentElement.clientHeight);");

        System.out.println("Document Width: " + documentWidth + " Document Height: " + documentHeight);

        return documentHeight;


    }


    public static int FullPageImage(String url) throws InterruptedException {

        int lastIndexofForwardSlash1 = url.lastIndexOf("/");
        String imageName = url.substring(lastIndexofForwardSlash1+1);
        System.out.println("imageName = " + imageName);

        //navigate to page
        Driver.getDriver().get(url);

        //check if cucies exist or not if exist close it
        if (Driver.getDriver().findElement(By.cssSelector("div[id=\"onetrust-close-btn-container\"]")).isDisplayed()){
            Driver.getDriver().findElement(By.cssSelector("div[id=\"onetrust-close-btn-container\"]")).click();
        }

        //get total height and devide 942 px to find out how many times to scroll down
        Long pageTotalHeight = getTotalHeight();
        System.out.println("pageTotalHeight = " + pageTotalHeight);
        int scrollCount = (int) (pageTotalHeight/942);

        for (int i = 0; i <=scrollCount ; i++) {

            Thread.sleep(2000);

            byte[] screenshot = ( (TakesScreenshot) Driver.getDriver() ).getScreenshotAs(OutputType.BYTES);

            // Get the resources directory path
            String resourcesDirectory = "src/main/resources/";

            // Define the directory path to save the screenshot within resources
            //String screenshotsDirectory = resourcesDirectory + "/screenshots/";

            // Create the screenshots directory if it doesn't exist
            File directory = new File(resourcesDirectory);


            // Create a file object with the directory path and file name
            File screenshotFile = new File(resourcesDirectory+ imageName + i +".png");

            try {
                // Write the byte array to the file
                FileOutputStream fileOutputStream = new FileOutputStream(screenshotFile);
                fileOutputStream.write(screenshot);
                fileOutputStream.close();

                System.out.println("Screenshot saved to: " + screenshotFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }


            js.executeScript("window.scrollBy(0, 942);");



        }
        return scrollCount;


    }


    public static void CombineAllİmages(int scrollCount, String url){

        int lastIndexofForwardSlash1 = url.lastIndexOf("/");
        String imageName = url.substring(lastIndexofForwardSlash1+1);

        int combinedImageHeight=0;
        int combinedImageWidth = 1904;
        ArrayList<BufferedImage> imagesList = new ArrayList<>();

        for (int i = 0; i <=scrollCount ; i++) {

            BufferedImage bufferedImage1 = ImageComparisonUtil.readImageFromResources(imageName+i+".png");
            imagesList.add(bufferedImage1);

        }

        for (BufferedImage img : imagesList) {
            combinedImageHeight += img.getHeight();

        }

        // Create a new combined image with the calculated width and height
        BufferedImage combinedImage = new BufferedImage(combinedImageWidth, combinedImageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combinedImage.createGraphics();

        // Draw the images onto the combined image
        int currentY = 0;
        for (BufferedImage img : imagesList) {
            g2d.drawImage(img, 0, currentY, null);
            currentY += img.getHeight();
        }

        // Dispose the Graphics2D object
        g2d.dispose();

        // Save the combined image to a file
        try {
            File output = new File("combinedImage.png");
            ImageIO.write(combinedImage, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }






    }






}

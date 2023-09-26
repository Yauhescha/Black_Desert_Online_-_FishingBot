package com.hescha.bdofishbot;

import com.hescha.bdofishbot.action_maker.Writter;
import com.hescha.bdofishbot.screen_reader.ImageColorCheck;
import com.hescha.bdofishbot.screen_reader.Reader;
import com.hescha.bdofishbot.text_parser.Parser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.hescha.bdofishbot.text_parser.Language.ENG;
import static com.hescha.bdofishbot.text_parser.Language.RUS;

public class App {

    public static final String haveFishTemplate = "Что-то поймалось";

    public static void main(String[] args) throws Exception {
        while (true) {
            fish();
        }
    }

    private static void fish() throws Exception {
        Parser parser = Parser.getParser();

        boolean shouldPressSpace = false;
        do {
            BufferedImage bufferedImage = Reader.grabIsHaveFish();
            String imgText1 = parser.getImgText(RUS, bufferedImage);
            shouldPressSpace = imgText1.contains(haveFishTemplate);
        } while (!shouldPressSpace);

        Writter.pressButton(' ');
        Thread.sleep(1430);
        Writter.pressButton(' ');


        Thread.sleep(500);
        BufferedImage fishingResult = Reader.grabFishingResult();
        boolean imageBadResult = ImageColorCheck.isImageBadResult(fishingResult);
        boolean imageGoodResult = ImageColorCheck.isImageGoodResult(fishingResult);
        boolean imagePerfectResult = ImageColorCheck.isImagePerfectResult(fishingResult);
        System.out.println("is result bad? - " + imageBadResult);
        System.out.println("is result good? - " + imageGoodResult);
        System.out.println("is result perfect? - " + imagePerfectResult);

//        if (!imageBadResult && !imageGoodResult && !imagePerfectResult) {
//            saveImage(fishingResult);
//        }

        if (imagePerfectResult) {
            Thread.sleep(3000);
            Writter.pressButton('R');
        } else if (imageBadResult) {
        } else {
            Thread.sleep(3000);
            BufferedImage miniGameImage = Reader.grabMiniGame();
//            saveImage(miniGameImage);
            String imgText1 = parser.getImgText(ENG, miniGameImage);
            System.out.println("minigame result - " + imgText1);
            imgText1 = imgText1.replaceAll("[^WASD]", "");
            System.out.println("minigame result REPLACED - " + imgText1);

            for (int i = 0; i < imgText1.length(); i++) {
                Writter.pressButton(imgText1.charAt(i));
                Thread.sleep(100);
            }
            Thread.sleep(3000);
            Writter.pressButton('R');
        }

        Thread.sleep(3000);
        Writter.pressButton(' ');
        Writter.pressButton('I');
        fish();

    }

    public static void saveImage(BufferedImage bufferedImage) {
        try {
            File output = new File("captcha/" + System.nanoTime() + ".png");
            ImageIO.write(bufferedImage, "png", output);
            System.out.println(output.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("IO exception" + e);
        }
    }
}

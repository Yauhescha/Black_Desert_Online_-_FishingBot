package com.hescha.bdofishbot.screen_reader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Reader {
    private static final Rectangle isHaveFish = new Rectangle(624, 55, 685, 30);
    private static final Rectangle fishingResult = new Rectangle(834, 450, 230, 45);
    private static final Rectangle miniGame = new Rectangle(750, 330, 400, 80);

    public static BufferedImage grabIsHaveFish() {
        try {
            return new Robot().createScreenCapture(isHaveFish);
        } catch (SecurityException | AWTException ignored) {
        }
        return null;
    }

    public static BufferedImage grabFishingResult() {
        try {
            return new Robot().createScreenCapture(fishingResult);
        } catch (SecurityException | AWTException ignored) {
        }
        return null;
    }

    public static BufferedImage grabMiniGame() {
        try {
            BufferedImage screenCapture = new Robot().createScreenCapture(miniGame);
            return ImageColorCheck.cutImageByCaptchaBorder(screenCapture);
//            return screenCapture;
        } catch (SecurityException | AWTException ignored) {
        }
        return null;
    }
}

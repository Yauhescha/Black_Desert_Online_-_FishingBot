package com.hescha.bdofishbot.screen_reader;

import java.awt.image.BufferedImage;

public class ImageColorCheck {
    private static final int BACKGROUND_COLOR = -13290182;
    public static final int WIDTH = 365;
    public static final int HEIGHT = 30;
    static int white = 0xFFFFFFFF;
    static int black = 0x00000011;


    public static BufferedImage cutImageByCaptchaBorder(BufferedImage image) {
        int xMin = Integer.MAX_VALUE;
        int yMin = Integer.MAX_VALUE;

        xMin=image.getWidth()-1;
        yMin=image.getHeight()-1;

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int pixelColor = image.getRGB(i, j);
                if (pixelColor == BACKGROUND_COLOR) {
                    //update min value
                    if (xMin > i) xMin = i;
                    if (yMin > j) yMin = j;
                }
            }
        }


        // Создание подкартинки на основе найденных координат
        int w = xMin + WIDTH >= image.getWidth() ? image.getWidth() - xMin : WIDTH;
        int h = yMin + HEIGHT >= image.getHeight() ? image.getHeight() - yMin : HEIGHT;

//        if(w>image.getWidth())w=image.getWidth()-1;
//        if(h>image.getHeight())h=image.getHeight()-1;
        BufferedImage subimage = image.getSubimage(xMin, yMin, w, h);
        for (int i = 0; i < subimage.getWidth(); i++) {
            for (int j = 0; j < subimage.getHeight(); j++) {
                int pixelColor = subimage.getRGB(i, j);
                if (pixelColor == BACKGROUND_COLOR) {
                    subimage.setRGB(i, j, white);
                } else {
                    subimage.setRGB(i, j, black);
                }

                if ((i + 1) % 37 == 0) {
                    subimage.setRGB(i, j, white);
                }
            }
        }

        return subimage;
    }


    public static boolean isImageBadResult(BufferedImage image) {
        int x = 85;
        int y = 5;
        for (int i = 0; i < 10; i++) {
            if (image.getRGB(x, y) != -6923731) return false;
        }
        return true;
    }

    public static boolean isImagePerfectResult(BufferedImage image) {
        int x = 10;
        int y = 4;
        for (int i = 0; i < 20; i++) {
            if (image.getRGB(x, y) != -164) return false;
        }
        return true;
    }

    public static boolean isImageGoodResult(BufferedImage image) {
        int x = 170;
        if (image.getRGB(x, 10) != -5373953) return false;
        if (image.getRGB(x, 11) != -6029313) return false;
        if (image.getRGB(x, 12) != -6750209) return false;
        if (image.getRGB(x, 13) != -7667713) return false;
        if (image.getRGB(x, 14) != -8388609) return false;
        x = 171;
        if (image.getRGB(x, 10) != -5373953) return false;
        if (image.getRGB(x, 11) != -6029313) return false;
        if (image.getRGB(x, 12) != -6750209) return false;
        if (image.getRGB(x, 13) != -7667713) return false;
        if (image.getRGB(x, 14) != -8388609) return false;
        return true;
    }
}


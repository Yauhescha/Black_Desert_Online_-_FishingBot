package com.hescha.bdofishbot.text_parser;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private final String TESSDATA_MAIN = "C:\\Users\\Administrator\\Downloads\\tessdata-main\\tessdata-main";
    private final Map<Language, Tesseract> map = new HashMap<>();
    private static final Parser parser = new Parser();

    private Parser() {
        var tesseractRU = new Tesseract();
        var tesseractEN = new Tesseract();

        tesseractRU.setLanguage(Language.RUS.toString());
        tesseractEN.setLanguage(Language.ENG.toString());

        tesseractRU.setDatapath(TESSDATA_MAIN);
        tesseractEN.setDatapath(TESSDATA_MAIN);

        map.put(Language.RUS, tesseractRU);
        map.put(Language.ENG, tesseractEN);
    }

    public static Parser getParser() {
        return parser;
    }

    public String getImgText(Language language, String imageLocation) throws TesseractException {
        return map.get(language).doOCR(new File(imageLocation));
    }

    public String getImgText(Language language, BufferedImage image) throws TesseractException {
        return map.get(language).doOCR(image);
    }
}


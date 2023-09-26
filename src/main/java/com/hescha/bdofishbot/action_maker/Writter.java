package com.hescha.bdofishbot.action_maker;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Writter {
    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void pressButton(char character) throws Exception {
        int key = getKey(character);

        robot.keyPress(key);
        long randTimeSleep = (long) (Math.random() * 200);
        if (character == ' ') randTimeSleep = 100;
        Thread.sleep(randTimeSleep);
        robot.keyRelease(key);
    }

    private static int getKey(char character) {
        if (character == 'W') return KeyEvent.VK_W;
        else if (character == 'A') return KeyEvent.VK_A;
        else if (character == 'S') return KeyEvent.VK_S;
        else if (character == 'D') return KeyEvent.VK_D;
        else if (character == 'R') return KeyEvent.VK_R;
        else if (character == 'I') return KeyEvent.VK_I;
        else return KeyEvent.VK_SPACE;
    }
}

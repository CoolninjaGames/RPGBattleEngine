package coolninja.rpg.Console;

import java.io.IOException;

/**
 * Built-in console effects functions
 *
 * @version 1.0
 * @since 1.0
 */
public class Console {

    /**
     * Creates 3 dots for the amount of times passed
     *
     * @param times
     * @param timeBetween
     * @since 1.0
     */
    public static void Dots(int times, long timeBetween) {
        for (int i = 0; i < times; i++) {
            try {
                Thread.sleep(timeBetween);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }
    }

    /**
     * Prints an error message with the given text
     *
     * @since 1.0
     * @param text
     * @param waitTime
     */
    public static void printError(String text, long waitTime) {
        System.out.println(Colors.RED_BACKGROUND + text);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Colors.RESET();
    }

    /**
     * Clears the console
     *
     * @since 1.0
     */
    public static void clear() {
        if (System.getProperty("os.name").contains("win")) {
            try {
                Runtime.getRuntime().exec("cls");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.print("\033[H\033[2J");
    }

    /**
     * Waits the amount of time given (in milliseconds)
     *
     * @param time
     * @since 1.0
     */
    public static void waitReal(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits the amount of half seconds given
     *
     * @param time
     * @since 1.0
     */
    public static void waitHalf(int time) {
        try {
            Thread.sleep(500 * time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits the amount of time given (in seconds)
     *
     * @param time
     * @since 1.0
     */
    public static void waitFull(int time) {
        try {
            Thread.sleep(1000 * time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

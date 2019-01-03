package blackbox.game.util;

/**
 * Random class. Util to generate random numbers,
 * strings, effects, etc...
 *
 * @author Bowserinator
 */
public final class Random {
    public static final String[] glitchChar = {"!", "@", "#", "%", "^", "&", "*", "+", "-", "_", "~", ".", "?"};

    /**
     * Generate a random integer between a and b
     * in the range [a, b). (Not including b)
     *
     * @param a Low bound
     * @param b High bound
     * @return  Random int in range
     */
    public static int randInt(int a, int b) {
        return a + (int)(Math.random() * (b-a));
    }

    /**
     * Pick a random string in a String[]
     * @param arr Array of strings to pick from
     * @return    Random element
     */
    public static String choice(String[] arr) {
        return arr[randInt(0, arr.length)];
    }

    /**
     * Create a glitch effect by inserting random glitch
     * characters in the string
     * @param text           String to modify
     * @param glitchPercent  Percentage of text to glitch
     * @return               Glitched text
     */
    public static String randomGlitch(String text, double glitchPercent) {
        for (int i = 0; i < text.length() * glitchPercent; i++) {
            int r = randInt(0, text.length());
            text = text.substring(0, r) + choice(glitchChar) + text.substring(r);
        }
        return text;
    }

    /**
     * Create a glitch effect by replacing random
     * characters in the string
     * @param text           String to modify
     * @param glitchPercent  Percentage of text to glitch
     * @return               Glitched text
     */
    public static String randomGlitch2(String text, double glitchPercent) {
        for (int i = 0; i < text.length() * glitchPercent; i++) {
            int r = randInt(0, text.length());
            text = text.substring(0, r) + choice(glitchChar) + text.substring(r + 1);
        }
        return text;
    }
}

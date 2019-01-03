package blackbox.game.util;

import com.badlogic.gdx.Gdx;

/**
 * Additional math utils
 * @author Bowserinator
 */
public class MathUtil {
    /**
     * Get a ratio of a / b to screen width. Ie
     * ratioW(1, 2) would yield 1/2 window width.
     * Useful for scaling objects.
     *
     * @param a Numerator
     * @param b Denominator
     * @return  Ratio of screen width
     */
    public static int ratioW(int a, int b) {
        return (int)((double)a / b * Gdx.graphics.getWidth());
    }

    /**
     * Get a ratio of a / b to screen height. Ie
     * ratioW(1, 2) would yield 1/2 window height.
     * Useful for scaling objects.
     *
     * @param a Numerator
     * @param b Denominator
     * @return  Ratio of screen height
     */
    public static int ratioH(int a, int b) {
        return (int)((double)a / b * Gdx.graphics.getHeight());
    }
}

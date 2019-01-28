package blackbox.game.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Util class for drawing text
 * onto the screen
 * @author Bowserinator
 */
public class DrawText {
    public static final float lineHeight = 1.5f;

    /**
     * Construct a DrawText class. Private because
     * this class should not be constructable.
     */
    private DrawText() {}

    /**
     * Render some text in a region. NOTE: Only works
     * for monospaced fonts; you may expect some weirdness
     * otherwise. If number of lines exceeds maxLines, it will
     * scroll downwards and display bottommost line, cutting off
     * the top.
     *
     * @param batch  Sprite batch to draw to
     * @param text   String to render
     * @param data   Monospace font data
     * @param x      x position of top corner
     * @param y      y position of top corner
     * @param w      width of rectangle
     * @param maxLines Most lines that can fit on screen
     */
    public static void drawTextRect(SpriteBatch batch, String text, MonospaceFontData data, int x, int y, int w, int maxLines) {
        int cols = (int)(w / data.charWidth);
        Array<String> chunked = chunkString(text, cols);
        int bound = Math.max(0, chunked.size - maxLines);

        drawLines(batch, chunked, data, x, y, bound);
    }

    /**
     * Render lines of text
     *
     * @param batch  Sprite batch to draw to
     * @param lines  Lines to render
     * @param data   Monospace font data
     * @param x      x position of top corner
     * @param y      y position of top corner
     * @param bound  Array size - number of lines to render, lowest is 0
     */
    public static void drawLines(SpriteBatch batch, Array<String> lines, MonospaceFontData data, int x, int y, int bound) {
        for (int index = lines.size - 1; index >= bound; index--) {
            String line = lines.get(index);
            if (line.startsWith(" ") && !line.startsWith("  "))
                line = line.substring(1);
            data.font.draw(batch, line, x, y - (int)(data.charHeight * (index - bound) * lineHeight));
        }
    }

    /**
     * Calculate how many characters can fit in a rectangle
     * of given width and height.
     *
     * @param data Font data to render
     * @param w Width of rect
     * @param h Height of rect
     * @return Number of characters
     */
    public static int charsFitInRect(MonospaceFontData data, int w, int h) {
        int cols = (int)(w / data.charWidth);
        int rows = (int)(h / (data.charHeight * lineHeight));
        return cols * rows;
    }

    /**
     * Calculate how many lines can fit in
     * a region of given height
     *
     * @param data Font data to render
     * @param h Height of rect
     * @return Number of lines
     */
    public static int linesFitInHeight(MonospaceFontData data, int h) {
        return (int)(h / (data.charHeight * lineHeight));
    }

    /**
     * Private method to chunk a string into chunks of size chunkSize
     * OR by newlines, whichever comes first.
     *
     * @param s         String to chunk
     * @param chunkSize Chunksize
     * @return          Chunked String
     */
    private static Array<String> chunkString(String s, int chunkSize) {
        Array<String> split = new Array<String>();
        String[] lines = s.split("\n");

        for (String line : lines) {
            /* Line is already short enough to be on
             * its own line */
            if (line.length() <= chunkSize)
                split.add(line);

            /* Split up the line based on the last remaining
             * space, or if non are available, the last character */
            else {
                while (line.length() > chunkSize) {
                    /* Add the string up to either the last space or
                     * the last character, if no spaces are available. Then
                     * set line equal to the remainder that was not added
                     * and repeat until the line is below the max width. */
                    int lastIndex = line.substring(0, chunkSize).lastIndexOf(" ");
                    lastIndex = lastIndex < 0 ? chunkSize : lastIndex;

                    split.add(line.substring(0, lastIndex));
                    line = line.substring(lastIndex);
                }

                /* Add any remaining characters, if any */
                if (line.length() > 0)
                    split.add(line);
            }
        }

        return split;
    }
}

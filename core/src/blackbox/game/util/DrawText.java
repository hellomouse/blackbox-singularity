package blackbox.game.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Util class for drawing text
 * onto the screen
 * @author Bowserinator
 */
public class DrawText {
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
     * @param h      height of rectangle
     * @param maxLines Most lines that can fit on screen
     */
    public static void drawTextRect(SpriteBatch batch, String text, MonospaceFontData data, int x, int y, int w, int h, int maxLines) {
        int cols = (int)(w / data.charWidth);
        Array<String> chunked = chunkString(text, cols);
        int bound = Math.max(0, chunked.size - maxLines);

        for (int index = chunked.size - 1; index >= bound; index--) {
            String line = chunked.get(index);
            if (line.startsWith(" ") && !line.startsWith("  "))
                line = line.substring(1);
            data.font.draw(batch, line, x, y - (int)(data.charHeight * (index - bound) * 1.5));
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
        int rows = (int)(h / (data.charHeight * 1.5));
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
        return (int)(h / (data.charHeight * 1.5));
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
        int i = 0;

        while (i < s.length()) {
            int nextNewline = s.indexOf("\n", i);
            int nextIndex = i + chunkSize;
            int add = nextNewline >= 0 ? 1 : 0;

            nextNewline = nextNewline < 0 ? nextIndex : nextNewline;
            nextIndex = Math.min(nextNewline, nextIndex);

            split.add(s.substring(i, Math.min(nextIndex, s.length())));
            i = nextIndex + add;
        }
        return split;
    }
}

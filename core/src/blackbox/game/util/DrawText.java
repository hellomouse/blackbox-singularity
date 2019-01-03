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
     * otherwise.
     *
     * @param batch  Sprite batch to draw to
     * @param text   String to render
     * @param data   Monospace font data
     * @param x      x position of top corner
     * @param y      y position of top corner
     * @param w      width of rectangle
     * @param h      height of rectangle
     */
    public static void drawTextRect(SpriteBatch batch, String text, MonospaceFontData data, int x, int y, int w, int h) {
        int rows = (int)(h / data.charHeight);
        int cols = (int)(w / data.charWidth);
        int index = 0;
        Array<String> chunked = chunkString(text, cols);

        while (index < rows && index < chunked.size) {
            data.font.draw(batch, chunked.get(index), x, y - (int)(data.charHeight * index * 1.5));
            index++;
        }
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

package blackbox.game.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * Stores some data about monospaced font
 * (Character width and height) and allows
 * for some computations with strings.
 *
 * @author Bowserinator
 */
public class MonospaceFontData {
    /**
     * charWidth  - Width of character (px)
     * charHeight - Height of character (px)
     * font       - Bitmap font to actually render
     */
    public float charWidth, charHeight;
    public BitmapFont font;

    /**
     * Create a new monospace font data from
     * a BitmapFont
     * @param font Font to use
     */
    public MonospaceFontData(BitmapFont font) {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "a");

       this.charWidth = layout.width;
       this.charHeight = layout.height;
       this.font = font;
    }

    /**
     * Returns the width of a string rendered using
     * this font.
     * @param s String to render
     * @return  Height in px
     */
    public float getWidth(String s) {
        return s.length() * this.charWidth;
    }
}

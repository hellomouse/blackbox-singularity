package blackbox.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import blackbox.game.BlackboxGame;
import blackbox.game.util.MonospaceFontData;
import blackbox.game.util.DrawText;
import blackbox.game.util.MathUtil;

/**
 * BackgroundScene
 *
 * Base class for the background
 * that displays the computer terminal. Handles
 * drawing/animating the background, and displaying
 * text on the screen.
 *
 * NOTE: if you want to add a custom
 * animation to the screen, simply draw over this class
 *
 * @author  Bowserinator
 * @version 1.0.0
 * @since   2018-12-06
 */
public abstract class BackgroundScene {
    /**
     * Config for the background size, which
     * should be 1920x1080. Image size is the
     * size for all panoramas
     */
    public static final int BG_WIDTH = 1920;
    public static final int BG_HEIGHT = 1080;
    public int imageWidth;
    public int imageHeight;

    /**
     * screenRect       - [x, y, w, h], region to display text
     * flickerIntensity - How intense image flickering is, 0 is none, 1 is flicker 0% to 200%
     * name             - Name of the current scene
     *
     * screenText       - Text to render on the screen
     * currentCharCount - Number of characters to render on the screen at moment
     * typingSpeed      - char/delta to type on screen. 20 is a good speed.
     * scroll           - How far image is scrolled left or right. More negative is more right facing, starts at 0 (left)
     * initialScroll    - Very first scroll value
     *
     * fontData          - The font to display on the screen. MUST BE MONOSPACED
     * maxLinesOnScreen  - The most characters that can fit on the screen at a time
     * typing            - Is it currently typing?
     */
    public int[] screenRect;
    public float flickerIntensity;
    private String name;

    private String screenText;
    private float currentCharCount;
    private float typingSpeed;

    public int scroll;
    public int initialScroll;
    private MonospaceFontData fontData;
    private int maxLinesOnScreen;
    public boolean typing;

    /**
     * The image texture and background
     * to be rendered to the screen. All image
     * textures should be the size as defined by
     * imageWidth and imageHeight
     */
    private TextureRegion background;

    /**
     * Create a new background scene object. Please override
     * this method in your subclass
     *
     * @param screenRect       Defines pixel region of screen: {x, y, w, h}
     * @param flickerIntensity Defines percentage to flicker (0-1.0f)
     * @param name             Name of the scene
     * @param imagePath        Texture path to image, relative to assets folder in android
     * @param fontData         Fontdata for the font to render the screen in
     */
    public BackgroundScene(int[] screenRect, float flickerIntensity, String name, String imagePath,
                           MonospaceFontData fontData) {
        this(screenRect, flickerIntensity, name, imagePath, fontData, 4590, 1080);
    }

    /**
     * Create a new background scene object. Please override this method in your subclass.
     * The imageWidth and imageHeight is the resolution of the background image file, which
     * DEFAULTS to 4590x1080. If you don't include them, then it will default to those.
     *
     * @param screenRect       Defines pixel region of screen: {x, y, w, h}
     * @param flickerIntensity Defines percentage to flicker (0-1.0f)
     * @param name             Name of the scene
     * @param imagePath        Texture path to image, relative to assets folder in android
     * @param fontData         Fontdata for the font to render the screen in
     * @param imageWidth       Width of bg image (px)
     * @param imageHeight      Height of bg image (px)
     */
    public BackgroundScene(int[] screenRect, float flickerIntensity, String name, String imagePath,
                           MonospaceFontData fontData, int imageWidth, int imageHeight) {
        this.screenRect = screenRect;
        this.flickerIntensity = flickerIntensity;
        this.name = name;
        this.fontData = fontData;

        this.screenText = "";
        this.currentCharCount = 0;
        this.scroll = -MathUtil.ratioH(imageWidth, imageHeight) / 4;

        /* Load the background texture */
        if (imagePath != null) {
            Texture img = new Texture(imagePath);
            this.background = new TextureRegion(img, 0, 0, imageWidth, imageHeight);
        }

        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.maxLinesOnScreen = screenRect != null ? DrawText.linesFitInHeight(fontData, screenRect[3]) : 0;
        this.typing = false;

        limitScroll();
        this.initialScroll = this.scroll;
    }

    /**
     * Render the background (not including UI)
     * @param delta Delta, incremented each frame by libgdx
     * @param batch SpriteBatch to draw this to
     * @param game  Game object
     */
    public void render(float delta, SpriteBatch batch, BlackboxGame game) {
        this.renderBackground(delta, batch, game);
        this.renderText(delta, batch, game);
    }

    /**
     * Render the background text (not including UI or image)
     * @param delta Delta, incremented each frame by libgdx
     * @param batch SpriteBatch to draw this to
     * @param game  Game object
     */
    public void renderText(float delta, SpriteBatch batch, BlackboxGame game) {
        // Render the text by current character count
        if (this.screenText.length() > 0) {
            this.currentCharCount += delta * this.typingSpeed;
            if (this.currentCharCount > this.screenText.length()) {
                this.currentCharCount = this.screenText.length();
                this.typing = false;
            }

            DrawText.drawTextRect(batch,
                    this.screenText.substring(0, (int) this.currentCharCount),
                    this.fontData,
                    MathUtil.ratioW(this.screenRect[0], BG_WIDTH) + scroll,
                    MathUtil.ratioH(this.screenRect[1], BG_HEIGHT),
                    MathUtil.ratioW(this.screenRect[2], BG_WIDTH),
                    this.maxLinesOnScreen);
        }
    }

    /**
     * Render the background image (not including UI or text)
     * @param delta Delta, incremented each frame by libgdx
     * @param batch SpriteBatch to draw this to
     * @param game  Game object
     */
    public void renderBackground(float delta, SpriteBatch batch, BlackboxGame game) {
        limitScroll();

        // Flickering effect
        float brightness = (float)(Math.random() * (this.flickerIntensity) + (1 - this.flickerIntensity));
        batch.setColor(brightness, brightness, brightness, 1F);

        // Draw the background image
        if (background != null)
            batch.draw(background, scroll, 0,
                    MathUtil.ratioH(imageWidth, imageHeight),
                    Gdx.graphics.getHeight());
        batch.setColor(1f,1f,1f,1f); // Reset brightness
    }

    /**
     * Helper to limit the scroll variable to edge of
     * the background image
     */
    private void limitScroll() {
        // Limit scroll to edges of image
        if (scroll > 0)
            scroll = 0;
        if (scroll < - MathUtil.ratioH(imageWidth, imageHeight) + MathUtil.ratioW(1, 1))
            scroll = - MathUtil.ratioH(imageWidth, imageHeight) + MathUtil.ratioW(1, 1);
    }

    /**
     * Render the UI (No curvature effect)
     * @param delta Delta, incremented each frame by libgdx
     * @param batch SpriteBatch to draw this to
     * @param game  Game object
     */
    public void renderUI(float delta, SpriteBatch batch, BlackboxGame game) {
        // Override this method to do stuff
    }

    /**
     * Clear the current text displayed on the screen
     * NOTE: screen won't be updated until next render() call
     */
    public void clearScreenText() {
        this.screenText = "";
        this.currentCharCount = 0;
    }

    /**
     * Type some text onto the screen. Adds a space between previous
     * text and current text.
     *
     * NOTE: screen won't be updated until next render() call
     * @param text  Text to render onto the screen.
     * @param speed Speed to type the text (char/delta)
     */
    public void typeText(String text, float speed) {
        this.typingSpeed = speed;
        this.screenText += this.screenText.length() > 0 ? " " + text : text;
        this.typing = true;
    }

    /**
     * Returns the name of the scene
     * @return Name of the scene
     */
    public String getName() {
        return name;
    }
}


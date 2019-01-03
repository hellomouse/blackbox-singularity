package blackbox.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import blackbox.game.BlackboxGame;
import blackbox.game.Config;
import blackbox.game.util.MonospaceFontData;
import blackbox.game.util.DrawText;
import blackbox.game.util.MathUtil;
import contribs.postprocessing.PostProcessor;
import contribs.postprocessing.effects.Curvature;
import contribs.utils.ShaderLoader;

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
    public static final int IMAGE_WIDTH = 4590;
    public static final int IMAGE_HEIGHT = 1080;

    /**
     * screenRect       - [x, y, w, h], region to display text
     * flickerIntensity - How intense image flickering is, 0 is none, 1 is flicker 0% to 200%
     * name             - Name of the current scene
     *
     * screenText       - Text to render on the screen
     * currentCharCount - Number of characters to render on the screen at moment
     * typingSpeed      - char/delta to type on screen. 20 is a good speed.
     * scroll           - How far image is scrolled left or right. More negative is more right facing, starts at 0 (left)
     *
     * fontData          - The font to display on the screen. MUST BE MONOSPACED
     */
    public int[] screenRect;
    public float flickerIntensity;
    private String name;

    private String screenText;
    private float currentCharCount;
    private float typingSpeed;

    public int scroll;
    private MonospaceFontData fontData;

    /**
     * The image texture and background
     * to be rendered to the screen. All image
     * textures should be the size as defined by
     * IMAGE_WIDTH and IMAGE_HEIGHT
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
        this.screenRect = screenRect;
        this.flickerIntensity = flickerIntensity;
        this.name = name;
        this.fontData = fontData;

        this.screenText = "";
        this.currentCharCount = 0;
        this.scroll = -MathUtil.ratioW(IMAGE_WIDTH, BG_WIDTH) / 4;

        /* Load the background texture */
        Texture img = new Texture(imagePath);
        this.background = new TextureRegion(img, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    /**
     * Render the background (not including UI)
     * @param delta Delta, incremented each frame by libgdx
     * @param batch SpriteBatch to draw this to
     * @param game  Game object
     */
    public void render(float delta, SpriteBatch batch, BlackboxGame game) {
        // Limit scroll to edges of image
        if (scroll > 0)
            scroll = 0;
        if (scroll < - MathUtil.ratioW(IMAGE_WIDTH, BG_WIDTH) + MathUtil.ratioW(1, 1))
            scroll = - MathUtil.ratioW(IMAGE_WIDTH, BG_WIDTH) + MathUtil.ratioW(1, 1);

        // Flickering effect
        float brightness = (float)(Math.random() * (this.flickerIntensity) + (1 - this.flickerIntensity));
        batch.setColor(brightness, brightness, brightness, 1F);

        // Draw the background image
        batch.draw(background, scroll, 0,
                   MathUtil.ratioW(IMAGE_WIDTH, BG_WIDTH),
                   MathUtil.ratioH(IMAGE_HEIGHT, BG_HEIGHT));
        batch.setColor(1f,1f,1f,1f); // Reset brightness

        // Render the text by current character count
        if (this.screenText.length() > 0) {
            this.currentCharCount += delta * this.typingSpeed;
            if (this.currentCharCount > this.screenText.length())
                this.currentCharCount = this.screenText.length();

            DrawText.drawTextRect(batch,
                    this.screenText.substring(0, (int) this.currentCharCount),
                    this.fontData,
                    MathUtil.ratioW(this.screenRect[0], BG_WIDTH) + scroll,
                    MathUtil.ratioH(this.screenRect[1], BG_HEIGHT),
                    MathUtil.ratioW(this.screenRect[2], BG_WIDTH),
                    MathUtil.ratioH(this.screenRect[3], BG_HEIGHT));
        }
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
    }

    /**
     * Returns the name of the scene
     * @return Name of the scene
     */
    public String getName() {
        return name;
    }
}


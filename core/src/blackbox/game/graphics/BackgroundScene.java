package blackbox.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import blackbox.game.BlackboxGame;

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
     * Config for the image texture, which
     * should all be 1920x1080
     */
    public static final int BG_WIDTH = 1920;
    public static final int BG_HEIGHT = 1080;

    /**
     * Basic instance variables. Name is read only,
     * the rest can be modified with their respective
     * setters and getters
     */
    private int[] screenRect;
    private float flickerIntensity;
    private String name;

    private String screenText;
    private float currentCharCount;
    private float typingSpeed;
    public int scroll = 0;

    /**
     * The image texture and background
     * to be rendered to the screen. All image
     * textures should be 1920x1080
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
     */
    public BackgroundScene(int[] screenRect, float flickerIntensity, String name, String imagePath) {
        this.screenRect = screenRect;
        this.flickerIntensity = flickerIntensity;
        this.name = name;

        this.screenText = "";
        this.currentCharCount = 0;

        Texture img = new Texture(imagePath);
        // this.background = new TextureRegion(img, 0, 0, BG_WIDTH, BG_HEIGHT);
        this.background = new TextureRegion(img, 0, 0, 2500, BG_HEIGHT);
    }

    /**
     * Render the background
     * @param delta Delta, incremented each frame by libgdx
     * @param batch SpriteBatch to draw this to
     * @param game  Game object
     */
    public void render(float delta, SpriteBatch batch, BlackboxGame game) {
        // Flickering effect
        float brightness = (float)(Math.random() * (this.flickerIntensity) + (1 - this.flickerIntensity));
        batch.setColor(brightness, brightness, brightness, 1F);
        // batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(background, scroll, 0, 2500 / 2, 1080 / 2);
        batch.setColor(1f,1f,1f,1f); // Reset brightness

        // Render the text
        this.currentCharCount += delta * this.typingSpeed;
        if (this.currentCharCount > this.screenText.length())
            this.currentCharCount = this.screenText.length();

        game.monoNormalFont.get("title1").draw(batch, this.screenText.substring(0, (int)this.currentCharCount),
                (int)((double)this.screenRect[0] / BG_WIDTH * Gdx.graphics.getWidth() + scroll),
                (int)((double)this.screenRect[1]  / BG_HEIGHT * Gdx.graphics.getHeight()));
    }

    /**
     * Clear the current text displayed on the screen
     * NOTE: screen won't be updated until next render() call
     */
    public void clearScreen() {
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

    /** Getters and setters */
    public int[] getScreenRect() {
        return screenRect;
    }
    public void setScreenRect(int[] screenRect) {
        this.screenRect = screenRect;
    }
    public float getFlickerIntensity() {
        return flickerIntensity;
    }
    public void setFlickerIntensity(float flickerIntensity) {
        this.flickerIntensity = flickerIntensity;
    }
    public String getName() {
        return name;
    }
}


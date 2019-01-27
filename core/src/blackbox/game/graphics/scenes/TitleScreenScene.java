package blackbox.game.graphics.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import blackbox.game.BlackboxGame;
import blackbox.game.Config;
import blackbox.game.graphics.BackgroundScene;
import blackbox.game.util.MathUtil;

/**
 * Background image for the title screen. Do not
 * type any text on this, as screen rect and
 * fontData are null
 */
public class TitleScreenScene extends BackgroundScene {
    /**
     * mid   - temp variable to save mid point coordinate
     * theta - variable to save view angle
     * logo  - game logo
     * top, bottom - Top and bottom rectangle graphics
     */
    private int mid;
    private float theta = 0;
    private TextureRegion logo;

    /**
     * Construct a new TitleScreenScene
     * @param game BlackboxGame object
     */
    public TitleScreenScene(BlackboxGame game) {
        super(null , 0.5f, "TitleScreen", "background/title-screen.jpg", null);

        Texture img = new Texture("img/logo.png");
        img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.logo = new TextureRegion(img, 0, 0, Config.LOGO_WIDTH, Config.LOGO_HEIGHT);
    }

    /**
     * Render the background
     * @param delta Delta, incremented each frame by libgdx
     * @param batch SpriteBatch to draw this to
     * @param game  Game object
     */
    public void render(float delta, SpriteBatch batch, BlackboxGame game) {
        super.render(delta, batch, game);

        /* Update the scroll position */
        mid = -MathUtil.ratioW(this.imageWidth, BG_WIDTH) / 4;
        scroll = mid + (int)(Math.sin(theta) * mid);
        theta += 0.002;
        if (theta > 2 * Math.PI)
            theta = 0;
    }

    /**
     * Render the UI
     * @param delta Delta, incremented each frame by libgdx
     * @param batch SpriteBatch to draw this to
     * @param game  Game object
     */
    public void renderUI(float delta, SpriteBatch batch, BlackboxGame game) {
        /* Draw logo in upper right corner with padding from edges */
        double hwRatio = (double)Config.LOGO_HEIGHT / Config.LOGO_WIDTH;
        int w = (int)(Gdx.graphics.getWidth() * Config.LOGO_WIDTH_PERCENT);
        int buffer = (int)(Gdx.graphics.getWidth() * Config.LOGO_PADDING);
        batch.draw(logo,
                Gdx.graphics.getWidth() - w - buffer,
                (int)(Gdx.graphics.getHeight() - (w + buffer) * hwRatio),
                w, (int)(w * hwRatio));

        /* Draw little message */
        game.robotoLightFont.get("normal").setColor(Color.GRAY);
        game.robotoLightFont.get("normal").draw(batch, Config.MENU_NOTE,
                (int)(Gdx.graphics.getWidth() * Config.LOGO_PADDING),
                (int)(Gdx.graphics.getWidth() * Config.LOGO_PADDING));
        game.robotoLightFont.get("normal").setColor(Color.WHITE); // Reset color
    }
}

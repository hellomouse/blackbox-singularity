package blackbox.game.graphics.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import blackbox.game.BlackboxGame;
import blackbox.game.Config;
import blackbox.game.graphics.BackgroundScene;
import blackbox.game.quotes.QuoteGenerator;

/**
 * Scene for the loading screen, displays a
 * random quote
 *
 * @author Bowserinator
 */
public class LoadingScene extends BackgroundScene {
    private TextureRegion logo;
    public String quote;
    public static int[] screenRect = {0, 0, 1920, 1080};

    /**
     * Construct a new TitleScreenScene
     * @param game BlackboxGame object
     */
    public LoadingScene(BlackboxGame game) {
        super(screenRect, 0f, "TitleScreen", null, null,
                1920, 1080);

        Texture img = new Texture("img/logo.png");
        img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.logo = new TextureRegion(img, 0, 0, Config.LOGO_WIDTH, Config.LOGO_HEIGHT);

        quote = QuoteGenerator.generateQuote();
    }

    /**
     * Render the background
     * @param delta Delta, incremented each frame by libgdx
     * @param batch SpriteBatch to draw this to
     * @param game  Game object
     */
    public void render(float delta, SpriteBatch batch, BlackboxGame game) {
        super.render(delta, batch, game);
    }

    /**
     * Render the UI
     * @param delta Delta, incremented each frame by libgdx
     * @param batch SpriteBatch to draw this to
     * @param game  Game object
     */
    public void renderUI(float delta, SpriteBatch batch, BlackboxGame game) {
        /* Draw logo in upper right corner with padding from edges */
        /* double hwRatio = (double)Config.LOGO_HEIGHT / Config.LOGO_WIDTH;
        int w = (int)(Gdx.graphics.getWidth() * Config.LOADING_LOGO_WIDTH_PERCENT);
        int buffer = (int)(Gdx.graphics.getWidth() * Config.LOGO_PADDING);
        batch.draw(logo,
                Gdx.graphics.getWidth() - w - buffer,
                (int)(Gdx.graphics.getHeight() - (w + buffer) * hwRatio),
                w, (int)(w * hwRatio));

        /* Render Quote */
        /* game.robotoLightFont.get("title3").setColor(Color.GRAY);
        game.robotoLightFont.get("title3").draw(batch, quote,
                10,
                Gdx.graphics.getHeight() / 2);
        game.robotoLightFont.get("title3").setColor(Color.WHITE); */ // Reset color
    }
}
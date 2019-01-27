package blackbox.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import blackbox.game.BlackboxGame;
import blackbox.game.graphics.BlackBoxScreen;
import blackbox.game.graphics.scenes.LoadingScene;
import blackbox.game.quotes.QuoteGenerator;

/**
 * The loading screen that displays; displays a quote
 * while it attempts to load the current saved scene
 *
 * @author Bowserinator
 */
public class LoadingScreen extends BlackBoxScreen {
    public Label text;

    /**
     * Construct a LoadingScreen
     * @param game BlackboxGame object
     */
    public LoadingScreen(final BlackboxGame game) {
        super(game, new LoadingScene(game));

        Label.LabelStyle textStyle = new Label.LabelStyle();

        textStyle.font = game.getBitMapFont("fonts/staatliches_regular.ttf", 64, Color.WHITE);

        text = new Label(((LoadingScene)(this.scene)).quote, textStyle);
        text.setFontScale(1f,1f);
        text.setWrap(true);
        text.setWidth(Gdx.graphics.getWidth() / 2);
        text.setHeight(Gdx.graphics.getHeight() * 3 / 4);
        text.setPosition(Gdx.graphics.getWidth() / 4,
                Gdx.graphics.getHeight() / 8);

        stage.addActor(text);


        //TODO get current scene, load assets with bar
    }

    public void render(float delta) {
        // TODO
        // loading bar
        // faded image in background (generic, like ngnl e1)
        // big quotes

        super.render(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            String quote = QuoteGenerator.generateQuote();
            text.setText(quote);
        }
    }
}

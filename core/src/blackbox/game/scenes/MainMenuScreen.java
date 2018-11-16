package blackbox.game.scenes;

import blackbox.game.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements Screen {
    final BlackboxGame game;
    OrthographicCamera camera;

    SpriteBatch batch;
    Texture img;

    private int textLeft, textTop, menuShift, menuSpacing;

    public MainMenuScreen(final BlackboxGame game) {
        this.game = game;

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        /* Config for menu */
        textLeft = Config.WINDOW_WIDTH / 30;
        textTop = (int)(Config.WINDOW_HEIGHT * 0.75);
        menuShift = 120 + (int)(Config.WINDOW_HEIGHT * 0.06);
        menuSpacing = 30;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        /* Render title and options */

        game.robotoLightFont.get("title2").draw(batch, "BLACKBOX", textLeft, textTop);
        game.robotoLightFont.get("title1").draw(batch, "SINGULARITY", textLeft, textTop - 64);

        game.robotoLightFont.get("normal").draw(batch, "New Game", textLeft, textTop - menuShift);
        game.robotoLightFont.get("normal").draw(batch, "Continue Game", textLeft, textTop - menuShift - menuSpacing);
        game.robotoLightFont.get("normal").draw(batch, "Settings", textLeft, textTop - menuShift - menuSpacing * 2);
        game.robotoLightFont.get("normal").draw(batch, "Extras", textLeft, textTop - menuShift - menuSpacing * 3);
        game.robotoLightFont.get("normal").draw(batch, "Quit Game", textLeft, textTop - menuShift - menuSpacing * 4);

        //batch.draw(img, 0, 0);
        batch.end();

        /*if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }*/
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        // rainMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

}

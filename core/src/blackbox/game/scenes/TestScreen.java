package blackbox.game.scenes;

import blackbox.game.*;
import blackbox.game.graphics.BlackBoxScreen;
import blackbox.game.graphics.scenes.OfficeNormalBackgroundScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Window;

public class TestScreen extends BlackBoxScreen {

    Texture img;
    TextureRegion background;

    public Music backgroundMusic;


    /* Config for menu */
    private int textLeft, textTop, menuShift, menuSpacing;

    public TestScreen(final BlackboxGame game) {
        super(game, new OfficeNormalBackgroundScene(game));

        /* Load music */
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/song1.wav"));

        /* Text spacing */
        textLeft = Config.WINDOW_WIDTH / 30;
        textTop = (int)(Config.WINDOW_HEIGHT * 0.7) - BlackboxGame.fontSizes[0];
        menuShift = BlackboxGame.fontSizes[0] * 2 + (int)(Config.WINDOW_HEIGHT * 0.06);
        menuSpacing = BlackboxGame.fontSizes[2] * 2;

        scene.typeText("Hellomouse OS v1.2.3\n Totally not a fake readme There is text here! You should read it!", 20); // 20

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            scene.scroll -= 20;
        } if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            scene.scroll += 20;
        }

    }

    @Override
    public void resize(int width, int height) {
        game.generateFonts();
    }

    @Override
    public void show() {
        /* Start playing background music
         * when TestScreen is loaded */
        //backgroundMusic.play();
        //backgroundMusic.setLooping(true);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        backgroundMusic.pause();
    }

    @Override
    public void resume() {
        //backgroundMusic.play();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        backgroundMusic.dispose();
    }
}

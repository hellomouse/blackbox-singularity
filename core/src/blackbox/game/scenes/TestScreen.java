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

import blackbox.game.graphics.util.Rectangle;
import blackbox.game.util.MathUtil;


import java.awt.Window;

import static blackbox.game.graphics.BackgroundScene.BG_HEIGHT;
import static blackbox.game.graphics.BackgroundScene.BG_WIDTH;

public class TestScreen extends BlackBoxScreen {

    Texture img;
    TextureRegion background;

    public Music backgroundMusic;

    /* Config for menu */

    public TestScreen(final BlackboxGame game) {
        super(game, new OfficeNormalBackgroundScene(game));

        /* Load music */
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/song1.wav"));

        scene.typeText("Hellomouse OS v1.2.3\n Totally not a fake readme There is text here! You should read it!", 20); // 20

        // addBlackBars();
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

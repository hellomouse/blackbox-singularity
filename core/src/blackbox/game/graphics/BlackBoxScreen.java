package blackbox.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import blackbox.game.BlackboxGame;
import blackbox.game.Config;
import blackbox.game.graphics.util.Rectangle;
import contribs.postprocessing.PostProcessor;
import contribs.postprocessing.effects.Bloom;
import contribs.postprocessing.effects.Curvature;
import contribs.utils.ShaderLoader;

/**
 * Custom Screen class for Blackbox. Contains generic
 * shader code to render the background scene object.
 *
 * Also creates generic variables such as the sprite
 * batch and camera.
 *
 * @author Bowserinator
 */
public abstract class BlackBoxScreen implements Screen {
    /**
     * game         - The game object all Screens contain
     * bgProcessor  - PostProcessor for the curvature/bloom shader
     * scene        - BackgroundScene to render
     * camera       - Camera for the scene (auto-generated)
     * batch        - SpriteBatch for the scene (auto-generated)
     * stage        - Stage for UI
     */
    public final BlackboxGame game;

    public PostProcessor bgProcessor;
    public BackgroundScene scene;
    public OrthographicCamera camera;
    public SpriteBatch batch;
    public Stage stage;

    /**
     * Construct a new BlackBoxScreen
     * @param game  Game object
     * @param scene BackgroundScene object
     */
    public BlackBoxScreen(final BlackboxGame game, BackgroundScene scene) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.scene = scene;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        /* Construct stage that holds GUI */
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        /* Generate the post processor */
        ShaderLoader.BasePath = Config.SHADER_PATH;
        bgProcessor = new PostProcessor( false, false, Config.IS_DESKTOP);

        /* Create the shader effect objects */
        Bloom bloom = new Bloom((int)(Gdx.graphics.getWidth() * 0.25f), (int)(Gdx.graphics.getHeight() * 0.25f));
        Curvature fishEye = new Curvature();
        fishEye.setDistortion(Config.CURVATURE_AMOUNT);

        bgProcessor.addEffect(bloom);
        bgProcessor.addEffect(fishEye);
    }

    /**
     * Adds some black bars to the top
     * and bottom of the screen, used in
     * some story scenes.
     */
    public void addBlackBars() {
        Rectangle top, bottom;
        top = new Rectangle(0,
                (int)(Gdx.graphics.getHeight() * (1 - Config.STORY_BAR_HEIGHT_RATIO)),
                Gdx.graphics.getWidth(),
                (int)(Gdx.graphics.getHeight() * Config.STORY_BAR_HEIGHT_RATIO),
                new Color(0, 0, 0, Config.STORY_BAR_ALPHA));
        bottom = new Rectangle(0,
                0,
                Gdx.graphics.getWidth(),
                (int)(Gdx.graphics.getHeight() * Config.STORY_BAR_HEIGHT_RATIO),
                new Color(0, 0, 0, Config.STORY_BAR_ALPHA));
        stage.addActor(top);
        stage.addActor(bottom);
    }

    @Override
    public void render(float delta) {
        /* Begin shader capture, reset Gdx gl and begin batch drawing */
        bgProcessor.capture();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        /* Render the scene */
        scene.render(delta, batch, game);

        /* Reset the batch and draw the UI without the shader effect */
        batch.end();
        bgProcessor.render();
        batch.begin();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        /* Render scene UI */
        scene.renderUI(delta, batch, game);
        batch.end();

        /* Render stage */
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resume() {
        bgProcessor.rebind();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bgProcessor.dispose();
    }

    @Override
    public void resize(int width, int height) {
        game.generateFonts();
    }

    @Override
    public void hide() {}

    @Override
    public void show() {}

    @Override
    public void pause() {}
}

package blackbox.game.scenes;

import blackbox.game.*;
import blackbox.game.graphics.BackgroundScene;
import blackbox.game.graphics.scenes.TitleScreenScene;
import blackbox.game.util.MathUtil;
import contribs.postprocessing.PostProcessor;
import contribs.postprocessing.effects.Bloom;
import contribs.postprocessing.effects.Curvature;
import contribs.utils.ShaderLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;


public class MainMenuScreen implements Screen {
    public final BlackboxGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Music backgroundMusic;
    private Table guiTable;
    private Stage stage;

    private PostProcessor bgProcessor;

    private BackgroundScene scene;


    public MainMenuScreen(final BlackboxGame game) {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        this.game = game;

        batch = new SpriteBatch();
        scene = new TitleScreenScene(game);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        /* Load music */
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/song1.wav"));

        /* Generate the main menu buttons */
        Array<TextButton> buttons = new Array<TextButton>();
        String[] menuButtonLabels = { "New Game", "Continue Game", "Settings", "Extras", "Credits", "Quit Game" };
        guiTable = new Table();

        guiTable.setPosition(0, Gdx.graphics.getHeight() / 6);
        guiTable.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() * 3 / 4);

        for (String label : menuButtonLabels) {
            TextButton button = new TextButton(label, game.textButtonStyle1);
            guiTable.add(button).left().pad(4);
            buttons.add(button);

            guiTable.row();
        }

        stage.addActor(guiTable);

        ShaderLoader.BasePath = Config.SHADER_PATH;
        bgProcessor = new PostProcessor( false, false, Config.isDesktop);

        Bloom bloom = new Bloom( (int)(Gdx.graphics.getWidth() * 0.25f), (int)(Gdx.graphics.getHeight() * 0.25f) );
        Curvature fishEye = new Curvature();
        fishEye.setDistortion(-0.14f);

        bgProcessor.addEffect(bloom);
        bgProcessor.addEffect(fishEye);

    }

    @Override
    public void render(float delta) {
        bgProcessor.capture();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        scene.render(delta, batch, game);

        batch.end();
        bgProcessor.render();


        batch.begin();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        scene.renderUI(delta, batch, game);

        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        /*if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }*/
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

        bgProcessor.rebind();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        backgroundMusic.dispose();

        bgProcessor.dispose();
    }
}

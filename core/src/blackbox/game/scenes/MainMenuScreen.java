package blackbox.game.scenes;

import blackbox.game.*;
import blackbox.game.graphics.BlackBoxScreen;
import blackbox.game.graphics.scenes.TitleScreenScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * The MainMenuScreen, which renders the main
 * menu graphics and handles the buttons in
 * the main menu.
 *
 * @author Bowserinator
 */
public class MainMenuScreen extends BlackBoxScreen {
    /**
     * backgroundMusic  - Music object that plays
     * guiTable         - Table containing buttons
     * stage            - Stage to contain the table
     */
    private Music backgroundMusic;
    private Table guiTable;

    /**
     * Generator for main menu options. menuButtonLabels is the array
     * of all button labels, add / remove strings here to create / delete
     * buttons in the main menu
     */
    private static final String[] menuButtonLabels = { "New Game", "Continue Game", "Settings", "Extras", "Credits", "Quit Game" };

    /**
     * Factory for the main menu screen. Returns a new screen depending
     * on the index given.
     *
     * @param index Button index (index in menuButtonlabels)
     * @param game  Game object the screen contains
     * @return Screen object to go to
     */
    private static Screen generateScreenFromIndex(int index, BlackboxGame game) {
        switch (index) {
            case 0: return new TestScreen(game);
            case 1: return new LoadingScreen(game);
            case 2: return new LoadingScreen(game);
            case 3: return new LoadingScreen(game);
            case 4: return new LoadingScreen(game);
            case 5: return new StoryScreen(game);
        }
        return null;
    }

    /**
     * Construct a new MainMenuScreen
     * @param game Game object screen should have
     */
    public MainMenuScreen(final BlackboxGame game) {
        super(game, new TitleScreenScene(game));

        /* Load music */
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/song1.wav"));

        /* Generate the main menu buttons */
        guiTable = new Table();
        guiTable.setPosition(0, Gdx.graphics.getHeight() / 6);
        guiTable.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() * 3 / 4);

        /* Generate the main menu table
         * Each button has an input listener tat changes the current
         * game screen, using a static factory method that returns
         * the scene to go to based on index.
         *
         * Index will be kept track of in the index array, where
         * the first element is the value i in the loop, since
         * the InputListener requires parameters to be final
         */
        final int[] index = {0};

        for (int i = 0; i < menuButtonLabels.length; i++) {
            TextButton button = new TextButton(menuButtonLabels[i], game.textButtonStyle1);
            index[0] = i;

            button.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    dispose(); // Run first to dispose of shader so new screen can use shader
                    game.setScreen(generateScreenFromIndex(index[0], game));
                    return true;
                }});

            guiTable.add(button).left().pad(4);
            guiTable.row();
        }

        stage.addActor(guiTable);
    }

    @Override
    public void resize(int width, int height) {
        game.generateFonts();
    }

    @Override
    public void show() {
        backgroundMusic.play();
        backgroundMusic.setLooping(true);
    }

    @Override
    public void hide() {}

    @Override
    public void pause() {
        backgroundMusic.pause();
    }

    @Override
    public void resume() {
        super.resume();
        backgroundMusic.play();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        backgroundMusic.dispose();
    }
}

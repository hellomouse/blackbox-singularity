package blackbox.game.scenes;

import blackbox.game.*;
import blackbox.game.conversation.Conversation;
import blackbox.game.conversation.graph.Choice;
import blackbox.game.conversation.story.Mystory;
import blackbox.game.graphics.BlackBoxScreen;
import blackbox.game.graphics.scenes.OfficeNormalBackgroundScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

/**
 * Story screen that renders a Conversation and allows
 * players to make choices
 * @author Bowserinator
 */
public class StoryScreen extends BlackBoxScreen {
    /**
     * Some public static config variables
     * for the story specifically
     *
     * The following calculate the time for the choices to fade in. The higher
     * the index, the lower the choice. INITIAL_TIME is fade time for top choice,
     * TIME_INC is increment (in seconds) of subsequent choices.
     *
     * - CHOICE_INITIAL_TIME: a for time computation in algorithm time = a + b * index
     * - CHOICE_TIME_INC: b for time computation in algorithm time = a + b * index
     *
     * - PLAYER_TYPE_SPEED: Typing speed when player inputs their choice (Should be fast
     *                      so time isn't spent reading selection)
     * - CHOICE_PADDING: Padding between options
     * - SCROLL_SPEED: How fast the scene can scroll (LEFT & RIGHT ARROWS)
     */
    public static final float CHOICE_INITIAL_TIME = 0.2f;
    public static final float CHOICE_TIME_INC = 0.3f;
    public static final float PLAYER_TYPE_SPEED = 50f;
    public static final float CHOICE_PADDING = 25f;
    public static final int SCROLL_SPEED = 20;

    private Table guiTable;
    private Array<TextButton> choices;

    private TextButton.TextButtonStyle choiceStyle;
    private Label.LabelStyle labelStyle;

    private int tableWidth;

    public Conversation story;
    public float typingSpeed;

    public StoryScreen(final BlackboxGame game) {
        super(game, new OfficeNormalBackgroundScene(game));

        /* Variable config */
        this.typingSpeed = 25f;

        /* Generate the table for the choices */
        tableWidth = Gdx.graphics.getWidth() / 3;

        guiTable = new Table();
        guiTable.setPosition(Gdx.graphics.getWidth() - tableWidth, 0);
        guiTable.setSize(tableWidth, Gdx.graphics.getHeight());
        stage.addActor(guiTable);

        /* Styling for the choice labels */
        choiceStyle = BlackboxGame.getTextButtonStyle(game.robotoLightFont.get("normal"), Color.LIGHT_GRAY, Color.WHITE);
        labelStyle = new Label.LabelStyle(game.robotoLightFont.get("small"), Color.WHITE);

        story = new Mystory(null);
        story.gotoStart();
        renderCurrentChoice();
    }

    private void renderCurrentChoice() {
        //TODO timeout
        scene.typeText(story.currentChatNode.getText(), this.typingSpeed);

        /* Clear the table and choice array when updating choices */
        choices = new Array<TextButton>();
        guiTable.clearChildren();

        /*
         * Calculate the delay required before displaying the choices, which
         * is equal to the number of characters to type / typing speed
         */
        float calculatedDelay = story.currentChatNode.getText().length() / this.typingSpeed;
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                int i = 0;
                for (final Choice choice : story.currentChatNode.getChoices()) {
                    /* Button to click on a choice */
                    TextButton button = new TextButton(choice.label, choiceStyle);
                    button.getLabel().setAlignment(Align.left);

                    /* Tiny label on top of the choice */
                    Label optionLabel = new Label("OPTION " + (i + 1), labelStyle);
                    optionLabel.setAlignment(Align.left);
                    guiTable.add(optionLabel).left().pad(0).width(tableWidth).row();

                    /* Fade in effect when choice is loaded: all choices fade
                     * in from the top to bottom */
                    button.getColor().a = 0.0f;
                    optionLabel.getColor().a = 0.0f;
                    button.addAction(Actions.fadeIn(CHOICE_INITIAL_TIME + CHOICE_TIME_INC * i));
                    optionLabel.addAction(Actions.fadeIn(CHOICE_INITIAL_TIME + CHOICE_TIME_INC * i));

                    /* When choice is selected go to the next one */
                    final int choice2 = i;
                    button.addListener(new InputListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            scene.typeText("\n\n> " + choice.displayText + "\n\n", PLAYER_TYPE_SPEED);

                            /*
                             * After the player finishes typing their choice (delay calculated
                             * by (choice length + 6) / player type speed (The 6 accounts for the
                             * "\n\n\n\n> " added in the typing)
                             *
                             * Execute the next choice and update choice display
                             */
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    story.currentChatNode.getChoices().get(choice2).onSelect(story);
                                    renderCurrentChoice();
                                }
                            }, (choice.displayText.length() + 6) / PLAYER_TYPE_SPEED);
                            return true;
                        }
                    });

                    guiTable.add(button).left().padBottom(CHOICE_PADDING).width(tableWidth);
                    guiTable.row().row();
                    choices.add(button);
                    i++;
                }
            }
        }, calculatedDelay);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            scene.scroll -= SCROLL_SPEED;
        } if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            scene.scroll += SCROLL_SPEED;
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
    public void resume() {
        //backgroundMusic.play();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

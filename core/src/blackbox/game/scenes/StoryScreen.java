package blackbox.game.scenes;

import blackbox.game.*;
import blackbox.game.conversation.Conversation;
import blackbox.game.conversation.graph.Choice;
import blackbox.game.conversation.story.TestStory;
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
    private Array<Label> choiceLabels;

    private TextButton.TextButtonStyle choiceStyle;
    private Label.LabelStyle labelStyle;

    private int tableWidth;

    public Conversation story;

    public StoryScreen(final BlackboxGame game) {
        super(game, new OfficeNormalBackgroundScene(game));

        /* Generate the table for the choices */
        tableWidth = (int)(Gdx.graphics.getWidth() / 3.1);

        guiTable = new Table();
        guiTable.setPosition(Gdx.graphics.getWidth() - tableWidth, 0);
        guiTable.setSize(tableWidth, Gdx.graphics.getHeight());
        stage.addActor(guiTable);

        /* Styling for the choice labels */
        choiceStyle = BlackboxGame.getTextButtonStyle(game.robotoLightFont.get("normal"), Color.LIGHT_GRAY, Color.WHITE);
        labelStyle = new Label.LabelStyle(game.robotoLightFont.get("small"), Color.WHITE);

        story = new TestStory(this);
        story.gotoStart();
        renderCurrentChoice();
    }

    private void renderCurrentChoice() {
        story.currentChatNode.onLoad(story);
        scene.typeText(story.currentChatNode.getText());

        /* Clear the table and choice array when updating choices */
        choices = new Array<TextButton>();
        choiceLabels = new Array<Label>();
        guiTable.clearChildren();

        /* Timeout timer for the choice */
        if (story.currentChatNode.getTimeout() > 0) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() { executeChoice(story.currentChatNode.getTimeoutChoice(), 0); }
            }, story.currentChatNode.getTimeout() / 1000f); /* Time given in ms, divide by 1000 */
        }

        /*
         * Calculate the delay required before displaying the choices, which
         * is equal to the number of characters to type / typing speed
         */
        float calculatedDelay = story.currentChatNode.getText().length() / this.scene.typingSpeed;
        if (calculatedDelay > 0 && story.currentChatNode.getChoices().size > 0) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    int i = 0;
                    for (final Choice choice : story.currentChatNode.getChoices()) {
                        /* Button to click on a choice */
                        TextButton button = new TextButton(choice.label, choiceStyle);
                        button.getLabel().setAlignment(Align.left);
                        button.getLabel().setWidth(tableWidth);
                        button.getLabel().setWrap(true);

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
                        final int choiceIndex = i;

                        button.addListener(new InputListener() {
                            @Override
                            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                executeChoice(choice, choiceIndex);
                                return true;
                            }
                        });

                        guiTable.add(button).left().padBottom(CHOICE_PADDING).width(tableWidth);
                        guiTable.row().row();

                        choices.add(button);
                        choiceLabels.add(optionLabel);
                        i++;
                    }
                }
            }, calculatedDelay);
        }
    }

    /**
     * "Select" a given choice parameter
     * @param choice      Choice object to select
     * @param choiceIndex Index of the selected choice (For fading animation)
     */
    public void executeChoice(final Choice choice, int choiceIndex) {
        /* Calculate time before displaying next choice, which is length + 6 over speed.
         * The 6 accounts for the \n\n> and \n\n added to the displayText */
        final float delayBeforeNextChoice = (choice.displayText.length() + 6) / PLAYER_TYPE_SPEED;
        scene.typingSpeed = PLAYER_TYPE_SPEED;

        /* Type user text, ie > I made this choice */
        if (choice.displayText.length() > 0)
            scene.typeText("\n\n> " + choice.displayText + "\n\n");

        /* Create newline for next node if no text is there. Only do
         * this if chat node contained actual text, since empty chatnodes
          * are often just for graphics/code and not story */
        else if (story.currentChatNode.getText().length() > 0)
            scene.typeText("\n\n");

        /* Fade out all current choices, fading the selected one last
         * All other choices fade at same rate */
        int diff;
        for (int i = 0; i < choices.size; i++) {
            diff = i == choiceIndex ? 1 : 0;

            float fadeTime = CHOICE_INITIAL_TIME + CHOICE_TIME_INC * diff;
            fadeTime = Math.min(fadeTime, delayBeforeNextChoice); // Max out when next choice loads for smooth fade

            choices.get(i).addAction(Actions.fadeOut(fadeTime));
            choiceLabels.get(i).addAction(Actions.fadeOut(fadeTime));
        }

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
                choice.onSelect(story);
                renderCurrentChoice();
            }
        }, delayBeforeNextChoice);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            scene.scroll -= SCROLL_SPEED;
            guiTable.setPosition(Gdx.graphics.getWidth() - tableWidth + scene.scroll - scene.initialScroll, 0);
        } if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            scene.scroll += SCROLL_SPEED;
            guiTable.setPosition(Gdx.graphics.getWidth() - tableWidth + scene.scroll - scene.initialScroll, 0);
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

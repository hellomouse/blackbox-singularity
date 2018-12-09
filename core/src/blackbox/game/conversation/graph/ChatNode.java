package blackbox.game.conversation.graph;

import com.badlogic.gdx.utils.Array;
import blackbox.game.conversation.Conversation;

/**
 * A "choice" in a conversation
 * where multiple options are
 * presented to the player.
 */
public abstract class ChatNode {
    /* Main variables */
    private Array<Choice> choices;
    private String text;

    /* Additional variables */
    private long startTime;
    private int timeout;  // Timeout = -1 for no timeout
    private Choice timeoutChoice;

    /**
     * Construct a new ChatNode
     * @param choices       List of choices
     * @param text          Text to display (What the AI says)
     * @param timeout       Timeout of the choice (-1 = no timeout)
     * @param timeoutChoice The choice executed when timed out
     */
    public ChatNode(Array<Choice> choices, String text, int timeout, Choice timeoutChoice) {
        this.choices = choices;
        this.text = text;
        this.timeout = timeout;
        this.timeoutChoice = timeoutChoice;
    }

    /**
     * Code that is executed when the choice is
     * loaded (When the player goes to the choice)
     * @param conversation The conversation the choice is in
     */
    public void onLoad(Conversation conversation) {
        this.startTime = System.currentTimeMillis();
    }

    /** Getter and setters */
    public Array<Choice> getChoices() {
        return choices;
    }
    public String getText() {
        return text;
    }
    public int getTimeout() {
        return timeout;
    }
}
